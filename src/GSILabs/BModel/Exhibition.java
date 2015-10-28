/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.Date;

/**
 *
 * @author elementary
 */
public class Exhibition implements LastingEvent, Comparable
{
    private String title;               // the title of the exhibition
    private String organizer;           // the organizer of the exhibition
    private Date[] timetable;           // the Dates that the exhibition will open on chronologic order
    private Location location;          // the location of the exhibition
    private Performer protagonist;    // the protagonist of the exhibition
    private String[] webs;              // the webs of the exhibition
    
    /**
     * Constructor of exhibition
     * @param title
     * @param organizer
     * @param timetable
     * @param location
     * @param protagonist
     * @param webs 
     */
    public Exhibition(String title, String organizer, Date[] timetable,Location location, Performer protagonist, String[] webs)
    {
        this.title = title;
        this.organizer = organizer;
        this.timetable = timetable;
        this.location=location;
        this.protagonist = protagonist;
        this.webs = webs;
    }
    
    /**
     * Gets the title of the exhibition
     * @return the title of the exhibition
     */
    public String getTitle(){
        return this.title;
    }
    
    /**
     * Gets the Location of the exhibition
     * @return Location
     */    
    public Location getLocation(){
        return this.location;
    }
    
    /**
     * Gets the exhibition's name
     * @return String
     */   
    @Override
    public String getName() {
        return this.title;
    }
    
    /**
     * Gets the exhibition's organizer
     * @return String
     */
    public String getOrganizer()
    {
        return this.organizer;  
    }
    
    /**
     * Gets the exhibition's open date
     * @return Date
     */ 
    @Override
    public Date getStartDate() {
        return this.timetable[0];
    }
    
    /**
     * Gets the exhibition's close date
     * @return Date
     */
    @Override
    public Date getEndingDate() {
        return this.timetable[this.timetable.length-1];
    }
    
    /**
     * Gets the days the exhibition will be open
     * @return Date[]
     */
    @Override
    public Date[] getDates() {  
        return this.timetable;
    }
    
    /**
     * Gets the exhibition's webs
     * @return String[]
     */
    public String[] getWebs()
    {
        return this.webs;
    }


    /**
     * Checks if the perfomer is in the exhibition
     * @param p
     * @return boolean, true if the performer is in the exhibition
     */
    @Override
    public boolean involvesPerformer(Performer p) {
        
        Performer[] involved = this.getPerformers();
        
        if(p instanceof Artist){
            for (Performer involved1 : involved) {
                if (p.equals(involved1)) {
                    return true;
                }
            }
        }else{
            // si p es un collectivo se compara cada miembro del colectivo con cada posicion de involved
            Performer[] aux = ((Collective)p).getComponents();
            for(Performer involved1 : involved){
                for (Performer aux1 : aux){
                    if(aux1.equals(involved1)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the performers of the exhibition
     * @return Performer[]
     */
    @Override
    public Performer[] getPerformers() {
        //si es un artista lo devolvemos en un Performer[]
        if(this.protagonist instanceof Artist){
            Performer[] p = new Performer[1];
            p[0]=this.protagonist;
            return p;
        }else{
        //si no es un artista es un collectivo
            Performer[] p = ((Collective)this.protagonist).getComponents();
            Performer[] q = new Performer[p.length+1];
            q[0]=this.protagonist;
            System.arraycopy(p, 0, q, 1, p.length);
            return q;
        }
    }

    /**
     * Compares two exhibitions
     * @param o
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o)
    {
        int i = this.getStartDate().compareTo(((Exhibition)o).getStartDate());
        if (i == 0)
        {
            return this.getName().compareTo(((Exhibition)o).getName());
        }
        return i;
    }

    
    /**
     * Text representation of a exhibition
     * @return String
     */
    @Override
    public String toString(){
        String s="";
        s=s+"Exhibition which name is "+this.getName()+"\n";
        s=s+"Starts the "+this.getStartDate().toString()+"\n";
        s=s+"Ends the "+this.getEndingDate().toString()+"\n";
        s=s+"on "+this.getLocation().toString()+"\n";
        Performer[] aux =this.getPerformers();
        s=s+"Stars :\n";
        for (Performer aux1 :aux){
            s=s+"  "+aux1.getName()+"\n";
        }
        return s;
    }
}
