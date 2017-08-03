package erpsystem.util.export.pdf.contacts;

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

public class ContactsPDF {
    private FileManager workspace;
    private String file_name;
    
    public boolean save_file(ResourceBundle default_strings,Map input){
        return true;
    }
    
        private void create_file(){
            workspace =  new FileManager();
            try{
                PDDocument doc = new PDDocument();
                PDPage page = new PDPage();
                PDDocumentInformation doc_info = new PDDocumentInformation();
                doc_info.setTitle("Contacts");
                doc_info.setAuthor("ERP");
                doc.addPage(page);
                file_name = Pdf_name();
                doc.save(workspace.getDocuments_root()+"/"+file_name);
                doc.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        private void fill_file(ObservableList<Map> input){
            File pdf_file = new File(workspace.getDocuments_business_data()+"/"+file_name);
            try{
                PDDocument pdf_doc = PDDocument.load(pdf_file);
                PDPage doc_page = pdf_doc.getPage(0);
                PDPageContentStream content = new PDPageContentStream(pdf_doc,doc_page);
                // Regular font.
                PDFont roboto_reg = PDType0Font.load(pdf_doc,new File("resources/fonts/pdf/Roboto-Regular.ttf"));
                // Bold 
                PDFont roboto_bold = PDType0Font.load(pdf_doc, new File("resources/font/pdf/Roboto-Bold.ttf"));
                // Italic 
                PDFont roboto_italic = PDType0Font.load(pdf_doc, new File("resources/fonts/pdf/Roboto-Italic.ttf"));
                
                // Header
                
                // Contents 
                
                // final line 
                
                
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        private String Pdf_name(){
            return "Something";
        }
        private boolean table_drawLine(){
            return true;
        }
        private boolean table_drawColumn(){
            return true;
        }
}
