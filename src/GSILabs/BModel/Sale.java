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
 * @author labora1
 */
public class Sale implements Comparable, XMLRepresentable{
    
    private Client client;
    private Ticket ticket;
    private float price;            // The ticket's price
    private String cCard;                   
    private Date soldDate=null;             // This value would be null if the ticket hasn't been sold
    
    public Sale (Client client, Ticket ticket, float price, String cCard){
        
        this.client = client;
        this.ticket = ticket;
        this.price = price;
        this.cCard = cCard;
    }
    
    public Client getClient(){
        
        return this.client;
    }
    
    public Ticket getTicket(){
        
        return this.ticket;
    }
    
    public float getPrice(){
        
        return this.price;
    }
    
    public String getcCard(){
        
        return this.cCard;
    }
    
    /**
     * This method returns the value of the soldDadte attribute. 
     * It could be null.
     * @return The value of soldDate
     */
    public Date getSoldDate(){
        
        return this.soldDate; 
    }

    @Override
    public int compareTo(Object o) {
        
        int i=0;
        
        if(((Sale)o).getTicket().getEvent().getName().equals(this.getTicket().getEvent().getName())){
            int aux1=this.ticket.getId()+this.client.getDni();
            int aux2=((Sale)o).getTicket().getId()+((Sale)o).getClient().getDni();
            return aux1-aux2;
        }else{
            return this.ticket.getEvent().getName().compareTo(((Sale)o).getTicket().getEvent().getName());
        }
        
    }

    @Override
    public String toXML() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements (sale)
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("sale");
        doc.appendChild(rootElement);

        // client elements
        Element xClient = doc.createElement("client");
        xClient.appendChild(doc.createTextNode("##C##"));
        rootElement.appendChild(xClient);
        
        // ticket elements
        Element xTicket = doc.createElement("ticket");
        xTicket.appendChild(doc.createTextNode("##T##"));
        rootElement.appendChild(xTicket);
        
        // price elements
        Element xPrice = doc.createElement("price");
        xPrice.appendChild(doc.createTextNode(Float.toString(this.getPrice())));
        rootElement.appendChild(xPrice);

        // cards elements
        Element xCard = doc.createElement("card");
        xCard.appendChild(doc.createTextNode(this.getcCard()));
        rootElement.appendChild(xCard);

        
        // write the content into string
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StringWriter outWriter = new StringWriter();
        StreamResult result = new StreamResult( outWriter );

        transformer.transform(source, result);
        StringBuffer sb = outWriter.getBuffer(); 
        String finalstring = sb.toString();
        
        String c = this.getClient().toXML();
        String c2 = c.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><client>", "");
        String c3 = c2.replace("</client>", "");
        finalstring = finalstring.replace("##C##",c3);


        String t = this.getTicket().toXML();
        String t2 = t.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><ticket>", "");
        String t3 = t2.replace("</ticket>", "");
        finalstring = finalstring.replace("##T##", t3);

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
