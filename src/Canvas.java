import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

enum Shapes{
    Circle,
    Square,
    Pen
}

public class Canvas extends JPanel {
    private JLabel jLabel;
    private static ArrayList<Shape> shapes;
    private static Pen pen;
    private Point shapePosition;
    private static Shapes currentShape;
    private static Color color;

    public Canvas(JLabel jLabel) {
        this.jLabel = jLabel;
        shapePosition = new Point();
        setFocusable(true);
        currentShape = Shapes.Circle;
        shapes = new ArrayList<>();
        pen = new Pen(5);
        color = Color.black;
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F1) {
                    jLabel.setText("Modified");
                    Color shapeCol = new Color((int) (Math.random() * 255 + 1), (int) (Math.random() * 255 + 1), (int) (Math.random() * 255 + 1));
                    switch (currentShape) {
                        case Circle -> shapes.add(new Circle(shapePosition.x, shapePosition.y, shapeCol));
                        case Square -> shapes.add(new Square(shapePosition.x, shapePosition.y, shapeCol));
                    }
                    repaint();
                }
            }
        };
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                PenPoint p = new PenPoint(x, y, pen.getSize(), color);
                if (currentShape == Shapes.Pen){
                    jLabel.setText("Modified");
                    pen.draw(getGraphics(), x, y, color);
                    shapes.add(p);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                PenPoint p = new PenPoint(x, y, pen.getSize(), color);
                if (currentShape == Shapes.Pen){
                    jLabel.setText("Modified");
                    pen.draw(getGraphics(), x, y, color);
                    shapes.add(p);
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
        if (shapes != null) {
            for (Shape e : shapes)
                e.paintComponent(g);
        }
    }

    public void save(File file){
        try (FileOutputStream fileOut = new FileOutputStream(file)){
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(shapes);
        } catch (IOException e){
            System.out.println("File Output Error");
        }
    }

    public void load(File file){
        try (FileInputStream fileIn = new FileInputStream(file)){
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            try {
                shapes = (ArrayList<Shape>) objectIn.readObject();
            } catch (ClassNotFoundException c){
                System.out.println("Class Not Found In File");
            }
        } catch (IOException e){
            System.out.println("File Input Error");
        }
        repaint();
    }

    public void clear(){
        shapes = new ArrayList<>();
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