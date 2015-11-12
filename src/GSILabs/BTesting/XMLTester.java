/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILabs.BTesting;

import GSILabs.BModel.Artist;
import GSILabs.BModel.Client;
import GSILabs.BModel.Collective;
import GSILabs.BModel.Location;
import GSILabs.BModel.ModelDate;
import GSILabs.BSystem.BusinessSystem;
import java.io.File;


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
    
        Artist art = new Artist("Nombre 1", "Descripcion 1", "Web 1");
        Artist art2 = new Artist("Nombre 2", "Descripcion 2", "Web 2");
        Artist art3 = new Artist("Nombre 3", "Descripcion 3", "Web 3");
        Artist art4 = new Artist("Nombre 4", "Descripcion 4");
        
        bus.addArtist(art);
        bus.addArtist(art2);
        bus.addArtist(art3);
        bus.addArtist(art4);
        
        
        Collective col = new Collective("Colectivo 1", art, art2, "Descripcion colectivo 1","Web colectivo 1");
        Collective col2 = new Collective("Colectivo 2", art3, art4, "Descripcion colectivo 2");
        
        bus.addCollective(col);
        bus.addCollective(col2);
        
        
        Location l1 = new Location("Loc 1", 1, "Pais 1", " Provincia 1", "Ciudad 1", "Calle 1", (short)1, "Web 1");
        Location l2 = new Location("Loc 2", 2, "Pais 2", " Provincia 2", "Ciudad 2", "Calle 2", (short)2);
        
        bus.addLocation(l1);
        bus.addLocation(l2);
        
        
        Client cli1 = new Client(1, "Nombre 1", "Apellidos 1", new ModelDate(2,9,1994), "1");
        Client cli2 = new Client(2, "Nombre 2", "Apellidos 2", new ModelDate(2,9,1994), "2");
        
        bus.addClient(cli1);
        bus.addClient(cli2);
        
        File f = new File("/home/linux1/bus.xml");
        bus.saveToXML(f);
    }
}
