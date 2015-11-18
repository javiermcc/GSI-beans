/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BSystem;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Client;
import GSILabs.BModel.Collective;
import GSILabs.BModel.Concert;
import GSILabs.BModel.Event;
import GSILabs.BModel.Exhibition;
import GSILabs.BModel.Festival;
import GSILabs.BModel.Location;
import GSILabs.BModel.ModelDate;
import GSILabs.BModel.Performer;
import GSILabs.BModel.Sale;
import GSILabs.BModel.Ticket;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.spreadsheet.TableGroup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author linux1
 */
public class BusinessSystem implements TicketOffice, XMLRepresentable{
    
    public TreeSet<Concert> concerts;
    public TreeSet<Exhibition> exhibitions;
    public TreeSet<Festival> festivals;
    public TreeSet<Client> clients;
    public TreeSet<Ticket> tickets;
    public TreeSet<Location> locations;
    public TreeSet<Artist> artists;
    public TreeSet<Collective> collectives;
    public TreeSet<Sale> sales;
    
    public BusinessSystem(){
        
        concerts=new TreeSet<>();
        exhibitions=new TreeSet<>();
        festivals=new TreeSet<>();
        clients=new TreeSet<>();
        tickets=new TreeSet<>();
        locations=new TreeSet<>();
        artists=new TreeSet<>();
        collectives=new TreeSet<>();
        sales=new TreeSet<>();
    }

    @Override
    public boolean addNewConcert(Concert c) {
        
        if (c != null){
            Performer[] cPerfor=c.getPerformers();
            Location auxLoc=c.getLocation();
            if (existLocation(auxLoc)){
                if(concerts.isEmpty()){            
                    concerts.add(c);
                    return concerts.contains(c);
                }
                Iterator<Concert> iterator = concerts.iterator();
                Concert aux;
                
                while (iterator.hasNext()){
                    aux=iterator.next();
                    
                    long time=aux.getStartDate().getTime() - c.getStartDate().getTime();
                    time = time /(1000*60*60*24);
                    time = Math.abs(time);
                    if(time<1){//the concerts have less than 24 hours between them
                        Performer[] auxPerfor=aux.getPerformers();
                        for(int k=0;k<cPerfor.length;k++){
                            for(int k2=0;k2<auxPerfor.length;k2++){
                                if(cPerfor[k].equals(auxPerfor[k2])){
                                    // some performer is in both concerts
                                    return false;
                                }
                            }
                        }
                    }
                }
                concerts.add(c); 
                return concerts.contains(c);
            } 
        }
        return false;
    }

    @Override
    public boolean replaceConcert(Concert c) {
        
        if (c != null){
            concerts.remove(c);
            concerts.add(c);
            return concerts.contains(c);
        }
        return false;
    }

    @Override
    public boolean deleteConcert(Concert c) {
        
        if (c != null){
            concerts.remove(c);
            return !(concerts.contains(c));
        }
        return false;
    }

    @Override
    public boolean addNewExhibition(Exhibition e) {
        
        if (e != null){
            exhibitions.add(e);
            return exhibitions.contains(e);
        }
        return false;
    }

    @Override
    public boolean replaceExhibition(Exhibition e) {
        
        if (e != null){
            exhibitions.remove(e);
            exhibitions.add(e);
            return exhibitions.contains(e);
        }
        return false;
    }

    @Override
    public boolean deleteExhibition(Exhibition e) {
        
        if (e != null){
            exhibitions.remove(e);
            return !(exhibitions.contains(e));
        }
        return false;
    }

    @Override
    public boolean addNewFestival(Festival f) {
        
        if (f != null){
            festivals.add(f);
            return festivals.contains(f);
        }
        return false;
    }

