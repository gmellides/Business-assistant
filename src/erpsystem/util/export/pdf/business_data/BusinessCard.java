package erpsystem.util.export.pdf.business_data;

import erpsystem.entities.corpotations.Business;
import erpsystem.util.system.FileManager;
import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

public class BusinessCard {
    private FileManager workspace;
    private String FileName;
    
    public void save_card(ResourceBundle strings,Business b_data){

    }
    
    private boolean create_file(){
        boolean flag = false;
         try{
                PDDocument  doc = new PDDocument();
                PDPage page = new PDPage();
                PDDocumentInformation doc_info = new PDDocumentInformation();
                // Set Doc Info 
                doc_info.setTitle("Στοιχεία Επιχείρησης");
                doc_info.setAuthor("ERP SYSTEM NAME");
                // add a new page
                doc.addPage(page);
                FileName = Pdf_name();
                doc.save(workspace.getDocuments_business_data()+"/"+FileName);
                doc.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        return flag;
    }
    private boolean fill_file(ResourceBundle strings,Business data){
        boolean flag = false;
            String[] default_strings = {"view_bus_businessname",
                                        "view_bus_description",
                                        "lbl_address",
                                        "lbl_city",
                                        "lbl_phone",
                                        "lbl_fax",
                                        "lbl_mail"};
            String[] data_strings = {data.getName(),
                                     data.getDescription(),
                                     data.getAddress(),
                                     data.getCity(),
                                     data.getPhone1(),
                                     data.getPhone2(),
                                     data.getFax(),
                                     data.getMail()};
            
            int index = 0;
            for (String item : default_strings){
                
            }
        return flag;
    }
    private String Pdf_name(){
        return "Καρτα Επιχείρησης";
    }
}
