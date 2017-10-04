/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.sales;

import erpsystem.util.datetime.DateTimeProvider;
import erpsystem.util.system.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class ReceiptPDF {
    
    PDFont roboto_reg,roboto_bold,roboto_italic;
    ResourceBundle bundle;
    String file_name;
    
    public void save_receipt(ResourceBundle default_strings){
       bundle = default_strings;
       file_name = "apodeiksei agoras - "+new DateTimeProvider().GetDateTime();
       create_file();
       fill_file();
    }
    private void create_file(){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle("apodeiksi agorias - date");
            doc_info.setAuthor(bundle.getString("pdf_author"));
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_root()+"/"+file_name+".pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void fill_file(){
        File pdf_file = new File(new FileManager().getDocuments_root()+"/"+file_name+".pdf");
    }
}
