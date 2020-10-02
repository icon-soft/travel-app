package com.gkemayo.voy.agency;

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
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RestController
//@RequestMapping("/travel/rest/agency/api")
@Api(value = "Agency Rest Controller: contains all operations for managing Agency")
public class AgencyRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AgencyRestController.class);

    @Autowired
    private AgencyServiceImpl agencyService;

    @GetMapping("/allAgencys")
    @ApiOperation(value = "List all Agency of the agency", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok: successfully listed"),
        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<AgencyDTO>> getAllAgencys() {
        Page<Agency> custumers = agencyService.getPaginatedAgencysList(0, 10);
        if (!CollectionUtils.isEmpty(custumers.toList())) {
            //on retire tous les élts null que peut contenir cette liste
//            custumers.getContent().toList().removeAll(Collections.singleton(null));
            List<AgencyDTO> custumerDTOs = custumers.stream().map(custumer -> {
                return mapAgencyToAgencyDTO(custumer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<AgencyDTO>>(custumerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<AgencyDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param agencyDTORequest
     * @return
     */
    @PostMapping("/addAgency")
    @ApiOperation(value = "Add a new Agency in the Library", response = AgencyDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 409, message = "Conflict: the customer already exist"),
        @ApiResponse(code = 201, message = "Created: the customer is successfully inserted"),
        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted")})
    public ResponseEntity<AgencyDTO> createNewAgency(@RequestBody AgencyDTO agencyDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        Agency existingAgency = agencyService.findAgencyByName(agencyDTORequest.getName());
        if (existingAgency != null) {
            return new ResponseEntity<AgencyDTO>(HttpStatus.CONFLICT);
        }
        Agency customerRequest = mapAgencyDTOToAgency(agencyDTORequest);
        customerRequest.setCreationDate(LocalDate.now());
        Agency customerResponse = agencyService.saveAgency(customerRequest);
        if (customerResponse != null) {
            AgencyDTO customerDTO = mapAgencyToAgencyDTO(customerResponse);
            return new ResponseEntity<AgencyDTO>(customerDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<AgencyDTO>(HttpStatus.NOT_MODIFIED);

    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param customerDTORequest
     * @return
     */
    @PutMapping("/updateAgency")
    @ApiOperation(value = "Update/Modify an existing customer in the Library", response = AgencyDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Not Found : the customer does not exist"),
        @ApiResponse(code = 200, message = "Ok: the customer is successfully updated"),
        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully updated")})
    public ResponseEntity<AgencyDTO> updateAgency(@RequestBody AgencyDTO customerDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        if (!agencyService.checkIfIdexists(customerDTORequest.getId())) {
            return new ResponseEntity<AgencyDTO>(HttpStatus.NOT_FOUND);
        }
        Agency customerRequest = mapAgencyDTOToAgency(customerDTORequest);
        Agency customerResponse = agencyService.updateAgency(customerRequest);
        if (customerResponse != null) {
            AgencyDTO customerDTO = mapAgencyToAgencyDTO(customerResponse);
            return new ResponseEntity<AgencyDTO>(customerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<AgencyDTO>(HttpStatus.NOT_MODIFIED);
    }

//	/**
//	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
//	 * @param customerId
//	 * @return
//	 */
    @DeleteMapping("/deleteAgency/{customerId}")
    @ApiOperation(value = "Delete a customer in the Library, if the customer does not exist, nothing is done", response = String.class)
    @ApiResponse(code = 204, message = "No Content: customer sucessfully deleted")
    public ResponseEntity<String> deleteAgency(@PathVariable Integer customerId) {
        agencyService.deleteAgency(customerId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
//

    @GetMapping("/paginatedSearch")
    @ApiOperation(value = "List customers of the Library in a paginated way", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok: successfully listed"),
        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<AgencyDTO>> searchAgencys(@RequestParam("beginPage") int beginPage,
            @RequestParam("endPage") int endPage) {
        //, UriComponentsBuilder uriComponentBuilder
        Page<Agency> customers = agencyService.getPaginatedAgencysList(beginPage, endPage);
        if (customers != null) {
            List<AgencyDTO> customerDTOs = customers.stream().map(customer -> {
                return mapAgencyToAgencyDTO(customer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<AgencyDTO>>(customerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<AgencyDTO>>(HttpStatus.NO_CONTENT);
    }
//
//	/**
//	 * Retourne le client ayant l'adresse email passé en paramètre.
//	 * @param email
//	 * @return
//	 */

//    @GetMapping("/searchByEmail")
//    @ApiOperation(value = "Search a customer in the Library by its email", response = AgencyDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<AgencyDTO> searchAgencyByEmail(@RequestParam("email") String email) {
//        //, UriComponentsBuilder uriComponentBuilder
//        Agency customer = agencyService.findAgencyByEmail(email);
//        if (customer != null) {
//            AgencyDTO customerDTO = mapAgencyToAgencyDTO(customer);
//            return new ResponseEntity<AgencyDTO>(customerDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<AgencyDTO>(HttpStatus.NO_CONTENT);
//    }
//	
//	/**
//	 * Retourne la liste des clients ayant le nom passé en paramètre.
//	 * @param lastName
//	 * @return
//	 */
//    @GetMapping("/searchByLastName")
//    @ApiOperation(value = "Search a customer in the Library by its Last name", response = List.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<List<AgencyDTO>> searchBookByLastName(@RequestParam("lastName") String lastName) {
//        //,	UriComponentsBuilder uriComponentBuilder
//        List<Agency> customers = agencyService.findAgencyByLastName(lastName);
//        if (customers != null && !CollectionUtils.isEmpty(customers)) {
//            List<AgencyDTO> customerDTOs = customers.stream().map(customer -> {
//                return mapAgencyToAgencyDTO(customer);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<AgencyDTO>>(customerDTOs, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<AgencyDTO>>(HttpStatus.NO_CONTENT);
//    }
    /**
     * Transforme un entity Agency en un POJO AgencyDTO
     *
     * @param customer
     * @return
     */
    private AgencyDTO mapAgencyToAgencyDTO(Agency customer) {
        ModelMapper mapper = new ModelMapper();
        AgencyDTO customerDTO = mapper.map(customer, AgencyDTO.class);
        return customerDTO;
    }

    /**
     * Transforme un POJO AgencyDTO en en entity Agency
     *
     * @param customerDTO
     * @return
     */
    private Agency mapAgencyDTOToAgency(AgencyDTO customerDTO) {
        ModelMapper mapper = new ModelMapper();
        Agency customer = mapper.map(customerDTO, Agency.class);
        return customer;
    }

}
