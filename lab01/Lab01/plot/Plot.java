package plot;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.ArrayList;
import matrix.*;

public class Plot extends JPanel
{
   private ArrayList<Plottable> plots;
   private int xmin;
   private int xmax;
   private int ymin;
   private int ymax;

   public Plot(int xmin, int xmax, int ymin, int ymax)
   {
      this.xmin = xmin;
      this.xmax = xmax;
      this.ymin = ymin;
      this.ymax = ymax;
      plots = new ArrayList<Plottable>();
   }

   public void add(Plottable plot)
   {
      plots.add(plot);
   }

   public int getYMAX()
   {
      return ymax;
   }

   public void setXMIN(int xmin)
   {
      this.xmin = xmin;
   }

   public void setXMAX(int xmax)
   {
      this.xmax = xmax;
   }

   public void setYMIN(int ymin)
   {
      this.ymin = ymin;
   }

   public void setYMAX(int ymax)
   {
      this.ymax = ymax;
   }

   /** Handles the complicated painting for the panel. <br> */
   public void paint(Graphics g)
   {
      int width = getSize().width;
      int height = getSize().height;

      //use double buffering
      Image offScreenBuffer = createImage(width, height);
      Graphics gOff = offScreenBuffer.getGraphics();

      gOff.setColor(Color.white);
      gOff.fillRect(0, 0, width, height);

      MatrixOperationsInterface window = Window.window(width, height, xmin, xmax, ymin, ymax);  

      for (Plottable plot : plots)
      {
         plot.draw(gOff, width, height, window);
      }

      g.drawImage(offScreenBuffer, 0, 0, null);  //copy the offScreenImage to the panel
   }
}

