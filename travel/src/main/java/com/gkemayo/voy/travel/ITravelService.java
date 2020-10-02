package com.gkemayo.voy.travel;

public interface ITravelService {
	
//	public List<Travel> findAllTicketsByTicketDateBefore(LocalDate maxEndDate);
//	
//	public List<Travel> getAllOpenTicketsOfThisCustomer(String email, TicketStatus status);
//	
//	public Travel getOpenedTicket(SimpleTicketDTO simpleTicketDTO);
//	
//	public boolean checkIfTicketExists(SimpleTicketDTO simpleTicketDTO);
	
	public Travel saveTravel(Travel travel);
	
	public void closeTravel(Travel travel);

}
