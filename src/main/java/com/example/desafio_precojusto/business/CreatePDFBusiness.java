package com.example.desafio_precojusto.business;

import com.example.desafio_precojusto.entity.Client;
import com.example.desafio_precojusto.entity.Duck;
import com.example.desafio_precojusto.repository.ClientRepository;
import com.example.desafio_precojusto.repository.DuckRepository;
import com.example.desafio_precojusto.repository.SaleRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CreatePDFBusiness {
    private DuckRepository duckRepository;
    private ClientRepository clientRepository;
    private SaleRepository saleRepository;

    public CreatePDFBusiness(DuckRepository duckRepository, ClientRepository clientRepository, SaleRepository saleRepository) {
        this.duckRepository = duckRepository;
        this.clientRepository = clientRepository;
        this.saleRepository = saleRepository;
    }

    public void generatePDF(HttpServletResponse response) throws IOException {

        List<Duck> ducks = duckRepository.findAll();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Gerenciamento de patos");
            contentStream.endText();

            int y = 650;

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, y);
            contentStream.showText("Nome");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Status");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Cliente");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Tipo de cliente");
            contentStream.newLineAtOffset(100, 0);
            contentStream.showText("Valor");
            contentStream.endText();

            for (Duck duck : ducks) {
                var id = saleRepository.findByDuckId(duck.getIdDuck());
                String name_client = "-";
                String type_client = "-";
                String total_value = "-";

                if (id != null) {
                    Client client = id.getIdClient();
                    name_client = client.getNameClient();
                    Boolean type_boolean_client = client.getDescont();
                    total_value = "R$ " + id.getValueSale();
                    if (type_boolean_client) {
                        type_client = "com desconto";
                    } else {
                        type_client = "sem desconto";
                    }
                }

                y -= 20;

                contentStream.beginText();
                contentStream.newLineAtOffset(100, y);
                contentStream.showText(duck.getNameDuck());
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(duck.getStatusDuck());
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(name_client);
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(type_client);
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(total_value);
                contentStream.endText();
            }
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=gerencimento_de_patos.pdf");

        ServletOutputStream outputStream = response.getOutputStream();
        document.save(outputStream);
        document.close();
        outputStream.close();
    }
}