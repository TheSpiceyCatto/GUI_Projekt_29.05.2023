import javax.swing.*;
import java.awt.*;

public class Shape extends JComponent {
    protected int x, y, r;
    protected Color color;
    public Shape(int x, int y, int r, Color color){
        this.color = color;
        this.r = r;
        this.x = x - (int)(r * 0.5);
        this.y = y - (int)(r * 0.5);
        setPreferredSize(new Dimension(r, r));
    }
}
