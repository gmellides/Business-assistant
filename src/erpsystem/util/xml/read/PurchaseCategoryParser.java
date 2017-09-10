/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.read;

import java.io.File;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class PurchaseCategoryParser {
    
    File xmlFile;
    DocumentBuilderFactory builder_factory;
    DocumentBuilder document_builder;
    Document xml_doc;
    
    public ObservableList<String> get_categories() throws Exception{
        ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/combobox_data/categories.xml");
            builder_factory = DocumentBuilderFactory.newInstance();
            document_builder = builder_factory.newDocumentBuilder();
            xml_doc = document_builder.parse(xmlFile);
            NodeList list = xml_doc.getElementsByTagName("cat");  
                for (int i=0; i<list.getLength(); i++){
                      Node current_node = list.item(i);
                      if (current_node.getNodeType() == Node.ELEMENT_NODE){
                          Element current_element = (Element) current_node;
                          data.add(current_element.getTextContent());
                      }
                }
          Comparator compare = Comparator.naturalOrder();
          FXCollections.sort(data, compare);
        return data;
    }
}
