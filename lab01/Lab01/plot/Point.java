package plot;
import matrix.*;

public class Point implements Comparable<Point>
{
   private MatrixOperationsInterface point;

   public Point(double x, double y)
   {
      point = MatrixCreator.create(3, 1);
      point.setElement(1, 1, x);
      point.setElement(2, 1, y);
      point.setElement(3, 1, 1);
   }

   public double getX()
   {
      return point.getElement(1, 1);
   }

   public double getY()
   {
      return point.getElement(2, 1);
   }

   public void setX(double x)
   {
      point.setElement(1, 1, x);
   }

   public void setY(double y)
   {
      point.setElement(2, 1, y);
   }

   public int compareTo(Point point)
   {
      double first = getX();
      double second = point.getX();

      final double TOL = .0001;
      int result;

      if (Math.abs(first - second) < TOL)
      {
         result = 0;
      }
      else if (first - second > 0)
      {
         result = 1;
      }
      else
      {
         result = -1;
      }

      return result;   
   }

   public Point multiply(MatrixOperationsInterface matrix)
   {
      Point result = new Point(0, 0);
      MatrixOperationsInterface temp = null;

      if (matrix.getNumRows() == 3 && matrix.getNumColumns() == 3)
      {
         temp = matrix.multiply(point); 
         result.setX(temp.getElement(1, 1));
         result.setY(temp.getElement(2, 1));
      }

      return result;
   }
}