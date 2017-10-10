/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.pdf.sales;

import erpsystem.database.sales.ReceiptDatabase;
import erpsystem.entities.corpotations.Business;
import erpsystem.util.datetime.DateTimeProvider;
import erpsystem.util.system.FileManager;
import erpsystem.util.xml.read.BusinessDataParser;
import erpsystem.util.xml.write.BusinessData;
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

public class ReceiptPDF {
    
    PDDocument pdf_doc;
    PDPage doc_page;
    PDFont roboto_reg,roboto_bold,roboto_italic;
    ResourceBundle bundle;
    String file_name;
    int ID;
    int layout_x,layout_y;
    
    public void save_receipt(ResourceBundle default_strings,int SaleId){
       bundle = default_strings;
       this.ID = SaleId;
       file_name = bundle.getString("filename_receipt_pdf")+new DateTimeProvider().GetDateTime_file();
       create_file();
       fill_file();
    }
    private void create_file(){
        try{
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            PDDocumentInformation doc_info = new PDDocumentInformation();
            doc_info.setTitle(bundle.getString("filename_receipt_pdf")+new ReceiptDatabase().get_cstName(ID));
            doc_info.setAuthor(bundle.getString("pdf_author"));
            doc.addPage(page);
            doc.save(new FileManager().getDocuments_root()+"/"+file_name+".pdf");
            doc.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void fill_file(){
        File pdf_file = new File(new FileManager().getDocuments_root()+"/"+file_name+".pdf");
        try{
            pdf_doc = PDDocument.load(pdf_file);
            doc_page = pdf_doc.getPage(0);
            PDPageContentStream content = new PDPageContentStream(pdf_doc,doc_page);        
            // Regular font.
            roboto_reg = PDType0Font.load(pdf_doc,new File("resources/fonts/pdf/Roboto-Regular.ttf"));
            // Bold 
            roboto_bold = PDType0Font.load(pdf_doc, new File("resources/fonts/pdf/Roboto-Bold.ttf"));
            // Italic 
            roboto_italic = PDType0Font.load(pdf_doc, new File("resources/fonts/pdf/Roboto-Italic.ttf")); 
                business_info(content);
                customer_info(content);
                product_table(content);
                sale_info(content);
            content.close();
            pdf_doc.save(pdf_file);
        }catch(IOException e){
            e.printStackTrace();
        }    
    }
        private void business_info(PDPageContentStream content) throws IOException{
            Business bsn_data = new BusinessDataParser(new BusinessData().get_File()).getData();
            layout_x = 55;
            layout_y = 680;
            PDImageXObject bsn_logo = PDImageXObject.createFromFile(new FileManager().getApp_data_business()+"/logo.png", pdf_doc);   
            content.drawImage(bsn_logo, layout_x,layout_y,75,90);
            layout_x +=100;
            layout_y += 60;
                write_line(content,roboto_bold,14,layout_x,
                           layout_y,bsn_data.getName());
            layout_y -= 25;
                write_line(content,roboto_reg,12,layout_x,
                           layout_y,bsn_data.getAddress()+","+bsn_data.getCity());
            layout_x = 55;
            layout_y -=30;
                write_line(content,roboto_reg,12,layout_x,
                           layout_y,bsn_data.getDescription());
            layout_y -= 25;
                write_line(content,roboto_reg,12,layout_x,layout_y,
                           bundle.getString("gnr_lbl_phone1")+bsn_data.getPhone1()
                           +"  "+bundle.getString("gnr_lbl_phone2")+bsn_data.getPhone2()
                           +" "+bundle.getString("gnr_lbl_fax")+bsn_data.getFax());
            layout_y -=25;
                write_line(content,roboto_bold,14,layout_x,
                           layout_y,"Αριθμός Παραγγελίας: "+ID);
        }
        private void customer_info(PDPageContentStream content) throws IOException{
            ObservableList<Map> cst_data = new ReceiptDatabase().get_cstInfo(ID);
            String[] ids = {"cst_name","cst_lastname","cst_address","cst_zipcode",
                            "cst_city","cst_state","cst_customerType","cst_phone",
                            "cst_fax","cst_mail"};
            Map<String,String> d = cst_data.get(0);
                layout_y -= 25;
                write_line(content,roboto_italic,14,layout_x+165,layout_y,
                        bundle.getString("view_window_title"));
                layout_y -= 25;
                write_line(content,roboto_reg,14,layout_x+25,layout_y,
                           bundle.getString("gnr_lbl_firstname")+d.get("cst_name")
                           +"             "+bundle.getString("gnr_lbl_lastname")+d.get("cst_lastname"));
                layout_y -= 25;
                write_line(content,roboto_reg,14,layout_x+25,layout_y,
                           bundle.getString("gnr_lbl_address")+d.get("cst_address")
                           +"             "+bundle.getString("gnr_lbl_zipcode")+d.get("cst_zipcode")
                           +"             "+bundle.getString("gnr_lbl_city")+d.get("cst_city"));
                layout_y -= 25;
                write_line(content,roboto_reg,14,layout_x+25,layout_y,
                           bundle.getString("gnr_lbl_state")+d.get("cst_state")
                           +"  "+bundle.getString("customer_type")+d.get("cst_customerType"));
                layout_y -= 25;
                write_line(content,roboto_reg,14,layout_x+25,layout_y,
                           bundle.getString("gnr_lbl_phone")+d.get("cst_phone")
                           +"          "+bundle.getString("gnr_lbl_fax")+d.get("cst_fax")
                           +"          "+bundle.getString("gnr_lbl_mail")+d.get("cst_mail"));

        }
        private void product_table(PDPageContentStream content) throws IOException{
            ObservableList<Map> prd_data = new ReceiptDatabase().get_prdInfo(ID);
            layout_y -= 30;
                write_line(content,roboto_reg,14,layout_x+165,layout_y,
                           bundle.getString("receipt_pdf_prdLbl"));
            layout_y -= 25;
            // Header
            String[] mapId = {"col_sale_prdName","col_sale_saleQuantity","col_sale_salePrice","col_pdf_finalPrice"}; 
            for (String id:mapId){
                write_line(content,roboto_italic,14,layout_x,layout_y,
                           bundle.getString(id));
                if (id.equals("col_sale_prdName")){
                    layout_x += 155;
                }else{
                    layout_x +=120;
                }
            }
            layout_x = 55;
            layout_y -= 25;
            // Data
            int index = 0;
            String[] prdIDs = {"prd_name","sal_quantity","prd_sellPrice","sal_price"}; 
            for (Map dat : prd_data){
                for (String id:prdIDs){
                    write_line(content,roboto_italic,14,layout_x,layout_y, (String) dat.get(prdIDs[index]));
                    if (id.equals("prd_name")){
                        layout_x += 155;
                    }else{
                        layout_x +=120;
                    }
                    index++;
                }
                index = 0;
                layout_x = 55;
                layout_y -=25;
            } 
        }
        private void sale_info(PDPageContentStream content) throws IOException{
            Map sale_data = new ReceiptDatabase().get_salInfo(ID).get(0);
            layout_x = 55;
            layout_y = 40;
            if (sale_data.get("sal_paymentMethod").equals("credit")){
                write_line(content,roboto_bold,14,layout_x,layout_y,
                       bundle.getString("lbl_paymentMethod")+" "+bundle.getString("rbtn_credit"));
            }else{
                write_line(content,roboto_bold,14,layout_x,layout_y,
                       bundle.getString("lbl_paymentMethod")+" "+bundle.getString("rbtn_Debit"));
            }
            layout_x += 250;
                write_line(content,roboto_bold,14,layout_x,layout_y,
                            bundle.getString("lbl_Summary")+sale_data.get("sal_finalPrice")+"€");
            layout_y -= 25;
            layout_x = 55;
                write_line(content,roboto_bold,14,layout_x,layout_y,
                           bundle.getString("receipt_pdf_date")+sale_data.get("sal_date"));
        }
    private void write_line(PDPageContentStream content,
                            PDFont font,
                            int font_size,
                            int layout_x,
                            int layout_y,
                            String data) throws IOException{
        content.setFont(font,font_size);
        content.beginText();
        content.newLineAtOffset(layout_x, layout_y);
        content.showText(data);
        content.endText();
    }
}
