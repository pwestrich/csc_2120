package plot;
import matrix.*;

public class Window
{
   public static MatrixOperationsInterface translate(double x, double y)
   {
      MatrixOperationsInterface T = MatrixCreator.create(3, 3);

      T.setElement(1,1,1);
      T.setElement(1,2,0);
      T.setElement(1,3,x);

      T.setElement(2,1,0);
      T.setElement(2,2,1);
      T.setElement(2,3,y);

      T.setElement(3,1,0);
      T.setElement(3,2,0);
      T.setElement(3,3,1);

      return T;
   }

   public static MatrixOperationsInterface scale(double x, double y)
   {
      MatrixOperationsInterface S = MatrixCreator.create(3, 3);

      S.setElement(1,1,x);
      S.setElement(1,2,0);
      S.setElement(1,3,0);

      S.setElement(2,1,0);
      S.setElement(2,2,y);
      S.setElement(2,3,0);

      S.setElement(3,1,0);
      S.setElement(3,2,0);
      S.setElement(3,3,1);

      return S;
   }

   //transform to pixel coords
   public static MatrixOperationsInterface window(int xPixels, int yPixels, double xMin, double xMax, double yMin, double yMax)
   {
      MatrixOperationsInterface translate = Window.translate(-xMin, -yMin);
      MatrixOperationsInterface aspect = Window.scale(2.0/(xMax - xMin), 2.0/(yMax - yMin));

      MatrixOperationsInterface result = MatrixCreator.create(3, 3);

      double nx = xPixels;
      double ny = yPixels;

      result.setElement(1,1,nx/2);  
      result.setElement(1,2,0);
      result.setElement(1,3,0); //change from (nx-1)/2 
      result.setElement(2,1,0);
      result.setElement(2,2,-ny/2);  
      result.setElement(2,3,ny); //change from (ny-1)/2   
      result.setElement(3,1,0);
      result.setElement(3,2,0);
      result.setElement(3,3,1);

      return result.multiply(aspect.multiply(translate));
   }
}

