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
import java.util.Date;
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
 *
 * @author elementary
 */
public class Exhibition implements LastingEvent, Comparable, XMLRepresentable{
    
    private final String title;               // the title of the exhibition
    private final String organizer;           // the organizer of the exhibition
    private final Date[] timetable;           // the Dates that the exhibition will open on chronologic order
    private final Location location;          // the location of the exhibition
    private final Performer protagonist;    // the protagonist of the exhibition
    private final String[] webs;              // the webs of the exhibition
    
    /**
     * Constructor of exhibition
     * @param title
     * @param organizer
     * @param timetable
     * @param location
     * @param protagonist
     * @param webs 
     */
    public Exhibition(String title, String organizer, Date[] timetable,Location location, Performer protagonist, String[] webs){
        this.title = title;
        this.organizer = organizer;
        this.timetable = timetable;
        this.location=location;
        this.protagonist = protagonist;
        this.webs = webs;
    }
    
    /**
     * Gets the Location of the exhibition
     * @return Location
     */    
    public Location getLocation(){
        return this.location;
    }
    
    /**
     * Gets the exhibition's name
     * @return String
     */   
    @Override
    public String getName() {
        return this.title;
    }
    
    /**
     * Gets the exhibition's organizer
     * @return String
     */
    public String getOrganizer(){
        return this.organizer;  
    }
    
    /**
     * Gets the exhibition's open date
     * @return Date
     */ 
    @Override
    public Date getStartDate() {
        return this.timetable[0];
    }
    
    /**
     * Gets the exhibition's close date
     * @return Date
     */
    @Override
    public Date getEndingDate() {
        return this.timetable[this.timetable.length-1];
    }
    
    /**
     * Gets the days the exhibition will be open
     * @return Date[]
     */
    @Override
    public Date[] getDates() {  
        return this.timetable;
    }
    
    /**
     * Gets the exhibition's webs
     * @return String[]
     */
    public String[] getWebs(){
        return this.webs;
    }


    /**
     * Checks if the perfomer is in the exhibition
     * @param p
     * @return boolean, true if the performer is in the exhibition
     */
    @Override
    public boolean involvesPerformer(Performer p) {
        
        Performer[] involved = this.getPerformers();
        
        if(p instanceof Artist){
            for (Performer involved1 : involved) {
                if (p.equals(involved1)) {
                    return true;
                }
            }
        }else{
            // si p es un collectivo se compara cada miembro del colectivo con cada posicion de involved
            Performer[] aux = ((Collective)p).getComponents();
            for(Performer involved1 : involved){
                for (Performer aux1 : aux){
                    if(aux1.equals(involved1)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the performers of the exhibition
     * @return Performer[]
     */
    @Override
    public Performer[] getPerformers() {
        //si es un artista lo devolvemos en un Performer[]
        if(this.protagonist instanceof Artist){
            Performer[] p = new Performer[1];
            p[0]=this.protagonist;
            return p;
        }else{
        //si no es un artista es un collectivo
            Performer[] p = ((Collective)this.protagonist).getComponents();
            Performer[] q = new Performer[p.length+1];
            q[0]=this.protagonist;
            System.arraycopy(p, 0, q, 1, p.length);
            return q;
        }
    }

    /**
     * Compares two exhibitions
     * @param o
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o){
        int i = this.getStartDate().compareTo(((Exhibition)o).getStartDate());
        if (i == 0)
        {
            return this.getName().compareTo(((Exhibition)o).getName());
        }
        return i;
    }

    
    /**
     * Text representation of a exhibition
     * @return String
     */
    @Override
    public String toString(){
        String s="";
        s=s+"Exhibition which name is "+this.getName()+"\n";
        s=s+"Starts the "+this.getStartDate().toString()+"\n";
        s=s+"Ends the "+this.getEndingDate().toString()+"\n";
        s=s+"on "+this.getLocation().toString()+"\n";
        Performer[] aux =this.getPerformers();
        s=s+"Stars :\n";
        for (Performer aux1 :aux){
            s=s+"  "+aux1.getName()+"\n";
        }
        return s;
    }

    @Override
    public String toXML() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements (client)
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("exhibition");
        doc.appendChild(rootElement);


        // title elements
        Element xTitle = doc.createElement("title");
        xTitle.appendChild(doc.createTextNode(this.getName()));
        rootElement.appendChild(xTitle);
        
        // organizer elements
        Element xOrganizer = doc.createElement("organizer");
        xOrganizer.appendChild(doc.createTextNode(this.getOrganizer()));
        rootElement.appendChild(xOrganizer);

        // location elements
        Element xLocation = doc.createElement("location");
        xLocation.appendChild(doc.createTextNode("A"));
        rootElement.appendChild(xLocation);
        
        // protagonist elements
        Element xProtagonist = doc.createElement("protagonist");
        Performer[] p = this.getPerformers();
        xProtagonist.appendChild(doc.createTextNode("B"));
        rootElement.appendChild(xProtagonist);
        
        
        // timetable elements
        Element xTimetable = doc.createElement("timetable");
        rootElement.appendChild(xTimetable);

        for (int i = 0; i < timetable.length; i++){

            // cards elements
            Element xHour = doc.createElement("hour");
            xHour.appendChild(doc.createTextNode(this.getStartDate().toString()));
            xTimetable.appendChild(xHour);
            
            // set attribute to card element
            Attr attr = doc.createAttribute("hour");
            attr.setValue("open");
            xHour.setAttributeNode(attr);

        }
        
        if (this.getWebs() != null){
            // web elements
            Element xWeb = doc.createElement("web");
            String[] w = this.getWebs();
            for (int i = 0; i < this.getWebs().length; i++){
                xWeb.appendChild(doc.createTextNode(webs[i]));
            }
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
        
        String a = this.getLocation().toXML();
        String sr = a.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><location>", "");
        String b = sr.replace("</location>", "");
        finalstring = finalstring.replace("A",b);
        if (p.length > 1){
            // Collective
            String ar = ((Collective)p[0]).toXML();
            String at = ar.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
            finalstring = finalstring.replace("B", at);
        }
        else {
            // Artist
            String col = ((Artist)p[0]).toXML();
            String colt = col.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
            finalstring = finalstring.replace("B", colt);
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
