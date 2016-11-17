package matrix;

/**
 *  This class extends the functionality of the BasicMatrix by implementing the common
 *  Matrix operations specified in MatrixOperationsInterface.  Direct access to the 2-D
 *  array is not possible (declared private in BasicMatrix), 
 *  but use of the public BasicMatrix methods is all that is required to perform
 *  the operations.
 */
class Matrix extends BasicMatrix implements MatrixOperationsInterface
{

   /** 
    * Constructor to create the Matrix with the given size. <br> 
    * Preconditions: Valid number of rows and columns specified. <br>
    * Postconditions: The Matrix object is created and is ready for use. <br>
    * Throws: MatrixException if rows or columns < 1. <br>
    * Note: Ragged Matrices are not allowed (Matrices will always be rectangular).
    */
   public Matrix(int rows, int columns)
   {
      super(rows,columns);
   }

   public MatrixOperationsInterface add(MatrixOperationsInterface otherMatrix) throws MatrixException
   {
      MatrixOperationsInterface result=null;

      if( otherMatrix==null || getNumRows()!=otherMatrix.getNumRows() || getNumColumns()!=otherMatrix.getNumColumns())
         throw new MatrixException ("Cannot add these two matrices.");
      else 
         result=new Matrix(getNumRows(),getNumColumns());    

      for (int i=1;i<=getNumRows();i++)
      {
         for (int j=1;j<=getNumColumns();j++)
         {
            result.setElement(i,j,getElement(i,j)+otherMatrix.getElement(i,j));
         }
      }

      return result;  
   }

   public MatrixOperationsInterface multiply(MatrixOperationsInterface otherMatrix) throws MatrixException
   {
      MatrixOperationsInterface result=null;
      double x=0;

      if (otherMatrix == null || getNumColumns() != otherMatrix.getNumRows())
         throw new MatrixException ("Cannot multiply these two matrices.");
      else 
         result = new Matrix(getNumRows(),otherMatrix.getNumColumns());

      //loop over all elements of resulting matrix
      for (int i = 1; i <= result.getNumRows(); i++)
      {
         for (int j = 1; j <= result.getNumColumns(); j++)
         {
            x=0;
            //sum up multiplying matrices to obtain value placed in new matrix
            for (int k=1;k<=getNumColumns();k++)
            {
               x = x + getElement(i,k)*otherMatrix.getElement(k,j);
            }
            result.setElement(i,j,x);
         }
      }

      return result;
   }

   public MatrixOperationsInterface transpose()
   {
      MatrixOperationsInterface result=new Matrix(getNumColumns(),getNumRows());

      for (int i=1;i<=getNumRows();i++)
      {
         for (int j=1;j<=getNumColumns();j++)
         {
            result.setElement(j,i,getElement(i,j));
         }
      }
    
      return result;
   }

   private static void substitute(double[][] a, int[] o, int n, double[] b, double[] x)
   {
      double sum;
      double factor;

      for (int i = 2; i <= n; i++)
      {
         sum = b[o[i - 1] - 1];
         for (int j = 1; j <= i - 1; j++)
         {
            sum = sum - a[o[i - 1] - 1][j - 1] * b[o[j - 1] - 1];
         }
         b[o[i - 1] - 1] = sum;
      }

      x[n - 1] = b[o[n - 1] - 1]/(a[o[n - 1] - 1][n - 1]);
      for (int i = n - 1; i >= 1; i--)
      {
         sum = 0;
         for (int j = i + 1; j <= n; j++)
         {
            sum = sum + a[o[i - 1] - 1][j - 1] * x[j - 1];
         }
         x[i - 1] = (b[o[i - 1] - 1] - sum) / a[o[i - 1] - 1][i - 1];
      }
   }

   private static void pivot(double[][] a, int[] o, double[] s, int n, int k)
   {
      int p = k;

      double big = Math.abs(a[o[k - 1] - 1][k - 1]/s[o[k - 1] - 1]);
      double dummy;

      for (int i = k; i < n; i++)
      {
         dummy = Math.abs(a[o[i - 1] - 1][k - 1]/s[o[i - 1] - 1]);
         if (dummy > big)
         {
            big = dummy;
            p = i;
         }
      }

      int dum = o[p - 1];
      o[p - 1] = o[k - 1];
      o[k - 1] = dum;
   }

   private static int decompose(double[][] a, int n, double tol, int[] o, double[] s)
   {

      for(int i = 1; i <= n; i++)
      {
         o[i - 1] = i;
         s[i - 1] = Math.abs(a[i - 1][1 - 1]);
         for (int j = 2; j <= n; j++)
         {
            if (Math.abs(a[i - 1][j - 1]) > s[i - 1])
            {
               s[i - 1] = Math.abs(a[i - 1][j - 1]);
            }
         }
      }

      for (int k = 1; k <= n - 1; k++)
      {
         pivot(a, o, s, n, k);
         if (Math.abs(a[o[k - 1] - 1][k - 1] / s[o[k - 1] - 1]) < tol)
         {
           return -1;
         }

         for (int i = k + 1; i <= n; i++)
         {
            double factor = a[o[i - 1] - 1][k - 1] / a[o[k - 1] - 1][k - 1];
            a[o[i - 1] - 1][k - 1] = factor;

            for (int j = k + 1; j <= n; j++)
            {
               a[o[i - 1] - 1][j - 1] = a[o[i - 1] - 1][j - 1] - factor * a[o[k - 1] - 1][j - 1];
            }

         }
      }

      if (Math.abs(a[o[n - 1] - 1][n - 1] / s[o[n - 1] - 1]) < tol)
      {
          return -1;
      }

      return 0;
   }

   private static double[][] invert(double[][] a) throws MatrixException
   {
      int n = a.length;
      double tol = .00000001;
      int er = 0;
      int[] o = new int[n];
      double[] s = new double[n];
      double[] b = new double[n];
      double[] x = new double[n];
      double[][] ai = new double[n][n];

      er = decompose(a, n, tol, o, s);

      if (er == -1)
      {
         throw new MatrixException("Matrix cannot be inverted.");
      }

      else
      {
         for (int i = 1; i <= n; i++)
         {
            for(int j = 1; j <= n; j++)
            {
               if (i == j)
               {
                  b[j - 1] = 1;
               }
               else
               {
                  b[j - 1] = 0;
               }
            }

            substitute(a, o, n, b, x);

            for(int j = 1; j <= n; j++)
            {
               ai[j - 1][i - 1] = x[j - 1];
            }
         }
  
      }

      return ai;
   }

   public static MatrixOperationsInterface toMatrix(double[][] array)
   {
      int rows = array.length;
      int cols = array[0].length;
      MatrixOperationsInterface mat = MatrixCreator.create(rows, cols);

      for (int i = 1; i <= rows; i++)
      {
         for (int j = 1; j <= cols; j++)
         {
            mat.setElement(i, j, array[i - 1][j - 1]);
         }
      }

      return mat;
   }

   public static double[][] toArray(MatrixOperationsInterface m)
   {
      int rows = m.getNumRows();
      int cols = m.getNumColumns();
      double[][] array = new double[rows][cols];

      for (int i = 1; i <= rows; i++)
      {
         for (int j = 1; j <= cols; j++)
         {
            array[i - 1][j - 1] = m.getElement(i, j);
         }
      }

      return array;
   }

   public MatrixOperationsInterface inverse() throws MatrixException
   {

      double[][] a = toArray(this);
      double[][] ai = invert(a);

      MatrixOperationsInterface inverse = toMatrix(ai);
      return inverse;
   }

}

