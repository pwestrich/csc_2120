import java.awt.*;
import javax.swing.JPanel;

public class DataPlot extends CenterFrame
{

   public DataPlot(int width, int height, String title, JPanel draw)
   {
      super(640, 480, title);

      add(draw);  
      setVisible(true);
   }

}

