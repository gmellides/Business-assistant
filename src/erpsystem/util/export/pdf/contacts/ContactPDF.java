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
    public boolean save_pdf(ResourceBundle default_strings,Map data){
        return true;
    }
    private void create_file(ResourceBundle rb){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle("Ονοματεπονιμο επαφισ");
            doc_info.setAuthor(rb.getString("pdf_author"));
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_root()+"/epafi.pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void fill_file(ResourceBundle rs,Map data){
        String[] stringID = {"gnr_lbl_firstname","gnr_lbl_lastname","gnr_lbl_country",
                              "gnr_lbl_zipcode","gnr_lbl_sex","gnr_lbl_address","gnr_lbl_city",
                              "gnr_lbl_state","gnr_lbl_phone1","gnr_lbl_phone2",
                              "gnr_lbl_mail","gnr_lbl_comments","gnr_lbl_website",};
        String[] mapID = {"firstname","lastname","country","zipcode",
                          "sex","address","city","greek_state","phone1",
                          "phone2","mail","comments","website"};
    }
}
