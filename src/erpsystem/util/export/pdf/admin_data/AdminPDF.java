/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.admin_data;

import erpsystem.entities.corpotations.BusinessAdmin;
import erpsystem.util.datetime.DateTimeProvider;
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

public class AdminPDF {
      private FileManager workspace;
    private String file_name;
    
    // ======== Public Method
    public boolean save_pdf(ResourceBundle bundle,BusinessAdmin data){
        boolean flag = false;
            create_file();
            fill_file(bundle,data);
            flag = true;
        return flag;
    }
    // =====================
    
    // ======= Private Class Methods
        /**
         * Creates an PDF File with one page and writes some useful informations
         * for example the Author(That is the app name), Description and Title. 
         */
        private void create_file(){
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
                file_name = Pdf_name();
                doc.save(workspace.getDocuments_business_data()+"/"+file_name);
                doc.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        /**
         * Loads the pdf file and it puts data into the file.
         * The String.properties file will be used to load basic strings
         * and a business object will be used for the values.
         * @param bundle
         * @param data 
         */
        private void fill_file(ResourceBundle bundle,BusinessAdmin data){
            String[] default_strings = {"gnr_lbl_firstname",
                                        "gnr_lbl_lastname",
                                        "edt_admin_description",
                                        "gnr_lbl_address",
                                        "gnr_lbl_city",
                                        "gnr_lbl_zipcode",
                                        "gnr_lbl_phone1",
                                        "gnr_lbl_phone2",
                                        "gnr_lbl_mail"};        
            String[] data_strings = {data.getFirstName(),
                                     data.getLastName(),
                                     data.getDescription(),
                                     data.getAddress(),
                                     data.getCity(),
                                     String.valueOf(data.getZipCode()),
                                     data.getPhone1(),
                                     data.getPhone2(),
                                     data.getMail()};
            
            File pdf_file = new File(workspace.getDocuments_business_data()+"/"+file_name);
            try{
                PDDocument pdf_doc = PDDocument.load(pdf_file);
                PDPage doc_page = pdf_doc.getPage(0);
                PDPageContentStream content = new PDPageContentStream(pdf_doc,doc_page);
                // Set External Font
                PDFont font = PDType0Font.load(pdf_doc,new File("resources/fonts/pdf/Roboto-Regular.ttf"));
                // Add Rectabgle to page
                content.addRect(20, 20, 570, 750);
                content.closeAndStroke();
                // Text to pdf
                // -- pdf title
                content.beginText();
                content.setFont(font, 28);
                content.newLineAtOffset(170,660);
                content.showText(bundle.getString("view_admin_data"));
                content.endText();
                // data to pdf
                int x = 65;
                int y = 500;
                    int data_index = 0;
                    for (String item : default_strings){
                        //prints the intro text
                        content.beginText();
                        content.setFont(font, 16);
                        content.newLineAtOffset(x,y);
                        content.showText(bundle.getString(item)+" "+data_strings[data_index]);
                        content.endText();
                        if (item.equals("edt_admin_description")){
                            y -= 70;
                        }else{
                            y -= 35;
                        }
                        data_index++;
                    }
                content.close();
                pdf_doc.save(pdf_file);
            }catch(IOException|IllegalArgumentException e){
                e.printStackTrace();
            }
        }
        /**
         * Returns the name that the fill will get the name is a 
         */
        private String Pdf_name(){
            return "Στοιχεία Διαχειρηστή-"+new DateTimeProvider().GetDateTime_file()+".pdf";
        }
}
