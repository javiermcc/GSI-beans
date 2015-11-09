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
import GSILabs.BModel.Location;
import GSILabs.BModel.ModelDate;
import GSILabs.BModel.Festival;
import GSILabs.BModel.Ticket;
import GSILabs.BSystem.BusinessSystem;
import java.sql.Date;

/**
 *
 * @author elementary
 */
public class P01Tester {
    
    public static Artist art;
    public static Client cli;
    public static Collective col;
    public static Concert con1,con2;
    public static Exhibition ex;
    public static Festival fest;
    public static Location loc1,loc2;
    public static Ticket tick;
    public static BusinessSystem busSys;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        ModelDate auxDate;
        Date auxDate1;
        Artist auxArt;
        Artist auxArt2;
        Client auxClient;
        busSys=new BusinessSystem();   
        
        
        
        /**
         * S1&S2
         * With these instructions we demonstrate that we can add a new client and
         * we can see the information of him/her with his/her identifier.
         * Also, we can see that if we want to get a client that does not exist, it retrieves
         * null.
         */
        System.out.println("S1 y S2 localizacion de un cliente a partir de su ID y busqueda de un cliente que no existe");
        auxDate=new ModelDate(1,1,1980);
        cli=new Client(1234567,"Pablo","Rodriguez Rodriguez",auxDate,"323425325");
        auxClient=busSys.retrieveClient(cli.getDni());
        System.out.println("Pruebas con el cliente que no existe:\n"+auxClient);
        if(!busSys.addClient(cli)){
            System.out.println("No se ha podido añadir el cliente");
        }
        auxClient=busSys.retrieveClient(cli.getDni());
        System.out.println("pruebas con un cliente que existe:\n"+auxClient.toString());
        System.out.println();
        
        System.out.println("");
        System.out.println("");
        /**
         * S3
         * Using these instructions we can see that we can not add a collective with the 
         * same name as an Artist
         */
        System.out.println("S3 No se puede introducir un artista y un colectivo con el mismo nombre");
        art=new Artist("Juan Perez","Cantate");
        auxArt=new Artist("Trueno","Cantante");
        auxArt2=new Artist("TruenoReturns","Cantante");
        busSys.addArtist(art);
        busSys.addArtist(auxArt);
        busSys.addArtist(auxArt2);
        col=new Collective("Trueno",art,auxArt2,"cantantes");
        if (busSys.addCollective(col)){
            System.out.println("Se ha añadido correctamente el colectivo");
        }else{
            System.out.println("No se ha podido añadir el colectivo");
        }
        System.out.println();
        System.out.println("");
        System.out.println("");
        /**
         * S4
         * With these instructions we can see that if we remove that artist, we can add
         * the collective with the same name
         */
        System.out.println("S4 si se borra un artista se puede introducir un colectivo con el nombre que tenia ese artista");
        busSys.removePerformer("Trueno");        
        System.out.println(busSys.existsPerformer("Trueno"));
        if (busSys.addCollective(col)){
            System.out.println("Se ha añadido correctamente el colectivo");
        }else{
            System.out.println("No se ha podido añadir el colectivo");
        }
        System.out.println();
        System.out.println("");
        System.out.println("");
        /**
         * S5
         * With these instructions we can see that it's not possible to add 2 events 
         * to the same artist the same day
         */
        System.out.println("S5 no se puede añadir conciertos el mismo dia si son del mismo artista");
        auxDate1=new Date(2016-1900,11,12);
        loc1=new Location("Reyno de Navarra",15000,"Spain","Navarra","Pamplona","Calle Sadar",(short) 3);
        busSys.addLocation(loc1);     
        con1=new Concert(loc1,art,"Gira",auxDate1);
        loc2=new Location("Navarra Arena",10000,"Spain","Navarra","Pamplona","Calle Sadar",(short) 1);
        busSys.addLocation(loc2);
        con2=new Concert(loc2,art,"Concierto Benefico",auxDate1);
        if (busSys.addNewConcert(con2)){
            //Se escribira esta linea si se ha añadido el concierto correctamente
            System.out.println("Se ha añadido el primer concierto");
        }else{
            //Se escribira est alinea si no se ha añadido el concierto correctamente
            System.out.println("No se ha añadido el primer concierto");
        }
        if (busSys.addNewConcert(con1)){
            //Se escribira esta linea si se ha añadido el concierto correctamente
            System.out.println("Se ha añadido el segundo concierto");
        }else{
            //Se escribira esta linea si no se ha añadido el concierto correctamente
            System.out.println("No se ha añadido el segundo concierto");
        }
        System.out.println();
        System.out.println("");
        System.out.println("");
        
