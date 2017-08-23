package erpsystem.util.export.pdf.contacts;

import erpsystem.util.datetime.DateTimeProvider;
import erpsystem.util.system.FileManager;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ContactsTablePDF {
    private FileManager workspace;
    private String file_name;
    PDFont roboto_reg,roboto_bold,roboto_italic;
    public boolean save_file(ResourceBundle bundle,ObservableList<Map> input){
        file_name = bundle.getString("filename_contactTable_pdf");
        create_file(bundle);
        fill_file(bundle,input);
        return true;
    }
    
        private void create_file(ResourceBundle bundle){
            try{
                PDDocument doc = new PDDocument();
                PDPage page = new PDPage();
                PDDocumentInformation doc_info = new PDDocumentInformation();
                doc_info.setTitle("Contacts");
                doc_info.setAuthor(bundle.getString("pdf_author"));
                doc.addPage(page);
                doc.save(new FileManager().getDocuments_root()+"/"+file_name+".pdf");
                doc.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        private void fill_file(ResourceBundle bundle,ObservableList<Map> input){
            File pdf_file = new File(new FileManager().getDocuments_root()+"/"+file_name+".pdf");
            try{
                PDDocument pdf_doc = PDDocument.load(pdf_file);
                PDPage doc_page = pdf_doc.getPage(0);
                PDPageContentStream content = new PDPageContentStream(pdf_doc,doc_page);
                // Regular font.
                roboto_reg = PDType0Font.load(pdf_doc,new File("resources/fonts/pdf/Roboto-Regular.ttf"));
                // Bold 
                roboto_bold = PDType0Font.load(pdf_doc, new File("resources/fonts/pdf/Roboto-Bold.ttf"));
                // Italic 
                roboto_italic = PDType0Font.load(pdf_doc, new File("resources/fonts/pdf/Roboto-Italic.ttf"));
                
                PDImageXObject contacts_icon = PDImageXObject.createFromFile("resources/images/contacts/contact_manager.png", pdf_doc);   
                content.drawImage(contacts_icon, 55, 700,90,90);
                // Pdf Info 
                content.setFont(roboto_reg,21);
                content.beginText();
                content.newLineAtOffset(200, 750);
                content.showText(file_name);
                content.endText();
                
                content.setFont(roboto_reg,9);
                content.beginText();
                content.newLineAtOffset(55, 685);
                content.showText(bundle.getString("pdf_table_date")+" "+new DateTimeProvider().GetDateTime());
                content.endText();
                
                content.setFont(roboto_reg,9);
                content.beginText();
                content.newLineAtOffset(480, 685);
                content.showText(bundle.getString("pdf_records")+input.size());
                content.endText();
                
                // Header bold 
                create_header(content,bundle);
                // Contents italic 
                create_rows(content,input);
                
                content.close();
                pdf_doc.save(pdf_file);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    private void create_header(PDPageContentStream content,ResourceBundle bundle) throws IOException{
        String[] headerID = {"col_firstname","col_lastname","col_sex","col_address","col_zipcode",
                             "col_city","col_mail","col_phone1","col_phone2"};
            content.setFont(roboto_bold, 9);
            int x = 55;
            for (String item : headerID){
                content.beginText();
                content.newLineAtOffset(x,655);
                content.showText(bundle.getString(item));
                switch(item){
                    default:
                        x+=55;
                        break;
                    case "col_lastname":
                        x+=65;
                        break;
                    case "col_sex":
                        x+=40;
                        break;
                    case "col_address":
                        x+=75;
                        break;
                    case "col_zipcode":
                        x+=30;
                        break;
                    case "col_phone1":
                        x+=70;
                        break;
                }        
                content.endText();
            }
    }
    private void create_rows(PDPageContentStream content,ObservableList<Map> input) throws IOException{
        String[] mapID = {"firstname","lastname","sex","address","zipcode",
                          "city","mail","phone1","phone2"};
        content.setFont(roboto_italic, 8);
        int x = 55;
        int y = 635;
        for (Map item : input){
            for (String mapValue : mapID){
                content.beginText();
                content.newLineAtOffset(x,y);
                content.showText(String.valueOf(item.get(mapValue)));
                switch(mapValue){
                    default:
                        x+=55;
                        break;
                    case "lastname":
                        x+=65;
                        break;
                    case "sex":
                        x+=40;
                        break;
                    case "address":
                        x+=75;
                        break;
                    case "zipcode":
                        x+=30;
                        break;
                    case "phone1":
                        x+=70;
                        break;
                }
             content.endText();
            }
            x = 55;
            y -= 20;
        }
    }
}
