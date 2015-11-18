/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.persistence;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Client;
import GSILabs.BModel.ModelDate;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.TreeSet;

/**
 *
 * @author elementary
 */
public class ParseElement {

    
    public static Artist parseArtist(String str){
        
        Artist artist = null;
        String name = null;
        String description = null;
        String web = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(str);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("artist");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;


                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    web = eElement.getElementsByTagName("web").item(0).getTextContent();
                }
            }
            artist = new Artist(name, description, web);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(artist);
        return artist;
    }
    
    public static Artist parseArtist(File f){
        
        Artist artist = null;
        String name = null;
        String description = null;
        String web = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("artist");
            System.out.println(nList.getLength());
                
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println(nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element eElement = (Element) nNode;


                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    web = eElement.getElementsByTagName("web").item(0).getTextContent();
                }
            }
            artist = new Artist(name, description, web);
            System.out.println(artist);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return artist;
    }
    
    
   /* public static Client parseClient(String str){
        
        Client client = null;
        int dni;
        String name = null;
        String surnames = null;
        ModelDate birthdate;
        TreeSet<String> cards;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(str);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("client");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;


                    dni = Integer.parseInt(eElement.getElementsByTagName("dni").item(0).getTextContent());
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    surnames = eElement.getElementsByTagName("surnames").item(0).getTextContent();
                    //birthdate = eElement.getElementsByTagName("birtdate").item(0).getTextContent();
                    //cards = eElement.getElementsByTagName("cards").item(0).getTextContent();
                }
            }
            client = new Client(dni,name, surnames, birthdate, cards);//que pasa si tiene varias cards?
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }*/
    
    /*public static Client parseClient(File f){
        
        Client client = null;
        int dni;
        String name = null;
        String surnames = null;
        ModelDate birthdate;
        TreeSet<String> cards;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("client");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;


                    dni = Integer.parseInt(eElement.getElementsByTagName("dni").item(0).getTextContent());
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    surnames = eElement.getElementsByTagName("surnames").item(0).getTextContent();
                    //birthdate = eElement.getElementsByTagName("birtdate").item(0).getTextContent();
                    //cards = eElement.getElementsByTagName("cards").item(0).getTextContent();
                }
            }
            client = new Client(dni,name, surnames, birthdate, cards);//que pasa si tiene varias cards?
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }*/
}
    
    
    
    
    /*
    Concert parseConcert(String str){
        
    }
    Concert parseConcert(File f){
        
    }
    */

