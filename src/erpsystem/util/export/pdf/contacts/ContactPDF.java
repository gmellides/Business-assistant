/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.contacts;

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
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ContactPDF {
    public boolean save_pdf(ResourceBundle bundle,Map data){
          boolean flag = false;
            create_file(bundle,data);
            fill_file(bundle,data);
            flag = true;
        return flag;
    }
    private void create_file(ResourceBundle rb,Map data){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle(data.get("firstname")+" "+data.get("lastname"));
            doc_info.setAuthor(rb.getString("pdf_author"));
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_root()+"/"+data.get("firstname")+" "+data.get("lastname")+".pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void fill_file(ResourceBundle bundle,Map data){
        String[] stringID = {"gnr_lbl_firstname","gnr_lbl_lastname","gnr_lbl_country",
                             "gnr_lbl_zipcode","gnr_lbl_sex","gnr_lbl_address","gnr_lbl_city",
                             "gnr_lbl_state","gnr_lbl_phone1","gnr_lbl_phone2",
                             "gnr_lbl_mail","gnr_lbl_comments","gnr_lbl_website",};
        String[] mapID = {"firstname","lastname","country","zipcode",
                          "sex","address","city","greek_state","phone1",
                          "phone2","mail","comments","website"};
        File pdf_file = new File(new FileManager().getDocuments_root()+"/"+data.get("firstname")+" "+data.get("lastname")+".pdf");
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
                    content.newLineAtOffset(180,680);
                    content.showText(bundle.getString("lbl_viewContactWin"));
                    content.endText();
                // Contact Icon
                    PDImageXObject contacts_icon = PDImageXObject.createFromFile("resources/images/contacts/view_contact.png", pdf_doc);   
                    content.drawImage(contacts_icon, 55, 650,100,100);
                // Contact Data.
                    int x = 65;
                    int y = 590;
                        int index = 0;
                        for (String item : stringID){
                            //prints the intro text
                            content.beginText();
                            content.setFont(font, 16);
                            content.newLineAtOffset(x,y);
                            content.showText(bundle.getString(item)+"     "+data.get(mapID[index]));
                            content.endText();
                            if (item.equals("comments")){
                                y -= 70;
                            }else{
                                y -= 35;
                            }
                            index++;
                        }
                content.close();
                pdf_doc.save(pdf_file);
        }catch(IOException|IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
