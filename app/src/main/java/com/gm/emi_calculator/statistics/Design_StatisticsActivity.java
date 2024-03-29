package com.gm.emi_calculator.statistics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gm.emi_calculator.C0852R2;
import com.gm.emi_calculator.Constant.Constant_CurrencyFormatDoller;
import com.gm.emi_calculator.Databasehelper.DB_Helper;
import com.gm.emi_calculator.R;
import com.gm.emi_calculator.Utility.DatePickerFragment;
import com.gm.emi_calculator.adapter.Adapter_Monthlycalculation;
import com.gm.emi_calculator.adapter.Adapter_YearlyCalculation;
import com.gm.emi_calculator.model.Model_Monthwisecalculation;
import com.gm.emi_calculator.model.Model_Yearwisecalculation;
import com.github.mikephil.charting.utils.Utils;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static com.gm.emi_calculator.Constant.Constant_CurrencyFormat.format;
import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class Design_StatisticsActivity extends AppCompatActivity {
    TextView tv_principal_amount,tv_interest_rate,tv_tenure,tv_emi,et_date;
    String currency = "₹";
    String f4146p;
    String f4140j;
    Cursor f4134L;
    String f4144n;
    String f4145o;
    String f4141k;
    String f4143m;
    String f4142l;
    String f4147q;
    double f4150t;
    double f4153w;
    double f4152v;
    double f4151u;
    SimpleDateFormat f4137O;
    static String f4122T = "₹";
    ListView list_view_monthly;

    Adapter_Monthlycalculation f4127E;
    static ArrayList<Model_Monthwisecalculation> f4120R = null;
    static ArrayList<Model_Yearwisecalculation> f4121S = null;
    HashMap<String, ArrayList<Model_Monthwisecalculation>> f4135M;
    Calendar f4138P = Calendar.getInstance();
    RadioButton rbtnmonthly;
    TextView tv_name;
    RadioButton rbtnyearly;
    Adapter_YearlyCalculation f4128F;
    DatePickerFragment f4139Q;
    Button BtnPdf;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design__statistics);
        BtnPdf = findViewById(R.id.btn_pdf);
        this.f4135M = new HashMap<>();
        this.f4137O = new SimpleDateFormat("dd/MM/yyyy");
        this.f4139Q = new DatePickerFragment();
        this.list_view_monthly = (ListView) findViewById(R.id.list_view_monthly);
        tv_principal_amount = findViewById(R.id.activity_statistics_tv_principal_amount);
        tv_interest_rate = findViewById(R.id.activity_statistics_tv_interest_rate);
        tv_tenure = findViewById(R.id.activity_statistics_tv_tenure);
        tv_emi = findViewById(R.id.activity_statistics_tv_emi);
        rbtnmonthly = findViewById(R.id.statistics_rbtn_monthly);
        rbtnyearly = findViewById(R.id.statistics_rbtn_yearly);
        tv_name = findViewById(R.id.activity_statistics_tv_name);
        et_date = findViewById(R.id.activity_statistics_et_date);

        LocalDate currentdate = LocalDate.now();

        Month currentMonth = currentdate.getMonth();
        int currentYear = currentdate.getYear();
        Format f = new SimpleDateFormat("MMM");
        String strMonth = f.format(new Date());
        et_date.setText(""+strMonth + " - " + currentYear);

        BtnPdf.setOnClickListener(new View.OnClickListener() {
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

        this.et_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Design_StatisticsActivity.this.f4139Q = new DatePickerFragment();
                Design_StatisticsActivity design_StatisticsActivity = Design_StatisticsActivity.this;
                design_StatisticsActivity.f4139Q.setDateViewObject(design_StatisticsActivity.et_date, false);
                Design_StatisticsActivity design_StatisticsActivity2 = Design_StatisticsActivity.this;
                design_StatisticsActivity2.f4139Q.show(design_StatisticsActivity2.getSupportFragmentManager(), Design_StatisticsActivity.this.getString(R.string.tag_datepicker));
            }
        });
        this.et_date.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                Design_StatisticsActivity design_StatisticsActivity = Design_StatisticsActivity.this;
                design_StatisticsActivity.f4138P = design_StatisticsActivity.f4139Q.getCalander();
                Design_StatisticsActivity design_StatisticsActivity2 = Design_StatisticsActivity.this;
                double d = design_StatisticsActivity2.f4150t;
                double d2 = design_StatisticsActivity2.f4151u;
                double d3 = design_StatisticsActivity2.f4152v;
                SimpleDateFormat simpleDateFormat = design_StatisticsActivity2.f4137O;
                Calendar calendar = design_StatisticsActivity2.f4138P;
                Design_StatisticsActivity.f4120R = design_StatisticsActivity2.scheduleMonthly(d, d2, d3, simpleDateFormat.format(Calendar.getInstance().getTime()), Design_StatisticsActivity.this.f4153w, Design_StatisticsActivity.f4122T);
                Design_StatisticsActivity design_StatisticsActivity3 = Design_StatisticsActivity.this;
                double d4 = design_StatisticsActivity3.f4150t;
                double d5 = design_StatisticsActivity3.f4151u;
                double d6 = design_StatisticsActivity3.f4152v;
                SimpleDateFormat simpleDateFormat2 = design_StatisticsActivity3.f4137O;
                Calendar calendar2 = design_StatisticsActivity3.f4138P;
                Design_StatisticsActivity.f4121S = scheduleYearly(d4, d5, d6, simpleDateFormat2.format(Calendar.getInstance().getTime()), Design_StatisticsActivity.this.f4153w, Design_StatisticsActivity.f4122T);
                Design_StatisticsActivity.this.f4127E = new Adapter_Monthlycalculation(Design_StatisticsActivity.this, Design_StatisticsActivity.f4120R);
                Design_StatisticsActivity.this.f4128F = new Adapter_YearlyCalculation(Design_StatisticsActivity.this, Design_StatisticsActivity.f4121S);
                if (Design_StatisticsActivity.this.rbtnmonthly.isChecked()) {
                    Design_StatisticsActivity.this.tv_name.setText("Month");
                    Design_StatisticsActivity.this.mo12320w();
                }
                if (Design_StatisticsActivity.this.rbtnyearly.isChecked()) {
                    Design_StatisticsActivity.this.tv_name.setText("Years");
                    Design_StatisticsActivity.this.mo12321x();
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });

        this.rbtnmonthly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (Design_StatisticsActivity.this.rbtnmonthly.isChecked()) {
                    Design_StatisticsActivity.this.tv_name.setText("Month");
                    Design_StatisticsActivity.this.mo12320w();
                }
            }
        });
        this.rbtnyearly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (Design_StatisticsActivity.this.rbtnyearly.isChecked()) {
                    Design_StatisticsActivity.this.tv_name.setText("Years");
                    Design_StatisticsActivity.this.mo12321x();
                }
            }
        });


        data();
        this.f4128F = new Adapter_YearlyCalculation(Design_StatisticsActivity.this, f4121S);
        this.f4127E = new Adapter_Monthlycalculation(Design_StatisticsActivity.this, f4120R);
        this.list_view_monthly.setAdapter(this.f4127E);



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
    private static void createTable(Section subCatPart)
            throws BadElementException {
        //PdfPTable table = new PdfPTable(4);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("Year"));
        c1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Principle"));
        c1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Interest"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Balance"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j=0;j<cells.length;j++){
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        //ArrayList<Model_Yearwisecalculation>= scheduleYearly(d4, d5, d6, simpleDateFormat2.format(Calendar.getInstance().getTime()), Design_StatisticsActivity.this.f4153w, Design_StatisticsActivity.f4122T);
        for (int i=1;i<5;i++){
        table.addCell("2021-22");
        table.addCell("1,475");
        table.addCell("413");
        table.addCell("44,525");
        }


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
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @SuppressLint("Range")
    private void data() {
        String stringExtra = getIntent().getStringExtra("LoanID");
        this.f4146p = stringExtra;
        if (stringExtra != null) {
            Cursor loanDetails = new DB_Helper(this).getLoanDetails(this.f4146p);
            this.f4134L = loanDetails;
            loanDetails.moveToFirst();
           // Log.d("LOANDETAILS",""+loanDetails);

//            if (this.f4134L.getCount() > 0) {
//                Cursor cursor = this.f4134L;
//                this.f4144n = cursor.getString(cursor.getColumnIndex("Emi"));
//                Cursor cursor2 = this.f4134L;
//                this.f4145o = cursor2.getString(cursor2.getColumnIndex("Emidate"));
//                Date date = null;
//                try {
//                    date = new SimpleDateFormat("dd/MM/yyyy").parse(this.f4145o);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
////                this.f4138P.setTime(date);
                  TextView textView = this.et_date;
                  textView.setText(theMonth(this.f4138P.getTime().getMonth()) + "-" + (this.f4138P.getTime().getYear() + R.id.accessibility_custom_action_11));

//                Cursor cursor3 = this.f4134L;
//                this.f4140j = cursor3.getString(cursor3.getColumnIndex("Amount"));
//                Cursor cursor4 = this.f4134L;
//                this.f4141k = cursor4.getString(cursor4.getColumnIndex("Interest"));
//                Cursor cursor5 = this.f4134L;
//                this.f4143m = cursor5.getString(cursor5.getColumnIndex("Tenure"));
//                Cursor cursor6 = this.f4134L;
//                this.f4142l = cursor6.getString(cursor6.getColumnIndex("Totalinterest"));
//                Cursor cursor7 = this.f4134L;
//                this.f4147q = cursor7.getString(cursor7.getColumnIndex("CurrencyType"));
//                this.f4144n = this.f4144n.replaceAll(",", "").replaceAll("\\u20B9", "").replaceAll("\\$", "");
//                this.f4140j = this.f4140j.replaceAll(",", "").replaceAll("\\u20B9", "").replaceAll("\\$", "");
//
//
//            }

        }
        else {
            this.f4147q = getIntent().getStringExtra("Currency");
            String stringExtra2 = getIntent().getStringExtra("amount");
            this.f4140j = stringExtra2;
            Log.d("Amount1", stringExtra2);
            this.f4145o = this.f4137O.format(Calendar.getInstance().getTime());
            this.f4143m = getIntent().getStringExtra("tenure");
            this.f4141k = getIntent().getStringExtra("interest");
            this.f4142l = getIntent().getStringExtra("interestAmount");
            this.f4144n = getIntent().getStringExtra("emi");
        }
        String clearFormet = clearFormet(format(Double.parseDouble(this.f4140j.replaceAll(",", ""))));
        this.f4140j = clearFormet;
        Log.d("Amount2", clearFormet);
        this.f4144n = clearFormet(this.f4144n);
        this.f4150t = Double.parseDouble(this.f4140j);
        Log.d("Amount3", this.f4150t + "");
        this.f4153w = Double.parseDouble(this.f4144n);
        this.f4152v = Double.parseDouble(this.f4143m);
        this.f4151u = Double.parseDouble(this.f4141k);
        f4120R = scheduleMonthly(this.f4150t, this.f4151u, this.f4152v, this.f4145o, this.f4153w, f4122T);
        f4121S = scheduleYearly(this.f4150t, this.f4151u, this.f4152v, this.f4145o, this.f4153w, f4122T);

        //runOnUiThread(new C1057g(this));
//        f4120R = scheduleMonthly(this.f4150t, this.f4151u, this.f4152v, this.f4145o, this.f4153w, f4122T);
//        f4121S = scheduleYearly(this.f4150t, this.f4151u, this.f4152v, this.f4145o, this.f4153w, f4122T);

        setdatavalue();


    }
    public static String theMonth(int i) {
        return new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}[i];
    }

    public ArrayList<Model_Yearwisecalculation> scheduleYearly(double d, double d2, double d3, String str, double d4, String str2) {
        String str3 = str2;
        ArrayList arrayList = new ArrayList();
        ArrayList<Model_Yearwisecalculation> arrayList2 = new ArrayList<>();
        new ArrayList();
        Object[] array = this.f4135M.keySet().toArray();
        Arrays.sort(array);
        for (Object obj : array) {
            arrayList.add(obj.toString());
        }
        double d5 = d;
        String str4 = "";
        int i = 0;
        double d6 = Utils.DOUBLE_EPSILON;
        double d7 = Utils.DOUBLE_EPSILON;
        double d8 = Utils.DOUBLE_EPSILON;
        while (i < arrayList.size()) {
            ArrayList arrayList3 = this.f4135M.get(arrayList.get(i));
            double d9 = Utils.DOUBLE_EPSILON;
            double d10 = Utils.DOUBLE_EPSILON;
            for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                d10 += getStringToDouble(((Model_Monthwisecalculation) arrayList3.get(i2)).getInterest());
                d9 += getStringToDouble(((Model_Monthwisecalculation) arrayList3.get(i2)).getPrincipal());
                str4 = ((Model_Monthwisecalculation) arrayList3.get(i2)).getFinancialYear();
            }
            double d11 = d5 - d9;
            Model_Yearwisecalculation model_Yearwisecalculation = new Model_Yearwisecalculation();
            model_Yearwisecalculation.setFinancialYear(str4);
            model_Yearwisecalculation.setBalance(Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.round(d11)), str3));
            model_Yearwisecalculation.setInterest(Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.round(d10)), str3));
            model_Yearwisecalculation.setPrincipal(Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.round(d9)), str3));
            model_Yearwisecalculation.setEmi(Constant_CurrencyFormatDoller.dollerFormat(String.valueOf(Math.round(d10 + d9)), str3));
            d6 = d6 + d9 + d10;
            model_Yearwisecalculation.setTotalPaid(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d6) + "", str3));
            d7 += d9;
            d8 += d10;
            model_Yearwisecalculation.setPrincipalPaid(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d7) + "", str3));
            model_Yearwisecalculation.setInterestPaid(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d8) + "", str3));
            model_Yearwisecalculation.setMonth((String) arrayList.get(i));
            arrayList2.add(model_Yearwisecalculation);
            i++;
            d5 = d11;
        }
        return arrayList2;
    }

    public double getStringToDouble(String str) {
        if (str.equalsIgnoreCase(".")) {
            return Utils.DOUBLE_EPSILON;
        }
        return Double.parseDouble(str.replaceAll(",", "").replaceAll("\\u20B9", "").replaceAll("\\$", ""));
    }


    private void setdatavalue()
    {

        TextView textView = this.tv_principal_amount;
        textView.setText(f4122T + " " + Constant_CurrencyFormatDoller.dollerFormat(this.f4140j, f4122T));
        TextView textView2 = this.tv_interest_rate;
        textView2.setText(this.f4141k + " %");
        this.tv_tenure.setText(this.f4143m);
        TextView textView3 = this.tv_emi;
        textView3.setText(f4122T + " " + Constant_CurrencyFormatDoller.dollerFormat(this.f4144n, f4122T));
//        TextView textView4 = this.et_date;
//        textView4.setText(theMonth(this.f4138P.getTime().getMonth()) + "-" + (this.f4138P.getTime().getYear() + C0852R2.C0855id.accessibility_custom_action_11));

    }
    public ArrayList<Model_Monthwisecalculation> scheduleMonthly(double d, double d2, double d3, String str, double d4, String str2) {
        double d5;
        Design_StatisticsActivity design_StatisticsActivity = this;
        String str3 = str2;
        design_StatisticsActivity.f4135M = new HashMap<>();
        try {
            design_StatisticsActivity.f4137O.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double d6 = d2 / 1200.0d;
        ArrayList<Model_Monthwisecalculation> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        double d7 = d4;
        ArrayList arrayList3 = arrayList2;
        String str4 = null;
        double d8 = 0.0d;
        double d9 = 0.0d;
        double d10 = 0.0d;
        double d11 = d;
        for (double d12 = Utils.DOUBLE_EPSILON; d11 > d12; d12 = Utils.DOUBLE_EPSILON) {
            Model_Monthwisecalculation model_Monthwisecalculation = new Model_Monthwisecalculation();
            double d13 = ((d6 * d11) * 100.0d) / 100.0d;
            if (d11 < d7) {
                d5 = d6;
                d7 = d11 + d13;
            } else {
                d5 = d6;
            }
            double round = (double) Math.round(d13);
            double d14 = d7 - round;
            double d15 = d11 - d14;
            model_Monthwisecalculation.setDate(design_StatisticsActivity.f4138P.getTime());
            if (str4 == null) {
                str4 = getFinancialYear();
            }
            model_Monthwisecalculation.setFinancialYear(getFinancialYear());
            StringBuilder sb = new StringBuilder();
            ArrayList<Model_Monthwisecalculation> arrayList4 = arrayList;
            sb.append(Math.round(d15));
            sb.append("");
            model_Monthwisecalculation.setBalance(Constant_CurrencyFormatDoller.dollerFormat(sb.toString(), str3));
            StringBuilder sb2 = new StringBuilder();
            double d16 = d15;
            sb2.append(Math.round(round));
            sb2.append("");
            model_Monthwisecalculation.setInterest(Constant_CurrencyFormatDoller.dollerFormat(sb2.toString(), str3));
            model_Monthwisecalculation.setPrincipal(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d14) + "", str3));
            model_Monthwisecalculation.setEmi(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d7) + "", str3));
            d8 = d8 + d14 + round;
            model_Monthwisecalculation.setTotalPaid(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d8) + "", str3));
            d9 += d14;
            d10 += round;
            model_Monthwisecalculation.setPrincipalPaid(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d9) + "", str3));
            model_Monthwisecalculation.setInterestPaid(Constant_CurrencyFormatDoller.dollerFormat(Math.round(d10) + "", str3));
            if (!str4.equalsIgnoreCase(getFinancialYear())) {
                design_StatisticsActivity = this;
                design_StatisticsActivity.f4135M.put(str4, arrayList3);
                arrayList3 = new ArrayList();
                str4 = getFinancialYear();
            } else {
                design_StatisticsActivity = this;
                if (Math.round(d16) == 0) {
                    design_StatisticsActivity.f4135M.put(getFinancialYear(), arrayList3);
                }
            }
            arrayList = arrayList4;
            arrayList.add(model_Monthwisecalculation);
            arrayList3.add(model_Monthwisecalculation);
            d11 = Math.round(d16) == 0 ? -1.0d : d16;
            design_StatisticsActivity.f4138P.add(2, 1);
            d6 = d5;
        }
        return arrayList;
    }
    public String getFinancialYear() {
        String str;
        int year = this.f4138P.getTime().getYear() + C0852R2.accessibility_custom_action_11;
        int month = this.f4138P.getTime().getMonth();
        if (this.f4147q.equalsIgnoreCase("$")) {
            return year + "";
        }
        if (month < 4) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(year - 1);
            sb.append("-");
            sb.append(year);
            str = sb.toString();
        } else {
            str = "" + year + "-" + (year + 1);
        }
        return str.substring(0, 5) + "" + str.substring(7);
    }
    public void mo12320w() {
        this.list_view_monthly.setAdapter(this.f4127E);
        this.f4127E.notifyDataSetChanged();
    }
    public void mo12321x() {
        this.list_view_monthly.setAdapter(this.f4128F);
        this.f4128F.notifyDataSetChanged();
    }



    public String clearFormet(String str) {
        return str.toString().replaceAll("[^\\d.]+", "");
    }

}