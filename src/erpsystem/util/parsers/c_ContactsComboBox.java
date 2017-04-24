/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.parsers;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class c_ContactsComboBox {
    File xmlFile;
    DocumentBuilderFactory builder_factory;
    DocumentBuilder document_builder;
    Document xml_doc;
    
    public ObservableList<String> getSex() throws Exception{
        ObservableList<String> ret = FXCollections.observableArrayList();
            xmlFile = new File(getClass().getResource("../erpsystem/data/menudata/contacts.xml").toExternalForm());
            builder_factory = DocumentBuilderFactory.newInstance();
            document_builder = builder_factory.newDocumentBuilder();
            xml_doc = document_builder.parse(xmlFile);
            NodeList nlist = xml_doc.getElementsByTagName("sex");
          Element element = (Element) nlist;
          ret.add(element.getElementsByTagName("male").item(0).getTextContent());
          ret.add(element.getElementsByTagName("female").item(0).getTextContent());
        return ret;
    }
    
 
    
}
