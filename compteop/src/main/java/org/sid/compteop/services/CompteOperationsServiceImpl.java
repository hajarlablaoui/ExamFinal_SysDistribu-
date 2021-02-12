package org.sid.compteop.services;

import org.sid.compteop.entities.Compte;
import org.sid.compteop.entities.Operation;
import org.sid.compteop.enums.Etat;
import org.sid.compteop.feign.ClientRest;
import org.sid.compteop.repositories.CompteRepository;
import org.sid.compteop.repositories.OperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
    @Service
    public class CompteOperationsServiceImpl implements CompteOperationsService {
        CompteRepository comptesRepository;
        OperationRepository operationsRepository;
        ClientRest clientRest;
        CompteOperationsServiceImpl(CompteRepository compteRepository,
                                     OperationRepository operationRepository,
                                     ClientRest clientRest){
        }

        private Compte findCompteByIdOrFail(Long compteId) throws RuntimeException{
            Optional<Compte> optionalCompte = comptesRepository.findById(compteId);
            if(!optionalCompte.isPresent())
                throw new RuntimeException("Compte Pas Trouver");
            return optionalCompte.get();
        }

        @Override
        public Compte addCompte(Compte compte) {
            return comptesRepository.save(compte);
        }

        @Override
        public Compte verseMontantCompte(Long compteId, double montant) {
            Compte compte = findCompteByIdOrFail(compteId);
            compte.setSolde(compte.getSolde() + montant);
            comptesRepository.save(compte);
            return compte;
        }

        @Override
        public Compte retraitMontantCompte(Long compteId, double montant){
            Compte compte = findCompteByIdOrFail(compteId);
            if(compte.getSolde() < montant )
                throw new RuntimeException("Solde Insuffisant !");
            compte.setSolde(compte.getSolde() - montant);
            comptesRepository.save(compte);
            return compte;
        }

        @Override
        public double virementCompte(Long compteDmetteurId, Long compteDestinataireId, double montant) {
            Compte compteEmetteur = findCompteByIdOrFail(compteDmetteurId);
            if(compteEmetteur.getSolde() < montant)
                throw  new RuntimeException("Solde Insuffisant");

            retraitMontantCompte(compteDmetteurId, montant);
            verseMontantCompte(compteDestinataireId, montant);
            return montant;
        }

        @Override
        public Collection<Operation> listOperations(Long compteId) {
            Compte compte = findCompteByIdOrFail(compteId);
            return compte.getOperations();
        }

        @Override
        public Compte getCompteetClient(Long compteId) {
            Compte compte = findCompteByIdOrFail(compteId);
            compte.setClient(clientRest.getClientById(compte.getClientId()));
            return compte;
        }

        @Override
        public Compte editCompteEtat(Long compteId, boolean actif) {
            Compte compte = findCompteByIdOrFail(compteId);
            if(actif){
                compte.setEtat(Etat.active);
            }
            else{
                compte.setEtat(Etat.suspended);
            }
            return comptesRepository.save(compte);
        }
    }

