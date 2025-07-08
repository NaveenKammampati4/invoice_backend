package com.middleware.invoice_ems.DTO;

import com.middleware.invoice_ems.Entity.InvoiceStatus;

public class CountInvoicesDTO {

    private InvoiceStatus invoiceStatus;
    private long count;

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public CountInvoicesDTO(InvoiceStatus invoiceStatus, long count) {
        this.invoiceStatus = invoiceStatus;
        this.count = count;
    }
}
