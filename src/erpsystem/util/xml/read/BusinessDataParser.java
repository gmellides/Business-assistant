/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.read;

import erpsystem.entities.corpotations.Business;
import erpsystem.util.security.DecryptionUtil;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BusinessDataParser {
    private final File xml_file;
    private DocumentBuilderFactory doc_builder_fact;
    private DocumentBuilder doc_builder;
    private Document xml_document; 
    private Business b_data;
    private DecryptionUtil decrypt;
    private HashMap<String,String> xml_data;
    
    public BusinessDataParser(File xml_file){
        this.xml_file = xml_file;
    }
    
    public Business getData(){
            String[] xml_elements = {
                             "business_name",
                             "business_address",
                             "business_description",
                             "business_phone1",
                             "business_phone2",
                             "business_fax",
                             "business_taxreg",
                             "business_city",
                             "business_date",
                             "business_mail"
            };
            try{           
            doc_builder_fact = DocumentBuilderFactory.newInstance();
            doc_builder = doc_builder_fact.newDocumentBuilder();
            xml_document = doc_builder.parse(xml_file);
            // for 
            xml_data = new HashMap<>();
            for (String element : xml_elements){
                NodeList list = xml_document.getElementsByTagName(element);
                for (int j=0; j<list.getLength(); j++){
                    Node current_node = list.item(j);
                    if (current_node.getNodeType() == Node.ELEMENT_NODE){
                        Element current_element = (Element) current_node;
                        xml_data.put(element,current_element.getTextContent());
                    }
                }
            }
            }catch(Exception e){
                e.printStackTrace();
            }
        return decrypt_data();
    }
    /** 
     * This Method Decrypts data from the XML and returns an object with actual values.
     * @return 
     */
    private Business decrypt_data(){
        decrypt =  new DecryptionUtil();
        LocalDate establish_date = LocalDate.parse(decrypt.decrypt_string(xml_data.get("business_date")));
        b_data = new Business(decrypt.decrypt_string(xml_data.get("business_name")),
                              decrypt.decrypt_string(xml_data.get("business_description")),
                              decrypt.decrypt_string(xml_data.get("business_address")),
                              decrypt.decrypt_string(xml_data.get("business_city")),
                              decrypt.decrypt_string(xml_data.get("business_phone1")),
                              decrypt.decrypt_string(xml_data.get("business_phone2")),
                              decrypt.decrypt_string(xml_data.get("business_fax")),
                              decrypt.decrypt_string(xml_data.get("business_taxreg")),
                              decrypt.decrypt_string(xml_data.get("business_mail")),
                              LocalDate.parse(decrypt.decrypt_string(xml_data.get("business_date"))));
        return b_data;
    }
    
    
}
