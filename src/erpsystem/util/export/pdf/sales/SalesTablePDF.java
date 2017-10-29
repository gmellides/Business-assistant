/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.sales;

import erpsystem.util.system.FileManager;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;


public class SalesTablePDF {
    PDFont roboto_reg,roboto_bold,roboto_italic;
    private int x,y;
    
    public boolean save_pdf(){
        x = 25;
        y = 725;
        
        return true;
    }
    
    private void create_file(ResourceBundle bundle){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle(bundle.getString("filename_salesTable_pdf"));
            doc_info.setAuthor(bundle.getString("pdf_author"));
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_root()+"/"+bundle.getString("filename_salesTable_pdf")+".pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
        
    private void create_individual_header(PDPageContentStream content,ResourceBundle bundle) throws IOException{
            String[] headerID = {"col_sale_id","col_firstname","col_lastname",
                                 "col_sale_paymentMethod","col_phone","col_sale_salePrice",
                                 "col_sale_dateTime"};
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
    private void fill_rows(PDPageContentStream content,ObservableList<Map> data) throws IOException{
         String[] mapID = {};
                content.setFont(roboto_italic, 8);
                x = 25;
                for (Map item : data){
                    for (String mapValue : mapID){
                        content.beginText();
                        content.newLineAtOffset(x,y);
                        content.showText(String.valueOf(item.get(mapValue)));
                        switch(mapValue){
                            default:
                                x+=55;
                                break;
                            case "cst_name":
                                x+=85;
                                break;
                            case "cst_address":
                                x+=75;
                                break;
                            case "cst_customerType":
                                x+=65;
                                break;
                            case "cst_zipcode":
                                x+=40;
                                break;
                            case "cst_phone":
                                x+=75;
                                break;
                        }  
                        content.endText();
                    }
                    x = 25;
                    y -= 20;
                }
        }
}
