package com.gkemayo.voy.projectiontravel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ProjectionTravelRepository extends JpaRepository<ProjectionTravel, Long> {

//	public Place findPlaceByEmailIgnoreCase(String email);
//	
//	public List<Place> findPlaceByLastNameIgnoreCase(String lastName);
}
