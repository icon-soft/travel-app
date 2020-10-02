/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.travel;

import com.gkemayo.voy.bus.Bus;
import com.gkemayo.voy.projectiontravel.ProjectionTravel;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
//@AssociationOverride(name = "pk.bus", joinColumns = @JoinColumn(name = "BUS_ID")),
//@AssociationOverride(name = "pk.customer", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
//})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Travel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "TRAVEL_ID")
    private Integer id;
    private int numero;
    @Column(nullable = false)
    private LocalDate travelDate;
    private LocalDate heureDepart;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "travel", cascade = CascadeType.ALL)
    private Set<ProjectionTravel> projectionTravels = new HashSet<ProjectionTravel>();
    
    
    
//    private TravelStatus status;

//    @EmbeddedId
//    private TravelId pk = new TravelId();

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        Travel other = (Travel) obj;
//        if (pk == null) {
//            if (other.pk != null) {
//                return false;
//            }
//        } else if (!pk.equals(other.pk)) {
//            return false;
//        }
//        return true;
//    }

}
