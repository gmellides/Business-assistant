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

public class ComboBox_Parser {
    File xmlFile;
    DocumentBuilderFactory builder_factory;
    DocumentBuilder document_builder;
    Document xml_doc;
    
    public ObservableList<String> get_sex() throws Exception{
        ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/combobox_data/basic_data.xml");
            builder_factory = DocumentBuilderFactory.newInstance();
            document_builder = builder_factory.newDocumentBuilder();
            xml_doc = document_builder.parse(xmlFile);
            NodeList list = xml_doc.getElementsByTagName("sex_value");  
                for (int i=0; i<list.getLength(); i++){
                      Node current_node = list.item(i);
                      if (current_node.getNodeType() == Node.ELEMENT_NODE){
                          Element current_element = (Element) current_node;
                          data.add(current_element.getTextContent());
                      }
                }
        return data;
    }
    public ObservableList<String> get_phonetype() throws Exception{
        ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/combobox_data/basic_data.xml");
            builder_factory = DocumentBuilderFactory.newInstance();
            document_builder = builder_factory.newDocumentBuilder();
            xml_doc = document_builder.parse(xmlFile);
            NodeList list = xml_doc.getElementsByTagName("type_value");  
                for (int i=0; i<list.getLength(); i++){
                      Node current_node = list.item(i);
                      if (current_node.getNodeType() == Node.ELEMENT_NODE){
                          Element current_element = (Element) current_node;
                          data.add(current_element.getTextContent());
                      }
                }
        return data;
    }
    public ObservableList<String> get_countries() throws Exception{
       ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/combobox_data/countries.xml");
            builder_factory = DocumentBuilderFactory.newInstance();
            document_builder = builder_factory.newDocumentBuilder();
            xml_doc = document_builder.parse(xmlFile);
            NodeList list = xml_doc.getElementsByTagName("value");
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
    public ObservableList<String> get_states_greece() throws Exception{
         ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/combobox_data/greece_data.xml");
            builder_factory = DocumentBuilderFactory.newInstance();
            document_builder = builder_factory.newDocumentBuilder();
            xml_doc = document_builder.parse(xmlFile);
            NodeList list = xml_doc.getElementsByTagName("state");
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
    public ObservableList<String> get_big_cities_greece() throws Exception{
        ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/combobox_data/greece_data.xml");
            builder_factory = DocumentBuilderFactory.newInstance();
            document_builder = builder_factory.newDocumentBuilder();
            xml_doc = document_builder.parse(xmlFile);
            NodeList list = xml_doc.getElementsByTagName("value");  
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
 