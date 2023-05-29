import javax.swing.*;
import java.awt.*;

public class Circle extends JComponent {
    int x, y, r;
    public Circle(int x, int y){
        this.x = x;
        this.y = y;
        r = 100;
        setPreferredSize(new Dimension(r, r));
    }

    @Override
    public void paintComponent(Graphics g){
        g.fillOval(x, y, r, r);
    }
}
