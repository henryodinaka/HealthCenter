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
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import health.center.model.Payment;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Kelechi
 */
public class PdfWriterClass {

    private final Payment payment;
    private final String path = System.getProperty("user.home") + "\\Payments\\";

    public PdfWriterClass(Payment payment) {
        this.payment = payment;
        try {
            Path paths = Paths.get(path);
            if (Files.notExists(paths)) {
                Files.createDirectories(paths);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile() throws IOException {
        PdfDocument doc = new PdfDocument(new PdfWriter("D:\\payment.pdf"));
        PdfFont boldfont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont normalfont = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        try (Document document = new Document(doc)) {
            document.add(new Paragraph().add("GOVERNMENT OF RIVERS STATE OF NIGERIA")
                    .setFont(boldfont).setFontSize(20));

            document.add(new Paragraph().add("(RIVERS STATE PRIMARY HEALTH CARE MANAGEMENT BOARD)")
                    .setFont(boldfont).setFontSize(13));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Table(2).setWidth(523)
                    .addCell(new Cell().setBorder(Border.NO_BORDER).setWidth(200)
                            .add(new Paragraph("PAYMENT VOUCHER").setFont(boldfont).setFontSize(20)))
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Table(3).setHorizontalAlignment(HorizontalAlignment.RIGHT)
                                    .setWidth(250)
                                    .setHeight(50)
                                    .addHeaderCell(new Cell().add(new Paragraph("Station").setFont(boldfont).setTextAlignment(TextAlignment.CENTER)))
                                    .addHeaderCell(new Cell().add(new Paragraph("Month").setFont(boldfont).setTextAlignment(TextAlignment.CENTER)))
                                    .addHeaderCell(new Cell().add(new Paragraph("P.V.No").setFont(boldfont).setTextAlignment(TextAlignment.CENTER)))
                                    .addCell("Station")
                                    .addCell("July")
                                    .addCell("SC1234"))
                            .setFontSize(12)));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Paragraph().add("Header:.....................................................................................")
                    .setFont(normalfont).setFontSize(12));

            document.add(new Paragraph().add("Dr to:......................................................................................")
                    .setFont(normalfont).setFontSize(12));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Table(4).setWidth(523)
                    .addHeaderCell(new Cell().add(new Paragraph("Date").setFont(boldfont).setTextAlignment(TextAlignment.CENTER)))
                    .addHeaderCell(new Cell().add(new Paragraph("Detailed Description Of Service or Article").setFont(boldfont).setTextAlignment(TextAlignment.CENTER)))
                    .addHeaderCell(new Cell().add(new Paragraph("Rate").setFont(boldfont).setTextAlignment(TextAlignment.CENTER)))
                    .addHeaderCell(new Cell().add(new Paragraph("Amount").setFont(boldfont).setTextAlignment(TextAlignment.CENTER)))
                    .addCell(new Cell().add(new Paragraph(" ")))
                    .addCell(new Cell().add(new Paragraph("Health care ")))
                    .addCell(new Cell().add(new Paragraph(" ")))
                    .addCell(new Cell().add(new Paragraph(" ")))
                    .addCell(new Cell().add(new Paragraph("Finacial Authority")))
                    .addCell(new Cell().add(new Paragraph("Amount")))
                    .addCell(new Cell().add(new Paragraph(" ")))
                    .addCell(new Cell().add(new Paragraph(" ")))
                    .setFontSize(12));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Paragraph().add("I CERTIFY THAT the above account is correct an was incurred under the authority quoted, and the service have been duly performed, and that the rate/price is according to regulation or fair and reasonable and that the amount of can be paid under the sub-head quoted ")
                    .setFont(normalfont).setFontSize(12));

            document.add(new Paragraph(new Text("\n")));
            
            document.add(new Table(2).setWidth(523)
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Paragraph().add("Place: .....................................................")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Paragraph().add("Signature: "))
                            .add(new Paragraph().add(new Text("\n")))
                            .add(new Paragraph().add("Name: "))
                            .add(new Paragraph().add(new Text("\n")))
                            .add(new Paragraph().add("Title: "))
                            .add(new Paragraph().add(new Text("\n")))
                            .add(new Paragraph().add("Officer Controlling Experience"))));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Paragraph().add("RECEIVED this ..............day of .........20.......in payment of the above, the sum of ................. ")
                    .setFont(normalfont).setFontSize(12));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Table(2).setWidth(523)
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Paragraph().add("........................................................"))
                            .add(new Paragraph().add("Witness to mark.")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Paragraph().add("........................................................"))
                            .add(new Paragraph().add("Signature of the Receiver."))));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Paragraph().add("*The certificate must be made to apply to the circumstances of the payment.")
                    .setFont(normalfont).setFontSize(12));
        }
    }

    public static void main(String[] args) {
        try {
            Payment payment = new Payment();
            new PdfWriterClass(payment).writeToFile();
        } catch (IOException e) {
            System.err.println("System Error: " + e.getMessage());
        }
    }
}
