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
        
        // Load the file.
        File file = new File("Test02.ods");
        final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
        
        
    }
    
}
