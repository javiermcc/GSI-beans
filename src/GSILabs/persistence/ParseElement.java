/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.persistence;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Client;
import GSILabs.BModel.Collective;
import GSILabs.BModel.ModelDate;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

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
        
        return artist;
    }
    
    
    public static Client parseClient(String str){
        
        Client client = null;
        int dni = 0;
        String name = null;
        String surnames = null;
        int year = 0;
        int month = 0;
        int day = 0;        
        ModelDate birthdate;
        TreeSet<String> cards = new TreeSet();
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("client");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;


                    dni = Integer.parseInt(eElement.getElementsByTagName("dni").item(0).getTextContent());
                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    surnames = eElement.getElementsByTagName("surnames").item(0).getTextContent();
                    year = Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent());
                    month = Integer.parseInt(eElement.getElementsByTagName("month").item(0).getTextContent());
                    day = Integer.parseInt(eElement.getElementsByTagName("day").item(0).getTextContent());
                    Node cardNode=eElement.getElementsByTagName("cards").item(0);
                    Element cElement = (Element) cardNode;
                    NodeList cardList = cElement.getElementsByTagName("card");
                    for (int t = 0; t < cardList.getLength(); t++){
                        
                        if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                            cards.add(cElement.getElementsByTagName("card").item(t).getTextContent());
                        }
                    }
                    
                }
            }
            birthdate = new ModelDate(year,month,day);
            String[] Cards = cards.toArray(new String[cards.size()]);
            
            client = new Client(dni,name, surnames, birthdate, Cards[0]);//que pasa si tiene varias cards?
            for(int j = 1; j < Cards.length; j++){
                client.addCard(Cards[j]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
    
    public static Client parseClient(File f){
         
        Client client = null;
        int dni = 0;
        String name = null;
        String surnames = null;
        int year = 0;
        int month = 0;
        int day = 0;        
        ModelDate birthdate;
        TreeSet<String> cards = new TreeSet();
        
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
                    year = Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent());
                    month = Integer.parseInt(eElement.getElementsByTagName("month").item(0).getTextContent());
                    day = Integer.parseInt(eElement.getElementsByTagName("day").item(0).getTextContent());
                    Node cardNode=eElement.getElementsByTagName("cards").item(0);
                    Element cElement = (Element) cardNode;
                    NodeList cardList = cElement.getElementsByTagName("card");
                    for (int t = 0; t < cardList.getLength(); t++){
                        
                        if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                            cards.add(cElement.getElementsByTagName("card").item(t).getTextContent());
                        }
                    }
                    
                }
            }
            birthdate = new ModelDate(year,month,day);
            String[] Cards = cards.toArray(new String[cards.size()]);

            client = new Client(dni,name, surnames, birthdate, Cards[0]);//que pasa si tiene varias cards?
            for(int j = 1; j < Cards.length; j++){
                client.addCard(Cards[j]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
    
    
    public static Collective parseCollective(String str){
        Collective collective = null;
        String[] artistas=null;
        Artist a=null;
        String name = null;
        String description = null;
        String web = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("collective");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;


                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    web = eElement.getElementsByTagName("web").item(0).getTextContent();
                    
                    Node artistNode=eElement.getElementsByTagName("artists").item(0);
                    String[] test = str.split("</?artists>");
                    String[] test2 = test[1].split("</?artist>");
                    Element cElement = (Element) artistNode;
                    NodeList artistList = cElement.getElementsByTagName("artist");
                    artistas = new String[artistList.getLength()];
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><artist>";
                    String aux2="</artist>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        artistas[idx]=aux.concat(test2[t]);
                        artistas[idx]=artistas[idx].concat(aux2);
                        idx=idx+1;
                    }
                    
                }
            }
            
            Artist[] Components = new Artist[artistas.length];
            for(int t = 0;t<artistas.length;t++){
                Components[t]=ParseElement.parseArtist(artistas[t]);
            }
            
            collective = new Collective(name,Components[0],Components[1], description, web);
            for(int j = 2; j < Components.length; j++){
                collective.addComponent(Components[j]);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collective;
    }
    
    public static Collective parseCollective(File f){
        
        Collective collective = null;
        String[] artistas=null;
        Artist a=null;
        String name = null;
        TreeSet<Artist> components = new TreeSet();
        String description = null;
        String web = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("collective");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;


                    name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    description = eElement.getElementsByTagName("description").item(0).getTextContent();
                    web = eElement.getElementsByTagName("web").item(0).getTextContent();
                    
                    Node artistNode=eElement.getElementsByTagName("artists").item(0);
                    
                    String[] test = str.split("</?artists>");
                    String[] test2 = test[1].split("</?artist>");
                    Element cElement = (Element) artistNode;
                    NodeList artistList = cElement.getElementsByTagName("artist");
                    artistas = new String[artistList.getLength()];
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><artist>";
                    String aux2="</artist>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        artistas[idx]=aux.concat(test2[t]);
                        artistas[idx]=artistas[idx].concat(aux2);
                        idx=idx+1;
                    }
                    
                }
            }
            
            Artist[] Components = new Artist[artistas.length];
            for(int t = 0;t<artistas.length;t++){
                Components[t]=ParseElement.parseArtist(artistas[t]);
            }
            
            collective = new Collective(name,Components[0],Components[1], description, web);
            for(int j = 2; j < Components.length; j++){
                collective.addComponent(Components[j]);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collective;
    }
    
    
    
}
    
    
    
    
    /*
    Concert parseConcert(String str){
        
    }
    Concert parseConcert(File f){
        
    }
    */

