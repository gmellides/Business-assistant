/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.suppliers;

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


public class SuppliersTablePDF {
    
    PDFont roboto_reg,roboto_bold,roboto_italic;
    private int x,y;
    
    public boolean save_file(ResourceBundle bundle,
                             ObservableList<Map> data_individual,
                             ObservableList<Map> data_companies){
        x = 55;
        y = 725;
        create_file(bundle);
        fill_file(bundle,
                  data_individual,
                  data_companies);
        return true;
    }
    
    private void create_file(ResourceBundle bundle){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle(bundle.getString("filename_supplierTable_pdf"));
            doc_info.setAuthor(bundle.getString("pdf_author"));
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_root()+"/"+bundle.getString("filename_supplierTable_pdf")+".pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void fill_file(ResourceBundle bundle,
                               ObservableList<Map> data_individual,
                               ObservableList<Map> data_companies){
            File pdf_file = new File(new FileManager().getDocuments_root()+"/"+bundle.getString("filename_supplierTable_pdf")+".pdf");
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
                
                // Pdf Info 
                // Title 
                content.setFont(roboto_reg,21);
                content.beginText();
                content.newLineAtOffset(250, y);
                content.showText(bundle.getString("filename_supplierTable_pdf"));
                content.endText();
                y -= 30; //  new line
                // Date
                content.setFont(roboto_reg,9);
                content.beginText();
                content.newLineAtOffset(x, y);
                content.showText(bundle.getString("pdf_table_date")+new DateTimeProvider().GetDateTime());
                content.endText();
                y -= 30; // new line for table 
                if (data_individual.size()>0){
                    // table title
                    content.setFont(roboto_reg,10);
                    content.beginText();
                    content.newLineAtOffset(270, y);
                    content.showText("("+bundle.getString("btn_customers")+")");
                    content.endText();
                    y -= 25; // new Line
                    create_individual_header(content,bundle);
                    y -= 30; // new line
                    create_individual_rows(content,data_individual);
                    y -= 30;// new line
                }
                if (data_companies.size()>0){
                    content.setFont(roboto_reg,10);
                    content.beginText();
                    content.newLineAtOffset(290, y);
                    content.showText("("+bundle.getString("btn_companies")+")");
                    content.endText();
                    y -= 30;
                    create_companies_header(bundle,content);
                    y -= 30;
                    create_companies_rows(content,data_companies);
                }
                content.close();
                pdf_doc.save(pdf_file);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    
    private void create_individual_header(PDPageContentStream content,ResourceBundle bundle) throws IOException{
            String[] headerID = {"col_firstname","col_lastname","col_address",
                                 "col_zipcode","col_city","col_customerType",
                                 "col_phone","col_fax","col_mail"};
            content.setFont(roboto_bold, 9);
            for (String item : headerID){
                content.beginText();
                content.newLineAtOffset(x,y);
                content.showText(bundle.getString(item));
                switch(item){
                    default:
                        x+=55;
                        break;
                    case "col_lastname":
                        x+=65;
                        break;
                    case "col_address":
                        x+=75;
                        break;
                    case "col_zipcode":
                        x+=30;
                        break;
                    case "col_phone":
                        x+=70;
                        break;
                }          
                content.endText();
            }
        }
    private void create_individual_rows(PDPageContentStream content,ObservableList<Map> data) throws IOException{
            String[] mapID = {"spl_name","spl_lastname","spl_address","spl_zipcode","spl_city",
                              "spl_supplierType","spl_phone","spl_fax","spl_mail"};
                content.setFont(roboto_italic, 8);
                x = 55;
                for (Map item : data){
                    for (String mapValue : mapID){
                        content.beginText();
                        content.newLineAtOffset(x,y);
                        content.showText(String.valueOf(item.get(mapValue)));
                        switch(mapValue){
                            default:
                                x+=55;
                                break;
                            case "spl_lastname":
                                x+=65;
                                break;
                            case "spl_address":
                                x+=75;
                                break;
                            case "spl_zipcode":
                                x+=30;
                                break;
                            case "spl_phone":
                                x+=70;
                                break;
                        }
                        content.endText();
                    }
                    x = 55;
                    y -= 20;
                }
            

        }
    private void create_companies_header(ResourceBundle bundle,PDPageContentStream content) throws IOException{
        // Header bold 
                String[] headerID = {"col_bName","col_address","col_zipcode","col_city",
                                     "col_customerType","col_phone","col_fax","col_mail"};
                content.setFont(roboto_bold, 9);
                x = 45;
                for (String item : headerID){
                    content.beginText();
                    content.newLineAtOffset(x,y);
                    content.showText(bundle.getString(item));
                    switch(item){
                        default:
                            x+=45;
                            break;
                        case "col_bName":
                            x+=110;
                            break;
                        case "col_address":
                            x+=85;
                            break;
                        case "col_customerType":
                            x+=55;
                            break;
                        case "col_zipcode":
                            x+=40;
                            break;
                        case "col_phone":
                            x+=60;
                            break;
                        case "col_fax":
                            x+=60;
                            break;
                    }            
                    content.endText();
                }
        }
    private void create_companies_rows(PDPageContentStream content,ObservableList<Map> data) throws IOException{
         String[] mapID = {"spl_name","spl_address","spl_zipcode","spl_city","spl_supplierType",
                           "spl_phone","spl_fax","spl_mail"};
                content.setFont(roboto_italic, 8);
                x = 45;
                for (Map item : data){
                    for (String mapValue : mapID){
                        content.beginText();
                        content.newLineAtOffset(x,y);
                        content.showText(String.valueOf(item.get(mapValue)));
                        switch(mapValue){
                            default:
                                x+=45;
                                break;
                            case "spl_name":
                                x+=110;
                                break;
                            case "spl_address":
                                x+=85;
                                break;
                            case "spl_supplierType":
                                x+=55;
                                break;
                            case "spl_zipcode":
                                x+=40;
                                break;
                            case "spl_phone":
                                x+=60;
                                break;
                            case "spl_fax":
                                x+=60;
                                break;
                        }  
                        content.endText();
                    }
                    x = 45;
                    y -= 20;
                }
        }
}
