/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.write;

import erpsystem.entities.corpotations.BusinessAdmin;
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

public class AdminData {
    private EncryptionUtil encrypt;
    private FileManager workspace;
    private File AdminXML;
    
    // ======== Public Methods
        /**
         * 
         * @param input
         * @return 
         */
        public boolean save_data(BusinessAdmin input){
            boolean flag = false;
                workspace = new FileManager();
                AdminXML = new File(workspace.getApp_data_admin()+"/admin_data.xml");
                if (!AdminXML.exists()){
                    create_xml(input);
                    flag = true;
                }else{
                    AdminXML.delete();
                    create_xml(input);
                    flag = true;
                }
            return flag;
        }
        
        public File get_File(){
            if (AdminXML != null){
                return AdminXML;
            }else{
                AdminXML = new File(workspace.getApp_data_admin()+"/admin_data.xml");
                return AdminXML;
            }
        }
    // ======================
    
    // ======== Private Methods
        /**
        * This method creates the XML file and puts data in it. 
        * Once the xml file will be created this method will encrypt 
        * string data and will save the encrypted data inside the XML 
        * file.
        * @param data 
        */
        private void create_xml(BusinessAdmin data){
            encrypt = new EncryptionUtil();
            String[] tags = {"admin_firstname","admin_lastname","admin_birthday","admin_sex",
                             "admin_address","admin_zipcode","admin_city","admin_phone1",
                             "admin_phone2","admin_description","admin_tax_reg","admin_mail"};
            String[] values = {data.getFirstName(),data.getLastName(),
                               String.valueOf(data.getBirthdate()),data.getSex(),data.getAddress(),
                               String.valueOf(data.getZipCode()),data.getCity(),data.getPhone1(),
                               data.getPhone2(),data.getDescription(),data.getTaxReg(),data.getMail()};
            try{
                DocumentBuilderFactory doc_fact = DocumentBuilderFactory.newInstance();
                DocumentBuilder doc_builder = doc_fact.newDocumentBuilder();
                Document xml_doc = doc_builder.newDocument();

                Element r_admin_elem = xml_doc.createElement("business_admin");
                xml_doc.appendChild(r_admin_elem);

                int index = 0;
                for (String item : tags){
                    Element element = xml_doc.createElement(item);
                    element.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(values[index])));
                    r_admin_elem.appendChild(element);
                    index++;
                }

                TransformerFactory pretty_format_factory = TransformerFactory.newInstance();
                Transformer pretty_format = pretty_format_factory.newTransformer();
                DOMSource xml_doc_source = new DOMSource(xml_doc);
                StreamResult save = new StreamResult(AdminXML);

                pretty_format.transform(xml_doc_source,save);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    // =========================
}
