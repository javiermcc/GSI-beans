/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.serializable.XMLRepresentable;
import java.io.File;

/**
 *
 * @author elementary
 */
public class Location implements Comparable, XMLRepresentable{
    
    private final String name;      // the name of the location
    private final int capacity;     // the capacity of the location
    private final String country;   // the country of the location
    private final String province;  // the province of the location
    private final String city;      // the city of the location
    private final String street;    // the street of the location
    private final short number;     // the number of the location
    private String web;             // the web of the location, if it exists
    
    /**
     * Constructor of location without web
     * @param name
     * @param capacity
     * @param country
     * @param province
     * @param city
     * @param street
     * @param number 
     */
    public Location(String name, int capacity, String country, String province, String city, String street, Short number){
        
        this.name = name;
        this.capacity = capacity;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.number = number;
        this.web="";
    }
    
    /**
     * Constructor of location with web
     * @param name
     * @param capacity
     * @param country
     * @param province
     * @param city
     * @param street
     * @param number
     * @param web 
     */
    public Location(String name, int capacity, String country, String province, String city, String street, Short number, String web){
        
        this.name = name;
        this.capacity = capacity;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.number = number;
        this.web = web;
    }
    
    /**
     * Gets the location's name
     * @return String
     */
    public String getName(){
        
        return this.name;
    }
    
    /**
     * Gets the location's capacity
     * @return int
     */
    public int getCapacity(){
        
        return this.capacity;
    }
    
    /**
     * Gets the location's country
     * @return String
     */
    public String getCountry(){
        
        return this.country;
    }
    
    /**
     * Gets the location's province
     * @return String
     */
    public String getProvince(){
        
        return this.province;
    }
    
    /**
     * Gets the location's city
     * @return String
     */
    public String getCity(){
        
        return this.city;
    }
    
    /**
     * Gets the location's street
     * @return String
     */
    public String getStreet(){
        
        return this.street;
    }
    
    /**
     * Gets the location's street number
     * @return Short
     */
    public Short getNumber(){
        
        return this.number;
    }
    
    
    /**
     * Overwrites the web of the given location
     * @param web the new web
     */
    void setWeb(String web){
        
        this.web=web;
    }
    
    /**
     * Gets the location's web
     * @return String
     */
    public String getWeb(){
        
        return this.web;
    }
    
    /**
     * Gets the location
     * @return String
     */
    public String getLocation(){
        
        return this.country + ", "+this.province+", " + this.city + ", " + this.street + ", " + this.number;
    }
    
    /**
     * Text representation of a location
     * @return String
     */
    @Override
    public String toString(){
        
        return "Name: " + this.getName() +
               ", Capacity: " + this.getCapacity() +
               ", Country: " + this.getCountry() +
               ", Province: " + this.getProvince() +
               ", City: " + this.getCity() +
               ", Street: " + this.getStreet() +
               ", Number: " + this.getNumber() +
               ", Web:" + this.getWeb();
    }
    
    /**
     * Compares if two locations are the same
     * @param l
     * @return boolean 
     */
    public boolean equals(Location l) {
        
        if(this.getCapacity()==l.getCapacity()){
          if(this.getLocation().equalsIgnoreCase(l.getLocation())){
            if(this.getWeb().equalsIgnoreCase(l.getWeb())){
                return true;
            }
          }
        }
        return false;
    }

    /**
     * Compares if two locations are the same
     * @param o
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o) {
        
        int p = this.getCountry().compareTo(((Location)o).getCountry());
        if(p==0){
            int pr = this.getProvince().compareTo(((Location)o).getLocation());
            if(pr==0){
                int c=this.getCity().compareTo(((Location)o).getCity());
                if(c==0){
                    int s=this.getStreet().compareTo(((Location)o).getStreet());
                    if(s==0){
                        return this.getNumber()-((Location)o).getNumber();
                    }
                    return s;
                }
                return c;
            }
            return pr;
        }
        return p;
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
