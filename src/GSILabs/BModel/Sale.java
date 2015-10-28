/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILabs.BModel;

import java.util.Date;

/**
 *
 * @author labora1
 */
public class Sale implements Comparable{
    
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
}
