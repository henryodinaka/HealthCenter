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
    private final String[] months = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
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
        PdfDocument doc = new PdfDocument(new PdfWriter(path + payment.getReceipt()));
        PdfFont boldfont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont normalfont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        final int year = payment.getMonth().getYear() + 1900;
        
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
                                    .addCell(months[payment.getMonth().getMonth()])
                                    .addCell(payment.getPaymentVoucherNum()))
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
                    .addCell(new Cell().add(new Paragraph(payment.getMonth().getDay() + " " + months[payment.getMonth().getMonth()] + ", " + year)))
                    .addCell(new Cell().add(new Paragraph(payment.getPurposeOfPayment())))
                    .addCell(new Cell().add(new Paragraph("NAIRA")))
                    .addCell(new Cell().add(new Paragraph(((Double)payment.getAmount()).toString())))
                    .addCell(new Cell().add(new Paragraph("Finacial Authority: " + payment.getBank())))
                    .addCell(new Cell().add(new Paragraph("TOTAL: " + ((Double)payment.getAmount()).toString())))
                    .addCell(new Cell().add(new Paragraph(" ")))
                    .addCell(new Cell().add(new Paragraph(" ")))
                    .setFontSize(12));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Paragraph().add("I CERTIFY THAT the above account is correct an was incurred under the authority quoted, and the service have been duly performed, and that the rate/price is according to regulation or fair and reasonable and that the amount of " + payment.getAmountInWords().toUpperCase() + " can be paid under the sub-head quoted ")
                    .setFont(normalfont).setFontSize(12));

            document.add(new Paragraph(new Text("\n")));
            
            document.add(new Table(2).setWidth(523)
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Paragraph().add("Place: .....................................................")))
                    .addCell(new Cell().setBorder(Border.NO_BORDER)
                            .add(new Paragraph().add("Signature: " + payment.isSignature()))
                            .add(new Paragraph().add(new Text("\n")))
                            .add(new Paragraph().add("Name: " + payment.getFullName().toUpperCase()))
                            .add(new Paragraph().add(new Text("\n")))
                            .add(new Paragraph().add("Title: " + payment.getTitle().toUpperCase()))
                            .add(new Paragraph().add(new Text("\n")))
                            .add(new Paragraph().add("Officer Controlling Experience"))));

            document.add(new Paragraph(new Text("\n")));

            document.add(new Paragraph().add("RECEIVED this " + payment.getMonth().getDay() + " day of " + months[payment.getMonth().getMonth()] + ", " + year + " in payment of the above, the sum of " + payment.getAmountInWords().toUpperCase())
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
}
