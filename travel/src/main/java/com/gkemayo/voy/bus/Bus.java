/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.bus;

import com.gkemayo.voy.agency.Agency;
import com.gkemayo.voy.place.Place;
import com.gkemayo.voy.projectiontravel.ProjectionTravel;
import com.gkemayo.voy.travel.Travel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Latitude
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus implements Serializable { 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BUS_ID")
    private Integer id;
    private String immatricule;
    private String description;

    @ManyToOne
    private Agency agency;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus", cascade = CascadeType.ALL)
//    private Set<Travel> travels = new HashSet<Travel>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus", cascade = CascadeType.ALL)
    private Set<Place> places = new HashSet<Place>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bus", cascade = CascadeType.ALL)
    private Set<ProjectionTravel> projectionTravels = new HashSet<ProjectionTravel>();
}
