package com.gkemayo.voy.ticket;

import com.gkemayo.voy.customer.Customer;
import com.gkemayo.voy.place.Place;
import com.gkemayo.voy.projectiontravel.ProjectionTravel;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@AssociationOverrides({
//@AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "BOOK_ID")),
//@AssociationOverride(name = "pk.customer", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
//})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

    /**
     *
     */
//    @EmbeddedId
//    private TicketId pk = new TicketId();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double prix;
    @Column(nullable = false)
    private LocalDate ticketDate;
    private int codePayement;
    private boolean reserve;

//    @Enumerated(EnumType.STRING)
//    private TicketStatus status;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Place place;
    @ManyToOne
    private ProjectionTravel projectionTravel;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
//        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ticket other = (Ticket) obj;
//        if (pk == null) {
//            if (other.pk != null) {
//                return false;
//            }
//        } else if (!pk.equals(other.pk)) {
//            return false;
//        }
        return true;
    }

}
