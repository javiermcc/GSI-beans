/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILib.Misc;

import GSILabs.BSystem.BusinessSystem;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author drzkn
 */
public class SSTest06 {
    
    public static void main (String[] args) throws IOException{
        
        BusinessSystem miBs=new BusinessSystem();
        File f = new File("P05EjExtra.ods");
        int numFest=miBs.importFestivals(f);
        System.out.println("Se han importado "+numFest+" tickets");
        
    }
    
}
