import javax.swing.*;
import java.awt.*;

public class Square extends Shape {
    public Square(int x, int y, Color color){
        super(x, y, 100, color);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, r, r);
    }
}
