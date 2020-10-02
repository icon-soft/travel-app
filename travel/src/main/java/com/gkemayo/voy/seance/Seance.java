/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.seance;

import com.gkemayo.voy.projectiontravel.ProjectionTravel;
import com.gkemayo.voy.ticket.Ticket;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Seance implements Serializable { 
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private LocalDate heureDebut;
    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seance", cascade = CascadeType.ALL)
//    private Set<ProjectionTravel> projectionTravels = new HashSet<ProjectionTravel>();
}
