package com.example.desafio_precojusto.controller;

import com.example.desafio_precojusto.business.CreateExcelBusiness;
import com.example.desafio_precojusto.business.DuckBusiness;
import com.example.desafio_precojusto.entity.Duck;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/generate")
public class createExcelController {
    @Autowired
    private CreateExcelBusiness createExcelBusiness;

    @GetMapping()
    public void generateExcelReport(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=courses.xls";

        response.setHeader(headerKey, headerValue);

        createExcelBusiness.generateExcel(response);

        response.flushBuffer();
    }
}