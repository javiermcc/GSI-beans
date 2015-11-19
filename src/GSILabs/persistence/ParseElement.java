/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.persistence;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Client;
import GSILabs.BModel.Collective;
import GSILabs.BModel.Concert;
import GSILabs.BModel.Event;
import GSILabs.BModel.Exhibition;
import GSILabs.BModel.Festival;
import GSILabs.BModel.Location;
import GSILabs.BModel.ModelDate;
import GSILabs.BModel.Performer;
import GSILabs.BModel.Sale;
import GSILabs.BModel.Ticket;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
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
                    Element aElement = (Element) artistNode;
                    NodeList artistList = aElement.getElementsByTagName("artist");
                    artistas = new String[artistList.getLength()];
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><artist>";
                    String aux2="</artist>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        artistas[idx]=aux.concat(test2[t]);
                        artistas[idx]=artistas[idx].concat(aux2);
                        idx++;
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
        String sCadena;
        String str = "";
        
        try {
            
            BufferedReader bf = new BufferedReader(new FileReader(f));
            
            while ((sCadena = bf.readLine())!=null) {
                str += sCadena;
            }
            
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
                    Element aElement = (Element) artistNode;
                    NodeList artistList = aElement.getElementsByTagName("artist");
                    artistas = new String[artistList.getLength()];
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><artist>";
                    String aux2="</artist>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        artistas[idx]=aux.concat(test2[t]);
                        artistas[idx]=artistas[idx].concat(aux2);
                        idx++;
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
    
    public static Concert parseConcert(String str){
         
        Concert concert = null;
        Location location;
        Performer performer;
        String name = null;
        int year = 0;
        int month = 0;
        int day = 0;
        Date date;
        String sLocation = null;
        String sp = null;
        String type = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("concert");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String[] test = str.split("</?location>");

                    String aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><location>";
                    String aux2 = "</location>";

                    sLocation = aux.concat(test[1]);
                    sLocation = sLocation.concat(aux2);
                    
                    String[] test3 = str.split("</?performer>");
                    String[] test4 = test3[1].split("</?performer>");

                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><performer>";
                    aux2 = "</performer>";
                    if (test4[0].contains("collective")){
                        type = "Collective";
                    } else {
                        type = "Artist";
                    }
                    sp = aux.concat(test4[0]);
                    sp = sp.concat(aux2);

                    name = eElement.getElementsByTagName("name").item(0).getTextContent();

                    year = Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent());
                    month = Integer.parseInt(eElement.getElementsByTagName("month").item(0).getTextContent());
                    day = Integer.parseInt(eElement.getElementsByTagName("day").item(0).getTextContent());

                }
            }

            location = ParseElement.parseLocation(sLocation);

            if (type.equals("Artist")){ 
                performer = ParseElement.parseArtist(sp);
            } else {
                performer = ParseElement.parseCollective(sp);
            }
            
            date = new Date(year-1900, month-1, day);

            concert = new Concert(location, performer, name, date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return concert;
    }
    
    public static Concert parseConcert(File f){
         
        Concert concert = null;
        Location location;
        Performer performer;
        String name = null;
        int year = 0;
        int month = 0;
        int day = 0;
        Date date;
        String sLocation = null;
        String sp = null;
        String type = null;
        String sCadena;
        String str = "";
        
        try {
            
            BufferedReader bf = new BufferedReader(new FileReader(f));
            
            while ((sCadena = bf.readLine())!=null) {
                str += sCadena;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("concert");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String[] test = str.split("</?location>");

                    String aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><location>";
                    String aux2 = "</location>";

                    sLocation = aux.concat(test[1]);
                    sLocation = sLocation.concat(aux2);
                    
                    String[] test3 = str.split("</?performer>");
                    String[] test4 = test3[1].split("</?performer>");

                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><performer>";
                    aux2 = "</performer>";
                    if (test4[0].contains("collective")){
                        type = "Collective";
                    } else {
                        type = "Artist";
                    }
                    sp = aux.concat(test4[0]);
                    sp = sp.concat(aux2);

                    name = eElement.getElementsByTagName("name").item(0).getTextContent();

                    year = Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent());
                    month = Integer.parseInt(eElement.getElementsByTagName("month").item(0).getTextContent());
                    day = Integer.parseInt(eElement.getElementsByTagName("day").item(0).getTextContent());

                }
            }

            location = ParseElement.parseLocation(sLocation);

            if (type.equals("Artist")){ 
                performer = ParseElement.parseArtist(sp);
            } else {
                performer = ParseElement.parseCollective(sp);
            }
            
            date = new Date(year-1900, month-1, day);

            concert = new Concert(location, performer, name, date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return concert;
    }
    
    public static Exhibition parseExhibition(String str){
         
        Exhibition exhibition = null;
        String title = null;
        String organizer = null;
        Date[] timetable;
        Location location;
        String[] sLocation = null;
        Performer protagonist;
        String[] sProtagonist = null;
        String[] webs = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("exhibition");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    organizer = eElement.getElementsByTagName("organizer").item(0).getTextContent();
                    
                    timetable = ;
                    
                    Node locationNode = eElement.getElementsByTagName("location").item(0);
                    String[] test = str.split("</?location>");
                    String[] test2 = test[1].split("</?location>");
                    Element lElement = (Element) locationNode;
                    NodeList locationList = lElement.getElementsByTagName("location");
                    sLocation = new String[locationList.getLength()];
                    int idx = 0;
                    String aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><location>";
                    String aux2 = "</location>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        sLocation[idx] = aux.concat(test2[t]);
                        sLocation[idx] = sLocation[idx].concat(aux2);
                        idx++;
                    }
                    
                    Node protagonistNode = eElement.getElementsByTagName("protagonist").item(0);
                    String[] test3 = str.split("</?protagonist>");
                    String[] test4 = test3[1].split("</?protagonist>");
                    Element pElement = (Element) protagonistNode;
                    NodeList protagonistList = pElement.getElementsByTagName("protagonist");
                    sProtagonist = new String[protagonistList.getLength()];
                    idx = 0;
                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><protagonist>";
                    aux2 = "</protagonist>";
                    
                    for (int t = 1; t <= test4.length; t=t+2){
                        sProtagonist[idx] = aux.concat(test2[t]);
                        sProtagonist[idx] = sProtagonist[idx].concat(aux2);
                        idx++;
                    }                    
                }
            }
            
            String slocati= sLocation[0];
            location = ParseElement.parseLocation(slocati);
            
            String sProta = sProtagonist[0];
            protagonist = ParseElement.parseArtist(sProta);

            
            exhibition = new Exhibition(title, organizer, timetable, location, protagonist, webs);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exhibition;
    }
    
    public static Exhibition parseExhibition(File f){
         
        Exhibition exhibition = null;
        String title = null;
        String organizer = null;
        Date[] timetable;
        Location location;
        String[] sLocation = null;
        Performer protagonist;
        String[] sProtagonist = null;
        String[] webs = null;
        String sCadena;
        String str = "";
        
        try {
            
            BufferedReader bf = new BufferedReader(new FileReader(f));
            
            while ((sCadena = bf.readLine())!=null) {
                str += sCadena;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("exhibition");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    organizer = eElement.getElementsByTagName("organizer").item(0).getTextContent();
                    
                    timetable = ;
                    
                    Node locationNode = eElement.getElementsByTagName("location").item(0);
                    String[] test = str.split("</?location>");
                    String[] test2 = test[1].split("</?location>");
                    Element lElement = (Element) locationNode;
                    NodeList locationList = lElement.getElementsByTagName("location");
                    sLocation = new String[locationList.getLength()];
                    int idx = 0;
                    String aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><location>";
                    String aux2 = "</location>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        sLocation[idx] = aux.concat(test2[t]);
                        sLocation[idx] = sLocation[idx].concat(aux2);
                        idx++;
                    }
                    
                    Node protagonistNode = eElement.getElementsByTagName("protagonist").item(0);
                    String[] test3 = str.split("</?protagonist>");
                    String[] test4 = test3[1].split("</?protagonist>");
                    Element pElement = (Element) protagonistNode;
                    NodeList protagonistList = pElement.getElementsByTagName("protagonist");
                    sProtagonist = new String[protagonistList.getLength()];
                    idx = 0;
                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><protagonist>";
                    aux2 = "</protagonist>";
                    
                    for (int t = 1; t <= test4.length; t=t+2){
                        sProtagonist[idx] = aux.concat(test2[t]);
                        sProtagonist[idx] = sProtagonist[idx].concat(aux2);
                        idx++;
                    }                    
                }
            }
            
            String slocati= sLocation[0];
            location = ParseElement.parseLocation(slocati);
            
            String sProta = sProtagonist[0];
            protagonist = ParseElement.parseArtist(sProta);

            
            exhibition = new Exhibition(title, organizer, timetable, location, protagonist, webs);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exhibition;
    }
    
    public static Festival parseFestival(String str){
         
        Festival festival = null;
        String[] sConcert = null;
        String name = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("festival");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    
                    Node concertNode = eElement.getElementsByTagName("concerts").item(0);
                    String[] test = str.split("</?concerts>");
                    String[] test2 = test[1].split("</?concert>");
                    Element cElement = (Element) concertNode;
                    NodeList concertList = cElement.getElementsByTagName("concert");
                    sConcert = new String[concertList.getLength()];
                    
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><concert>";
                    String aux2="</concert>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        sConcert[idx] = aux.concat(test2[t]);
                        sConcert[idx] = sConcert[idx].concat(aux2);
                        idx++;
                    }

                    name = eElement.getElementsByTagName("name").item(0).getTextContent();   
                }
            }
            
            Concert[] concerts = new Concert[sConcert.length];
            for(int t = 0;t < sConcert.length;t++){
                concerts[t] = ParseElement.parseConcert(sConcert[t]);
            }

            festival = new Festival(concerts[0], concerts[0].getStartDate(), concerts[concerts.length-1].getStartDate(), name);
            for(int j = 1; j < concerts.length; j++){
                festival.addConcert(concerts[j]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return festival;
    }
    
    public static Festival parseFestival(File f){
         
        Festival festival = null;
        String[] sConcert = null;
        String name = null;
        String sCadena;
        String str = "";


        
        try {
            
            BufferedReader bf = new BufferedReader(new FileReader(f));
            
            while ((sCadena = bf.readLine())!=null) {
                str += sCadena;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("festival");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    
                    Node concertNode = eElement.getElementsByTagName("concerts").item(0);
                    String[] test = str.split("</?concerts>");
                    String[] test2 = test[1].split("</?concert>");
                    Element cElement = (Element) concertNode;
                    NodeList concertList = cElement.getElementsByTagName("concert");
                    sConcert = new String[concertList.getLength()];
                    
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><concert>";
                    String aux2="</concert>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        sConcert[idx] = aux.concat(test2[t]);
                        sConcert[idx] = sConcert[idx].concat(aux2);
                        idx++;
                    }

                    name = eElement.getElementsByTagName("name").item(0).getTextContent();   
                }
            }
            
            Concert[] concerts = new Concert[sConcert.length];
            for(int t = 0;t < sConcert.length;t++){
                concerts[t] = ParseElement.parseConcert(sConcert[t]);
            }

            festival = new Festival(concerts[0], concerts[0].getStartDate(), concerts[concerts.length-1].getStartDate(), name);
            for(int j = 1; j < concerts.length; j++){
                festival.addConcert(concerts[j]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return festival;
    }
    
    public static Location parseLocation(String str){
         
        Location location = null;
        String name = null;
        int capacity = 0;
        String country = null;
        String province = null;
        String city = null;
        String street = null;
        Short number = 0;
        String web = null;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("location");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element lElement = (Element) nNode;                    
                    
                    name = lElement.getElementsByTagName("name").item(0).getTextContent();
                    capacity = Integer.parseInt(lElement.getElementsByTagName("capacity").item(0).getTextContent());
                    country = lElement.getElementsByTagName("country").item(0).getTextContent();
                    province = lElement.getElementsByTagName("province").item(0).getTextContent();
                    city = lElement.getElementsByTagName("city").item(0).getTextContent();
                    street = lElement.getElementsByTagName("street").item(0).getTextContent();
                    number = Short.parseShort(lElement.getElementsByTagName("number").item(0).getTextContent());
                    web = lElement.getElementsByTagName("web").item(0).getTextContent();
                }
            }
            location = new Location(name, capacity, country, province, city, street, number, web);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
    
    public static Location parseLocation(File f){
         
        Location location = null;
        String name = null;
        int capacity = 0;
        String country = null;
        String province = null;
        String city = null;
        String street = null;
        Short number = 0;
        String web = null;
        String sCadena;
        String str = "";
        
        try {
            
            BufferedReader bf = new BufferedReader(new FileReader(f));
            
            while ((sCadena = bf.readLine())!=null) {
                str += sCadena;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("location");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element lElement = (Element) nNode;                    
                    
                    name = lElement.getElementsByTagName("name").item(0).getTextContent();
                    capacity = Integer.parseInt(lElement.getElementsByTagName("capacity").item(0).getTextContent());
                    country = lElement.getElementsByTagName("country").item(0).getTextContent();
                    province = lElement.getElementsByTagName("province").item(0).getTextContent();
                    city = lElement.getElementsByTagName("city").item(0).getTextContent();
                    street = lElement.getElementsByTagName("street").item(0).getTextContent();
                    number = Short.parseShort(lElement.getElementsByTagName("number").item(0).getTextContent());
                    web = lElement.getElementsByTagName("web").item(0).getTextContent();
                }
            }
            location = new Location(name, capacity, country, province, city, street, number, web);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }
    
    public static Sale parseSale(String str){
         
        Sale sale = null;
        String[] sClient = null;
        String[] sTicket = null;
        float price = 0;
        String cCard = null;
        Date soldDate;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("sale");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element sElement = (Element) nNode;                    
                    
                    Node clientNode = sElement.getElementsByTagName("clients").item(0);
                    String[] test = str.split("</?clients>");
                    String[] test2 = test[1].split("</?client>");
                    Element cElement = (Element) clientNode;
                    NodeList clientList = cElement.getElementsByTagName("client");
                    sClient = new String[clientList.getLength()];
                    int idx=0;
                    String aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><artist>";
                    String aux2 = "</artist>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        sClient[idx]=aux.concat(test2[t]);
                        sClient[idx]=sClient[idx].concat(aux2);
                        idx++;
                    }
                    
                    Node ticketNode = sElement.getElementsByTagName("tickets").item(0);
                    String[] test3 = str.split("</?tickets>");
                    String[] test4 = test3[1].split("</?ticket>");
                    Element tElement = (Element) clientNode;
                    NodeList ticketList = tElement.getElementsByTagName("ticket");
                    sTicket = new String[ticketList.getLength()];
                    idx = 0;
                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><ticket>";
                    aux2 = "</ticket>";
                    
                    for (int t = 1; t <= test4.length; t=t+2){
                        sTicket[idx] = aux.concat(test4[t]);
                        sTicket[idx] = sTicket[idx].concat(aux2);
                        idx = idx+1;
                    }
                    
                    price = Float.parseFloat(sElement.getElementsByTagName("price").item(0).getTextContent());
                    cCard = sElement.getElementsByTagName("card").item(0).getTextContent();
                }
            }
            
            Client[] sCli = new Client[sClient.length];
            for(int t = 0; t < sClient.length; t++){
                sCli[t] = ParseElement.parseClient(sClient[t]);
            }
            
            Ticket[] sTic = new Ticket[sTicket.length];
            for(int t = 0; t < sTicket.length; t++){
                sTic[t] = ParseElement.parseTicket(sTicket[t]);
            }
            
            sale = new Sale(sCli[0], sTic[0], price, cCard);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sale;
    }
    
    public static Sale parseSale(File f){
         
        Sale sale = null;
        String[] sClient = null;
        String[] sTicket = null;
        float price = 0;
        String cCard = null;
        Date soldDate;
        String sCadena;
        String str = "";
        
        try {
            
            BufferedReader bf = new BufferedReader(new FileReader(f));
            
            while ((sCadena = bf.readLine())!=null) {
                str += sCadena;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(f);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("sale");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element sElement = (Element) nNode;                    
                    
                    Node clientNode = sElement.getElementsByTagName("clients").item(0);
                    String[] test = str.split("</?clients>");
                    String[] test2 = test[1].split("</?client>");
                    Element cElement = (Element) clientNode;
                    NodeList clientList = cElement.getElementsByTagName("client");
                    sClient = new String[clientList.getLength()];
                    int idx=0;
                    String aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><artist>";
                    String aux2 = "</artist>";
                    
                    for (int t = 1; t <= test2.length; t=t+2){
                        sClient[idx]=aux.concat(test2[t]);
                        sClient[idx]=sClient[idx].concat(aux2);
                        idx++;
                    }
                    
                    Node ticketNode = sElement.getElementsByTagName("tickets").item(0);
                    String[] test3 = str.split("</?tickets>");
                    String[] test4 = test3[1].split("</?ticket>");
                    Element tElement = (Element) clientNode;
                    NodeList ticketList = tElement.getElementsByTagName("ticket");
                    sTicket = new String[ticketList.getLength()];
                    idx = 0;
                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><ticket>";
                    aux2 = "</ticket>";
                    
                    for (int t = 1; t <= test4.length; t=t+2){
                        sTicket[idx] = aux.concat(test4[t]);
                        sTicket[idx] = sTicket[idx].concat(aux2);
                        idx = idx+1;
                    }
                    
                    price = Float.parseFloat(sElement.getElementsByTagName("price").item(0).getTextContent());
                    cCard = sElement.getElementsByTagName("card").item(0).getTextContent();
                }
            }
            
            Client[] sCli = new Client[sClient.length];
            for(int t = 0; t < sClient.length; t++){
                sCli[t] = ParseElement.parseClient(sClient[t]);
            }
            
            Ticket[] sTic = new Ticket[sTicket.length];
            for(int t = 0; t < sTicket.length; t++){
                sTic[t] = ParseElement.parseTicket(sTicket[t]);
            }
            
            sale = new Sale(sCli[0], sTic[0], price, cCard);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sale;
    }
    
    public static Ticket parseTicket(String str){
         
        Ticket ticket = null;
        int id = 0;
        String[] sEvent = null;
        String[] sIdentifiers = null;
        String sAssociated = null;
        boolean used = false;
        
        try {
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("ticket");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element tElement = (Element) nNode;                    
                    
                    id = Integer.parseInt(tElement.getElementsByTagName("id").item(0).getTextContent());
                    
                    Node eventNode = tElement.getElementsByTagName("events").item(0);
                    String[] test = str.split("</?events>");
                    String[] test2 = test[1].split("</?event>");
                    Element eElement = (Element) eventNode;
                    NodeList eventList = eElement.getElementsByTagName("event");
                    sEvent = new String[eventList.getLength()];
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><event>";
                    String aux2="</event>";
                    
                    for (int t = 1; t <= test2.length; t = t + 2){
                        sEvent[idx] = aux.concat(test2[t]);
                        sEvent[idx] = sEvent[idx].concat(aux2);
                        idx++;
                    }
                    
                    Node identifiersNode = tElement.getElementsByTagName("identifiers").item(0);
                    test = str.split("</?identifiers>");
                    test2 = test[1].split("</?identifier>");
                    Element iElement = (Element) identifiersNode;
                    NodeList identifiersList = iElement.getElementsByTagName("identifier");
                    sIdentifiers = new String[identifiersList.getLength()];
                    idx = 0;
                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><event>";
                    aux2 = "</event>";
                    
                    for (int t = 1; t <= test2.length; t = t + 2){
                        sIdentifiers[idx] = aux.concat(test2[t]);
                        sIdentifiers[idx] = sIdentifiers[idx].concat(aux2);
                        idx++;
                    }
                    
                    sAssociated = tElement.getElementsByTagName("associated").item(0).getTextContent();
                }
            }
            
            Event[] event = new Event[sEvent.length];
            for(int t = 0;t < sEvent.length; t++){
                event[t] = ParseElement.parseEvent(sEvent[t]);
            }
            
            int[] identifiers = new int[sIdentifiers.length];
            for(int t = 0;t < sIdentifiers.length; t++){
                identifiers[t] = Integer.parseInt(sIdentifiers[t]);
            }
            
            Client associated = ParseElement.parseClient(sAssociated);
            
            ticket = new Ticket(id, event[0], identifiers, associated, used);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
    
    public static Ticket parseTicket(File f){
         
        Ticket ticket = null;
        int id = 0;
        String[] sEvent = null;
        String[] sIdentifiers = null;
        String sAssociated = null;
        boolean used = false;
        String sCadena;
        String str = "";
        String[] test2 = null;
        Event event = null;
        
        try {
            
            BufferedReader bf = new BufferedReader(new FileReader(f));
            
            while ((sCadena = bf.readLine())!=null) {
                str += sCadena;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            String str2 = str.replaceFirst("<?.*?>", "");
            InputStream stream = new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);

            doc.getDocumentElement().normalize();
            	
            NodeList nList = doc.getElementsByTagName("ticket");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element tElement = (Element) nNode;                    
                    
                    id = Integer.parseInt(tElement.getElementsByTagName("id").item(0).getTextContent());
                    
                    Node eventNode = tElement.getElementsByTagName("events").item(0);
                    String[] test = str.split("</?event>");
                    System.out.println("hola"+test[1]);
                    
                    if (test[1].contains("<concert>")){
                        //test2 = test[1].split("</?concert>");
                        System.out.println("a1dios");
                        Concert concert = ParseElement.parseConcert("<?.*?>"+test[1]);
                        event = (Event) concert;
                        System.out.println("adios");
                    }
                    
                    System.out.println("adios"+test2[1]);
                    
                    Element eElement = (Element) eventNode;
                    NodeList eventList = eElement.getElementsByTagName("event");
                    sEvent = new String[eventList.getLength()];
                    int idx=0;
                    String aux="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><event>";
                    String aux2="</event>";
                    
                    for (int t = 1; t <= test2.length; t = t + 2){
                        sEvent[idx] = aux.concat(test2[t]);
                        sEvent[idx] = sEvent[idx].concat(aux2);
                        idx++;
                    }
                    
                    Node identifiersNode = tElement.getElementsByTagName("identifiers").item(0);
                    test = str.split("</?identifiers>");
                    test2 = test[1].split("</?identifier>");
                    Element iElement = (Element) identifiersNode;
                    NodeList identifiersList = iElement.getElementsByTagName("identifier");
                    sIdentifiers = new String[identifiersList.getLength()];
                    idx = 0;
                    aux = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><event>";
                    aux2 = "</event>";
                    
                    for (int t = 1; t <= test2.length; t = t + 2){
                        sIdentifiers[idx] = aux.concat(test2[t]);
                        sIdentifiers[idx] = sIdentifiers[idx].concat(aux2);
                        idx++;
                    }
                    
                    sAssociated = tElement.getElementsByTagName("associated").item(0).getTextContent();
                }
            }
            
            
            int[] identifiers = new int[sIdentifiers.length];
            for(int t = 0;t < sIdentifiers.length; t++){
                identifiers[t] = Integer.parseInt(sIdentifiers[t]);
            }
            
            Client associated = ParseElement.parseClient(sAssociated);
            
            ticket = new Ticket(id, event, identifiers, associated, used);
            System.out.println("ticket"+ticket.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
