/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.business_data;

import erpsystem.entities.business.Business;
import erpsystem.util.datetime.DateTimeProvider;
import erpsystem.util.system.FileManager;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public class BusinessPDF {
    private FileManager workspace;
    private DateTimeProvider date_time;
    
    public boolean save_pdf(Business data){
        boolean flag = false;
        return flag;
    }
    
    public void create_file(){
        workspace = new FileManager();
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            
            // Set Doc Info 
            doc_info.setTitle("Στοιχεία Επιχείρησης");
            doc_info.setAuthor("ERP SYSTEM NAME");
            // add a new page
            doc.addPage(page);
            doc.save(workspace.getDocuments_business_data()+"/"+Pdf_name());
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    };
    public void fill_file(Business input){
    
    };
    public void close(){};
    
    public String Pdf_name(){
        date_time = new DateTimeProvider();
        return "Στοιχεία Επιχείρησης-"+date_time.GetDateTime_file()+".pdf";
    }
}
