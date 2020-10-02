package com.gkemayo.voy.bus;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/bus/api")
@Api(value = "Bus Rest Controller: contains all operations for managing customers")
public class BusRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BusRestController.class);

    @Autowired
    private BusServiceImpl busService;

    @GetMapping("/allBuss")
    @ApiOperation(value = "List all Bus of the agency", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok: successfully listed"),
        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<BusDTO>> getAllBookBuss() {
        Page<Bus> buses = busService.getPaginatedBussList(0, 10);
        if (!CollectionUtils.isEmpty(buses.toList())) {
            //on retire tous les élts null que peut contenir cette liste
//            custumers.getContent().removeAll(Collections.singleton(null));
            List<BusDTO> busDTOs = buses.stream().map(bus -> {
                return mapBusToBusDTO(bus);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<BusDTO>>(busDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<BusDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param busDTORequest
     * @return
     */
    @PostMapping("/addBus")
    @ApiOperation(value = "Add a new Bus in the Library", response = BusDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 409, message = "Conflict: the customer already exist"),
        @ApiResponse(code = 201, message = "Created: the customer is successfully inserted"),
        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted")})
    public ResponseEntity<BusDTO> createNewBus(@RequestBody BusDTO busDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
//        Bus existingBus = customerService.findBusByEmail(customerDTORequest.getEmail());
//        if (existingBus != null) {
//            return new ResponseEntity<BusDTO>(HttpStatus.CONFLICT);
//        }
        Bus busRequest = mapBusDTOToBus(busDTORequest);
//        customerRequest.setCreationDate(LocalDate.now());
        Bus busResponse = busService.saveBus(busRequest);
        if (busResponse != null) {
            BusDTO busDTO = mapBusToBusDTO(busResponse);
            return new ResponseEntity<BusDTO>(busDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<BusDTO>(HttpStatus.NOT_MODIFIED);

    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param customerDTORequestbusDTORequest@return
     */
    @PutMapping("/updateBus")
    @ApiOperation(value = "Update/Modify an existing customer in the Library", response = BusDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Not Found : the customer does not exist"),
        @ApiResponse(code = 200, message = "Ok: the customer is successfully updated"),
        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully updated")})
    public ResponseEntity<BusDTO> updateBus(@RequestBody BusDTO busDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        if (!busService.checkIfIdexists(busDTORequest.getId())) {
            return new ResponseEntity<BusDTO>(HttpStatus.NOT_FOUND);
        }
        Bus busRequest = mapBusDTOToBus(busDTORequest);
        Bus busResponse = busService.updateBus(busRequest);
        if (busResponse != null) {
            BusDTO busDTO = mapBusToBusDTO(busResponse);
            return new ResponseEntity<BusDTO>(busDTO, HttpStatus.OK);
        }
        return new ResponseEntity<BusDTO>(HttpStatus.NOT_MODIFIED);
    }

//	/**
//	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
//	 * @param customerId
//	 * @return
//	 */
    @DeleteMapping("/deleteBus/{customerId}")
    @ApiOperation(value = "Delete a customer in the Library, if the customer does not exist, nothing is done", response = String.class)
    @ApiResponse(code = 204, message = "No Content: customer sucessfully deleted")
    public ResponseEntity<String> deleteBus(@PathVariable Integer busId) {
        busService.deleteBus(busId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
//

    @GetMapping("/paginatedSearch")
    @ApiOperation(value = "List customers of the Library in a paginated way", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok: successfully listed"),
        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<BusDTO>> searchBuss(@RequestParam("beginPage") int beginPage,
            @RequestParam("endPage") int endPage) {
        //, UriComponentsBuilder uriComponentBuilder
        Page<Bus> buses = busService.getPaginatedBussList(beginPage, endPage);
        if (buses != null) {
            List<BusDTO> busDTOs = buses.stream().map(bus -> {
                return mapBusToBusDTO(bus);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<BusDTO>>(busDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<BusDTO>>(HttpStatus.NO_CONTENT);
    }
//
//	/**
//	 * Retourne le client ayant l'adresse email passé en paramètre.
//	 * @param email
//	 * @return
//	 */

    @GetMapping("/searchByEmail")
    @ApiOperation(value = "Search a customer in the Library by its email", response = BusDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok: successfull research"),
        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<BusDTO> searchBusByEmail(@RequestParam("email") String email) {
        //, UriComponentsBuilder uriComponentBuilder
//        Bus customer = customerService.findBusByEmail(email);
//        if (customer != null) {
//            BusDTO customerDTO = mapBusToBusDTO(customer);
//            return new ResponseEntity<BusDTO>(customerDTO, HttpStatus.OK);
//        }
        return new ResponseEntity<BusDTO>(HttpStatus.NO_CONTENT);
    }
//	
//	/**
//	 * Retourne la liste des clients ayant le nom passé en paramètre.
//	 * @param lastName
//	 * @return
//	 */

    @GetMapping("/searchByLastName")
    @ApiOperation(value = "Search a customer in the Library by its Last name", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok: successfull research"),
        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<BusDTO>> searchBookByLastName(@RequestParam("lastName") String lastName) {
        //,	UriComponentsBuilder uriComponentBuilder
//        List<Bus> customers = customerService.findBusByLastName(lastName);
//        if (customers != null && !CollectionUtils.isEmpty(customers)) {
//            List<BusDTO> customerDTOs = customers.stream().map(customer -> {
//                return mapBusToBusDTO(customer);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<BusDTO>>(customerDTOs, HttpStatus.OK);
//        }
        return new ResponseEntity<List<BusDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Transforme un entity Bus en un POJO BusDTO
     *
     * @param customer
     * @return
     */
    private BusDTO mapBusToBusDTO(Bus customer) {
        ModelMapper mapper = new ModelMapper();
        BusDTO customerDTO = mapper.map(customer, BusDTO.class);
        return customerDTO;
    }

    /**
     * Transforme un POJO BusDTO en en entity Bus
     *
     * @param customerDTO
     * @return
     */
    private Bus mapBusDTOToBus(BusDTO customerDTO) {
        ModelMapper mapper = new ModelMapper();
        Bus customer = mapper.map(customerDTO, Bus.class);
        return customer;
    }

}
