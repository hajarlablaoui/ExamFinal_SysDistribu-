package org.sid.compteop.services;

import org.sid.compteop.entities.Compte;
import org.sid.compteop.entities.Operation;

import java.util.Collection;

public interface CompteOperationsService {
    public Compte addCompte(Compte compte);
    public Compte verseMontantCompte(Long compteId, double montant);
    public Compte retraitMontantCompte(Long compteId, double montant);
    public double virementCompte(Long compteDmetteurId,Long compteDestinataireId, double montant);
    public Collection<Operation> GetAllOperations(Long compteId);
    public Compte getCompteetClient(Long compteId);
    public Compte editCompteEtat(Long compteId, boolean actif);
}
