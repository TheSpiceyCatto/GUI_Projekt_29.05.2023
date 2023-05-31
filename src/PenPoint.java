import java.awt.*;

public class PenPoint extends Shape{
//    Color color;
//    Point point;

    public PenPoint(int x, int y, int r, Color color) {
        super(x, y, r, color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(r));
        g2d.drawLine(x, y, x, y);
    }

//    public Point getPoint() {
//        return point;
//    }
//
//    public void setPoint(Point point) {
//        this.point = point;
//    }
}
