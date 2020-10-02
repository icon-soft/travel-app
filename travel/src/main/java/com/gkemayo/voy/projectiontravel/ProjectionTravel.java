/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.projectiontravel;

import com.gkemayo.voy.bus.Bus;
import com.gkemayo.voy.seance.Seance;
import com.gkemayo.voy.ticket.Ticket;
import com.gkemayo.voy.travel.Travel;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class ProjectionTravel implements Serializable { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dateTravel;
    private double prix;
    
    @ManyToOne
    private Travel travel;
    @ManyToOne
    private Bus bus;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectionTravel", cascade = CascadeType.ALL)
    private Set<Ticket> projectionTravels = new HashSet<Ticket>();
    @ManyToOne(targetEntity = Seance.class)
    private Seance seance;
}
