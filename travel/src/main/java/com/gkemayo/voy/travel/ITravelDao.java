package com.gkemayo.voy.travel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravelDao extends JpaRepository<Travel, Integer> {

//	public List<Travel> findByEndDateBefore(LocalDate maxEndDate);
	
//	@Query(   "SELECT lo "
//			+ "FROM Ticket lo "
//			+ "INNER JOIN lo.pk.customer c "
//			+ "WHERE UPPER(c.email) = UPPER(?1) "
//			+ "   AND lo.status = ?2 ")
//	public List<Ticket> getAllOpenTicketsOfThisCustomer(String email, TicketStatus status);
	
//	@Query(   "SELECT lo "
//			+ "FROM Ticket lo "
//			+ "INNER JOIN lo.pk.book b "
//			+ "INNER JOIN lo.pk.customer c "
//			+ "WHERE b.id =	?1 "
//			+ "   AND c.id = ?2 "
//			+ "   AND lo.status = ?3 ")
//	public Ticket getTicketByCriteria(Integer bookId, Integer customerId, TicketStatus status);
}
