/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gkemayo.voy.agency;

/**
 *
 * @author Latitude
 */
import com.gkemayo.voy.bus.Bus;
import com.gkemayo.voy.ville.Ville;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Agency Model")
public class AgencyDTO implements Comparable<AgencyDTO> {

    @ApiModelProperty(value = "Agency id")
    private Integer id;

    @ApiModelProperty(value = "Agency name")
    private String name;

    @ApiModelProperty(value = "Agency creation date in the system")
    private LocalDate creationDate;

    @ApiModelProperty(value = "Agency creation date in the system")
    private int nbreBus;
//    @ApiModelProperty(value = "Agency creation date in the system")
//    private Ville ville;
//    @ApiModelProperty(value = "Agency creation date in the system")
//    private Collection<Bus> buses;

    @Override
    public int compareTo(AgencyDTO o) {
        return this.name.compareToIgnoreCase(o.getName());
    }

}
