/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health.center.utils;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.io.IOException;

/**
 *
 * @author Kelechi
 */
public class PdfWriterClass {

    public PdfWriterClass() {
        System.setProperty("file.home", "D:\\file.pdf");
    }

    public void writeToFile() throws IOException {
        PdfDocument doc = new PdfDocument(new PdfWriter(System.getProperty("file.home")));
        PdfFont boldfont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
        PdfFont normalfont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
        try (Document document = new Document(doc)) {
            document.add(new Table(2)
                    .setWidth(523)
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Paragraph("Voucher No.:")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Table(2).setHorizontalAlignment(HorizontalAlignment.RIGHT)
                            .setWidth(250)
                            .setHeight(50)
                            .addHeaderCell("Month")
                            .addHeaderCell("P.V No.")
                            .addCell("")
                            .addCell("")))
                    .setFont(normalfont).setFontSize(12)
            ).add(new Paragraph(new Text("\n"))
            ).add(new Paragraph().add("GOVERNMENT OF RIVERS STATE OF NIGERIA")
                    .setFont(boldfont).setFontSize(15)
                    .setTextAlignment(TextAlignment.CENTER)
            ).add(new Paragraph().add("(RIVERS STATE PRIMARY HEALTH CARE MANAGEMENT BOARD)")
                    .setFont(boldfont).setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER)
            ).add(new Paragraph(new Text("\n"))
            ).add(new Table(3).addHeaderCell("Date")
                    .addHeaderCell("Purpose Of Payment")
                    .addHeaderCell("Amount")
                    .addCell(new Cell())
                    .addCell(new Cell())
                    .addCell(new Cell())
                    .setWidth(523)
                    .setHeight(250)
                    .setFont(normalfont).setFontSize(12)
            ).add(new Paragraph(new Text("\n"))
            ).add(new Paragraph().add("Amount in words: ........................................................................"
                    + "........................................................................................................"
                    + "........................................................................................................"
                    + "........................................................................................................"
                    + "........................................................................................................")
                    .setFont(normalfont).setFontSize(12)
            ).add(new Paragraph(new Text("\n"))
            ).add(new Paragraph().add("Bank:..................................................................................")
                    .setFont(normalfont).setFontSize(12)
            ).add(new Paragraph(new Text("\n"))
            ).add(new Paragraph().add("Company:...............................................................................")
                    .setFont(normalfont).setFontSize(12)
            ).add(new Paragraph(new Text("\n"))
            ).add(new Paragraph().add("Name:..................................................................................")
                    .setFont(normalfont).setFontSize(12)
            ).add(new Paragraph(new Text("\n"))
            ).add(new Paragraph().add("Signature:.............................................................................")
                    .setFont(normalfont).setFontSize(12)
            );
        }
    }

    public static void main(String[] args) {
        try {
            new PdfWriterClass().writeToFile();
        } catch (IOException e) {
            System.err.println("System Error: " + e.getMessage());
        }
    }
}