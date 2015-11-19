/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILabs.BTesting;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Client;
import GSILabs.BModel.Collective;
import GSILabs.BModel.Concert;
import GSILabs.BModel.Exhibition;
import GSILabs.BModel.Festival;
import GSILabs.BModel.Location;
import GSILabs.BModel.ModelDate;
import GSILabs.BModel.Sale;
import GSILabs.BModel.Ticket;
import GSILabs.BSystem.BusinessSystem;
import GSILabs.persistence.ParseElement;
import java.io.File;
import java.util.Date;
import java.util.Iterator;


/**
 *
 * @author linux1
 */
public class XMLTester {
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    
        BusinessSystem bus = new BusinessSystem();
    
        Artist art1 = new Artist("Artista 1", "Descripcion 1", "Web 1");
        Artist art2 = new Artist("Artista 2", "Descripcion 2", "Web 2");
        Artist art3 = new Artist("Artista 3", "Descripcion 3", "Web 3");
        Artist art4 = new Artist("Artista 4", "Descripcion 4");
        
        bus.addArtist(art1);
        bus.addArtist(art2);
        bus.addArtist(art3);
        bus.addArtist(art4);
        
        
        Collective col1 = new Collective("Colectivo 1", art1, art2, "Descripcion colectivo 1","Web colectivo 1");
        Collective col2 = new Collective("Colectivo 2", art3, art4, "Descripcion colectivo 2");
        
        bus.addCollective(col1);
        bus.addCollective(col2);
        
        
        Location l1 = new Location("Loc 1", 1, "Pais 1", " Provincia 1", "Ciudad 1", "Calle 1", (short)1, "Web 1");
        Location l2 = new Location("Loc 2", 2, "Pais 2", " Provincia 2", "Ciudad 2", "Calle 2", (short)2);
        
        bus.addLocation(l1);
        bus.addLocation(l2);
        
        
        Client cli1 = new Client(1, "Nombre 1", "Apellidos 1", new ModelDate(1998,9,1), "1");
        Client cli2 = new Client(2, "Nombre 2", "Apellidos 2", new ModelDate(1223,9,2), "2");
        
        bus.addClient(cli1);
        bus.addClient(cli2);
        
                                                            //year - 1900,  month - 1, day
        Concert con1 = new Concert(l1, art1, "Concierto 1", new Date(2000-1900, 11, 10));
        Concert con2 = new Concert(l2, col1, "Concierto 2", new Date(2000-1900, 11, 13));
        
        bus.addNewConcert(con1);
        bus.addNewConcert(con2);//no se ade por l2
        
        Date[] date = new Date[2];
        date[0] = new Date(2001-1900,10,12);
        date[1] = new Date(2001-1900,11,14);
        String[] webs = new String[2];
        webs[0] = "Web 1";
        webs[1] = "Web 2";
        Exhibition ex1 = new Exhibition("Exibicion 1", "Organizador 1", date, l1, art1, webs);
        Exhibition ex2 = new Exhibition("Exibicion 2", "Organizador 2", date, l1, col2, webs);
        
        bus.addNewExhibition(ex1);
        bus.addNewExhibition(ex2);
        
        
        Festival f1 = new Festival(con1, date[0], date[1], "Festival 1");
        Festival f2 = new Festival(con2, date[0], date[1], "Festival 2");
        
        bus.addNewFestival(f1);
        bus.addNewFestival(f2);
        bus.addConcertToFestival(f1, con2);
        bus.addConcertToFestival(f2, con1);
        
        
        int[] compas = new int[2];
        compas[0] = 123;
        compas[1] = 456;
        Ticket t1 = new Ticket(1, con1);
        Ticket t2 = new Ticket(2, con2, false);
        Ticket t3 = new Ticket(3, f1, cli1);
        Ticket t4 = new Ticket(4, f2, cli2, true);
        Ticket t5 = new Ticket(5, ex1, compas, cli1);
        Ticket t6 = new Ticket(6, ex2, compas, cli2, true);
        
        bus.addNewTicket(t1);
        bus.addNewTicket(t2);// este ticket no se añade porque pone que con2 no existe
        bus.addNewTicket(t3);
        bus.addNewTicket(t4);
        bus.addNewTicket(t5);
        bus.addNewTicket(t6);
        
        float precio = 20;
        //Sale s1 = new Sale(cli1, t1, precio, );
        //Sale s2 = new Sale(cli2, t2, precio, );
        
        bus.addSale(t1, cli1, precio, cli1.searchCard(0));
        bus.addSale(t2, cli1, precio, cli1.searchCard(0));
        bus.addSale(t3, cli1, precio, cli1.searchCard(0));
        bus.addSale(t4, cli2, precio, cli2.searchCard(0));
        bus.addSale(t5, cli2, precio, null);//obviamente no se añade al tener la tarjeta nula
        bus.addSale(t6, cli2, precio, cli2.searchCard(0));
        

        
        
        File ff = new File("/home/elementary/busf.xml");
        bus.saveToXML(ff);
        

        cli1.addCard("32");
        cli1.addCard("34");
        File fi = new File("/home/elementary/fes.xml");
        //f1.saveToXML(fi);
        String asdf = f1.toXML();

        //String fi2 = "/home/linux1/artist.xml"; //no se si se refiere a esto con lo de string o meter directamente el xml
        //ParseElement x = new ParseElement();// los metodos tienen que ser estaticos , nohay q instanciar la clase

        Festival cola = ParseElement.parseFestival(fi);
        System.out.println(cola);
        System.out.println("**********************************************");
        cola = ParseElement.parseFestival(asdf);
        System.out.println("**********************************************");
        System.out.println("**********************************************");
        System.out.println(cola);

        /*bus.addArtist(z);*/
        
        
        
        
        File f = new File("/home/elementary/bus.xml");
        bus.saveToXML(f);
    }
}