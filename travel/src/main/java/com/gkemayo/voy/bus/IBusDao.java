package com.gkemayo.voy.bus;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBusDao extends JpaRepository<Bus, Integer> {

	public Bus findBusByImmatriculeIgnoreCase(String immatricule);
		
}
