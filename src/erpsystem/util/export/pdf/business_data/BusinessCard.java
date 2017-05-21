package erpsystem.util.export.pdf.business_data;

import erpsystem.entities.business.Business;
import erpsystem.util.system.FileManager;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class BusinessCard {
    private FileManager location;
    private PDDocument doc;
    
    public boolean cardExist(){
        boolean flag = false;
           File card = new File(CardPath);
           if (card.exists()){
               card.delete();
               flag = true;
           }
        return flag;
    }
    
    public void create_file() throws IOException{
        if (cardExist()){
            doc = new PDDocument();
                PDPage new_page = new PDPage();
                doc.addPage(new_page);
                PDDocumentInformation pdf_info = doc.getDocumentInformation();
                pdf_info.setTitle("Κάρτα Επιχείρησης");
                pdf_info.setAuthor("apptitle");
                Calendar e = Calendar.getInstance();
                pdf_info.setCreationDate(e);
                pdf_info.setSubject("Αυτή η Κάρτα δημιουργήθικε απο το προγραμμα και ειναι ετοιμη για εκτυπωση.");
            doc.save(CardPath);
            doc.close();
        }
    }
    
    public void create_card(Business data){
        try{
            create_file();
                File pdf = new File(CardPath);
                PDPage page = doc.getPage(0);
                PDPageContentStream pdf_content = new PDPageContentStream(doc,page);
                // Header
                pdf_content.beginText();
                pdf_content.setFont(PDType1Font.HELVETICA, 24);
                pdf_content.newLineAtOffset(25, 700);
                pdf_content.showText(data.getBusiness_Name());
                pdf_content.endText();
                // card layout
                pdf_content.setNonStrokingColor(Color.yellow);
                pdf_content.addRect(170, 520, 300, 150);
                pdf_content.fill();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private String CardPath = location.getDocuments_business_data()+"/Κάρτα Επιχείρησεις.pdf";
}
