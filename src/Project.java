import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project {
    public static void main(String[] args) {
        new Project();
    }

    private Project(){
        SwingUtilities.invokeLater(this::createGUI);
    }

    private void createGUI(){
        JFrame jFrame = new JFrame("Simple Draw");
        jFrame.setLayout(new BorderLayout());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas drawArea = new Canvas();

        JLabel shapeStatus = new JLabel("Circle");
        JLabel fileStatus = new JLabel("New");

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenuFile = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        JMenuItem saveAsItem = new JMenuItem("Save As...", KeyEvent.VK_A);
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
        JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        quitItem.addActionListener(e -> jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING)));

        jMenuFile.add(openItem);
        jMenuFile.add(saveItem);
        jMenuFile.add(saveAsItem);
        jMenuFile.addSeparator();
        jMenuFile.add(quitItem);

        JMenu jMenuDraw = new JMenu("Draw");


        JMenu figureMenu = new JMenu("Figure");
        ButtonGroup buttonGroup = new ButtonGroup();
        figureMenu.setMnemonic(KeyEvent.VK_F);
        JRadioButtonMenuItem circleButton = new JRadioButtonMenuItem("Circle");
        circleButton.setMnemonic(KeyEvent.VK_C);
        circleButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        circleButton.addActionListener(e -> {
            Canvas.setCurrentShape(Shapes.Circle);
            shapeStatus.setText("Circle");
        });
        JRadioButtonMenuItem squareButton = new JRadioButtonMenuItem("Square");
        squareButton.setMnemonic(KeyEvent.VK_R);
        squareButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        squareButton.addActionListener(e -> {
            Canvas.setCurrentShape(Shapes.Square);
            shapeStatus.setText("Square");
        });
        JRadioButtonMenuItem penButton = new JRadioButtonMenuItem("Pen");
        penButton.setMnemonic(KeyEvent.VK_P);
        penButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        penButton.addActionListener(e -> {
            Canvas.setCurrentShape(Shapes.Pen);
            shapeStatus.setText("Pen");
        });
        buttonGroup.add(circleButton);
        buttonGroup.add(squareButton);
        buttonGroup.add(penButton);
        figureMenu.add(circleButton);
        figureMenu.add(squareButton);
        figureMenu.add(penButton);
        JMenuItem colorItem = new JMenuItem("Color", KeyEvent.VK_C);
        colorItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        colorItem.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(jFrame, "Pick Color", Canvas.getColor());
            if(selectedColor != null){
                Canvas.setColor(selectedColor);
            }
        });
        JMenuItem clearItem = new JMenuItem("Clear", KeyEvent.VK_C);
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        clearItem.addActionListener(e -> drawArea.clear());

        jMenuDraw.add(figureMenu);
        jMenuDraw.add(colorItem);
        jMenuDraw.addSeparator();
        jMenuDraw.add(clearItem);

        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuDraw);

        JToolBar statusBar = new JToolBar();
        statusBar.setLayout(new BorderLayout());
        statusBar.add(shapeStatus, BorderLayout.WEST);
        statusBar.add(fileStatus, BorderLayout.EAST);


        jFrame.add(drawArea, BorderLayout.CENTER);
        jFrame.add(statusBar, BorderLayout.SOUTH);
        jFrame.setJMenuBar(jMenuBar);
        jFrame.setSize(500, 500);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
    }
}