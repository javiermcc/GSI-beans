/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.util.TreeSet;

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
