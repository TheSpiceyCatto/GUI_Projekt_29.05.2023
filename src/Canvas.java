import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

enum Shapes{
    Circle,
    Square,
    Pen
}

public class Canvas extends JPanel {

    private static ArrayList<Circle> circles;
    private static ArrayList<Square> squares;
    private static ArrayList<PenPoint> points;
    private static Pen pen;
    private Point shapePosition;
    private static Shapes currentShape;
    private static Color color;

    public Canvas() {
        shapePosition = new Point();
        setFocusable(true);
        currentShape = Shapes.Circle;
        circles = new ArrayList<>();
        squares = new ArrayList<>();
        points = new ArrayList<>();
        pen = new Pen(5);
        color = Color.black;
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    Color shapeCol = new Color((int) (Math.random() * 255 + 1), (int) (Math.random() * 255 + 1), (int) (Math.random() * 255 + 1));
                    switch (currentShape) {
                        case Circle -> circles.add(new Circle(shapePosition.x, shapePosition.y, shapeCol));
                        case Square -> squares.add(new Square(shapePosition.x, shapePosition.y, shapeCol));
                    }
                    repaint();
                }
            }
        };
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                PenPoint p = new PenPoint();
                p.setPoint(e.getPoint());
                p.setColor(color);
                int x = e.getX();
                int y = e.getY();
                if (currentShape == Shapes.Pen){
                    pen.draw(getGraphics(), x, y, color);
                    points.add(p);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                PenPoint p = new PenPoint();
                p.setPoint(e.getPoint());
                p.setColor(color);
                int x = e.getX();
                int y = e.getY();
                if (currentShape == Shapes.Pen){
                    pen.draw(getGraphics(), x, y, color);
                    points.add(p);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e){
                shapePosition = e.getPoint();
            }
        };
        addKeyListener(keyAdapter);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (circles != null) {
            for (Circle e : circles){
                e.paintComponent(g);
            }
        }
        if (squares != null) {
            for (Square e : squares)
                e.paintComponent(g);
        }
        if (points != null){
            for (PenPoint p : points)
                pen.draw(g, p.getPoint().x, p.getPoint().y, p.getColor());
        }
    }

    public void clear(){
        circles = new ArrayList<>();
        squares = new ArrayList<>();
        points = new ArrayList<>();
        repaint();
    }

    public static void setCurrentShape(Shapes newShape) {
        currentShape = newShape;
    }

    public static Color getColor() {
        return color;
    }

    public static void setColor(Color color) {
        Canvas.color = color;
    }
}