    @Override
    public boolean addConcertToFestival(Festival f, Concert c) {
        
        if ((f != null) && (c != null)){
            boolean existsc = false;
            boolean existsf = false;
            Concert auxc;
            Iterator<Concert> iteratorc = concerts.iterator();
            while (iteratorc.hasNext()){
                auxc = iteratorc.next();
                if (c.compareTo(auxc) == 0){ // exists concert
                    existsc = true;
                }
            }
            
            Festival auxf;
            Festival[] auxf2 = null;
            int i = 0;
            Iterator<Festival> iteratorf = festivals.iterator();
            while(iteratorf.hasNext()){
                auxf = iteratorf.next();
                if ((auxf.getStartDate().getTime() <= c.getStartDate().getTime()) && (c.getStartDate().getTime() <= auxf.getEndingDate().getTime())){
                    auxf2[i] = auxf;
                    i++;
                    if (auxf.compareTo(f) == 0){
                        existsf = true;
                    }
                }
            }   
            
            if (existsc && existsf){
                for (int j = 0; j < auxf2.length; j++){
                    for (int k = 0; k < auxf2[j].getConcerts().length; k++){
                        if ((auxf2[j].getConcerts()[k].compareTo(c) == 0) && (auxf2[j].getConcerts()[k].getStartDate() != c.getStartDate())){
                            f.addConcert(c);
                            Concert[] con =f.getConcerts();
                            for(Concert co :con){
                                if(co.equals(c)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean replaceFestival(Festival f) {
        
        if (f != null)
        {
            festivals.remove(f);
            festivals.add(f);
            return festivals.contains(f);
        }
        return false;
    }

    @Override
    public boolean deleteFestival(Festival f) {
        
        if (f != null)
        {
            festivals.remove(f);
            return !(festivals.contains(f));
        }
        return false;
    }

    @Override
    public boolean existsEvent(Event e) {
        
        if (e != null)
        {
            if (e instanceof Festival){
                Festival aux1;
                Iterator<Festival> Fiterator= festivals.iterator();
                while(Fiterator.hasNext()){
                    aux1=Fiterator.next();
                    if(((Festival)e).equals(aux1)){
                        return true;
                    }
                }
            }     
            
            if (e instanceof Concert){
                Concert aux2;
                Iterator<Concert> Citerator= concerts.iterator();
                while(Citerator.hasNext()){
                    aux2=Citerator.next();
                    if(((Concert)e).equals(aux2)){
                        return true;
                    }
                }
            }
            
            if (e instanceof Exhibition){
                Exhibition aux3;
                Iterator<Exhibition> Eiterator= exhibitions.iterator();
                while(Eiterator.hasNext()){
                    aux3=Eiterator.next();
                    if(((Exhibition)e).equals(aux3)){
                        return true;
                    }
                }
            }
            
            return false;  
  
        }
        return false;
    }

    @Override
    public Event[] retrieveEvents(String name) {
        //CAMBIAR 
        if (name != null)
        {
            ArrayList<Event> list = new ArrayList<>();
            
            /*Event[] events = new Event[1];
            Event[] eventsaux;
            boolean empty=true;
            */
            Festival aux1;
            Iterator<Festival> Fiterator= festivals.iterator();
            while(Fiterator.hasNext()){
                aux1=Fiterator.next();
                if(aux1.getName().contains(name)){
                    list.add(aux1);
                    /*if(empty){
                        events[0]=aux1;
                        empty=false;
                    }else{
                        eventsaux= new Event[events.length+1];
                        for(int i=0;i<events.length;i++){
                            eventsaux[i]=events[i];
                        }
                        eventsaux[eventsaux.length]=aux1;
                        events=eventsaux;
                    }*/
                }
            }
            
            Concert aux2;
            Iterator<Concert> Citerator= concerts.iterator();
            while(Citerator.hasNext()){
                aux2=Citerator.next();
                if(aux2.getName().contains(name)){
                    list.add(aux2);
                    /*if(empty){
                        events[0]=aux2;
                        empty=false;
                    }else{
                        eventsaux= new Event[events.length+1];
                        for(int i=0;i<events.length;i++){
                            eventsaux[i]=events[i];
                        }
                        eventsaux[eventsaux.length]=aux2;
                        events=eventsaux;
                    }*/
                }
            }
            
            Exhibition aux3;
            Iterator<Exhibition> Eiterator= exhibitions.iterator();
            while(Eiterator.hasNext()){
                aux3=Eiterator.next();
                if(aux3.getName().contains(name)){
                    list.add(aux3);
                    /*
                    if(empty){
                        events[0]=aux3;
                        empty=false;
                    }else{
                        eventsaux= new Event[events.length+1];
                        for(int i=0;i<events.length;i++){
                            eventsaux[i]=events[i];
                        }
                        eventsaux[eventsaux.length]=aux3;
                        events=eventsaux;
                    }*/
                }
                Event[] events = (Event[])list.toArray();
                if(events.length==0){
                    return new Event[0];
                }else{
                    return events;
                }
            }
            
            return new Event[0];
            
        }
        return new Event[0];
    }

    @Override
    public Event[] retrieveEvents(Location loc) {
        
        if (loc != null)
        {
            ArrayList<Event> list = new ArrayList<>();
            /*Event[] events = new Event[1];
            Event[] eventsaux;
            boolean empty=true;*/
            boolean added=false;
            
            Festival aux1;
            Iterator<Festival> Fiterator= festivals.iterator();
            while(Fiterator.hasNext()){
                aux1=Fiterator.next();
                Concert[] Caux =aux1.getConcerts();
                added=false;
                for(int i=0;i<Caux.length && !added;i++){
                    if(Caux[i].getLocation().equals(loc)){
                        /*if(empty){
                            events[0]=aux1;
                            empty=false;
                            
                        }else{
                            eventsaux= new Event[events.length+1];
                            for(int j=0;j<events.length;j++){
                                eventsaux[j]=events[j];
                            }
                            eventsaux[eventsaux.length]=aux1;
                            events=eventsaux;
                        }*/
                        list.add(aux1);
                        added=true;
                    }
                }
            }
            
            Concert aux2;
            Iterator<Concert> Citerator= concerts.iterator();
            while(Citerator.hasNext()){
                aux2=Citerator.next();
                if(aux2.getLocation().equals(loc)){
                    /*if(empty){
                        events[0]=aux2;
                        empty=false;
                    }else{
                        eventsaux= new Event[events.length+1];
                        for(int i=0;i<events.length;i++){
                            eventsaux[i]=events[i];
                        }
                        eventsaux[eventsaux.length]=aux2;
                        events=eventsaux;
                    }*/
                    list.add(aux2);
                }
            }
            
            Exhibition aux3;
            Iterator<Exhibition> Eiterator= exhibitions.iterator();
            while(Eiterator.hasNext()){
                aux3=Eiterator.next();
                if(aux3.getLocation().equals(loc)){
                    /*if(empty){
                        events[0]=aux3;
                        empty=false;
                    }else{
                        eventsaux= new Event[events.length+1];
                        for(int i=0;i<events.length;i++){
                            eventsaux[i]=events[i];
                        }
                        eventsaux[eventsaux.length]=aux3;
                        events=eventsaux;
                    }*/
                    list.add(aux3);
                }
                Event[] events = (Event[])list.toArray();
                if(events.length==0){
                    return new Event[0];
                }else{
                    return events;
                }
            }
            
            return new Event[0];
        }
        return new Event[0];
    }

    @Override
    public Event[] retrieveEvents(Date d) {
        
        if (d != null)
        {
            ArrayList<Event> list = new ArrayList<>();/*
            Event[] events = new Event[1];
            Event[] eventsaux;
            boolean empty=true;*/
            boolean added=false;
            
            Festival aux1;
            Iterator<Festival> Fiterator= festivals.iterator();
            while(Fiterator.hasNext()){
                aux1=Fiterator.next();
                Concert[] Caux =aux1.getConcerts();
                added=false;
                for(int i=0;i<Caux.length && !added;i++){
                    if(Caux[i].getDates()[0].equals(d)){
                        /*if(empty){
                            events[0]=aux1;
                            empty=false;
                            
                        }else{
                            eventsaux= new Event[events.length+1];
                            for(int j=0;j<events.length;j++){
                                eventsaux[j]=events[j];
                            }
                            eventsaux[eventsaux.length]=aux1;
                            events=eventsaux;
                        }*/
                        list.add(aux1);
                        added=true;
                    }
                }
            }
            
            Concert aux2;
            Iterator<Concert> Citerator= concerts.iterator();
            while(Citerator.hasNext()){
                aux2=Citerator.next();
                if(aux2.getDates().equals(d)){
                    /*if(empty){
                        events[0]=aux2;
                        empty=false;
                    }else{
                        eventsaux= new Event[events.length+1];
                        for(int i=0;i<events.length;i++){
                            eventsaux[i]=events[i];
                        }
                        eventsaux[eventsaux.length]=aux2;
                        events=eventsaux;
                    }*/
                    list.add(aux2);
                }
            }
            
            Exhibition aux3;
            Iterator<Exhibition> Eiterator= exhibitions.iterator();
            while(Eiterator.hasNext()){
                aux3=Eiterator.next();
                if(aux3.getDates().equals(d)){
                    /*if(empty){
                        events[0]=aux3;
                        empty=false;
                    }else{
                        eventsaux= new Event[events.length+1];
                        for(int i=0;i<events.length;i++){
                            eventsaux[i]=events[i];
                        }
                        eventsaux[eventsaux.length]=aux3;
                        events=eventsaux;
                    }*/
                    list.add(aux3);
                }
                Event[] events = (Event[])list.toArray();
                if(events.length==0){
                    return new Event[0];
                }else{
                    return events;
                }
            }
            
            return new Event[0];
        }
        return new Event[0];
    }


    /**
     * Checks the age of majority
     * @param birthdate
     * @return boolean
     */
    private static boolean ageMajority(ModelDate birthdate){
        Calendar c1 = new GregorianCalendar();
        
        boolean resul=false;
        if ((c1.get(Calendar.YEAR)-birthdate.getYear())>18){
            resul=true;
        }else if ((c1.get(Calendar.YEAR)-birthdate.getYear())==18){
            if (c1.get(Calendar.MONTH)<birthdate.getMonth()){
               resul=false;
            }else if (c1.get(Calendar.MONTH)==birthdate.getMonth()){
                resul=(c1.get(Calendar.DATE)>=birthdate.getDay());
            }else if(c1.get(Calendar.MONTH)>birthdate.getMonth()){
                resul=true;
            }
        }else if ((c1.get(Calendar.YEAR)-birthdate.getYear())<18){
            resul=false;
        }
        return resul;
    }
    
    @Override
    public boolean addClient(Client c) {
        
        if ((c != null) && (ageMajority(c.getBirthdate()))){
            clients.add(c);
            return clients.contains(c);
        }
        return false;
    }

    @Override
    public boolean modifyClient(Client c) {
        
        if ((c != null) && (ageMajority(c.getBirthdate()))){
            clients.remove(c);
            clients.add(c);
            return clients.contains(c);
        }
        return false;
    }

    @Override
    public boolean addCardToClient(Client c, String cCard) {
        
        if ((c != null) && cCard != null){
            if(c.searchCard(cCard)==false){
                c.addCard(cCard);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsClient(Client c) {
        
        if (c != null){
            return clients.contains(c);
        }
        return false;
    }

    @Override
    public boolean containsClient(int id) {
        
        Client c;
        Iterator<Client> iterator = clients.iterator();
        while(iterator.hasNext()){
            c = iterator.next();
            return c.getDni() == id;
        }
        return false;
    }

    @Override
    public Client retrieveClient(int identifier) {
        
        Client c,auxClient=null;
        Iterator<Client> iterator = clients.iterator();
        while(iterator.hasNext()){
            c = iterator.next();
            if (c.getDni() == identifier){
                auxClient=c;
            }
        }
        
        return auxClient;
    }

    @Override
        public Ticket[] getListOfTickets(Client c) {
        
        if (c != null){
            Ticket aux;
            Ticket[] tics = null;
            int i = 0;
            Iterator<Ticket> iterator = tickets.iterator();
            while(iterator.hasNext()){
                aux = iterator.next();
                if (aux.getAssociated().compareTo(c) == 0){
                    tics[i] = aux;
                    i++;
                }
            } 
            return tics;
        }
        return null;
    }

    @Override
    public float getTotalSpending(Client c) {
        
        if (c != null){
            Sale aux;
            float price = 0;
            Iterator<Sale> iterator = sales.iterator();
            while(iterator.hasNext()){
                aux = iterator.next();
                if (aux.getClient().compareTo(c) == 0){
                    price = price + aux.getPrice();
                }
            } 
            return price;
        }
        return -1;
    }

    @Override
    public boolean addNewTicket(Ticket t) {
       
        if ((t != null) && (t.getEvent() != null)){ //&& (t.getIDs() != null) && (t.getAssociated() != null)){
            if (existsEvent(t.getEvent())){
                
                if(t.getAssociated()==null){// el ticket no tiene cliente asociado
                    tickets.add(t);
                    return tickets.contains(t);
                }
                else{if (clients.contains(t.getAssociated())){
                    
                    tickets.add(t);
                    return tickets.contains(t);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasIDCollision(Ticket t) {
        
        if (t != null){
            Ticket aux;
            Iterator<Ticket> iterator = tickets.iterator();
            while(iterator.hasNext()){
                aux = iterator.next();
                if (aux.getIDs().equals(t.getIDs())){
  
                    return true;
                }                
            }
           // return tickets.contains(t);
        }
        return false;
    }

    @Override
    public boolean availableTicketID(Event e, int id) {
        
        if ((e != null) && (id > 0)){
            if (existsEvent(e)){
                Ticket aux;
                Iterator<Ticket> iterator = tickets.iterator();
                while(iterator.hasNext()){
                    aux = iterator.next();
                    if (aux.getEvent().equals(e)){
                        if (aux.getId() == id){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean setIDUsed(Ticket t, Event e, int id) {
        
        if ((t != null) && (e != null) && (id > 0)){
            if (existsEvent(e)){
                Ticket aux;
                Iterator<Ticket> iterator = tickets.iterator();
                while(iterator.hasNext()){
                    aux = iterator.next();
                    if (aux.compareTo(t) == 0){
                        if (aux.getId() == id){
                            if (aux.getUsed()){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean addSale(Ticket t, Client c, Float price, String cCard) {
        
        if ((t != null) && (c != null) && (cCard != null) && (price > 0)){
            Ticket auxt;
            Iterator<Ticket> iterator = tickets.iterator();
            while(iterator.hasNext()){
                
                auxt = iterator.next();
                if (auxt.compareTo(t) == 0){
                   
                    Client auxc;
                    Iterator<Client> it = clients.iterator();
                    while (it.hasNext()){
                        auxc = it.next();
                        if (auxc.compareTo(c) == 0){
                            if (auxc.searchCard(cCard) ==true){                       
                               
                                Sale s = new Sale(c, t, price, cCard);
                                sales.add(s);
                                return sales.contains(s);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean addLocation(Location loc) {
        
        if ((loc != null) && (loc.getName() != null) && (loc.getCapacity() > 0) && (loc.getCountry() != null) && (loc.getCountry() != null) 
                && (loc.getProvince() != null) && (loc.getCity() != null) && (loc.getStreet() != null) && (loc.getNumber() > 0)){
            locations.add(loc);
            return locations.contains(loc);
        }
        return false;
    }
    
    public boolean existLocation(Location l){
        
        Location loc;
        if (l != null){
            
            Iterator<Location> iterator = locations.iterator();
            while(iterator.hasNext()){
                
                loc=iterator.next();
                if( loc.equals(l)){
                    return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public Location getLocation(String name) {
        
        if (name != null){
            Location loc;
            Iterator<Location> iterator = locations.iterator();
            while(iterator.hasNext()){
                loc = iterator.next();
                if (loc.getName().equals(name)){
                    return loc;
                }
            }  
        }
        return null;
    }

    @Override
    public boolean deleteLocation(Location loc) {
        
        if (loc != null){
            locations.remove(loc);
            return !(locations.contains(loc));
        }
        return false;
    }

    @Override
    public Location[] getLocations(int minCapacity) {
        
        if (minCapacity > 0){
            Location loc;
            int i =0;
            TreeSet<Location> t=new TreeSet<>();
            Iterator<Location> iterator = locations.iterator();
            while(iterator.hasNext()){
                loc = iterator.next();
                if (loc.getCapacity() >= minCapacity){
                    t.add(loc);
                    i++;
                }
            } 
            Iterator<Location> it = t.iterator();
            int k=0;
            Location[] locs=new Location[i];
            while(it.hasNext()){
                locs[k]=it.next();
                k++;
            }
            return locs;
        }
        return null;
    }

    @Override
    public boolean addArtist(Artist a) {
        artists.add(a);
        return existsArtist(a.getName());
    }

    @Override
    public boolean addCollective(Collective c) {
        collectives.add(c);
        return existsCollective(c.getName());
    }

    @Override
    public boolean modifyArtist(Artist a) {
        
        
        if(existsArtist(a.getName())){
            removePerformer(a.getName());
            artists.add(a);
            return existsArtist(a.getName());
        }
        return false;
    }

    @Override
    public boolean modifyCollective(Collective c) {
        
        if(existsCollective(c.getName())){
            removePerformer(c.getName());
            collectives.add(c);
            return existsCollective(c.getName());
        }
        return false;
    }

    @Override
    public boolean removePerformer(String performerName) {
        if(existsPerformer(performerName)){
            Performer p=retrievePerformer(performerName);
            if(p instanceof Artist){
                return artists.remove((Artist)p);
            }                
            else{
                return collectives.remove((Collective)p);
            }
        }else{
            //lanzar no existe el performer exception
            return false;
        }
    }

    @Override
    public boolean existsPerformer(String performerName) {
        
        boolean a = existsArtist(performerName);
        boolean b = existsCollective(performerName);
        
        return (a || b);
    }

    @Override
    public boolean existsArtist(String artistName) {
        
        if (artistName != null){
            if (artists.stream().anyMatch((artist) -> (artist.getName().equals(artistName)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsCollective(String artistName) {
        
        if (artistName != null){
            for (Iterator<Collective> it = collectives.iterator(); it.hasNext();) {
                Collective collective = it.next();
                if (collective.getName().equals(artistName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Performer retrievePerformer(String performerName) {
        
        if (performerName != null){
            for (Iterator<Artist> it = artists.iterator(); it.hasNext();) {
                Artist artist = it.next();
                if (artist.getName().equals(performerName)) {
                    return artist;
                }
            }
            for (Iterator<Collective> it = collectives.iterator(); it.hasNext();) {
                Collective collective = it.next();
                if (collective.getName().equals(performerName)) {
                    return collective;
                }
            }
        }
        return null;
    }
    
    /**
     * This method import a number of tickets froma a, existent  file in ods format.
     * This file must only have one page.
     * Before inserting all the tickets we are going to create some default objects to let our system
     * works as it has to.
     * This method is proved in GSILib.Misc.SSTest05.
     * @param f the file in ods format
     * @return the numbers of tickets imported correctly
     * @throws IOException 
     */
    public int importTickets (File f) throws IOException{
        
        int numTickets=0,j=0;
        final Sheet miSheet = SpreadSheet.createFromFile(f).getSheet(0);
        final TableGroup miTable = miSheet.getRowGroup();
        int rows=miSheet.getRowCount(),columns=miSheet.getColumnCount();
        String valorFest,valorTick,valorUso;
        Festival auxFest;
        Concert auxCon;
        Date sd,ed,d;
        ModelDate bd;
        String name;
        Location auxLoc;
        Artist auxArt;
        Client auxCli;
        Ticket auxTick;
        
        auxLoc = new Location("Localizacion por defecto",10000,"España","Navarra","Pamplona","Calle mayor",(short) 5);
        this.addLocation(auxLoc);
        sd = new Date(2016-1900,10,2);
        ed = new Date(2016-1900,10,4);
        d = new Date(2016-1900,10,1);
        bd=new ModelDate(1996,10,1);
        auxArt = new Artist("artista por defecto","Descripcion por defecto","Descripcion por defecto");
        this.addArtist(auxArt);
        auxCon = new Concert(auxLoc,auxArt,"Concierto por defecto",d);
        this.addNewConcert(auxCon);
        auxCli=new Client(1232,"Pablo","Perez",bd,"3454562");
        this.addClient(auxCli);
        
        for (int i=0;i<rows;i++){
        
            valorFest = miSheet.getCellAt(j,i).getTextValue();
            auxFest=new Festival(auxCon,sd,ed,valorFest);
            this.addNewFestival(auxFest);
            System.out.print(auxFest.getName());
            j++;
            while (!(miSheet.getCellAt(j,i).isEmpty())){
                
                valorTick=miSheet.getCellAt(j,i).getTextValue();
                System.out.print(valorTick);
                j++;
                valorUso=miSheet.getCellAt(j,i).getTextValue();
                System.out.print(valorUso);
                j++;
                if (valorUso.equalsIgnoreCase(" used ")){

                    auxTick=new Ticket(Integer.parseInt(valorTick),auxFest,auxCli,true);

                }else{

                    auxTick=new Ticket(Integer.parseInt(valorTick),auxFest,auxCli,false);

                }
                if(this.addNewTicket(auxTick))
                    numTickets++;
                
            }
           
            System.out.println();
            
        }
        
        return numTickets;
        
    }
    
    @Override
    public String toXML() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements (BusinessSystem)
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("BusinessSystem");
        doc.appendChild(rootElement);
        
        // artists elements
        Element xArtists = doc.createElement("artists");
        rootElement.appendChild(xArtists);

        for (int i = 0; i < artists.size(); i++){

            xArtists.appendChild(doc.createTextNode("##A"+i+"##"));
        }
 
        // collective elements
        Element xCollectives = doc.createElement("collectives");
        rootElement.appendChild(xCollectives);

        for (int i = 0; i < collectives.size(); i++){

            xCollectives.appendChild(doc.createTextNode("##COL"+i+"##"));
        }
        
        // location elements
        Element xLocations = doc.createElement("locations");
        rootElement.appendChild(xLocations);

        for (int i = 0; i < locations.size(); i++){

            xLocations.appendChild(doc.createTextNode("##L"+i+"##"));
        }
        
        // client elements
        Element xClients = doc.createElement("clients");
        rootElement.appendChild(xClients);

        for (int i = 0; i < clients.size(); i++){

            xClients.appendChild(doc.createTextNode("##CLI"+i+"##"));
        }
        
        // concerts elements
        Element xConcerts = doc.createElement("concerts");
        rootElement.appendChild(xConcerts);

        for (int i = 0; i < concerts.size(); i++){

            xConcerts.appendChild(doc.createTextNode("##CON"+i+"##"));
        }
        
        // exhibitions elements
        Element xExhibitions = doc.createElement("exhibitions");
        rootElement.appendChild(xExhibitions);

        for (int i = 0; i < exhibitions.size(); i++){

            xExhibitions.appendChild(doc.createTextNode("##EX"+i+"##"));
        }
        
        // festivals elements
        Element xFestivals = doc.createElement("festivals");
        rootElement.appendChild(xFestivals);

        for (int i = 0; i < festivals.size(); i++){

            xFestivals.appendChild(doc.createTextNode("##F"+i+"##"));
        }
        
        // tickets elements
        Element xTickets = doc.createElement("tickets");
        rootElement.appendChild(xTickets);

        for (int i = 0; i < tickets.size(); i++){

            xTickets.appendChild(doc.createTextNode("##T"+i+"##"));
        }
        
        // sales elements
        Element xSales = doc.createElement("sales");
        rootElement.appendChild(xSales);

        for (int i = 0; i < sales.size(); i++){

            xSales.appendChild(doc.createTextNode("##S"+i+"##"));
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
        
        
        int a = 0;
        Artist auxA;
        Iterator<Artist> iA = artists.iterator();
        while(iA.hasNext()){

            auxA = iA.next();
            String art = auxA.toXML();
            String art2 = art.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##A"+a+"##", art2);
            a++;
        }
        
        int col = 0;
        Collective auxCol;
        Iterator<Collective> iCol = collectives.iterator();
        while(iCol.hasNext()){

            auxCol = iCol.next();
            String col1 = auxCol.toXML();
            String col2 = col1.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##COL"+col+"##", col2);
            col++;
        }
        
        int l = 0;
        Location auxL;
        Iterator<Location> iL = locations.iterator();
        while(iL.hasNext()){

            auxL = iL.next();
            String loc2 = auxL.toXML();
            String loc3 = loc2.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##L"+l+"##", loc3);
            l++;
        }
        
        int cli = 0;
        Client auxCli;
        Iterator<Client> iCli = clients.iterator();
        while(iCli.hasNext()){

            auxCli = iCli.next();
            String cli2 = auxCli.toXML();
            String cli3 = cli2.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##CLI"+cli+"##", cli3);
            cli++;
        }
        
        int con = 0;
        Concert auxCon;
        Iterator<Concert> iCon = concerts.iterator();
        while(iCon.hasNext()){

            auxCon = iCon.next();
            String con2 = auxCon.toXML();
            String con3 = con2.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##CON"+con+"##", con3);
            con++;
        }
        
        int ex = 0;
        Exhibition auxEx;
        Iterator<Exhibition> iEx = exhibitions.iterator();
        while(iEx.hasNext()){

            auxEx = iEx.next();
            String ex2 = auxEx.toXML();
            String ex3 = ex2.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##EX"+ex+"##", ex3);
            ex++;
        }
        
        int f = 0;
        Festival auxF;
        Iterator<Festival> iF = festivals.iterator();
        while(iF.hasNext()){

            auxF = iF.next();
            String f2 = auxF.toXML();
            String f3 = f2.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##F"+f+"##", f3);
            f++;
        }
        
        int t = 0;
        Ticket auxT;
        Iterator<Ticket> iT = tickets.iterator();
        while(iT.hasNext()){

            auxT = iT.next();
            String t2 = auxT.toXML();
            String t3 = t2.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##T"+t+"##", t3);
            t++;
        }
        
        int s = 0;
        Sale auxS;
        Iterator<Sale> iS = sales.iterator();
        while(iS.hasNext()){

            auxS = iS.next();
            String s2 = auxS.toXML();
            String s3 = s2.replaceFirst("<?.*?>", "");
            finalstring = finalstring.replace("##S"+s+"##", s3);
            s++;
        }
        
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