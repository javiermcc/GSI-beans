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
import java.util.ArrayList;
import java.util.Date;
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
 *
 * @author linux1
 */
public class Festival implements LastingEvent, Comparable, XMLRepresentable{

    TreeSet<Concert> concerts;  // The concerts that make a festival
    Date startDate;             // The date of the first concert, the festival's start date
    Date endDate;               // The date of the last concert, the festival's end date
    String name;                // The name of the festival  
    
    
    public Festival(Concert c,Date sd,Date ed,String n){
        this.concerts=new TreeSet<>();
        concerts.add(c);
        this.startDate=sd;
        this.endDate=ed;
        this.name=n;
    }
    
    
    /**
     * Gets the festival's start date
     * @return Date
     */
    @Override
    public Date getStartDate() {
        
        return startDate;
    }

    /**
     * Gets the festival's end date
     * @return Date
     */
    @Override
    public Date getEndingDate() {
        return endDate;
    }

    /**
     * Gets festival's name
     * @return String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets festival's concert's dates
     * @return Date[]
     */
    @Override
    public Date[] getDates() {
        Date[] dates = null;
        int i=0;
        Concert c;
        Iterator<Concert> iterator = concerts.iterator();
        while(iterator.hasNext()){
            c = iterator.next();
            dates[i]=c.getStartDate();
            i++;
        }
        return dates;
    }

    /**
     * Checks if the perfomer is in the festival
     * @param p
     * @return boolean, true if the performer is in the concert
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
     * Gets festival's performers
     * @return Performer[]
     */
    @Override
    public Performer[] getPerformers() {
        
        ArrayList<Performer> performers=new ArrayList<>();
        Iterator<Concert> iterator = concerts.iterator();
        Concert c;
        Performer[] perfaux;
        while (iterator.hasNext()){
            //recuperamos los performers de cada concierto
            c=iterator.next();
            perfaux=c.getPerformers();
            for(Performer p:perfaux){
                //por cada performer del concierto comprobamos si ya esta añadido,si no lo esta lo añadimos
                if(!performers.contains(p)){
                    performers.add(p);
                }
            }
        }
        return (Performer[])performers.toArray();
    }

    /**
     * Compares two festivals
     * @param o
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o) {
        
        int i = this.startDate.compareTo(((Festival)o).startDate);
        if (i == 0)
        {
            return this.getName().compareTo(((Festival)o).getName());
        }
        return i;
    }
    
    
    /**
     * Text representation of a festival
     * @return String
     */@Override
    public String toString(){
        String s="";
        s=s+"Festival which name is "+this.getName()+"\n";
        s = s + "Festival starts: "+ this.getStartDate().toString() + "\n" + "Ends: " + this.getEndingDate().toString() + "\n";
        Concert c;
        Iterator<Concert> itc= concerts.iterator();
        s=s+"Involves the following concerts:\n";
        while (itc.hasNext()){
            c=itc.next();
            s=s+"---------------------------------------";
            s=s+c.toString();
        }
        return s;
    }
    
    /**
     * Adds a concert to the festival
     * @param c
     * @return True, if added succesfully
     */
    public boolean addConcert(Concert c){
        
        this.concerts.add(c);
        return this.concerts.contains(c);
        
    }
    
    /**
     * Gets festival's concerts
     * @return Concert[]
     */
    public Concert[] getConcerts(){
        Concert[] aux = new Concert[concerts.size()];
        int i = 0;
        Iterator<Concert> iterator = concerts.iterator();
        while(iterator.hasNext()){
            
            aux[i] = iterator.next();
            i++;
        }
        return aux;
    }

    @Override
    public String toXML() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements (festival)
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("festival");
        doc.appendChild(rootElement);


        // name elements
        Element xName = doc.createElement("name");
        xName.appendChild(doc.createTextNode(this.getName()));
        rootElement.appendChild(xName);
        
        // date elements
        Element xStartDate = doc.createElement("startDate");
        rootElement.appendChild(xStartDate);
        int year = this.startDate.getYear() + 1900;
        int month = this.startDate.getMonth() + 1;
        int day = this.startDate.getDate();
        
        Element xYear = doc.createElement("year");
        xYear.appendChild(doc.createTextNode(Integer.toString(year)));
        xStartDate.appendChild(xYear);
        
        // month elements
        Element xMonth = doc.createElement("month");
        xMonth.appendChild(doc.createTextNode(Integer.toString(month)));
        xStartDate.appendChild(xMonth);
        
        // day elements
        Element xDay = doc.createElement("day");
        xDay.appendChild(doc.createTextNode(Integer.toString(day)));
        xStartDate.appendChild(xDay);
        int hours = this.startDate.getHours() + 1;
        int minutes = this.startDate.getMinutes() + 1;
        
        Element xHour = doc.createElement("hours");
        xHour.appendChild(doc.createTextNode(Integer.toString(hours)));
        xStartDate.appendChild(xHour);
        
        Element xMinutes = doc.createElement("minutes");
        xMinutes.appendChild(doc.createTextNode(Integer.toString(minutes)));
        xStartDate.appendChild(xMinutes);
       
        // endDate elements
        Element xEndDate = doc.createElement("endDate");
        rootElement.appendChild(xEndDate);
        year = this.endDate.getYear() + 1900;
        month = this.endDate.getMonth() + 1;
        day = this.endDate.getDate();
        
        xYear = doc.createElement("year");
        xYear.appendChild(doc.createTextNode(Integer.toString(year)));
        xEndDate.appendChild(xYear);
        
        // month elements
        xMonth = doc.createElement("month");
        xMonth.appendChild(doc.createTextNode(Integer.toString(month)));
        xEndDate.appendChild(xMonth);
        
        // day elements
        xDay = doc.createElement("day");
        xDay.appendChild(doc.createTextNode(Integer.toString(day)));
        xEndDate.appendChild(xDay);
        hours = this.endDate.getHours() + 1;
        minutes = this.endDate.getMinutes() + 1;
        
        xHour = doc.createElement("hours");
        xHour.appendChild(doc.createTextNode(Integer.toString(hours)));
        xEndDate.appendChild(xHour);
        
        xMinutes = doc.createElement("minutes");
        xMinutes.appendChild(doc.createTextNode(Integer.toString(minutes)));
        xEndDate.appendChild(xMinutes);
        
        // concerts elements
        Element xConcerts = doc.createElement("concerts");
        rootElement.appendChild(xConcerts);
        Concert[] w = this.getConcerts();
        for (int i = 0; i < w.length; i++){
            xConcerts.appendChild(doc.createTextNode("##C##"+ i));
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
            
        
        
        for (int i = 0; i < w.length; i++){
            
            String a = w[i].toXML();
            String sr = a.replaceFirst("<?.*?>", "");
            finalstring=finalstring.replace("##C##"+i,sr);
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
