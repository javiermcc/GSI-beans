/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILabs.BModel;

/**
 *
 * @author labora1
 */
public class Sale {
    
    private Client client;
    private Ticket ticket;
    private float price;
    
    public Sale (Client client, Ticket ticket, float price){
        
        this.client = client;
        this.ticket = ticket;
        this.price = price;
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
}
