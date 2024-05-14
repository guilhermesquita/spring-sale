package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.entity.Client;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.ClientRepository;
import com.example.desafio_precojusto.repository.DuckRepository;
import com.example.desafio_precojusto.repository.SaleRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateExcelBusiness {
    private DuckRepository duckRepository;
    private ClientRepository clientRepository;
    private SaleRepository saleRepository;

    public CreateExcelBusiness(DuckRepository duckRepository, ClientRepository clientRepository, SaleRepository saleRepository) {
        this.duckRepository = duckRepository;
        this.clientRepository = clientRepository;
        this.saleRepository = saleRepository;
    }

    public void generateExcel(HttpServletResponse response) throws Exception {

        List<Duck> ducks = duckRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("gerenciamento_de_patos");

        HSSFRow mergedRow = sheet.createRow(1);
        mergedRow.createCell(1).setCellValue("Gerenciamento de patos");

        CellStyle mergedCellStyle = workbook.createCellStyle();
        mergedCellStyle.setAlignment(HorizontalAlignment.CENTER);
        mergedCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 26);
        mergedCellStyle.setFont(font);
        mergedRow.getCell(1).setCellStyle(mergedCellStyle);
        sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(1, 1, 1, 5));

        HSSFRow headerRow = sheet.createRow(2);
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("Status");
        headerRow.createCell(3).setCellValue("Cliente");
        headerRow.createCell(4).setCellValue("Tipo de cliente");
        headerRow.createCell(5).setCellValue("Valor");

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.LEFT);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (int i = 1; i < headerRow.getLastCellNum(); i++) {
            headerRow.getCell(i).setCellStyle(headerCellStyle);
        }

        int dataRowIndex = 3;

        for (Duck duck : ducks) {
            var id = saleRepository.findByDuckId(duck.getIdDuck());
            String name_client = null;
            String type_client = null;
            String total_value = null;
            if(id != null){
                Client client = id.getIdClient();
                name_client = client.getNameClient();
                Boolean type_boolean_client = client.getDescont();
                total_value = "R$ " + id.getValueSale();
                if(type_boolean_client){
                    type_client = "com desconto";
                }else {
                    type_client = "sem desconto";
                }
            }else{
                name_client = "-";
                type_client = "-";
                total_value = "-";
            }

            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(1).setCellValue(duck.getNameDuck());
            dataRow.createCell(2).setCellValue(duck.getStatusDuck());
            dataRow.createCell(3).setCellValue(name_client);
            dataRow.createCell(4).setCellValue(type_client);
            dataRow.createCell(5).setCellValue(total_value);
            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }
}