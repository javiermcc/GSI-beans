/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILib.Misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author linux1
 */
public class SSTest01{
    
    public static void main(String[] args) throws IOException {
        
        /**
         * miArray: the bidimensional array we are going to create
         * miFila: this is an auxiliar variable where we are going to store each row of our bidimensional array
         * miS: an object of the class required to do a SpreadSheet
         * This the file where we are going to save the spreadsheet
         */
        Object[][] miArray=new Object[4][6];
        Object[] miFila=new Object[6];
        final Sheet miS;
        File outfile=new File("test01.ods");
        
        
        for (int i=0;i<4;i++){
            for (int j=0;j<6;j++){
                miArray[i][j]=(int) Math.floor(Math.random()*24+1);
            }
        }
        
        /**
         * First we create a table model to save the bidimensional array
         */
        DefaultTableModel model=new DefaultTableModel();
        model.setColumnCount(6);
        
        /**
         * With these iteration we insert each row of the bidimensional array
         */
        for (int i=0;i<4;i++){
            miFila=miArray[i];
            model.insertRow(i,(Object[])miFila);
        }
        
        /**
         * We save all the changes we have made on the Spreadsheet
         */
        try{
            SpreadSheet.createEmpty(model).saveAs(outfile);
        }catch(FileNotFoundException e){
            System.out.println("No se ha encontrado el fichero deseado");
        }
        
        miS = SpreadSheet.createFromFile(outfile).getSheet(0);
        
        /**
         * With this iteration we overwrite the row that has 
         * letters
         */
        for (int i=0;i<4;i++){
            for (int j=0;j<6;j++){
                miS.setValueAt(miArray[i][j],j,i);
            }
        }
        
        /**
         * With these iteration we delete the last row we
         * have in the SpreedSheet
         */
        for (int i=0;i<6;i++){
            miS.setValueAt(null, i, 4);
        }
        
        /**
         * We close the File and then we open the SpreadSheet
         */
        File outFile = new File("test01.ods");
        OOUtils.open(miS.getSpreadSheet().saveAs(outFile));
            
    }
    
}
