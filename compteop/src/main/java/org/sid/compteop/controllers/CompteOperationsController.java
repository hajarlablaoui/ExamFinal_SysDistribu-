package org.sid.compteop.controllers;


import lombok.AllArgsConstructor;
import org.sid.compteop.entities.Compte;
import org.sid.compteop.entities.Operation;
import org.sid.compteop.services.CompteOperationsService;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@AllArgsConstructor
public class CompteOperationsController {
    CompteOperationsService compteOperationsService;

    @PostMapping("comptes")
    Compte addCompte(@RequestBody() Compte comptePayload){
        return compteOperationsService.addCompte(comptePayload);
    }

    @PostMapping("/operations/versement")
    Compte versement(@RequestParam("compteId") Long compteId,
                     @RequestParam("montant") double montant){
        return compteOperationsService.verseMontantCompte(compteId, montant);
    }

    @PostMapping("/operations/retrait")
    Compte retrait(@RequestParam("compteId") Long compteId,
                   @RequestParam("montant") double montant){
        return compteOperationsService.retraitMontantCompte(compteId, montant);
    }

    @PostMapping("/operations/virement")
    double virement(@RequestParam("compteEmId") Long compteEmId,
                    @RequestParam("compteRecId") Long compteRecId,
                    @RequestParam("montant") double montant){
        return compteOperationsService.virementCompte(compteEmId,compteRecId, montant);
    }

    @GetMapping("/comptes/{id}/operations")
    Collection<Operation> compteOperations(@PathVariable("id") Long compteId
    ){
        return compteOperationsService.GetAllOperations(compteId);
    }

    @GetMapping("/comptes/{id}/clients")
    Compte compteclient(@PathVariable("id") Long compteId
    ){
        System.out.println(compteId);
        return compteOperationsService.getCompteetClient(compteId);
    }

    @PostMapping("/comptes/{id}/etat")
    Compte editEtatCompte(@PathVariable("id") Long compteId, @RequestParam("etat") boolean etat
    ){
        return compteOperationsService.editCompteEtat(compteId, etat);
    }

}
