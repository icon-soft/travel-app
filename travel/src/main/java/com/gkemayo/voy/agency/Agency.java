/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.agency;

import com.gkemayo.voy.bus.Bus;
import com.gkemayo.voy.ville.Ville;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Latitude
 */
@Entity
//@AssociationOverrides({
//    @AssociationOverride(name = "pk.ville", joinColumns = @JoinColumn(name = "VILLE_ID")),
//    @AssociationOverride(name = "pk.agency", joinColumns = @JoinColumn(name = "AGENCY_ID"))
//})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agency  implements Serializable { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AGENCY_ID")
    private Integer id;
    private String name;
    private LocalDate creationDate;
    private int nbreBus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "VILLE_ID", referencedColumnName = "VILLE_ID")
    private Ville ville;
    @OneToMany(mappedBy = "agency")
    private Collection<Bus> buses;

}
