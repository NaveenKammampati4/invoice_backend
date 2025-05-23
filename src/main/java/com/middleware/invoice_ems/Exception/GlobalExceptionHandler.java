//package com.middleware.invoice_ems.Exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
//        return buildResponseEntity(HttpStatus.NOT_FOUND, e.getMessage(), request);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) {
//        // Skip handling for Swagger endpoints
//        if (request.getDescription(false).contains("/v3/api-docs")) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request);
//    }
//
//    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message, WebRequest request) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("status", status.value());
//        body.put("error", status.getReasonPhrase());
//        body.put("message", message);
//        body.put("path", request.getDescription(false));
//        return new ResponseEntity<>(body, status);
//    }
//}
//
