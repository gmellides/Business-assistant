/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.read;

import erpsystem.entities.business.BusinessAdmin;
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

public class AdminXML_Parser {
    private final File xml_file;
    private DocumentBuilderFactory doc_builder_fact;
    private DocumentBuilder doc_builder;
    private Document xml_document; 
    private BusinessAdmin BusinessAdminData;
    private DecryptionUtil decrypt;
    
    public AdminXML_Parser(File xml_file){
        this.xml_file = xml_file;
    }
    
    
    public BusinessAdmin getData(){
        HashMap<String,String> xml_data = null;
        String[] xml_elements = {"admin_firstname",
                                 "admin_lastname",
                                 "admin_sex",
                                 "admin_bithday",
                                 "admin_address",
                                 "admin_zipcode",
                                 "admin_tax_reg",
                                 "admin_phone1",
                                 "admin_phone2",
                                 "admin_mail"};
        try{           
            doc_builder_fact = DocumentBuilderFactory.newInstance();
            doc_builder = doc_builder_fact.newDocumentBuilder();
            xml_document = doc_builder.parse(xml_file);
         
            xml_data = new HashMap<>();
            for (String element : xml_elements){
                NodeList list = xml_document.getElementsByTagName(element);
                for (int i=0; i<list.getLength(); i++){
                    Node current_node = list.item(i);
                    if (current_node.getNodeType() == Node.ELEMENT_NODE){
                        Element current_element = (Element) current_node;
                        xml_data.put(element,current_element.getTextContent());
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return decrypt_data(xml_data);
    }
    
    
    private BusinessAdmin decrypt_data(HashMap<String,String> input){
        decrypt =  new DecryptionUtil();
        BusinessAdminData = new BusinessAdmin(decrypt.decrypt_string(input.get("admin_firstname")),
                                              decrypt.decrypt_string(input.get("admin_lastname")),
                                              decrypt.decrypt_string(input.get("admin_sex")),
                                              LocalDate.parse(decrypt.decrypt_string(input.get("admin_birthday"))),
                                              decrypt.decrypt_string(input.get("admin_address")),          
                                              Integer.parseInt(decrypt.decrypt_string(input.get("admin_zipcode"))),
                                              decrypt.decrypt_string(input.get("admin_tax_reg")),
                                              decrypt.decrypt_string(input.get("admin_phone1")),
                                              decrypt.decrypt_string(input.get("admin_phone2")),
                                              decrypt.decrypt_string(input.get("admin_mail")));
        
        return BusinessAdminData;
    }
   
    
}
