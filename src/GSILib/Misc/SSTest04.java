/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILib.Misc;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Collective;
import GSILabs.BModel.Concert;
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
    
        Artist a1,a2,a3,a4,a5,a6,a7,a8;
        Collective c1,c2;
        Concert con1,con2,con3,con4;
        Location l1,l2;
        Festival f1;
        Date d1,d2,d3,d4;
        BusinessSystem miBs=new BusinessSystem();

        
        a1=new Artist("El canijo de Jerez","Cantante");
        a2=new Artist("Alex Papito","Reggaetonero","www.papitoelmio.es");
        a3=new Artist("Ra$king","Artista en todas sus facetas","www.thuglife.es");
        a4=new Artist("La pili","Cantante","www.musicalapili.es");
        a5=new Artist("Patrick Stump","Cantante y guitarrista");
        a6=new Artist("Pete Wentz","Guitarrista y bajista");
        a7=new Artist("Jared Leto","Cantante y actor");
        a8=new Artist("Steve Aoki","Productor de música","www.steveaoki.com");
        
        c1=new Collective("Alex y los rebujitos",a2,a3,"www.alexylosrebujitos.es");
        c1.addComponent(a4);
        
        c2=new Collective("FallOut Boy",a5,a6,"www.falloutboy.com");
        
        miBs.addArtist(a1);
        miBs.addArtist(a7);
        miBs.addCollective(c1);
        miBs.addCollective(c2);
        
        l1=new Location("Sadar",15000,"España","Navarra","Pamplona","Calle Sadar",(short) 5);
        miBs.addLocation(l1);
        l2=new Location("Lizarreria",3000,"España","Navarra","Estella","Calle Mayor",(short) 1);
        miBs.addLocation(l2);

        d1=new Date(2016-1900,21,2);
        d2=new Date(2016-1900,30,3);
        
        con1=new Concert(l1,a1,"El canijo de Jerez en Concierto",d1);
        miBs.addNewConcert(con1);
        con2=new Concert(l1,c1,"La fiesta padre",d2);
        con3=new Concert(l1,c2,"The great Party",d2);

        f1=new Festival();
        miBs.addConcertToFestival(f1, con2);
        

    }
    
}
