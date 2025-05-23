package com.middleware.invoice_ems.Controller;

import com.middleware.invoice_ems.DTO.InvoiceDTO;
import com.middleware.invoice_ems.Entity.Invoice;
import com.middleware.invoice_ems.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

