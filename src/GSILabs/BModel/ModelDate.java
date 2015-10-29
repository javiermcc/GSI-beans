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
