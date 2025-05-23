package com.middleware.invoice_ems.Service;

import com.middleware.invoice_ems.DTO.ClientDTO;
import com.middleware.invoice_ems.DTO.InvoiceDTO;
import com.middleware.invoice_ems.DTO.InvoiceItemDTO;
import com.middleware.invoice_ems.Entity.Client;
import com.middleware.invoice_ems.Entity.Invoice;
import com.middleware.invoice_ems.Entity.InvoiceItem;
//import com.middleware.invoice_ems.Exception.ResourceNotFoundException;
import com.middleware.invoice_ems.Repository.ClientRepository;
import com.middleware.invoice_ems.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final ClientRepository clientRepository;

    private final EmailService emailService;

    public InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository, EmailService emailService) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
    }

    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = convertToEntity(invoiceDTO);
//        if(invoice.getClient()!=null && invoice.getClient().getId()>0){
//            System.out.println(invoice.getClient().getId()+" Client Id");
//            Client existingClient = clientRepository.findById(invoice.getClient().getId()).orElseThrow(() -> new RuntimeException("Client not found"));
//            invoice.setClient(existingClient);
//        } else if (invoice.getClient()!=null) {
//            Client newClient = invoice.getClient();
//            System.out.println(" Client Details "+invoice.getClient());
//            clientRepository.save(newClient);
//            invoice.setClient(newClient);
//        }
            Client newClient = invoice.getClient();
            System.out.println(" Client Details "+invoice.getClient().toString());
            clientRepository.save(newClient);
            invoice.setClient(newClient);
            Invoice savedInvoice = invoiceRepository.save(invoice);

            emailService.notifyClient(savedInvoice.getClient());
        return savedInvoice;
    }

    public Invoice updateInvoice(int id, InvoiceDTO invoiceDTO) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice Id not found"));
        updateInvoiceFields(existingInvoice, invoiceDTO);
        return invoiceRepository.save(existingInvoice);
    }

    public Invoice getInvoice(int id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice Id not found"));
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    private Invoice convertToEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setIssueDate(dto.getIssueDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setTax(dto.getTax());
        invoice.setCompanyName(dto.getCompanyName());
        invoice.setInvoiceType(dto.getInvoiceType());
        if (dto.getClient() != null) {
            Client client = new Client();
            client.setClientName(dto.getClient().getClientName());
            client.setClientEmail(dto.getClient().getClientEmail());
            client.setClientPhone(dto.getClient().getClientPhone());
            client.setClientAddress(dto.getClient().getClientAddress());
            invoice.setClient(client);
        }

        if (dto.getInvoiceItems() != null) {
            List<InvoiceItem> items = dto.getInvoiceItems().stream()
                    .map(itemDto -> {
                        InvoiceItem item = new InvoiceItem();
                        item.setDescription(itemDto.getDescription());
                        item.setQuantity(itemDto.getQuantity());
                        item.setUnitPrice(itemDto.getUnitPrice());
                        item.setTotalPrice(itemDto.getTotalPrice());
                        item.setInvoice(invoice);
                        return item;
                    })
                    .collect(Collectors.toList());
            invoice.setInvoiceItems(items);
        }

//        if (dto.getPayments() != null) {
//            List<Payment> payments = dto.getPayments().stream()
//                    .map(paymentDto -> {
//                        Payment payment = new Payment();
//                        payment.setAmount(paymentDto.getAmount());
//                        payment.setPaymentDate(paymentDto.getPaymentDate());
//                        payment.setPaymentMethod(paymentDto.getPaymentMethod());
//                        payment.setPaymentStatus(paymentDto.getPaymentStatus());
//                        payment.setTransactionId(paymentDto.getTransactionId());
//                        payment.setInvoice(invoice);
//                        return payment;
//                    })
//                    .collect(Collectors.toList());
//            invoice.setPayments(payments);
//        }

        return invoice;
    }

    public Long getInvoiceCountByMonthAndCompany(int month, int year, String clientName){
        return invoiceRepository.countInvoicesByClientAndMonth(month, year, clientName);
    }

    private void updateInvoiceFields(Invoice invoice, InvoiceDTO dto) {
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setIssueDate(dto.getIssueDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setTax(dto.getTax());
        invoice.setCompanyName(dto.getCompanyName());
        invoice.setInvoiceType(dto.getInvoiceType());
        if (dto.getInvoiceItems() != null) {
            invoice.getInvoiceItems().clear(); // Clear old items first
            invoice.getInvoiceItems().addAll(dto.getInvoiceItems().stream()
                    .map(itemDto -> {
                        InvoiceItem invoiceItem = new InvoiceItem();
                        invoiceItem.setDescription(itemDto.getDescription());
                        invoiceItem.setQuantity(itemDto.getQuantity());
                        invoiceItem.setUnitPrice(itemDto.getUnitPrice());
                        invoiceItem.setTotalPrice(itemDto.getTotalPrice());
                        invoiceItem.setInvoice(invoice);
                        return invoiceItem;
                    })
                    .collect(Collectors.toList()));
        }
    }

    public InvoiceDTO convertToDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setIssueDate(invoice.getIssueDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setTax(invoice.getTax());
        dto.setCompanyName(invoice.getCompanyName());
        dto.setInvoiceType(invoice.getInvoiceType());
        if (invoice.getClient() != null) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(invoice.getClient().getId());
            clientDTO.setClientName(invoice.getClient().getClientName());
            clientDTO.setClientEmail(invoice.getClient().getClientEmail());
            clientDTO.setClientPhone(invoice.getClient().getClientPhone());
            clientDTO.setClientAddress(invoice.getClient().getClientAddress());
            dto.setClient(clientDTO);
        }

        if (invoice.getInvoiceItems() != null) {
            List<InvoiceItemDTO> itemDTOS = invoice.getInvoiceItems().stream()
                    .map(item -> {
                        InvoiceItemDTO itemDTO = new InvoiceItemDTO();
                        itemDTO.setDescription(item.getDescription());
                        itemDTO.setQuantity(item.getQuantity());
                        itemDTO.setUnitPrice(item.getUnitPrice());
                        itemDTO.setTotalPrice(item.getTotalPrice());
                        return itemDTO;
                    })
                    .collect(Collectors.toList());
            dto.setInvoiceItems(itemDTOS);
        }

//        if (invoice.getPayments() != null) {
//            List<PaymentDTO> paymentDTOS = invoice.getPayments().stream()
//                    .map(payment -> {
//                        PaymentDTO paymentDTO = new PaymentDTO();
//                        paymentDTO.setAmount(payment.getAmount());
//                        paymentDTO.setPaymentDate(payment.getPaymentDate());
//                        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
//                        paymentDTO.setPaymentStatus(payment.getPaymentStatus());
//                        paymentDTO.setTransactionId(payment.getTransactionId());
//                        return paymentDTO;
//                    })
//                    .collect(Collectors.toList());
//            dto.setPayments(paymentDTOS);
//        }

        return dto;
    }
}
