/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.read;

import erpsystem.entities.business.Business;
import erpsystem.util.safety.DecryptionUtil;
import erpsystem.util.system.FileManager;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BusinessXML_Parser {
    private FileManager workspace;
    private File xml_file;
    private DocumentBuilderFactory doc_builder_fact;
    private DocumentBuilder doc_builder;
    private Document xml_document; 
    
    public Business getData(){
            workspace = new FileManager();
            String[] xml_elements = {"business_name","business_address","business_description",
                                     "business_phone","business_fax","business_taxreg","business_city",
                                     "business_date","business_mail"};
            String file_location = workspace.getApp_data_business()+"/business_data.xml";
            try{
            // Init
            xml_file = new File(file_location);
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
        return data_to_obj();
    }
    
    public Business data_to_obj(){
        decrypt =  new DecryptionUtil();
        LocalDate establish_date = LocalDate.parse(decrypt.decrypt_string(xml_data.get("business_date")));
        b_data = new Business(decrypt.decrypt_string(xml_data.get("business_name")),
                              decrypt.decrypt_string(xml_data.get("business_description")),
                              decrypt.decrypt_string(xml_data.get("business_address")),
                              decrypt.decrypt_string(xml_data.get("business_city")),
                              decrypt.decrypt_string(xml_data.get("business_phone")),
                              decrypt.decrypt_string(xml_data.get("business_fax")),
                              decrypt.decrypt_string(xml_data.get("business_taxreg")),
                              decrypt.decrypt_string(xml_data.get("business_mail")),
                              establish_date);
        return b_data;
    }
    
    private Business b_data;
    private DecryptionUtil decrypt;
    private HashMap<String,String> xml_data;
}
