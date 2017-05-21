/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.write;

import erpsystem.entities.business.BusinessAdmin;
import erpsystem.util.safety.EncryptionUtil;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class BusinessAdminXML_c {
    EncryptionUtil encrypt;
    
   
    public boolean save_data(BusinessAdmin input){
        boolean flag = false;
            if (!file_exist()){
                create_xml_structure(input);
            }else{
                File admin_data = new File(System.getProperty("user.dir")+"/user_data/admin_data.xml");
                admin_data.delete();
                create_xml_structure(input);
            }
        return flag;
    }
    /*
       Dom xml parser 
    */
    public void create_xml_structure(BusinessAdmin data){
        encrypt = new EncryptionUtil();
        try{
            DocumentBuilderFactory doc_fact = DocumentBuilderFactory.newInstance();
            DocumentBuilder doc_builder = doc_fact.newDocumentBuilder();
            Document xml_doc = doc_builder.newDocument();
            
            Element r_admin_elem = xml_doc.createElement("business_admin");
            xml_doc.appendChild(r_admin_elem);
            
            Element e_firstname = xml_doc.createElement("fisrname");
            e_firstname.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getFirstName())));
            r_admin_elem.appendChild(e_firstname);
           
            Element e_sex = xml_doc.createElement("sex");
            e_sex.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getSex())));
            r_admin_elem.appendChild(e_sex);
          
            Element e_address = xml_doc.createElement("address");
            e_address.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getAddress())));
            r_admin_elem.appendChild(e_address);
            
            Element e_zipcode = xml_doc.createElement("zipcode");
            e_zipcode.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(String.valueOf(data.getZipCode()))));
            r_admin_elem.appendChild(e_zipcode);
           
            Element e_country = xml_doc.createElement("country");
            e_country.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getCountry())));
            r_admin_elem.appendChild(e_country);
            // specify
            Element e_taxreg = xml_doc.createElement("tax_reg");
            e_taxreg.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getTaxReg())));
            r_admin_elem.appendChild(e_taxreg);
            
            Element e_phone1 = xml_doc.createElement("phone1");
            e_phone1.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getPhone1())));
            r_admin_elem.appendChild(e_phone1);
            
            Element e_phone2 = xml_doc.createElement("phone2");
            e_phone2.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getPhone2())));
            r_admin_elem.appendChild(e_phone2);
          
            Element e_mail = xml_doc.createElement("mail");
            e_mail.appendChild(xml_doc.createTextNode(encrypt.encrypt_string(data.getMail())));
            r_admin_elem.appendChild(e_mail);
           
            TransformerFactory pretty_format_factory = TransformerFactory.newInstance();
            Transformer pretty_format = pretty_format_factory.newTransformer();
            DOMSource xml_doc_source = new DOMSource(xml_doc);
            StreamResult save = new StreamResult(new File(System.getProperty("user.dir")+"/user_data/admin_data.xml"));
            
            pretty_format.transform(xml_doc_source,save);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public boolean file_exist(){
        File admin_data = new File(System.getProperty("user.dir")+"/user_data/admin_data.xml");
        return admin_data.exists();
    }
}
