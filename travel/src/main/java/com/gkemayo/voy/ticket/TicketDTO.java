package com.gkemayo.voy.ticket;

import com.gkemayo.voy.customer.Customer;
import com.gkemayo.voy.customer.CustomerDTO;
import com.gkemayo.voy.place.Place;
import com.gkemayo.voy.projectiontravel.ProjectionTravel;
import com.gkemayo.voy.travel.TravelDTO;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Ticket Model")
@Data
public class TicketDTO implements Comparable<TicketDTO> {

    @ApiModelProperty(value = "Travel concerned by the ticket")
    private TravelDTO travelDTO = new TravelDTO();

    @ApiModelProperty(value = "Customer concerned by the ticket")
    private CustomerDTO customerDTO = new CustomerDTO();

    @ApiModelProperty(value = "Ticket begining date")
    private LocalDate ticketDate;

    @ApiModelProperty(value = "Price concerned by the ticket")
    private double prix;

    @ApiModelProperty(value = "Price concerned by the ticket")
    private int codePayement;

    @ApiModelProperty(value = "Price concerned by the ticket")
    private boolean reserve;

    @ApiModelProperty(value = "Price concerned by the ticket")
    private Customer customer;

    @ApiModelProperty(value = "Price concerned by the ticket")
    private Place place;

    @ApiModelProperty(value = "Price concerned by the ticket")
    private ProjectionTravel projectionTravel;

    @Override
    public int compareTo(TicketDTO o) {
        // ordre decroissant
        return 1;
    }

}
