package com.gkemayo.voy.bus;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IBusService {

	public Bus saveBus(Bus bus);

	public Bus updateBus(Bus bus);

	public void deleteBus(Integer busId);
	
	public boolean checkIfIdexists(Integer id);

	public Bus findBusByImmatricule(String immatricule);
	
	public Bus findBusById(Integer busId);

	public Page<Bus> getPaginatedBussList(int begin, int end);

}
