/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;


import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.util.Arrays;

/**
 *
 * @author drzkn
 */
public class Ticket implements Comparable, XMLRepresentable{
    
    /**
     * It's not possible for a pair event-dnis to be created if it exists another
     * pair with the same value.
     */
    private final int id;
    private final Event event;              /*The event the ticket is associated to*/
    private final int[] identifiers;        /*Identifies the people who are allowed by the ticket using the seat numbers, that must be unique for the event*/
    private final Client associated;        /*The client the ticket is associated to*/
    private boolean used;                   /*Its value define if a ticket have been used or not*/
    
    /**
     * Constructor of the ticekt
     * @param id
     * @param e 
     */
    public Ticket (int id, Event e){
        
        this.id = id;
        this.event = e;
        this.identifiers = null;
        this.associated = null;
        this.used = false;
    }
    
    /**
     * Constructor of the ticekt
     * @param id
     * @param e 
     * @param value 
     */
    public Ticket (int id, Event e,boolean value){
        
        this.id = id;
        this.event = e;
        this.identifiers = null;
        this.associated = null;
        this.used = value;
    }
    
    /**
     * Constructor of the ticket with the associated
     * @param id
     * @param e
     * @param a 
     */
    public Ticket (int id, Event e, Client a){
        
        this.id = id;
        this.event = e;
        this.identifiers = null;
        this.associated = a;
        this.used = false;
    }
    
    /**
     * Constructor of the ticket with the associated
     * @param id
     * @param e
     * @param a 
     * @param value 
     */
    public Ticket (int id, Event e, Client a,boolean value){
        
        this.id = id;
        this.event = e;
        this.identifiers = null;
        this.associated = a;
        this.used = value;
    }
    
    /**
     * It creates a Ticket that only allows one client to get into an event
     * @param id
     * @param e The event the ticket belongs to
     * @param dnis The whole set of clients that are associated to a ticket
     * @param a The client the ticket is associated with
     */
    public Ticket (int id, Event e,int[] dnis, Client a){
        
        this.id = id;
        this.event = e;
        this.identifiers = dnis;
        this.associated = a;
        this.used = false;
    }
    
    /**
     * Gets the id of the ticket for the event
     * @return int
     */
    public int getId(){
        
        return this.id;
    }
    
    /**
     * This method returns the value of the event of the ticket
     * @return the event associated to the ticket
     */
    public Event getEvent(){
        
        return event; 
    }
    
    /**
     * This method returns the collection of all the dnis that are linked to this ticket
     * @return return a String with all the dnis
     */
    public int[] getIDs(){
        
        return this.identifiers; 
    }
    
    /**
     * This method returns the client associated to this ticket
     * @return The dni of the client associated to this ticket
     */
    public Client getAssociated(){
        
        return this.associated;
    }
    
    /**
     * Returns the value of the attribute used of a ticket
     * @return TRUE if the ticket have been used, FALSE what else.
     */
    public boolean getUsed(){
        
        return this.used; 
    }
    
    /**
     * Transforms the value of the field used into true.
     * A ticket that have been used can't be turned to unused.
     */
    public void use(){
        
        this.used=true;   
    }
    
    /**
     * This method returns a textual description of this ticket
     * @return A string with the textual form of a ticket
     */
    @Override
    public String toString() {
        
        if (this.getUsed()){

                return "Ticket's ID: "+this.getId()+
                        "Ticket for the event: "+this.getEvent().toString()+"\nClient that allow to enter: "
                +Arrays.toString(getIDs())+"\nClient associated: "+ getAssociated()+"\nThis ticket has been used";
        }
        else{
                return "Ticket for the event: "+this.getEvent().toString()+"\nClient that allow to enter: "
                +Arrays.toString(getIDs())+"\nClient associated: "+ getAssociated()+"\nThis ticket has not been used";
        }
        
    }
    
    /**
     * Compare each field of two tickets. 
     * @param t A ticket to compare
     * @return TRUE if all the fields of the two tickets are equals and FALSE what else
     */
    public boolean equals(Ticket t){
        
        if (this.getEvent().equals(t.getEvent())){
            if (this.getId() == t.getId()){  
                if (Arrays.equals(this.getIDs(), t.getIDs())){   
                    if (this.getAssociated().equals(t.getAssociated())){  
                        return true;
                    }
                }
            }
        }
        return false; 
    }
    
    /**
     * This is a method a bit similar to the previous one, but with one difference, in the specifiacation
     * of the system is written that a ticket can't exist if it exists another ticket with the 
     * same identifiers and the same event.
     * @param t The other ticket we are going to compare
     * @return 
     */
    public boolean exists(Ticket t){
        
        if (this.getId() == (t.getId())){   
            if (Arrays.equals(this.getIDs(), t.getIDs())){    
                return true;
            }
        }
        return false;        
    }

    /**
     * Compares two tickets
     * @param o
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o) {
        int i=0;
        
        if(((Ticket)o).getEvent().equals(this.getEvent())){
            return this.getId()-((Ticket)o).getId();
            
        }else{
            return this.getId()+((Ticket)o).getId();
        }
    }

    @Override
    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
