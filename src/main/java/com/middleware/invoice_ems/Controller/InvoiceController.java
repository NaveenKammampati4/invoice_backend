package com.middleware.invoice_ems.Controller;

import com.middleware.invoice_ems.DTO.CountInvoicesDTO;
import com.middleware.invoice_ems.DTO.InvoiceDTO;
import com.middleware.invoice_ems.Entity.Invoice;
import com.middleware.invoice_ems.Entity.InvoiceStatus;
import com.middleware.invoice_ems.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/submit")
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        Invoice createdInvoice = invoiceService.createInvoice(invoiceDTO);
        return ResponseEntity.ok(invoiceService.convertToDTO(createdInvoice));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable int id, @RequestBody InvoiceDTO invoiceDTO) {
        return ResponseEntity.ok(invoiceService.convertToDTO(invoiceService.updateInvoice(id, invoiceDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable int id) {
        return ResponseEntity.ok(invoiceService.convertToDTO(invoiceService.getInvoice(id)));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices().stream()
                .map(invoiceService::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/getByMonth/{month}/{year}/{clientName}")
    public ResponseEntity<Long> getInvoiceByMonth(@PathVariable int month,@PathVariable int year,@PathVariable String clientName) {
        return ResponseEntity.ok(invoiceService.getInvoiceCountByMonthAndCompany(month, year, clientName));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Invoice>> getInvoicesByStatus(@PathVariable String status) {
        try {
            InvoiceStatus invoiceStatus = InvoiceStatus.valueOf(status.toUpperCase());
            List<Invoice> invoices = invoiceService.getInvoicesByStatus(invoiceStatus);

//            if (invoices.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(invoices);
//            }

            return ResponseEntity.ok(invoices);

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList()); // invalid status like /status/invalid
        }
    }


    @GetMapping("/status")
    public ResponseEntity<List<Invoice>> getInvoicesByStatuses(@RequestParam List<String> statuses){
        List<InvoiceStatus> invoiceStatuses= statuses.stream()
                .map(String::toUpperCase)
                .map(InvoiceStatus::valueOf)
                .collect(Collectors.toList());
        List<Invoice> invoice = invoiceService.getInvoicesByStatuses(invoiceStatuses);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Invoice>> getAllPendingInvoices(){
        List<Invoice> pendingInvoices = invoiceService.getAllPendingInvoices();
        if (pendingInvoices.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pendingInvoices);
        }
        return ResponseEntity.ok(pendingInvoices);
    }

    @PutMapping("/paid/{id}")
    public ResponseEntity<String> paidInvoice(@PathVariable int id){
        invoiceService.paidInvoice(id);
        return ResponseEntity.ok("Invoice Status Updated Successfully");
    }

    @GetMapping("/getInvoiceData")
    public ResponseEntity<List<CountInvoicesDTO>> getAllInvoiceData(){
        List<CountInvoicesDTO> invoiceData = invoiceService.getAllInvoiceData();
        return ResponseEntity.ok(invoiceData);
    }

    @PutMapping("/overDue/{id}")
    public ResponseEntity<String> overDueInvoice(@PathVariable int id){
        invoiceService.overDueInvoice(id);
        return ResponseEntity.ok("Invoice Status Updated Successfully");
    }
}

