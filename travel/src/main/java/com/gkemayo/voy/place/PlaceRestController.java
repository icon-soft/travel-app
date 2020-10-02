package com.gkemayo.voy.place;

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
@RequestMapping("/rest/customer/api")
@Api(value = "Place Rest Controller: contains all operations for managing customers")
public class PlaceRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(PlaceRestController.class);

    @Autowired
    private PlaceServiceImpl customerService;

    @GetMapping("/allPlaces")
    @ApiOperation(value = "List all Place of the agency", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok: successfully listed"),
        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<PlaceDTO>> getAllBookPlaces() {
        Page<Place> custumers = customerService.getPaginatedPlacesList(0, 10);
        if (!CollectionUtils.isEmpty(custumers.toList())) {
            //on retire tous les élts null que peut contenir cette liste
//            custumers.getContent().removeAll(Collections.singleton(null));
            List<PlaceDTO> custumerDTOs = custumers.stream().map(custumer -> {
                return mapPlaceToPlaceDTO(custumer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<PlaceDTO>>(custumerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<PlaceDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param customerDTORequest
     * @return
     */
//    @PostMapping("/addPlace")
//    @ApiOperation(value = "Add a new Place in the Library", response = PlaceDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 409, message = "Conflict: the customer already exist"),
//        @ApiResponse(code = 201, message = "Created: the customer is successfully inserted"),
//        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted")})
//    public ResponseEntity<PlaceDTO> createNewPlace(@RequestBody PlaceDTO customerDTORequest) {
//        //, UriComponentsBuilder uriComponentBuilder
//        Place existingPlace = customerService.findPlaceByEmail(customerDTORequest.getEmail());
//        if (existingPlace != null) {
//            return new ResponseEntity<PlaceDTO>(HttpStatus.CONFLICT);
//        }
//        Place customerRequest = mapPlaceDTOToPlace(customerDTORequest);
//        customerRequest.setCreationDate(LocalDate.now());
//        Place customerResponse = customerService.savePlace(customerRequest);
//        if (customerResponse != null) {
//            PlaceDTO customerDTO = mapPlaceToPlaceDTO(customerResponse);
//            return new ResponseEntity<PlaceDTO>(customerDTO, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<PlaceDTO>(HttpStatus.NOT_MODIFIED);
//
//    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param customerDTORequest
     * @return
     */
//    @PutMapping("/updatePlace")
//    @ApiOperation(value = "Update/Modify an existing customer in the Library", response = PlaceDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 404, message = "Not Found : the customer does not exist"),
//        @ApiResponse(code = 200, message = "Ok: the customer is successfully updated"),
//        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully updated")})
//    public ResponseEntity<PlaceDTO> updatePlace(@RequestBody PlaceDTO customerDTORequest) {
//        //, UriComponentsBuilder uriComponentBuilder
//        if (!customerService.checkIfIdexists(customerDTORequest.getId())) {
//            return new ResponseEntity<PlaceDTO>(HttpStatus.NOT_FOUND);
//        }
//        Place customerRequest = mapPlaceDTOToPlace(customerDTORequest);
//        Place customerResponse = customerService.updatePlace(customerRequest);
//        if (customerResponse != null) {
//            PlaceDTO customerDTO = mapPlaceToPlaceDTO(customerResponse);
//            return new ResponseEntity<PlaceDTO>(customerDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<PlaceDTO>(HttpStatus.NOT_MODIFIED);
//    }
//
////	/**
////	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
////	 * @param customerId
////	 * @return
////	 */
//    @DeleteMapping("/deletePlace/{customerId}")
//    @ApiOperation(value = "Delete a customer in the Library, if the customer does not exist, nothing is done", response = String.class)
//    @ApiResponse(code = 204, message = "No Content: customer sucessfully deleted")
//    public ResponseEntity<String> deletePlace(@PathVariable Integer customerId) {
//        customerService.deletePlace(customerId);
//        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
//    }
////
//
//    @GetMapping("/paginatedSearch")
//    @ApiOperation(value = "List customers of the Library in a paginated way", response = List.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfully listed"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<List<PlaceDTO>> searchPlaces(@RequestParam("beginPage") int beginPage,
//            @RequestParam("endPage") int endPage) {
//        //, UriComponentsBuilder uriComponentBuilder
//        Page<Place> customers = customerService.getPaginatedPlacesList(beginPage, endPage);
//        if (customers != null) {
//            List<PlaceDTO> customerDTOs = customers.stream().map(customer -> {
//                return mapPlaceToPlaceDTO(customer);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<PlaceDTO>>(customerDTOs, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<PlaceDTO>>(HttpStatus.NO_CONTENT);
//    }
////
////	/**
////	 * Retourne le client ayant l'adresse email passé en paramètre.
////	 * @param email
////	 * @return
////	 */
//
//    @GetMapping("/searchByEmail")
//    @ApiOperation(value = "Search a customer in the Library by its email", response = PlaceDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<PlaceDTO> searchPlaceByEmail(@RequestParam("email") String email) {
//        //, UriComponentsBuilder uriComponentBuilder
//        Place customer = customerService.findPlaceByEmail(email);
//        if (customer != null) {
//            PlaceDTO customerDTO = mapPlaceToPlaceDTO(customer);
//            return new ResponseEntity<PlaceDTO>(customerDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<PlaceDTO>(HttpStatus.NO_CONTENT);
//    }
////	
////	/**
////	 * Retourne la liste des clients ayant le nom passé en paramètre.
////	 * @param lastName
////	 * @return
////	 */
//
//    @GetMapping("/searchByLastName")
//    @ApiOperation(value = "Search a customer in the Library by its Last name", response = List.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<List<PlaceDTO>> searchBookByLastName(@RequestParam("lastName") String lastName) {
//        //,	UriComponentsBuilder uriComponentBuilder
//        List<Place> customers = customerService.findPlaceByLastName(lastName);
//        if (customers != null && !CollectionUtils.isEmpty(customers)) {
//            List<PlaceDTO> customerDTOs = customers.stream().map(customer -> {
//                return mapPlaceToPlaceDTO(customer);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<PlaceDTO>>(customerDTOs, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<PlaceDTO>>(HttpStatus.NO_CONTENT);
//    }
//
//    /**
//     * Transforme un entity Place en un POJO PlaceDTO
//     *
//     * @param customer
//     * @return
//     */
    private PlaceDTO mapPlaceToPlaceDTO(Place customer) {
        ModelMapper mapper = new ModelMapper();
        PlaceDTO customerDTO = mapper.map(customer, PlaceDTO.class);
        return customerDTO;
    }

    /**
     * Transforme un POJO PlaceDTO en en entity Place
     *
     * @param customerDTO
     * @return
     */
    private Place mapPlaceDTOToPlace(PlaceDTO customerDTO) {
        ModelMapper mapper = new ModelMapper();
        Place customer = mapper.map(customerDTO, Place.class);
        return customer;
    }

}
