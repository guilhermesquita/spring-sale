package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.business.CreatePDFBusiness;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/generate/pdf")
public class CreatePDFController {

    private final CreatePDFBusiness createPDFBusiness;

    @Autowired
    public CreatePDFController(CreatePDFBusiness createPDFBusiness) {
        this.createPDFBusiness = createPDFBusiness;
    }

    @GetMapping("/pdf")
    public void generatePDFReport(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=ducks.pdf";
        response.setHeader(headerKey, headerValue);
        createPDFBusiness.generatePDF(response);
        response.flushBuffer();
    }
}