/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.business_data;

import erpsystem.entities.business.Business;
import erpsystem.util.datetime.DateTimeProvider;
import erpsystem.util.system.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class BusinessPDF {
    private FileManager workspace;
    private DateTimeProvider date_time;
    private PDDocument doc;
    private String file_name;
    
    public boolean save_pdf(ResourceBundle bundle,Business data){
        boolean flag = false;
            create_file();
            fill_file(bundle,data);
        return flag;
    }
    
    public void create_file(){
        workspace = new FileManager();
        try{
            doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            
            // Set Doc Info 
            doc_info.setTitle("Στοιχεία Επιχείρησης");
            doc_info.setAuthor("ERP SYSTEM NAME");
            // add a new page
            doc.addPage(page);
            file_name = Pdf_name();
            doc.save(workspace.getDocuments_business_data()+"/"+file_name);
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    };
    public void fill_file(ResourceBundle bundle,Business input){
        File pdf_file = new File(workspace.getDocuments_business_data()+"/"+file_name);
        try{
            PDDocument pdf_doc = PDDocument.load(pdf_file);
            PDPage doc_page = pdf_doc.getPage(0);
            PDPageContentStream content = new PDPageContentStream(pdf_doc,doc_page);
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 24);
            content.newLineAtOffset(25, 500);
                content.showText("Γεια σας");
            /*
                content.showText(bundle.getString("edt_bus_businessname")+"  "+input.getBusiness_Name());
                content.newLine();
                content.showText(bundle.getString("edt_bus_description")+"  "+input.getBusiness_Description());
                content.newLine();
                content.showText(bundle.getString("edt_bus_taxreg")+"  "+input.getBusiness_TaxReg());
                content.newLine();
                content.showText(bundle.getString("lbl_address")+"  "+input.getBusiness_Address());
                content.newLine();
                content.showText(bundle.getString("lbl_city")+"  "+input.getBusiness_City());
                content.newLine();
                content.showText(bundle.getString("lbl_phone")+"  "+input.getBusiness_Phone());
                content.newLine();
                content.showText(bundle.getString("lbl_fax")+"  "+input.getBusiness_Fax());
                content.newLine();
                content.showText(bundle.getString("lbl_mail")+"  "+input.getBusiness_Mail());
                */
                content.endText();
            content.close();
            pdf_doc.save(pdf_file);
        }catch(IOException e){
            e.printStackTrace();
        }
    };

    
    public String Pdf_name(){
        date_time = new DateTimeProvider();
        return "Στοιχεία Επιχείρησης-"+date_time.GetDateTime_file()+".pdf";
    }
}
