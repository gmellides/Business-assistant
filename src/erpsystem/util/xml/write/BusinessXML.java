/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.write;

import erpsystem.entities.business.Business;
import erpsystem.util.safety.EncryptionUtil;
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

public class BusinessXML {
    private EncryptionUtil encrypt;
    private FileManager workspace;
    /**
     * Method that is used from other classes  
    * @param input
     * @return 
     */
    public boolean save_data(Business input){
        boolean flag = false;
            if (!file_exist()){
                create_xml_structure(input);
            }else{
                File admin_data = new File(workspace.getApp_data_business()+"/business_data.xml");
                admin_data.delete();
                create_xml_structure(input);
            }
        return flag;
    }
    /**
     * Creates the XML file Structure and place data inside the tags
     * this method gets an object as an input, decrypts data and save it 
     * into the XML Document.
     * @param data 
     */
    public void create_xml_structure(Business data){
        encrypt = new EncryptionUtil();
        try{
            DocumentBuilderFactory doc_fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder doc_builder = doc_fact.newDocumentBuilder();
            Document xml_doc = doc_builder.newDocument();
            
            Element r_admin_elem = xml_doc.createElement("business_data");
            xml_doc.appendChild(r_admin_elem);
            
            Element e_businessName = xml_doc.createElement("business_name");
            e_businessName.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getBusiness_Name())));
            r_admin_elem.appendChild(e_businessName);
           
            Element e_businessAddress = xml_doc.createElement("business_address");
            e_businessAddress.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getBusiness_Address())));
            r_admin_elem.appendChild(e_businessAddress);
           // specify
            Element e_businessDescription = xml_doc.createElement("business_description");
            e_businessDescription.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getBusiness_Description())));
            r_admin_elem.appendChild(e_businessDescription);
            
            Element e_businessPhone = xml_doc.createElement("business_phone");
            e_businessPhone.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(String.valueOf(data.getBusiness_Phone()))));
            r_admin_elem.appendChild(e_businessPhone);
           
            Element e_businessFax = xml_doc.createElement("business_fax");
            e_businessFax.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getBusiness_Fax())));
            r_admin_elem.appendChild(e_businessFax);
            // specify
            Element e_businessTaxreg = xml_doc.createElement("business_taxreg");
            e_businessTaxreg.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getBusiness_TaxReg())));
            r_admin_elem.appendChild(e_businessTaxreg);
            
            Element e_businessCity = xml_doc.createElement("business_city");
            e_businessCity.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getBusiness_City())));
            r_admin_elem.appendChild(e_businessCity);
            
            Element e_businessDate = xml_doc.createElement("business_date");
            e_businessDate.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(String.valueOf(data.getBusiness_Date()))));
            r_admin_elem.appendChild(e_businessDate);
          
            Element e_businessMail = xml_doc.createElement("business_mail");
            e_businessMail.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getBusiness_Mail())));
            r_admin_elem.appendChild(e_businessMail);
           
            TransformerFactory pretty_format_factory = TransformerFactory.newInstance();
            Transformer pretty_format = pretty_format_factory.newTransformer();
            DOMSource xml_doc_source = new DOMSource(xml_doc);
            StreamResult save = new StreamResult(new File(workspace.getApp_data_business()+"/business_data.xml"));
            
            pretty_format.transform(xml_doc_source,save);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Returns true or false if the xml file Exist or not.
     * @return 
     */
    public boolean file_exist(){
        File admin_data = new File(System.getProperty("user.dir")+"/user_data/business_data.xml");
        return admin_data.exists();
    }
}
