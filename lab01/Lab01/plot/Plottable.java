package plot;
import java.awt.Graphics;
import matrix.MatrixOperationsInterface;

public interface Plottable
{
    public void draw(Graphics g, int width, int height, MatrixOperationsInterface window);
}