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
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author labora1
 */
public class SSTest04 {
    
    public static void main (String[] args) throws IOException {
    
        Artist[] a = new Artist[10];
        Collective[] c = new Collective[2];
        Concert[] con = new Concert[3];
        Concert auxCon;
        Location[] l = new Location[4];
        Exhibition[] e = new Exhibition[2];
        Exhibition auxEx;
        Festival f1,auxFest;
        Date[] d = new Date[4];
        Date[] d5 = new Date[2];
        Date[] auxDate;
        String[] webs1={"www.museoreinasofia.es","www.fundacionpinturaparatodos.es"};
        String[] webs2={"www.renacimeintohoy.es","www.arteclasicopamplona.es"};
        String[] auxWeb;
        BusinessSystem miBs=new BusinessSystem();
        Sheet Concert,Exhibition,Festival;
        File f=new File("test04.ods");

        
        a[0]=new Artist("El canijo de Jerez","Cantante");
        a[1]=new Artist("Alex Papito","Reggaetonero","www.papitoelmio.es");
        a[2]=new Artist("Ra$king","Artista en todas sus facetas","www.thuglife.es");
        a[3]=new Artist("La pili","Cantante","www.musicalapili.es");
        a[4]=new Artist("Patrick Stump","Cantante y guitarrista");
        a[5]=new Artist("Pete Wentz","Guitarrista y bajista");
        a[6]=new Artist("Jared Leto","Cantante y actor");
        a[7]=new Artist("Steve Aoki","Productor de música","www.steveaoki.com");
        a[8]=new Artist("Pablo Picasso","Pintor");
        a[9]=new Artist("Michelangelo","Pintor,escultor y arquitecto");
        
        c[0]=new Collective("Alex y los rebujitos",a[2],a[3],"www.alexylosrebujitos.es");
        c[0].addComponent(a[4]);
        
        c[1]=new Collective("FallOut Boy",a[4],a[5],"www.falloutboy.com");
        
        miBs.addArtist(a[0]);
        miBs.addArtist(a[6]);
        miBs.addArtist(a[8]);
        miBs.addArtist(a[9]);
        miBs.addCollective(c[0]);
        miBs.addCollective(c[1]);
        
        l[0]=new Location("Sadar",15000,"España","Navarra","Pamplona","Calle Sadar",(short) 5);
        miBs.addLocation(l[0]);
        l[1]=new Location("Lizarreria",3000,"España","Navarra","Estella","Calle Mayor",(short) 1);
        miBs.addLocation(l[1]);
        l[2]=new Location("Museo Reina Sofia",5000,"España","Madrid","Madrid","Paseo de la castellana",(short) 23);
        miBs.addLocation(l[2]);
        l[3]=new Location("Museo de arte clasico de pamplona",3000,"España","Navarra","Pamplona","Calle La estafeta",(short)3);
        miBs.addLocation(l[3]);

        d[0]=new Date(2016-1900,21,2);
        d[1]=new Date(2016-1900,30,3);
        
        con[0]=new Concert(l[0],a[0],"El canijo de Jerez en Concierto",d[0]);
        miBs.addNewConcert(con[0]);
        con[1]=new Concert(l[0],c[0],"La fiesta padre",d[1]);
        con[2]=new Concert(l[0],c[1],"The great Party",d[1]);

        f1=new Festival();
        miBs.addConcertToFestival(f1, con[1]);
        
        d[2]=new Date(2016-1900,21,2);
        d[3]=new Date(2016-1900,21,3);
        d5[0]=d[2];
        d5[1]=d[3];
        
        e[0] = new Exhibition("Exposicion de Pablo Picasso","Fundación pintura para todos",d5,l[2],a[8],webs1);
        miBs.addNewExhibition(e[0]);
        e[1] = new Exhibition("Miguel Angel: su historia","Renacimiento hoy",d5,l[3],a[9],webs2);
        miBs.addNewExhibition(e[1]);
        
        Iterator<Concert> itConcert = miBs.concerts.iterator();
        Iterator<Exhibition> itExhibition = miBs.exhibitions.iterator();
        Iterator<Festival> itFestival = miBs.festivals.iterator();
        
        Concert=SpreadSheet.createFromFile(f).getSheet(0);
        Exhibition=SpreadSheet.createFromFile(f).getSheet(1);
        Festival=SpreadSheet.createFromFile(f).getSheet(2);
        
        int i=0,j=0;
        while (itConcert.hasNext()){
            
            auxCon=itConcert.next();
            Concert.setValueAt(auxCon.getName(),j,i);
            i++;
            Concert.setValueAt(auxCon.getPerformers(),j,i);
            i++;
            Concert.setValueAt(auxCon.getDates(),j,i);
            i++;
            Concert.setValueAt(auxCon.getLocation().getName(),j,i);
            j++;
            i=0;
            
        }
        
        j=0;
        while(itExhibition.hasNext()){
            
            auxEx=itExhibition.next();
            Exhibition.setValueAt(auxEx.getName(),j,i);
            i++;
            Exhibition.setValueAt(auxEx.getOrganizer(),j,i);
            i++;
            Exhibition.setValueAt(auxEx.getStartDate(),j,i);
            i++;
            Exhibition.setValueAt(auxEx.getEndingDate(),j,i);
            i++;
            Exhibition.setValueAt(auxEx.getLocation().getName(),j,i);
            i++;
            Exhibition.setValueAt(auxEx.getPerformers(),j,i);
            i++;
            auxWeb=auxEx.getWebs();
            
            for (String auxWeb1 : auxWeb) {
                Exhibition.setValueAt(auxWeb1,j,i);
            }
            j++;
            i=0;
            
        }
        
        j=0;
        while (itFestival.hasNext()){
            
            Concert[] auxConcerts;
            auxFest=itFestival.next();  
            Festival.setValueAt(auxFest.getName(),j,i);
            i++;
            auxConcerts=auxFest.getConcerts();
            for (Concert auxConcert : auxConcerts) {
                Festival.setValueAt(auxConcert.getName(),j,i);
                i++;
            }
            Festival.setValueAt(auxFest.getStartDate(),j,i);
            i++;
            Festival.setValueAt(auxFest.getEndingDate(),j,i);
            i=0;
            j++;
            
        }
        
        
    }
    
}
