//package com.middleware.invoice_ems.Entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Payment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String paymentMethod;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate paymentDate;
//    private BigDecimal amount;
//    private String transactionId;
//    private String paymentStatus;
//
//    @ManyToOne
//    @JoinColumn(name = "invoice_id")
//    private Invoice invoice;
//
//
//}
