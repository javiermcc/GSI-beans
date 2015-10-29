/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;


import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.util.Date;

/**
 *
 * @author linux1
 */
public class Concert implements ImpermanentEvent,Comparable, XMLRepresentable{
    
    private Location location; //the location of the concert
    private Performer performer; // the performer of the concert
    private String name; // name associated with the concert
    private Date date; // the date the concert takes place, also the start date
    
    /**
     * Constructor of a concert
     * @param l the Location
     * @param p the Performer
     * @param n the Name
     * @param d the Date
     */
    public Concert(Location l,Performer p,String n,Date d){
        this.location=l;
        this.performer=p;
        this.name=n;
        this.date=d;
    }

    /**
     * Gets concert's start date
     * @return Date
     */
    @Override
    public Date getStartDate() {
        return this.date;

    }
    /**
     * Gets concert's location
     * @return Location
     */
    @Override
    public Location getLocation() {
        return this.location;
    }

    /**
     * Gets concert's name
     * @return String
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets concert's dates
     * @return Date[]
     */
    @Override
    public Date[] getDates() {
        Date[] dates=null;
        dates[0]=this.date;
        return dates;
    }

    /**
     * Checks if the perfomer is in the concert
     * @param p
     * @return boolean, true if the performer is in the concert
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
     * Gets the performers of the concert
     * @return Performer[]
     */
    @Override
    public Performer[] getPerformers() {
        //si es un artista lo devolvemos en un Performer[]
        if(this.performer instanceof Artist){
            Performer[] p = new Performer[1];
            p[0]=this.performer;
            return p;
        }else{
        //si no es un artista es un collectivo
            Performer[] p = ((Collective)this.performer).getComponents();
            Performer[] q = new Performer[p.length+1];
            q[0]=this.performer;
            System.arraycopy(p, 0, q, 1, p.length);
            return q;
        }
 }

    /**
     * Compares two concerts
     * @param o
     * @return int, 0 if are the same
     */
    @Override
    public int compareTo(Object o) {
       int i = this.getStartDate().compareTo(((Concert)o).getStartDate());
       if(i==0){
           return this.getName().compareTo(((Concert)o).getName());
       }
       return i;
    }
    
    /**
     * Text representation of a concert
     * @return String
     */
    @Override
    public String toString(){
        String s="";
        s=s+"Concert which name is "+this.getName()+"\n";
        s=s+"Starts the "+this.getStartDate().toString()+"\n";
        s=s+"on "+this.getLocation().toString()+"\n";
        Performer[] aux =this.getPerformers();
        s=s+"Stars :\n";
        for (Performer aux1 :aux){
            s=s+"  "+aux1.getName()+"\n";
        }
        return s;
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
