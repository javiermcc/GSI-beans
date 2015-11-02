/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Artist implements the interface Performer. Each artist has a unique name and
 * a description. It's possible to an artist to have or not a webpage.
 * @author drzkn
 * @version 1.0 (17/9/2015)
 */
public class Artist implements Performer,Comparable, XMLRepresentable{

    /**
     * name refers to the artist's name
     * description refers to the description of the performance of 
     * each colective
     * web refers to teh web of each colective (if it exists)
     */
    private String name;
    private String description;
    private String web;
    
    /**
     * Creates an artist without webpage. The artist name can't be the same to 
     * another performer's name
     * @param n name of the new artist
     * @param d description of the new artist
     */
    public Artist(String n, String d){
        
        this.name = n;
        this.description = d;
        this.web = null;
        
    }
    
    /**
     * Creates an artist with webpage.  The artist name can't be the same to 
     * another performer's name.
     * @param n name of the new artist
     * @param d description of the new artist
     * @param w artis's webpage
     */
    public Artist(String n, String d, String w){
        
        name=n;
        description=d;
        web=w;
        
    }
    
    /**
     * Return the name of the performer. It must be unique, meaning that different Performer
     * cannot have a repeated name. This holds regardless of the type (class) of performer
     * they are
     * @return the artistic name of the performer
     */
    @Override
    public String getName() {

        return this.name;
    
    }

     /**
     * Retrieves a textual representation of the work by the performer.
     * @return Description of the work of the performer
     */
    @Override
    public String getWorkDescription() {

        return this.description;
        
    }
    
     /**
     * Returns the webpage of an artist
     * @return The artist's webpage
     */
    public String getWeb(){
        
        return this.web;
        
    }

    /**
     * Overwrites the description for the given performer. This is done regardless
     * of the description that was previously associated with the performer (if any).
     * @param description New description for the work of the performer
     */
    @Override
    public void setWorkDescription(String description) {

        this.description=description;
    
    }
    
    /**
     * Overwrites the webpage for the given performer. This is done regardless
     * of the webpage that was previously associated with the performer (if any).
     * @param web la nueva web del artista
     */
    public void setWeb(String web){
        
        this.web=web;
        
    }
    
    /*  
     * Text representation of an artist
     */
    @Override
    public String toString(){
        
        if (web.isEmpty())
            return "Name of the artist: "+name+"\nDescription of the artist"
                +description;
        else
            return "Name of the artist: "+name+"\nDescription of the artist"
                +description+"\nWeb of the artist: "+web;
    
    }
    
    /**
     * Compares two artists.
     * @param a1 refers to the artist we are going to campare
     * @return True if the name is the same. False what else.
     */
    @Override
    public boolean equals(Object o){
        
        return this.name.equals(((Performer)o).getName());
        
    }
    
    /**
     * Compares an artist and a collective
     * @param c The collective we are going to compare with
     * @return True if the name is the same. False what else.
     */
    /*public boolean equals(Collective c){
        
        return this.name.equals(c.getName());
        
    }*/
    
    /**
     * Compares two artists.
     * @param o refers to the artist we are going to campare
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o) {

        return this.getName().compareTo(((Performer)o).getName());
        
    }

    @Override
    public String toXML() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("artist");
        doc.appendChild(rootElement);


        // name elements
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(this.getName()));
        rootElement.appendChild(name);

        // description elements
        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(this.getWorkDescription()));
        rootElement.appendChild(description);

        if (this.getWeb() != null){
            // web elements
            Element web = doc.createElement("web");
            web.appendChild(doc.createTextNode(this.getWeb()));
            rootElement.appendChild(web);
        }

        
        // write the content into string
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StringWriter outWriter = new StringWriter();
        StreamResult result = new StreamResult( outWriter );

        transformer.transform(source, result);
        StringBuffer sb = outWriter.getBuffer(); 
        String finalstring = sb.toString();
       
        return finalstring;
        

        } catch (ParserConfigurationException pce) {
              pce.printStackTrace();
        } catch (TransformerException tfe) {
              tfe.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveToXML(File f) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String toXML = this.toXML();

        try {
            PrintWriter out = new PrintWriter(f);
            out.write(toXML);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Artist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean saveToXML(String filePath) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String toXML = this.toXML();
       
        try { 
            File f = new File(filePath);
            PrintWriter out = new PrintWriter(f);
            out.write(toXML);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Artist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
