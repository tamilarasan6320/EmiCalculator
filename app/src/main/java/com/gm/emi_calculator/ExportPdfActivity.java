package com.gm.emi_calculator;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ExportPdfActivity extends AppCompatActivity {
    Button ExportPdf;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_pdf);

        ExportPdf  = findViewById(R.id.exportpdf);
        ExportPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void createPdf() throws FileNotFoundException, DocumentException {

        File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "pdfdemo");
        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();
            Log.i(LOG_TAG, "Pdf Directory created");
        }

        //Create time stamp
        Date date = new Date() ;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

        File myFile = new File(pdfFolder + timeStamp + ".pdf");

        OutputStream output = new FileOutputStream(myFile);

        //Step 1
        Document document = new Document();


        //Step 2
        PdfWriter.getInstance(document, output);


        //Step 3
        document.open();

        //Step 4 Add content
        addContent(document);

        //Step 5: Close the document
        document.close();

    }
    private static void addContent(Document document) throws DocumentException {

        Anchor anchor = new Anchor("Loan Repayment Schedule", catFont);
        anchor.setName("Loan Repayment Schedule");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor),0);

        Paragraph subPara = new Paragraph("Emi 944 for loan amount 50,000 @ 5% for 60 Months", subFont);
        Section subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Hello"));
//        subCatPart.add(new Paragraph("Paragraph 1"));
//        subCatPart.add(new Paragraph("Paragraph 2"));
//        subCatPart.add(new Paragraph("Paragraph 3"));

        // add a list
        //createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 2);
        subCatPart.add(paragraph);

        // add a table
        createTable(subCatPart);

        // now add all this to the document
        document.add(catPart);

        // Next section
//        anchor = new Anchor("Second Chapter", catFont);
//        anchor.setName("Second Chapter");
//
//        // Second parameter is the number of the chapter
//        catPart = new Chapter(new Paragraph(anchor), 1);
//
//        subPara = new Paragraph("Subcategory", subFont);
//        subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("This is a very important message"));
//
//        // now add all this to the document
//        document.add(catPart);

    }
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    private static void createList(Section subCatPart) {
        com.itextpdf.text.List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }
    private static void createTable(Section subCatPart)
            throws BadElementException {
        //PdfPTable table = new PdfPTable(4);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

//        PdfPCell c1 = new PdfPCell(new Phrase("Year"));
//        c1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Principle"));
//        c1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Interest"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Balance"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//        table.setHeaderRows(1);

        PdfPTable table = new PdfPTable(new float[] { 2, 1, 2 });
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Name");
        table.addCell("Age");
        table.addCell("Location");
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j=0;j<cells.length;j++){
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (int i=1;i<5;i++){
            table.addCell("Name:"+i);
            table.addCell("Age:"+i);
            table.addCell("Location:"+i);
        }

//        table.addCell("2021-22");
//        table.addCell("1,475");
//        table.addCell("413");
//        table.addCell("44,525");
//        table.addCell("2021-23");
//        table.addCell("1,475");
//        table.addCell("413");
//        table.addCell("24,525");
//        table.addCell("2021-24");
//        table.addCell("1,475");
//        table.addCell("413");
//        table.addCell("48,525");

        subCatPart.add(table);

    }
    public static ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<String>();
        String[] tableTitleList = {" Title", " (Re)set", " Obs", " Mean", " Std.Dev", " Min", " Max", "Unit"};
        data.addAll(Arrays.asList(tableTitleList));
        for (int i = 0; i < 10; ) {
            ArrayList<String> dataLine = new ArrayList<String>();
            i++;
            for (int j = 0; j < tableTitleList.length; j++) {
                dataLine.add(tableTitleList[j] + " " + i);
            }
            data.addAll(dataLine);
        }
        return data;
    }

}