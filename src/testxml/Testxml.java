/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testxml;


import java.io.File;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 *
 * @author linux1
 */
public class Testxml {

public static void main(String argv[]) {

  try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("company");
        doc.appendChild(rootElement);

        // staff elements
        Element staff = doc.createElement("Staff");
        rootElement.appendChild(staff);

        // set attribute to staff element
        Attr attr = doc.createAttribute("id");
        attr.setValue("1");
        staff.setAttributeNode(attr);

        // shorten way
        // staff.setAttribute("id", "1");

        // firstname elements
        Element firstname = doc.createElement("firstname");
        firstname.appendChild(doc.createTextNode("Josu"));
        staff.appendChild(firstname);

        // lastname elements
        Element lastname = doc.createElement("lastname");
        lastname.appendChild(doc.createTextNode("Jubera"));
        staff.appendChild(lastname);

        // nickname elements
        Element nickname = doc.createElement("nickname");
        nickname.appendChild(doc.createTextNode("Jub3r"));
        staff.appendChild(nickname);

        // salary elements
        Element salary = doc.createElement("salary");
        salary.appendChild(doc.createTextNode("-1"));
        staff.appendChild(salary);

       
        Element staff1 = doc.createElement("Staff");
        rootElement.appendChild(staff1);

        // set attribute to staff element
        Attr attr1 = doc.createAttribute("id");
        attr1.setValue("2");
        staff1.setAttributeNode(attr1);
        
        
        Element firstname1 = doc.createElement("firstname");
        firstname1.appendChild(doc.createTextNode("Diego"));
        staff1.appendChild(firstname1);

        // lastname elements
        Element lastname1 = doc.createElement("lastname");
        lastname1.appendChild(doc.createTextNode("Razquin"));
        staff1.appendChild(lastname1);

        // nickname elements
        Element nickname1 = doc.createElement("nickname");
        nickname1.appendChild(doc.createTextNode("Gallu"));
        staff1.appendChild(nickname1);

        // salary elements
        Element salary1 = doc.createElement("salary");
        salary1.appendChild(doc.createTextNode("666"));
        staff1.appendChild(salary1);
        
        
        
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        
       
        
        StreamResult result = new StreamResult(new File("/home/elementary/test.xml"));

        
        StringWriter test=new StringWriter();
        
        //StreamResult result = new StreamResult(test);
        // Output to console for testing
        //StreamResult result = new StreamResult(System.out);


        
        
        transformer.transform(source, result);
        
        //System.out.println(test.toString());
        //String r=test.toString();
       

        //System.out.println("File saved!");

  } catch (ParserConfigurationException pce) {
        pce.printStackTrace();
  } catch (TransformerException tfe) {
        tfe.printStackTrace();
  }
	
}
    
}
