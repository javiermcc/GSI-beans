/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILib.Misc;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Collective;
import GSILabs.BModel.Concert;
import GSILabs.BModel.Exhibition;
import GSILabs.BModel.Festival;
import GSILabs.BModel.Location;
import GSILabs.BSystem.BusinessSystem;
import java.io.IOException;
import java.sql.Date;

/**
 *
 * @author labora1
 */
public class SSTest04 {
    
    public static void main (String[] args) throws IOException {
    
        Artist a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
        Collective c1,c2;
        Concert con1,con2,con3,con4;
        Location l1,l2,l3,l4;
        Exhibition e1,e2;
        Festival f1;
        Date d1,d2,d3,d4;
        Date[] d5 = null;
        String[] webs1={"www.museoreinasofia.es","www.fundacionpinturaparatodos.es"};
        String[] webs2={"www.renacimeintohoy.es","www.arteclasicopamplona.es"};
        BusinessSystem miBs=new BusinessSystem();

        
        a1=new Artist("El canijo de Jerez","Cantante");
        a2=new Artist("Alex Papito","Reggaetonero","www.papitoelmio.es");
        a3=new Artist("Ra$king","Artista en todas sus facetas","www.thuglife.es");
        a4=new Artist("La pili","Cantante","www.musicalapili.es");
        a5=new Artist("Patrick Stump","Cantante y guitarrista");
        a6=new Artist("Pete Wentz","Guitarrista y bajista");
        a7=new Artist("Jared Leto","Cantante y actor");
        a8=new Artist("Steve Aoki","Productor de música","www.steveaoki.com");
        a9=new Artist("Pablo Picasso","Pintor");
        a10=new Artist("Michelangelo","Pintor,escultor y arquitecto");
        
        c1=new Collective("Alex y los rebujitos",a2,a3,"www.alexylosrebujitos.es");
        c1.addComponent(a4);
        
        c2=new Collective("FallOut Boy",a5,a6,"www.falloutboy.com");
        
        miBs.addArtist(a1);
        miBs.addArtist(a7);
        miBs.addArtist(a9);
        miBs.addArtist(a10);
        miBs.addCollective(c1);
        miBs.addCollective(c2);
        
        l1=new Location("Sadar",15000,"España","Navarra","Pamplona","Calle Sadar",(short) 5);
        miBs.addLocation(l1);
        l2=new Location("Lizarreria",3000,"España","Navarra","Estella","Calle Mayor",(short) 1);
        miBs.addLocation(l2);
        l3=new Location("Museo Reina Sofia",5000,"España","Madrid","Madrid","Paseo de la castellana",(short) 23);
        miBs.addLocation(l3);
        l4=new Location("Museo de arte clasico de pamplona",3000,"España","Navarra","Pamplona","Calle La estafeta",(short)3);
        miBs.addLocation(l4);

        d1=new Date(2016-1900,21,2);
        d2=new Date(2016-1900,30,3);
        
        con1=new Concert(l1,a1,"El canijo de Jerez en Concierto",d1);
        miBs.addNewConcert(con1);
        con2=new Concert(l1,c1,"La fiesta padre",d2);
        con3=new Concert(l1,c2,"The great Party",d2);

        f1=new Festival();
        miBs.addConcertToFestival(f1, con2);
        
        d3=new Date(2016-1900,21,2);
        d4=new Date(2016-1900,21,3);
        d5[1]=d3;
        d5[2]=d4;
        
        e1 = new Exhibition("Exposicion de Pablo Picasso","Fundación pintura para todos",d5,l3,a9,webs1);
        miBs.addNewExhibition(e1);
        e2 = new Exhibition("Miguel Angel: su historia","Renacimiento hoy",d5,l4,a10,webs2);
        miBs.addNewExhibition(e2);
        

    }
    
}
