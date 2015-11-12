/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 *  Colective class implements Performer interface. A colective is a group of 
 * artist
 * @author drzkn
 * version 1.0 (17/09/2015)
 */
public class Collective implements Performer,Comparable, XMLRepresentable{

    /**
     * name refers to the colective's name
     * components refers to all the artist that compunds the colective
     * description refers to the description of the performance of 
     * each artist
     * web refers to teh web of each artist (if it exists)
     */
    private final String name;
    private TreeSet<Artist> components;
    private String description;
    private String web;
    
    /**
     * Creates a new Colective without webpage. A colective can only has 1 artist inicially. 
     * It's possible to add new artist after the creation.
     * @param n refers to the name of the colective
     * @param a1 refers to one of the founders of the colective
     * @param a2 refers to the other founder of the collective
     * @param d refers to the description of the colective's performance 
     */
    public Collective(String n, Artist a1, Artist a2,String d){
        
        name=n;
        components=new TreeSet<>();
        components.add(a1);
        components.add(a2);
        description=d;
        web=null;
        
    }
    
    /**
     * Creates a new Collective with webpage.colective can only has 1 artist inicially. 
     * It's possible to add new artist after the creation.
     * @param n refers to the name of the collective
     * @param a1 refers to one of the founders of the collective
     * @param a2 refers to the other founder of the collective 
     * @param d refers to the description of the collective's performance 
     * @param w refers to the web of the new collective
     */
    public Collective(String n, Artist a1, Artist a2, String d,String w){
        
        name=n;
        components.add(a1);
        components.add(a2);
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

        return name;
        
    }
    
    /**
     * Retrieves a textual representation of the work by the performer.
     * @return Description of the work of the performer
     */
    @Override
    public String getWorkDescription() {

        return description;
        
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
     * Overwrites the value of the web. This is done regardless
     * of the web that was previously associated with the performer (if any).
     * @param w refers to the new web.
     */
    public void setWeb(String w){
        
        web=w;
    
    }
    
    /**
     * Retrieves the link of the webpage of the collective.
     * @return Webpage of the collective.
     */
    public String getWeb(){
        
        return web;
        
    }
    
    /**
     * Adds a new component to the collective. The artist must exist:
     * @param a The new artist of the collective
     * @return The result of the addition 
     */
    public boolean addComponent(Artist a){
        
        components.add(a);
        return this.components.contains(a);
        
    }
    
    /**
     * Delete an existing artist from the components.
     * @param a The artist we are going to delete
     * @return The result of deleting
     */
    public boolean deleteCopmonent(Artist a){
        
        components.remove(a);
        return !(this.components.contains(a));
        
    }
    
    public Artist getComponents(int pos){

        Artist aux;
        String ret;
        int i = 0;
        Iterator<Artist> iterator = components.iterator();
        while(iterator.hasNext()){

            aux = iterator.next();
            if (i == pos){
                //ret = aux.getName();
                return aux;
            }
            i++;
        }
        return null;
    }
    
     /**
     * Retrieves the components of the collective in a Artist[]
     * @return An array with all the components
     */
    public Artist[] getComponents(){
        Artist[] artists=null;
        artists=(Artist[])components.toArray(new Artist[components.size()]);
        
        return artists;
    } 
    
    /**
     * Retrieves the components of the collective in a String
     * @return A string with all the components
     */
    public String getComponentsString(){
        
        return components.toString();
    } 
   
    /**
     * Text representation of a collective
     * @return String
     */
    @Override
    public String toString(){
        
        if (web.isEmpty())
            return "Name of the collective: "+name+"\nDescription of the collective"
                +description+"Components: "+this.getComponentsString();
        else
            return "Name of the collective: "+name+"\nDescription of the collective"
                +description+"\nWeb of the collective: "+web+"Components: "+this.getComponentsString();
        
    }
    
    
    @Override
   public boolean equals(Object o){
       return this.name.equals(((Performer)o).getName());
   }
    
    
    /*
    /**
     * Compares two collectives
     * @param c1 refers to the collective we are going to compare with
     * @return True if the name is the same. False what else.
     *
    public boolean equals(Collective c1){
        
        return this.name.equals(c1.getName());
        
    }
    
    /**
     * Compares a collective and an artist
     * @param a
     * @return True if the name is the same. False what else.
     *
    public boolean equals(Artist a){
        
        return this.name.equals(a.getName());
        
    }*/

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

        // root elements (collective)
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("collective");
        doc.appendChild(rootElement);

        // name elements
        Element xName = doc.createElement("name");
        xName.appendChild(doc.createTextNode(this.getName()));
        rootElement.appendChild(xName);
        
        // artists elements
        Element xArtists = doc.createElement("artists");
        rootElement.appendChild(xArtists);

        for (int i = 0; i < components.size(); i++){

            xArtists.appendChild(doc.createTextNode("##A##"+i));
        }

        if (this.getWeb() != null){
            // web elements
            Element xWeb = doc.createElement("web");
            xWeb.appendChild(doc.createTextNode(this.getWeb()));
            rootElement.appendChild(xWeb);
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
       

        for (int i = 0; i < components.size(); i++){


            String a=this.getComponents(i).toXML();
            String sr = a.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
            finalstring=finalstring.replace("##A##"+i,sr);
        }

        
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
