/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

/**
 * Artist implements the interface Performer. Each artist has a unique name and
 * a description. It's possible to an artist to have or not a webpage.
 * @author drzkn
 * @version 1.0 (17/9/2015)
 */
public class Artist implements Performer,Comparable{

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
        
        name=n;
        description=n;
        web=null;
        
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
     * Returns the webpage of an artist
     * @return The artist's webpage
     */
    public String getWeb(){
        
        return web;
        
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
    public boolean equals(Artist a1){
        
        return this.name.equals(a1.name);
        
    }
    
    /**
     * Compares an artist and a collective
     * @param c The collective we are going to compare with
     * @return True if the name is the same. False what else.
     */
    public boolean equals(Collective c){
        
        return this.name.equals(c.getName());
        
    }
    
    /**
     * Compares two artists.
     * @param o refers to the artist we are going to campare
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o) {

        return this.getName().compareTo(((Artist)o).getName());
        
    }
    
}
