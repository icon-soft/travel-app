/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.bus;

/**
 *
 * @author Latitude
 */
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Bus Model")
public class BusDTO implements Comparable<BusDTO>{
	
	@ApiModelProperty(value = "Bus id")
	private Integer id;
	
	@ApiModelProperty(value = "Bus immatricule")
	private String immatricule;
	
	@ApiModelProperty(value = "Bus description")
	private String description;
	
	@Override
	public int compareTo(BusDTO o) {
		return this.immatricule.compareToIgnoreCase(o.getImmatricule());
	}
		
}

