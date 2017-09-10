/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.write;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

public class ProductCategory {
    File categoryXML;
    public boolean Update_xml(String Category_str){
        categoryXML = new File("resources/xml/combobox_data/categories.xml");
        try{
                DocumentBuilderFactory doc_fact = DocumentBuilderFactory.newInstance();
                DocumentBuilder doc_builder = doc_fact.newDocumentBuilder();
                Document xml_doc = doc_builder.parse(categoryXML);
                
                NodeList list = xml_doc.getElementsByTagName("product_category");
                 Element r_eleme = (Element) list.item(0);
                 Element newcat = xml_doc.createElement("cat");
                 newcat.appendChild(xml_doc.createTextNode(Category_str));
                 r_eleme.appendChild(newcat);
                 
                 xml_doc.getDocumentElement().normalize();
                TransformerFactory pretty_format_factory = TransformerFactory.newInstance();
                Transformer pretty_format = pretty_format_factory.newTransformer();
                DOMSource xml_doc_source = new DOMSource(xml_doc);
                StreamResult save = new StreamResult(categoryXML);
                pretty_format.transform(xml_doc_source,save);
            }catch(Exception e){
                e.printStackTrace();
            }
        
        return true;
    }
}
