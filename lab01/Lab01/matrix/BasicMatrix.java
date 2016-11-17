package matrix;
import java.text.DecimalFormat;

/**  
 * This class is designed to abstract 2-D arrays.  The main features are that indices start with one
 * rather than zero, and methods make 2-D array operations like multiplication much simpler for the user. 
 * This class only supports rectangular 2-D arrays, however. 
 */

class BasicMatrix implements BasicMatrixInterface
{

   /**
    * The 2-D array that we are abstracting. <br>
    */
   private double[][] matrix;

   /** 
    * Constructor to create the Matrix with the given size. <br> 
    * Preconditions: Valid number of rows and columns specified. <br>
    * Postconditions: The Matrix object is created and is ready for use. <br>
    * Throws: MatrixException if rows or columns < 1. <br>
    * Note: Ragged Matrices are not allowed (Matrices will always be rectangular).
    */
   public BasicMatrix(int rows, int columns)
   {
      if (rows<1 || columns <1)
         throw new MatrixException("Invalid row or column.");

      matrix=new double[rows][columns];
   }

   public int getNumRows()
   {
      return matrix.length;
   }

   public int getNumColumns()
   {
      return matrix[0].length;
   }

   public void setElement(int row, int column, double value) throws MatrixException
   {
      if (row>getNumRows() || row<1 || column>getNumColumns() || column<1)
         throw new MatrixException("Invalid row or column.");

      matrix[row-1][column-1]=value;
   }

   public double getElement(int row, int column) throws MatrixException
   {
      if (row>getNumRows() || row<1 || column>getNumColumns() || column<1)
         throw new MatrixException("Invalid row or column.");

      return matrix[row-1][column-1];
   }

   /** Constants used in the toString() method for decently formatted output of the Matrix. <br> */
   private static final int SPACING=8;
   private static final int PLACES=2;
   private static final int LEADING=4;

   /** Used to display the matrix contents. <br> */
   public String toString()
   {
      DecimalFormat fmt;
      if (PLACES==1)
         fmt=new DecimalFormat("0.0");
      else if (PLACES==2)
         fmt=new DecimalFormat("0.00");
      else if (PLACES==3)
         fmt=new DecimalFormat("0.000");
      else if (PLACES==4)
         fmt=new DecimalFormat("0.0000");
      fmt.setMinimumIntegerDigits(LEADING);  //this causes preceding digits to be displayed
      fmt.setNegativePrefix("");
      fmt.setNegativeSuffix("-");//the minus sign will come after the number

      String temp="";
      for (int i=1;i<=getNumRows();i++) //obviously, we need to loop over the entire matrix
      {
         for (int j=1;j<=getNumColumns();j++)
         {
            String spacer="";
            StringBuffer formatter;
            if ( getElement(i,j)>=-0.000001 && getElement(i,j)<=0.00000 )
               setElement(i,j,0.0);
            formatter=new StringBuffer(fmt.format(getElement(i,j)));

            //each entry should take up exactly 8 spaces, including the possible negative sign which comes after the number      
            for (int k=1;k<=(SPACING-formatter.length());k++)  //may need extra spaces if there is no negative sign
            {
               spacer+=" ";
            }

            //default formatting will show preceding zeroes, which I don't want
            //however, I asked for them above so that alignment can be maintained
            for (int k=0;k<formatter.length();k++)  //loop over the formatted numbers to eliminate unneccessary preceding zeroes
            {
               if (formatter.charAt(k)!='0')  //if the zeroes before the decimal follow a digit, don't remove the zeroes
               {
                  break;
               }
               if (formatter.charAt(k)=='0' && formatter.charAt(k+1)!='.')
               {
                  formatter.setCharAt(k,' ');
               }
               else if (formatter.charAt(k+1)=='.')  //don't remove zeroes after the decimal, however
               {
                  break;
               }
            }

            temp+= formatter + spacer;
            
         }
         temp+="\n";  //next row
      }

      return temp;
    
   }

}

