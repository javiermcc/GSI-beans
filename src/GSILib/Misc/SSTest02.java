/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILib.Misc;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author labora1
 */
public class SSTest02 {
    
    public static void main(String[] args) throws IOException {
        
        /**
         * miArray: the bidimensional array we are going to create
         * miFila: this is an auxiliar variable where we are going to store each row of our bidimensional array
         * miS: an object of the class required to do a SpreadSheet
         * miCell: the variable where we store each cell of the SpreadSheet we are going to change
         * This the file where we are going to save the spreadsheet
         */
        Object[][] miArray=new Object[4][6];
        Object[] miFila=new Object[6];
        final Sheet miS;
        MutableCell miCell;
        File outfile=new File("test02.ods");
        
        
        for (int i=0;i<4;i++){
            for (int j=0;j<6;j++){
                miArray[i][j]=(int) Math.floor(Math.random()*24+1);
            }
        }
        
        /**
         * First we create a table model to save the bidimensional array
         * We update the dimensions of the table model to our specifications
         */
        DefaultTableModel model=new DefaultTableModel();
        model.setColumnCount(9);
        model.setRowCount(9);
        
        /**
         * We insert each row of the Table
         */
        for (int i=0;i<4;i++){
            miFila=miArray[i];
            model.insertRow(i,(Object[])miFila);
        }
       
        
        try{
            SpreadSheet.createEmpty(model).saveAs(outfile);
        }catch(FileNotFoundException e){
            System.out.println("No se ha encontrado el fichero deseado");
        }
        
        miS = SpreadSheet.createFromFile(outfile).getSheet(0);
                
        /**
         * With these two iterative instructions we replace the table to the 
         * positions we want
         */
        for (int i=0;i<6;i++){
            miS.setValueAt(null, i, 4);
        }
        
        for (int i=0;i<9;i++){
            miS.setValueAt(null, i, 0);
        }
        
        /**
         * We update each Cell of the table, we color the cells with cyan if the value
         * is greater than 10 and with red what else
         */
        for (int i=0;i<4;i++){
            for (int j=0;j<6;j++){
                miS.setValueAt(miArray[i][j], j+3, i+6);
                miS.setValueAt(null, j, i);
                miCell=miS.getCellAt(j+3,i+6);
                if ((int)miArray[i][j]<=10){
                    miCell.setBackgroundColor(Color.red);
                }else{
                    miCell.setBackgroundColor(Color.cyan);
                }
            }
        }
        
        File outFile = new File("Test02.ods");
        OOUtils.open(miS.getSpreadSheet().saveAs(outFile));
            
    }
    
}
