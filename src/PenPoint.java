import java.awt.*;

public class PenPoint extends Shape{
    public PenPoint(int x, int y, int r, Color color) {
        super(x, y, r, color);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(r));
        g2d.drawLine(x, y, x, y);
    }
}
