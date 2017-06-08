/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.write;

import erpsystem.util.security.EncryptionUtil;
import erpsystem.util.system.FileManager;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AppData {
   
   private FileManager workspace;
   private EncryptionUtil encrypt;
   private File Temp_Data;
   
    public void save_data(String[] values){
        
    }
    public File get_File(){
            if (Temp_Data != null){
                return Temp_Data;
            }else{
                workspace = new FileManager();
                Temp_Data = new File(workspace.getApp_data_root()+"/temp_data.xml");
                return Temp_Data;
            }
        }
    private boolean create_file(String[] values){
        String[] xml_tags = {"count_contacts","count_customers","count_suppliers",
                             "count_employess","count_sales","count_products",
                             "count_purchases"};
        
            workspace = new FileManager();
            encrypt = new EncryptionUtil();

            try{
                DocumentBuilderFactory doc_fact = DocumentBuilderFactory.newInstance();
                DocumentBuilder doc_builder = doc_fact.newDocumentBuilder();
                Document xml_doc = doc_builder.newDocument();

                Element r_admin_elem = xml_doc.createElement("temp_data");
                xml_doc.appendChild(r_admin_elem);

                int index = 0;
                for (String item : xml_tags){
                    Element element = xml_doc.createElement(item);
                    element.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(values[index])));
                    r_admin_elem.appendChild(element);
                    index++;
                }

                TransformerFactory pretty_format_factory = TransformerFactory.newInstance();
                Transformer pretty_format = pretty_format_factory.newTransformer();
                DOMSource xml_doc_source = new DOMSource(xml_doc);
                StreamResult save = new StreamResult(Temp_Data);

                pretty_format.transform(xml_doc_source,save);
            }catch(Exception e){
                e.printStackTrace();
            }
        
        
        return true;
    }
}

/*
<temp_data>
</temp_data>
*/