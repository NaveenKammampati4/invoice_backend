package com.middleware.invoice_ems.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    @JsonProperty("id")
    private int id;

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;

    @JsonProperty("issueDate")
    private LocalDate issueDate;

    @JsonProperty("dueDate")
    private LocalDate dueDate;
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;
    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("invoiceType")
    private String invoiceType;
    @JsonProperty("tax")
    private BigDecimal tax;
//    @JsonProperty("status")
//    private String status;
    @JsonProperty("client")
    private ClientDTO client;
    @JsonProperty("invoiceItems")
    private List<InvoiceItemDTO> invoiceItems;
//    @JsonProperty("payments")
//    private List<PaymentDTO> payments;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public List<InvoiceItemDTO> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemDTO> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
}
