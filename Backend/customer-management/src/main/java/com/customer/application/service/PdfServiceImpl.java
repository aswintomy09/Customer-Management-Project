package com.customer.application.service;

import com.customer.application.entity.Customer;
import com.customer.application.entity.Order;
import com.customer.application.exception.PDFException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.awt.Color.*;

@Service
@Slf4j
public class PdfServiceImpl implements PdfService{

    @Autowired
    private CustomerService customerService;

    public byte[] downloadPdf(Long customerId) {
        log.info("Downloading PDF for customer: {}", customerId);
        Customer customer = customerService.getCustomerById(customerId);

        // Define the output file path
        String invoiceFilePath = "pdfTemplate" + ".pdf";

        // Create the invoice PDF
        createInvoicePdf(customer, invoiceFilePath);
        log.info("Creating the invoice PDF");

        log.info("Reading the PDF into byte array");
        Path pdfPath = Paths.get(invoiceFilePath);
        try {
            return Files.readAllBytes(pdfPath);
        } catch (IOException e) {
            log.error("Could not read the PDF file.", e);
            throw new PDFException("Could not read the PDF file.", e);
        }
    }

    public void createInvoicePdf(Customer customer, String filePath) {
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
            Font font = new Font(bf, 12, Font.NORMAL, BLUE);
            Font fontInvoice = new Font(bf, 18, Font.NORMAL);
            PdfPTable titleTable = new PdfPTable(2); // 2 columns
            titleTable.setWidthPercentage(70);

            // Column for Logo
            Image logo = Image.getInstance("src/main/resources/logo.png");
            logo.scaleToFit(50, 50);

            PdfPCell logoCell = new PdfPCell(logo, false);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setVerticalAlignment(Element.ALIGN_RIGHT);
            titleTable.addCell(logoCell);

            // Column for Application Name
            Font appNameFont = new Font(Font.HELVETICA, 24, Font.BOLD);
            Paragraph appName = new Paragraph("Shopify", appNameFont);
            PdfPCell appNameCell = new PdfPCell();
            appNameCell.addElement(appName);
            appNameCell.setBorder(Rectangle.NO_BORDER);
            appNameCell.setVerticalAlignment(Element.ALIGN_LEFT);
            titleTable.addCell(appNameCell);

            // Adding customer details
            Paragraph title = new Paragraph("Customer Invoice", fontInvoice);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(titleTable);

            font.setColor(BLACK);
            Paragraph customerDetails = new Paragraph("Customer Name: " + customer.getFirstname() + " " + customer.getLastname(), font);
            customerDetails.setSpacingAfter(20);
            document.add(title);
            document.add(customerDetails);



            // Create a table
            PdfPTable table = new PdfPTable(2); // Assuming Order has 3 fields
            table.setWidthPercentage(80); // make the table 80% of the page width
            table.setSpacingBefore(20); // add a space before the table
            table.setSpacingAfter(20); // and after the table

            // Create a cell object for common settings
            PdfPCell cell;
            Font tableHeaderFont = new Font(bf, 12, Font.BOLD, WHITE);

            // First Header
            cell = new PdfPCell(new Phrase("Item", tableHeaderFont));
            cell.setBackgroundColor(BLUE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // Second Header
            cell = new PdfPCell(new Phrase("Price", tableHeaderFont));
            cell.setBackgroundColor(BLUE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // Adding rows for each Order
            for (Order order : customer.getOrders()) {
                table.addCell(order.getItem());

                cell = new PdfPCell(new Phrase(order.getPrice().toString(), font));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            document.add(table);
            document.add(new Paragraph("Total: " + customer.getOrderTotal()));

        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF", e);
            throw new PDFException("Error generating PDF", (IOException) e);
        }
    }
}
