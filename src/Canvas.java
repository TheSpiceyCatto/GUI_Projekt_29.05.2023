import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

enum Shapes{
    Circle,
    Square,
    Pen
}

public class Canvas extends JPanel {

    private static ArrayList<Circle> circles;
    private static ArrayList<Square> squares;
    private static Shapes currentShape;
    private static Color color;

    public Canvas() {
        currentShape = Shapes.Circle;
        circles = new ArrayList<>();
        squares = new ArrayList<>();
        color = Color.black;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                switch (currentShape){
                    case Circle -> circles.add(new Circle(x, y, color));
                    case Square -> squares.add(new Square(x, y, color));
                    case Pen -> System.out.println("Imagine Pen");
                    default -> System.out.println("Shapeless");
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (circles != null) {
            for (Circle e : circles)
                e.paintComponent(g);
        }
        if (squares != null) {
            for (Square e : squares)
                e.paintComponent(g);
        }
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