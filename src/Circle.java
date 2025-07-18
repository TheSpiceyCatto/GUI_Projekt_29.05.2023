import java.awt.*;

public class Circle extends Shape {
    public Circle(int x, int y, Color color){
        super(x, y, 25, color);
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillOval(x, y, r, r);
    }
}