        /**
         * S6
         * With these instruction we can see that the system can calculate the result
         * of all the tickets that are associated to a client 
         */
        System.out.println("S6 calculo del gasto en entradas de clientes");
        Float price = 20f;
        tick = new Ticket(1,con2,cli);
        if (busSys.addNewTicket(tick)){
            System.out.println("Se ha añadido ticket");
        }
        else{
            System.out.println("No se ha añadido el ticket");
        }
        Ticket tick2 = new Ticket(10,con2,cli);
        if (busSys.addNewTicket(tick2)){
            System.out.println("Se ha añadido ticket");
        }
        else{
            System.out.println("No se ha añadido el ticket");
        }
        Ticket tick3 = new Ticket(102,con2,cli);
        if (busSys.addNewTicket(tick3)){
            System.out.println("Se ha añadido ticket");
        }
        else{
            System.out.println("No se ha añadido el ticket");
        }
        
        String[] saux;
        saux = (String[])cli.getCards();
        System.out.println(saux.length);
        
        busSys.addSale(tick2, cli, price, cli.getCards()[0]);
        busSys.addSale(tick3, cli, price, cli.getCards()[0]);
        if (busSys.addSale(tick, cli, price, cli.getCards()[0])){
            Float spent = busSys.getTotalSpending(cli);
            System.out.println("The client "+ cli.toString() +" spent "+ spent +" euros.");
        }
        else{
            System.out.println("nO VS");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
       
        /**
         * S7
         * With these instructions we can see that it's not possible to add a Concert
         * to a Location that doesn't exist
         */
        System.out.println("S7 no se puede añadir un concierto en una localizacion que no existe");
        System.out.println("");
        loc1=new Location("Pabellon de Anaitasuna",2000,"Spain","Navarra","Pamplona","Calle Doctor Lopez Sanz",(short) 2);
        auxDate1=new Date(2017-1900,1-1,1);
        con1=new Concert(loc1,art,"Fiesta de la juventud",auxDate1);
        if (busSys.addNewConcert(con1)){
            System.out.println("Se ha añadido el concierto correctamente");
        }else{
            System.out.println("No se ha añadido el concierto correctamente");
        }
        System.out.println();
        System.out.println("");
        System.out.println("");
        
        /**
         * S8
         * Using these instructions we can demonstrate that it's not possible to add a
         * concert to a festival that has already been added
         */
        
        Festival festaux=new Festival(con1,con1.getStartDate(),con1.getStartDate(),"la madre que nos pario");
        System.out.println();
        System.out.println(festaux);
        System.out.println();
        System.out.println();
        
        System.out.println("S8 no se puede añadir un mismo concierto a un festival 2 veces");
        if (busSys.addConcertToFestival(fest, con1)){
            System.out.println("Se ha añadido el concierto por segunda vez");
        }else{
            System.out.println("No se ha podido añadir el concierto por segunda vez");
        }
        System.out.println();
        System.out.println("");
        System.out.println("");
        /**
         * S9
         * Using these instruction we can demonstrate that it's not possible to add a sale 
         * to a Client that does not exist
         */
        System.out.println("No se puede añadir una venta a un cliente que no existe");
        Client inexistentClient = new Client(0000005,"Anonymus","Legion",auxDate,"5611236523");
        Ticket tick4 = new Ticket(1002,con2,inexistentClient);
        if (busSys.addNewTicket(tick4)){
            System.out.println("Se ha añadido ticket");
            busSys.addSale(tick4,inexistentClient , price, inexistentClient.getCards()[0]);
        }
        else{
            System.out.println("No se ha añadido el ticket porque el cliente no existe");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        /**
         * S10
         * We demonstrate that it's not possible to add a Client who is younger than 18 years old 
         */
        System.out.println("S10 no se puede añadir un cliente menor de edad");
        auxDate=new ModelDate(1,1,2000);
        cli=new Client(1234567,"Pablo","Rodriguez Rodriguez",auxDate,"324354");
        if(busSys.addClient(cli)){
            System.out.println("Se ha añadido un cliente menor de edad");
        }else{
            System.out.println("No se puede añadir un cliente menor de edad");
        }

        Date[] date = new Date[2];
        date[0] = new Date(2000,12,12);
        date[1] = new Date(2000,12,13);
        String[] webs = new String[2];
        webs[0] = "dwedc";
        webs[1] = "asdsad";
        ex = new Exhibition("o", "im", date ,loc1, col, webs);
        busSys.addNewExhibition(ex);

    }
    
}
