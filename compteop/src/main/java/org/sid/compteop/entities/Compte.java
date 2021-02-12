package org.sid.compteop.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sid.compteop.enums.Etat;
import org.sid.compteop.enums.TypeCompte;
import org.sid.compteop.models.Client;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double solde=0;
    private Date dateCreation;

    @OneToMany(cascade = {CascadeType.ALL})
    Collection<Operation> operations;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;


    Long clientId;
    @Transient
    Client client;

}
