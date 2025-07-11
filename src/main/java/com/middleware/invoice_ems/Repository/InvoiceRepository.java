package com.middleware.invoice_ems.Repository;


import com.middleware.invoice_ems.DTO.CountInvoicesDTO;
import com.middleware.invoice_ems.DTO.InvoiceDTO;
import com.middleware.invoice_ems.Entity.Invoice;
import com.middleware.invoice_ems.Entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

//    List<Invoice> findByClientAndStatus(Client client);

    @Query("SELECT COUNT(i) FROM Invoice i " +
            "WHERE FUNCTION('MONTH', i.issueDate) = :month " +
            "AND FUNCTION('YEAR', i.issueDate) = :year " +
            "AND i.client.id IN (SELECT c.id FROM Client c WHERE c.clientName = :clientName)")
    Long countInvoicesByClientAndMonth(@Param("month") int month,
                                       @Param("year") int year,
                                       @Param("clientName") String clientName);

    List<Invoice> findByInvoiceStatus(InvoiceStatus invoiceStatus);

    List<Invoice> findByInvoiceStatusIn(List<InvoiceStatus> statuses);


    @Query("SELECT new com.middleware.invoice_ems.DTO.CountInvoicesDTO(i.invoiceStatus, COUNT(i)) FROM Invoice i GROUP BY i.invoiceStatus ORDER BY i.invoiceStatus ASC")
    List<CountInvoicesDTO> countAllInvoices();

    @Query("SELECT i FROM Invoice i WHERE i.issueDate >= :issueDate AND i.dueDate <= :dueDate")
    List<Invoice> fetchByIssueAndDueDates(@Param("issueDate") LocalDate issueDate,
                                          @Param("dueDate") LocalDate dueDate);


}
