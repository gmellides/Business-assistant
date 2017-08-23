/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.suppliers;

import erpsystem.util.system.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class SupplierPDF {
    
    public boolean save_pdf(ResourceBundle bundle,Map data){
            create_file(bundle,data);
            fill_file(bundle,data);
        return true;
    }
    private void create_file(ResourceBundle rb,Map data){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle(data.get("firstname")+" "+data.get("lastname"));
            doc_info.setAuthor(rb.getString("pdf_author"));
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_business_data()+"/"+data.get("firstname")+" "+data.get("lastname")+".pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void fill_file(ResourceBundle bundle,Map data){
        String[] stringID = {"lbl_firstname","lbl_lastname","lbl_sex","lbl_address",
        "lbl_zipcode","lbl_country","lbl_state","lbl_supplierType","lbl_city","lbl_phone",
        "lbl_mail","lbl_fax","lbl_bankName","lbl_IBAN","lbl_date"};
        String[] mapID = {"firstname","lastname","sex","address","zipcode","country",
        "state","supplier_type","city","phone","mail","fax","bank","iban","import_date"};
        File pdf_file = new File(new FileManager().getDocuments_business_data()+"/"+data.get("firstname")+" "+data.get("lastname")+".pdf");
        try{
            PDDocument pdf_doc = PDDocument.load(pdf_file);
            PDPage doc_page = pdf_doc.getPage(0);
            PDPageContentStream content = new PDPageContentStream(pdf_doc,doc_page);
            PDFont font = PDType0Font.load(pdf_doc,new File("resources/fonts/pdf/Roboto-Regular.ttf"));
            // Add Rectabgle to page
                content.addRect(20, 20, 570, 750);
                content.closeAndStroke();
                // Text to pdf
                // -- pdf title
                content.beginText();
                content.setFont(font, 28);
                content.newLineAtOffset(190,680);
                content.showText(bundle.getString(""));
                content.endText();
                // data to pdf
                int x = 65;
                int y = 600;
                    int index = 0;
                    for (String item : stringID){
                        //prints the intro text
                        content.beginText();
                        content.setFont(font, 16);
                        content.newLineAtOffset(x,y);
                        content.showText(bundle.getString(item)+" "+data.get(mapID[index]));
                        content.endText();
                        y -= 35;                     
                        index++;
                    }
                content.close();
                pdf_doc.save(pdf_file);
        }catch(IOException|IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
