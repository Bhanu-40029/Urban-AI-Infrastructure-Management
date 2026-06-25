package Util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

    public static void generateEmergencyReport() {

        try {

            Connection con = DBConnection.getConnection();

            if(con == null) {

                System.out.println("Database Connection Failed.");

                return;
            }

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream("Emergency_Report.pdf"));

            document.open();

            Font title =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            18);

            Font heading =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            14);

            Font normal =
                    FontFactory.getFont(
                            FontFactory.HELVETICA,
                            12);

            document.add(
                    new Paragraph(
                            "URBAN AI CITY MANAGEMENT",
                            title));

            document.add(
                    new Paragraph(
                            "Emergency Analytics Report",
                            heading));

            document.add(
                    new Paragraph(
                            "Generated On : "
                            + LocalDateTime.now(),
                            normal));

            document.add(new Paragraph(" "));

            Statement stmt =
                    con.createStatement();

            ResultSet rs;

            int total = 0;
            int completed = 0;
            int pending = 0;
            int progress = 0;

            rs = stmt.executeQuery(
                    "SELECT COUNT(*) FROM emergency_requests");

            if(rs.next()) {

                total = rs.getInt(1);
            }

            rs.close();

            rs = stmt.executeQuery(
                    "SELECT COUNT(*) FROM emergency_requests WHERE status='Completed'");

            if(rs.next()) {

                completed = rs.getInt(1);
            }

            rs.close();

            rs = stmt.executeQuery(
                    "SELECT COUNT(*) FROM emergency_requests WHERE status='Pending'");

            if(rs.next()) {

                pending = rs.getInt(1);
            }

            rs.close();

            rs = stmt.executeQuery(
                    "SELECT COUNT(*) FROM emergency_requests WHERE status='In Progress'");

            if(rs.next()) {

                progress = rs.getInt(1);
            }

            rs.close();

            document.add(
                    new Paragraph(
                            "========== SUMMARY ==========",
                            heading));

            document.add(
                    new Paragraph(
                            "Total Emergency Requests : "
                                    + total,
                            normal));

            document.add(
                    new Paragraph(
                            "Completed Requests : "
                                    + completed,
                            normal));

            document.add(
                    new Paragraph(
                            "Pending Requests : "
                                    + pending,
                            normal));

            document.add(
                    new Paragraph(
                            "In Progress Requests : "
                                    + progress,
                            normal));

            document.add(new Paragraph(" "));
            document.add(
                    new Paragraph(
                            "========== EMERGENCY DETAILS ==========",
                            heading));

            document.add(new Paragraph(" "));

            PdfPTable table =
                    new PdfPTable(6);

            table.setWidthPercentage(100);

            table.addCell("Request ID");
            table.addCell("Location");
            table.addCell("Type");
            table.addCell("Priority");
            table.addCell("Status");
            table.addCell("Request Time");

            rs = stmt.executeQuery(
                    "SELECT * FROM emergency_requests");

            while(rs.next()) {

                table.addCell(
                        String.valueOf(
                                rs.getInt(
                                        "request_id")));

                table.addCell(
                        rs.getString(
                                "location"));

                table.addCell(
                        rs.getString(
                                "emergency_type"));

                table.addCell(
                        String.valueOf(
                                rs.getInt(
                                        "priority")));

                table.addCell(
                        rs.getString(
                                "status"));

                table.addCell(
                        String.valueOf(
                                rs.getTimestamp(
                                        "request_time")));
            }

            document.add(table);

            document.add(new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Report Generated Automatically",
                            heading));

            document.add(
                    new Paragraph(
                            "Urban AI City Management System",
                            normal));

            document.add(new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "----------------------------------------------"));

            document.add(
                    new Paragraph(
                            "End of Report"));
            rs.close();

            stmt.close();

            con.close();

            document.close();

            System.out.println(
                    "\n======================================");

            System.out.println(
                    " Emergency PDF Generated Successfully ");

            System.out.println(
                    " File Name : Emergency_Report.pdf");

            System.out.println(
                    "======================================");

        }

        catch(Exception e) {

            e.printStackTrace();
        }

    }

}