/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.write;

import erpsystem.entities.business.Business;
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

public class BusinessData {
    private EncryptionUtil encrypt;
    private FileManager workspace;
    private File BusinessXML;
   
    // ======== Public Methods
        /**
         * Method that is used from other classes  
         * @param input
         * @return 
         */
        public boolean save_data(Business input){
            boolean flag = false;
                workspace = new FileManager();
                BusinessXML = new File(workspace.getApp_data_business()+"/business_data.xml");
                if (!BusinessXML.exists()){
                    create_xml_structure(input);
                }else{
                    BusinessXML.delete();
                    create_xml_structure(input);
                }
            return flag;
        }
        /**
         * 
         * @return 
         */
        public File get_File(){
            if (BusinessXML != null){
                return BusinessXML;
            }else{
                workspace = new FileManager();
                BusinessXML = new File(workspace.getApp_data_business()+"/business_data.xml");
                return BusinessXML;
            }
        }
    // =========================
        
    // ======== Private Methods
        /**
         * Creates the XML file Structure and place data inside the tags
         * this method gets an object as an input, decrypts data and save it 
         * into the XML Document.
         * @param data 
         */
        private void create_xml_structure(Business data){
            workspace = new FileManager();
            encrypt = new EncryptionUtil();

            String[] tags = {"business_name",
                             "business_address",
                             "business_description",
                             "business_phone",
                             "business_fax",
                             "business_taxreg",
                             "business_city",
                             "business_date",
                             "business_mail"};
            String[] values = {data.getBusiness_Name(),
                               data.getBusiness_Address(),
                               data.getBusiness_Description(),
                               data.getBusiness_Phone(),
                               data.getBusiness_Fax(),
                               data.getBusiness_TaxReg(),
                               data.getBusiness_City(),
                               String.valueOf(data.getBusiness_Date()),
                               data.getBusiness_Mail()};
            try{
                DocumentBuilderFactory doc_fact = DocumentBuilderFactory.newInstance();
                DocumentBuilder doc_builder = doc_fact.newDocumentBuilder();
                Document xml_doc = doc_builder.newDocument();

                Element r_admin_elem = xml_doc.createElement("business_data");
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
                StreamResult save = new StreamResult(BusinessXML);

                pretty_format.transform(xml_doc_source,save);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    // =========================
}
