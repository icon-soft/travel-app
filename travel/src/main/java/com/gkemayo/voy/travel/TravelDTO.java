/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.travel;

import com.gkemayo.voy.customer.CustomerDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

/**
 *
 * @author Latitude
 */
@ApiModel(value = "Ticket Model")
public class TravelDTO implements Comparable<TravelDTO> {

	@ApiModelProperty(value = "Travel concerned by the ticket")
	private TravelDTO bookDTO = new TravelDTO();

	@ApiModelProperty(value = "Customer concerned by the ticket")
	private CustomerDTO customerDTO = new CustomerDTO();

	@ApiModelProperty(value = "Ticket begining date")
	private LocalDate ticketDate;

    @Override
    public int compareTo(TravelDTO o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
