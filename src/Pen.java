import javax.sound.sampled.Line;
import java.awt.*;
import java.util.ArrayList;

public class Pen{
    private int size;
    public Pen(int size){
        this.size = size;
    }


    public void draw(Graphics g, int x, int y, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(size));
        g2d.drawLine(x, y, x, y);
    }

    public int getSize() {
        return size;
    }
}
