/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILib.Misc;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author labora1
 */
public class SSTest03 {
    
    public static void main (String[] args) throws IOException{
        
        /**
         * We load the file
         * We extract a Sheet from test02.ods
         */
        File file = new File("test02.ods");
        final Sheet miSheet = SpreadSheet.createFromFile(file).getSheet(0);
        String valor;
        
        System.out.println("La tabla creada en el SSTest02");
        
        for (int i=0;i<4;i++){
            
            for (int j=0;j<6;j++){

                valor=miSheet.getCellAt(j+3,i+5).getTextValue();
                System.out.print(valor+" ");
                
            }
            System.out.println();
            
        }
        
        
    }
    
}
