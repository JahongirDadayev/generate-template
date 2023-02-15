package com.example.springsocial.service.serviceImpl;

import com.example.springsocial.model.request.WordRequestDto;
import com.example.springsocial.repository.MinistryDataRepository;
import com.example.springsocial.repository.MinistryRepository;
import com.example.springsocial.service.TemplateService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    @Value(value = "${generate.template.title_uz}")
    private String titleUz;

    @Value(value = "${generate.template.title_eng}")
    private String titleEng;

    @Value(value = "${generate.template.address}")
    private String address;

    @Value(value = "${generate.template.person_roll}")
    private String personRoll;

    @Value(value = "${generate.template.person_name}")
    private String personName;

    private final MinistryRepository ministryRepository;

    private final MinistryDataRepository ministryDataRepository;

    @Override
    public void generateTemplate(WordRequestDto requestDto, HttpServletResponse response) throws IOException, WriterException {
        PdfWriter pdfWriter = new PdfWriter(response.getOutputStream());
        PdfDocument pdfdocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfdocument);
        PdfPage pdfPage = pdfdocument.addNewPage();
        PdfFont font = PdfFontFactory.createFont("src/main/resources/static/shrift/ofont.ru_Uk_Antique.ttf","Cp1251", true);

        //title image
        Table table = new Table(3);
        Cell cell = new Cell();
        Image image = new Image(ImageDataFactory.create("src/main/resources/static/image/img.png"));
        image.setWidth(100).setHeight(100);
        cell.add(image);
        cell.setBorder(Border.NO_BORDER);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.addCell(cell);

        //title name
        cell = new Cell();
        Paragraph paragraph = new Paragraph();
        paragraph.setTextAlignment(TextAlignment.LEFT);
        paragraph.setFontColor(new DeviceRgb(0, 51, 153));
        paragraph.setFontSize(12);
        paragraph.setFont(font);
        paragraph.setBold();
        paragraph.setMarginBottom(5);
        paragraph.add(titleUz);
        cell.add(paragraph);
        paragraph = new Paragraph();
        paragraph.setTextAlignment(TextAlignment.LEFT);
        paragraph.setFontColor(new DeviceRgb(0, 51, 153));
        paragraph.setFontSize(12);
        paragraph.setFont(font);
        paragraph.setBold();
        paragraph.setMarginBottom(10);
        paragraph.add(titleEng);
        cell.add(paragraph);
        paragraph = new Paragraph();
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.setFontColor(new DeviceRgb(0, 51, 153));
        paragraph.setFontSize(8);
        paragraph.setFont(font);
        paragraph.setBold();
        paragraph.setMarginBottom(10);
        paragraph.add(address);
        cell.add(paragraph);
        cell.setBorder(Border.NO_BORDER);
        table.addCell(cell);
        PdfCanvas canvas = new PdfCanvas(pdfPage);
        canvas.moveTo(142, 747)
                .lineTo(pdfPage.getPageSizeWithRotation().getRight() - 130, 747)
                .setColor(new DeviceRgb(0, 51, 153), false)
                .stroke();

        //title QrCode
        cell = new Cell();
        cell.setBorder(Border.NO_BORDER);
        Image imageQrCode = generateQrCode("Jahongir Dadayev Java Backend Developer", 100, 100);
        cell.add(imageQrCode);
        cell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        cell.setVerticalAlignment(VerticalAlignment.TOP);
        table.addCell(cell);
        document.add(table);

        //line
        canvas = new PdfCanvas(pdfPage);
        canvas.moveTo(50, 650)
                .lineTo(pdfPage.getPageSizeWithRotation().getRight() - 50, 650)
                .setColor(new DeviceRgb(0, 0, 0), false)
                .setLineWidth(5)
                .stroke();

        //date
        paragraph = new Paragraph();
        paragraph.setMarginLeft(20);
        paragraph.setMarginBottom(5);
        paragraph.add("\"" + requestDto.getDate() + "\"  ");
        paragraph.add(requestDto.getNumber());
        paragraph.setFontSize(12);
        paragraph.setFont(font);
        paragraph.setBold();
        document.add(paragraph);

        //ministry
        table = new Table(1);
        cell = new Cell();
        cell.setBorder(Border.NO_BORDER);
        cell.setWidth(200);
        cell.setHeight(35);
        paragraph = new Paragraph();
        paragraph.setBold();
        paragraph.add(ministryRepository.findById(requestDto.getMinistry().getId()).orElseThrow().getName());
        paragraph.setTextAlignment(TextAlignment.CENTER);
        paragraph.setFontSize(12);
        paragraph.setFont(font);
        cell.add(paragraph);
        table.addCell(cell);
        table.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        document.add(table);

        //data
        String[] splitData = ministryDataRepository.findById(requestDto.getMinistryData().getId()).orElseThrow().getData().split("\n");
        Arrays.stream(splitData).forEach(data -> {
            Paragraph dataParagraph = new Paragraph();
            dataParagraph.add("\u00A0\u00A0\u00A0" + data);
            dataParagraph.setTextAlignment(TextAlignment.JUSTIFIED);
            dataParagraph.setMarginLeft(20);
            dataParagraph.setMarginRight(20);
            dataParagraph.setFontSize(12);
            dataParagraph.setFont(font);
            document.add(dataParagraph);
        });

        //person
        table = new Table(3);
        table.setMarginTop(30);
        table.setMarginLeft(30);
        cell = new Cell();
        paragraph = new Paragraph();
        paragraph.add(personRoll);
        paragraph.setFontSize(13);
        paragraph.setFont(font);
        paragraph.setBold();
        cell.setBorder(Border.NO_BORDER);
        cell.add(paragraph);
        cell.setWidth(270);
        cell.setHeight(90);
        cell.setTextAlignment(TextAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);

        //person QrCode
        cell = new Cell();
        cell.setBorder(Border.NO_BORDER);
        imageQrCode = generateQrCode("Jahongir Dadayev Java Backed Developer", 90, 90);
        cell.add(imageQrCode);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);

        //person name
        cell = new Cell();
        paragraph = new Paragraph();
        paragraph.add(personName);
        paragraph.setFontSize(13);
        paragraph.setFont(font);
        paragraph.setBold();
        cell.setBorder(Border.NO_BORDER);
        cell.add(paragraph);
        cell.setWidth(190);
        cell.setHeight(90);
        cell.setTextAlignment(TextAlignment.CENTER);
        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
        document.add(table);
        document.close();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"message.pdf\"");
    }

    private Image generateQrCode(String data, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", stream);
        byte[] qrCodeBytes = stream.toByteArray();
        return new Image(ImageDataFactory.create(qrCodeBytes));
    }
}


