package com.gkemayo.voy.ticket;

import java.time.LocalDate;
import java.util.List;

public interface ITicketService {
	
//	public List<Ticket> findAllTicketsByTicketDateBefore(LocalDate maxEndDate);
//	
//	public List<Ticket> getAllOpenTicketsOfThisCustomer(String email, TicketStatus status);
//	
//	public Ticket getOpenedTicket(SimpleTicketDTO simpleTicketDTO);
//	
//	public boolean checkIfTicketExists(SimpleTicketDTO simpleTicketDTO);
	
	public Ticket saveTicket(Ticket ticket);
	
	public void closeTicket(Ticket ticket);

}
