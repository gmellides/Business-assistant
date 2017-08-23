/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.admin_data;

import erpsystem.entities.corpotations.BusinessAdmin;
import erpsystem.util.system.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class AdminCard {
    public boolean save_card(ResourceBundle bundle,BusinessAdmin data){
            create_file(bundle);
            fill_file(bundle,data);
        return true;
    }
    
    private boolean create_file(ResourceBundle bundle){
        boolean flag = false;
         try{
                PDDocument  doc = new PDDocument();
                PDPage page = new PDPage();
                PDDocumentInformation doc_info = new PDDocumentInformation();
                // Set Doc Info 
                doc_info.setTitle(bundle.getString("filename_adminCard_pdf"));
                doc_info.setAuthor(bundle.getString("pdf_author"));
                // add a new page
                doc.addPage(page);
                doc.save(new FileManager().getDocuments_business_data()+"/"+bundle.getString("filename_adminCard_pdf")+".pdf");
                doc.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        return flag;
    }
    private boolean fill_file(ResourceBundle bundle,BusinessAdmin data){
        boolean flag = false;
            File pdf_file = new File(new FileManager().getDocuments_business_data()+"/"+bundle.getString("filename_adminCard_pdf")+".pdf");
            try{
            PDDocument pdf_doc = PDDocument.load(pdf_file);
            PDPage doc_page = pdf_doc.getPage(0);
            PDPageContentStream content = new PDPageContentStream(pdf_doc,doc_page);
            PDFont font = PDType0Font.load(pdf_doc,new File("resources/fonts/pdf/Roboto-Regular.ttf"));
            // Add Rectabgle for the card
                content.addRect(20, 600, 270, 150);
                content.closeAndStroke();
                // Text to pdf
                // -- pdf title
               
                content.beginText();
                content.setFont(font, 19);
                content.newLineAtOffset(50,720);
                content.showText(data.getFirstName()+" "+data.getLastName());
                content.endText();
                // Description Text
                content.beginText();
                content.setFont(font, 9);
                content.newLineAtOffset(50,695);
                content.showText(data.getDescription());
                content.endText();
                // Address Text
                content.beginText();
                content.setFont(font, 9);
                content.newLineAtOffset(55,660);
                content.showText(data.getAddress()+","+data.getZipCode()+","+data.getCity());
                content.endText();
                // Phone 1
                content.beginText();
                content.setFont(font, 9);
                content.newLineAtOffset(35,640);
                content.showText(bundle.getString("gnr_lbl_phone1")+data.getPhone1());
                content.endText();
                // Phone 2
                content.beginText();
                content.setFont(font, 9);
                content.newLineAtOffset(150,640);
                content.showText(bundle.getString("gnr_lbl_phone2")+data.getPhone2());
                content.endText();
                
            
                // Mail
                content.beginText();
                content.setFont(font, 9);
                content.newLineAtOffset(35,620);
                content.showText(bundle.getString("gnr_lbl_mail")+data.getMail());
                content.endText();
                content.close();
                pdf_doc.save(pdf_file);
        }catch(IOException|IllegalArgumentException e){
            e.printStackTrace();
        }
        return flag;
    }
}
