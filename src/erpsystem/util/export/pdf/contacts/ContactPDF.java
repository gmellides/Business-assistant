/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.contacts;

import erpsystem.util.system.FileManager;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public class ContactPDF {
    public boolean save_pdf(ResourceBundle default_strings){
        return true;
    }
    private void create_file(ResourceBundle rs){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle("Ονοματεπονιμο επαφισ");
            doc_info.setAuthor("Business Assistant");
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_root()+"/epafi.pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void fill_file(ResourceBundle rs,Map data){
        String[] stringID = {};
    }
}
