package matrix;

/**  This class is used to create and return a MatrixOperationsInterface object. */
public class MatrixCreator 
{

   private MatrixCreator()
   {
   }

   /** 
    *  Creates and returns a MatrixOperationsInterface object with the specified dimensions (row major). <br>
    *  If the number of rows and cols are the same, an identity matrix is returned. <br>
    */
   public static MatrixOperationsInterface create(int rows, int columns)
   {
      MatrixOperationsInterface matrix = new Matrix(rows,columns);

      if (rows == columns)  //square matrix so return the identity matrix
      {
         for (int x = 1; x <= rows; x++)
         {
            matrix.setElement(x, x, 1);
         }
      }

      return matrix;
   }

}