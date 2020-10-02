package com.gkemayo.voy.agency;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@RepositoryRestResource
public interface IAgencyDao extends JpaRepository<Agency, Integer> {

    public Agency findAgencyByNameIgnoreCase(String name);

}
