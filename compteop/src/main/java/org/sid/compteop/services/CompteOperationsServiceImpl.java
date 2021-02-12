package org.sid.compteop.services;

import org.sid.compteop.entities.Compte;
import org.sid.compteop.entities.Operation;
import org.sid.compteop.enums.Etat;
import org.sid.compteop.enums.TypeOperation;
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


        private Compte findCompteById(Long compteId) throws RuntimeException{
            Optional<Compte> optionalCompte = comptesRepository.findById(compteId);
            if(!optionalCompte.isPresent())
                throw new RuntimeException("Ce compte n'existe pas !!");
            return optionalCompte.get();
        }

        @Override
        public Compte addCompte(Compte compte) {
            return comptesRepository.save(compte);
        }

        @Override
        public Compte verseMontantCompte(Long compteId, double montant) {
            Compte compte = findCompteById(compteId);
            compte.setSolde(compte.getSolde() + montant);
            Operation operation = new Operation();
            operation.setMontant(montant);
            operation.setTypeOperation(TypeOperation.credit);
            operation.setCompte(compte);

            compte.getOperations().add(operation);
            comptesRepository.save(compte);
            return compte;
        }

        @Override
        public Compte retraitMontantCompte(Long compteId, double montant){
            Compte compte = findCompteById(compteId);
            if(compte.getSolde() < montant )
                throw new RuntimeException("Votre Solde est Insuffisant !!");
            compte.setSolde(compte.getSolde() - montant);
            Operation operation = new Operation();
            operation.setMontant(montant);
            operation.setTypeOperation(TypeOperation.debit);
            compte.getOperations().add(operation);
            comptesRepository.save(compte);
            return compte;
        }

        @Override
        public double virementCompte(Long compteEmId, Long compteRecId, double montant) {
            Compte compteEmetteur = findCompteById(compteEmId);
            if(compteEmetteur.getSolde() < montant)
                throw  new RuntimeException("Votre Solde est Insuffisant !!");

            retraitMontantCompte(compteEmId, montant);
            verseMontantCompte(compteRecId, montant);
            return montant;
        }

        @Override
        public Collection<Operation> GetAllOperations(Long id) {
            Compte compte = findCompteById(id);
            return compte.getOperations();
        }

        @Override
        public Compte getCompteetClient(Long compteId) {
            Compte compte = findCompteById(compteId);
            compte.setClient(clientRest.getClientById(compte.getClientId()));
            return compte;
        }

        @Override
        public Compte editCompteEtat(Long compteId, boolean actif) {
            Compte compte = findCompteById(compteId);
            if(actif){
                compte.setEtat(Etat.active);
            }
            else{
                compte.setEtat(Etat.suspended);
            }
            return comptesRepository.save(compte);
        }
    }

