/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.xml.read;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class c_ContactsComboBox {
    File xmlFile;
    DocumentBuilderFactory builder_factory;
    DocumentBuilder document_builder;
    Document xml_doc;
    
    public ObservableList<String> get_sex() throws Exception{
        ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/contacts/contacts.xml");
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
            xmlFile = new File("resources/xml/contacts/contacts.xml");
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
            xmlFile = new File("resources/xml/contacts/countries.xml");
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
       return data;      
   }
    public ObservableList<String> get_states_greece() throws Exception{
         ObservableList<String> data = FXCollections.observableArrayList();
            xmlFile = new File("resources/xml/contacts/greece_data.xml");
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
        return data;
    }
    public ObservableList<String> get_big_cities_greece() throws Exception{
        ObservableList<String> data = FXCollections.observableArrayList();
      //  HashMap<String,String> d = new HashMap<String,String>();
            xmlFile = new File("resources/xml/contacts/greece_data.xml");
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
        return data;
    }
    
    public ObservableList<String> hash_to_observable(HashMap input){
        ObservableList<String> d = FXCollections.observableArrayList();
            Set set = States.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()){
                d.add(input.get(1).toString());
            }
        return d;
    }
    public String Selevegsrth(String Input){
        String ew = null;
        
        return ew;
    }

    private HashMap<String,String> States;
}
