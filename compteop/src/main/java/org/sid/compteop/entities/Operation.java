package org.sid.compteop.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sid.compteop.enums.TypeOperation;

import javax.persistence.*;
import java.util.Date;

@Entity @AllArgsConstructor @NoArgsConstructor @ToString @Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private double montant=0;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Compte compte;

    @Enumerated(EnumType.STRING)
    private TypeOperation typeOperation;
}
