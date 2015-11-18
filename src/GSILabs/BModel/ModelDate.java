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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author drzkn
 */
public class ModelDate implements XMLRepresentable{
    
    private final int year;     // Date's year
    private final int month;    // Date's month
    private final int day;      // Date's day
    
    /**
     * Constructor of the modeldate
     * @param y
     * @param m
     * @param d 
     */
    public ModelDate(int y, int m, int d){
        
        this.year=y;
        this.month=m;
        this.day=d;
        
    }
    
    /**
     * Gets the day of the modeldate
     * @return int
     */
    public int getDay(){
        
        return this.day;
        
    }
    
    /**
     * Gets the month of the modeldate
     * @return int
     */
    public int getMonth(){
        
        return this.month;
        
    }
    
    /**
     * Gets the year of the modeldate
     * @return int
     */
    public int getYear(){
        
        return this.year;
        
    }
    
    /**
     * Text representation of a modeldate in yyyy/mm/dd format
     * @return String
     */
    @Override
    public String toString(){
        
        return getYear()+"-"+getMonth()+"-"+getDay();
        
    }

    @Override
    public String toXML() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements (collective)
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("date");
        doc.appendChild(rootElement);

        // year elements
        Element xYear = doc.createElement("year");
        xYear.appendChild(doc.createTextNode(Integer.toString(this.year)));
        rootElement.appendChild(xYear);
        
        // month elements
        Element xMonth = doc.createElement("month");
        xMonth.appendChild(doc.createTextNode(Integer.toString(this.month)));
        rootElement.appendChild(xMonth);
        
        // day elements
        Element xDay = doc.createElement("day");
        xDay.appendChild(doc.createTextNode(Integer.toString(this.day)));
        rootElement.appendChild(xDay);
        
        
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
