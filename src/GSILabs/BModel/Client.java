/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILabs.BModel;

import GSILabs.Exceptions.*;
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
 *
 * @author elementary
 */
public class Client implements Comparable, XMLRepresentable{
    
    /**
     * 
     */
    
    private final int dni;                      // the identifier of the client is always a 8-digit number
    private final String name;           // the name of the client
    private final String surnames;       // the surnames of the client
    private final ModelDate birthdate;   // the birthdate of the client
    private TreeSet<String> cards;               // the cards of the client
    
    /**
     * Constructor of the client
     * @param dni
     * @param name
     * @param surnames
     * @param birthdate
     * @param card 
     */ 
    public Client(int dni, String name, String surnames, ModelDate birthdate, String card){
        
        this.dni = dni;
        this.name = name;
        this.surnames = surnames;
        this.birthdate = birthdate;
        this.cards=new TreeSet<>();
        this.cards.add(card);
        
    }
    
    /**
     * Gets the client's dni
     * @return int
     */
    public int getDni(){
        
        return this.dni;
        
    }
    
    /**
     * Gets the client's name
     * @return String
     */
    public String getName(){
        
        return this.name;
        
    }
    
    /**
     * Gets the client's surname
     * @return String
     */
    public String getSurnames(){
        
        return this.surnames;
        
    }
    
    /**
     * Gets client's birthdate
     * @return ModelDate
     */
    public ModelDate getBirthdate(){
        
        return this.birthdate;
        
    }

    /**
     * Gets client's cards
     * @return String[] with the cards
     */
    public String[] getCards(){
        
        return cards.toArray(new String[cards.size()]);
    }
    
    /**
     * Adds a card to the client's cards
     * @param card 
     * @return boolean
     */
    public boolean addCard(String card){
        this.cards.add(card);
        return this.cards.contains(card);
        /*String[] NewCards = new String[cards.length+1];
        for(int i=0;i<cards.length;i++){
            NewCards[i]=cards[i];
        }
        NewCards[NewCards.length-1]=card;
        this.cards=NewCards;*/
    }
    
    /**
     * Deletes a card from the client's card
     * @param card 
     * @return  
     * @throws GSILabs.Exceptions.MissingCardException  
     */
    public boolean deleteCard(String card) throws MissingCardException{
        if (this.cards.contains(card)){
            this.cards.remove(card);
            return !(this.cards.contains(card));
        }
        else{
            throw new MissingCardException();
        }
        /*String[] NewCards = new String[cards.length-1];
        //BS search if the card is in the array before calling this method
        for(int i = 0 ; i<position;i++){
            NewCards[i]=cards[i];
        }
        for(int i = position+1;i<cards.length;i++){
            NewCards[i-1]=cards[i];
        }
        this.cards=NewCards;*/
    }
    
    /**
     * Hace falta en BS para addCardToClient y addSale asi que hay que hacerlo bien
     * Search a card returns if the client has this card
     * @param card
     * @return int
     */
    public boolean searchCard(String card)
    {// this method return if the client has this card
       Iterator<String> cIterator=cards.iterator();
       String aux;
       while(cIterator.hasNext()){
           aux=cIterator.next();
           if(card.equals(aux)){
               return true;
           }
       }
       return false;
    }
    
    /**
     * Gets the card of a position
     * @param pos The position
     * @return String the card we want
     */
    public String searchCard(int pos){
        
        String aux;
        int i = 0;
        Iterator<String> iterator = cards.iterator();
        while(iterator.hasNext()){

            aux = iterator.next();
            if (i == pos){

                return aux;
            }
            i++;
        }
        return null;
    }
    
    /**
     * Text representation of a client
     * @return String
     */
    @Override
    public String toString()
    { 
        return "Dni: " + this.getDni() +
               ", Name: " + this.getName() +
               ", Surnames: " + this.getSurnames() +
               ", Birthday: " + this.getBirthdate() + 
               ", Cards: " + this.cards.toString();
    } 
    
    /**
     * SEGURAMENTE SE BORRE NO USAR --------------------------------------------
     * Converts the cards into a string, text representation of the cards
     * @return String
     */
    /*public String getCardsString(){
        /*String end="";
        
        for(int i = 0 ; i<cards.length;i++){
            end=end+cards[i]+" ";
        }
        //System.out.println(end);
        return end;
        //return this.cards[0];//end;
        
    }
    */
    
    /**
     * Compares two people
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o)
    {
        return (this.dni==((Client)o).getDni());
        
        /*if (person == null)
        {
            return false;
        }
        if (this.getDni() != person.getDni())
        {
            if (this.name == null ? person.name == null : this.name.equals(person.name))
            {
                if (this.surnames == null ? person.surnames == null : this.surnames.equals(person.surnames))
                {
                    if (this.birthdate == person.birthdate)
                    {
                        if (this.cards.equals(person.cards))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;*/
    }

    @Override
    public int compareTo(Object o) {
        return this.dni-((Client)o).getDni();
            /*int i = this.getDni()-((Client)o).getDni();
            if(i==0){
                return this.getName().compareTo(((Client)o).getName());
            }
            return i;*/
    }

    @Override
    public String toXML() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("client");
        doc.appendChild(rootElement);


        // dni elements
        Element dni = doc.createElement("dni");
        dni.appendChild(doc.createTextNode(Integer.toString(this.getDni())));
        rootElement.appendChild(dni);
        
        // name elements
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(this.getName()));
        rootElement.appendChild(name);

        // surnames elements
        Element surnames = doc.createElement("surnames");
        surnames.appendChild(doc.createTextNode(this.getSurnames()));
        rootElement.appendChild(surnames);
        
        // birthdate elements
        Element birthdate = doc.createElement("birthdate");
        birthdate.appendChild(doc.createTextNode(this.getBirthdate().toString()));
        rootElement.appendChild(birthdate);
        
        // cards elements
        Element cCards = doc.createElement("cards");
        rootElement.appendChild(cCards);

        for (int i = 0; i < cards.size(); i++){

            // cards elements
            Element card = doc.createElement("card");
            card.appendChild(doc.createTextNode(this.searchCard(i)));
            cCards.appendChild(card);
            
            // set attribute to card element
            Attr attr = doc.createAttribute("card");
            attr.setValue(Integer.toString(i));
            card.setAttributeNode(attr);

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