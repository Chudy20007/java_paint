package l1z1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainPanel extends JPanel  {
    
    Timer timer=new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if(e.getSource()==timer){
              System.out.println("A");
              Graphics g = mainPanelHandler.getGraphics();
              mainPanelHandler.paintComponent(g);
    }  
        }
    });
    List<Figures> list = new ArrayList<Figures>();
    static MainPanel mainPanelHandler;
    JLabel figuresLabel;
    JLabel colorsLabel;
    JLabel figureHeightLabel;
    JLabel figureWidthLabel;
    JComboBox figures;
    JComboBox colors;
    JTextArea figureHeight;
    JTextArea figureWidth;

    GridLayout gridBag;
    JMenuBar mainMenuBar;
    JMenu fileDialog;
    JMenu figureDialog;
    JMenuItem saveFile;
    JMenuItem loadFile;
    JMenuItem closeFile;
    JMenuItem newFigure;
    MainFrame mainProgramFrame;

    public MainPanel() {
        setLayout(null);
        mainPanelHandler = this;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth, screenHeight - 30);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    Integer.parseInt(figureHeight.getText());
                    Integer.parseInt(figureWidth.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Check fields!");
                    return;
                }
                // System.out.println(figures.getSelectedItem().toString());
                switch (figures.getSelectedItem().toString()) {

                    case ("rectangle"): {
                        list.add(new Rectangle("rectangle", e.getPoint(), colors.getSelectedItem().toString(), Integer.parseInt(figureWidth.getText()), Integer.parseInt(figureHeight.getText())));

                        Rectangle rect = (Rectangle) list.get(list.size() - 1);
                        rect.drawFigure();

                        break;
                    }

                    case ("square"): {
                        list.add(new Square("square", e.getPoint(), colors.getSelectedItem().toString(), Integer.parseInt(figureHeight.getText())));
                        Square square = (Square) list.get(list.size() - 1);

                        square.drawFigure();
                        break;
                    }

                    case ("triangle"): {
                        list.add(new Triangle("triangle", e.getPoint(), colors.getSelectedItem().toString(), Integer.parseInt(figureHeight.getText())));
                        Triangle triangle = (Triangle) list.get(list.size() - 1);

                        triangle.drawFigure();

                        break;
                    }

                    case ("circle"): {
                        list.add(new Circle("circle", e.getPoint(), colors.getSelectedItem().toString(), Integer.parseInt(figureHeight.getText())));
                        Circle circle = (Circle) list.get(list.size() - 1);

                        circle.drawFigure();

                        break;
                    }

                }

//                     Iterator<Figures> iter = myQueue.iterator();
//                        while (iter.hasNext()) {
//                            Figures current = iter.next();
//                           System.out.println("=="+current+"==");
//                        }
            }

        });

        generateMenu();
        generateMenuListeners();
        generateFigureComponents();
timer.start();
    }

    @Override
        protected void paintComponent(Graphics g) {
            //this.repaint();
            System.out.print("x");
        if (list.size() > 0) {

        
            for (int i = 0; i < list.size(); i++) {
                switch (list.get(i).name) {
                    case ("rectangle"): {

                        Rectangle rect = (Rectangle) list.get(i);
                        rect.drawFigure();

                        break;
                    }

                    case ("square"): {

                        Square square = (Square) list.get(i);

                        square.drawFigure();
                        break;
                    }

                    case ("triangle"): {

                        Triangle triangle = (Triangle) list.get(i);

                        triangle.drawFigure();

                        break;
                    }

                    case ("circle"): {

                        Circle circle = (Circle) list.get(i);

                        circle.drawFigure();

                        break;
                    }
                }
            }
        }
    }

    public MainPanel getMainPanel() {
        return mainPanelHandler;
    }

    private void generateMenu() {
        mainMenuBar = new JMenuBar();
        mainMenuBar.setBounds(0, 0, getWidth(), getHeight());
        mainMenuBar.setVisible(true);
        fileDialog = new JMenu("File");
        figureDialog = new JMenu("Figures");
        saveFile = new JMenuItem("Save");
        closeFile = new JMenuItem("Close");
        loadFile = new JMenuItem("Load");
        newFigure = new JMenuItem("New");
        figureDialog.add(newFigure);
        fileDialog.add(saveFile);
        fileDialog.add(loadFile);
        fileDialog.add(closeFile);
        mainMenuBar.add(fileDialog);
        mainMenuBar.add(figureDialog);

        add(mainMenuBar);
        mainMenuBar.setLocation(0, 0);
        mainMenuBar.setSize(getWidth(), 20);

    }

    private void generateMenuListeners() {

        saveFile.addActionListener(new MainMenuListener());
        closeFile.addActionListener(new MainMenuListener());
        loadFile.addActionListener(new MainMenuListener());
        newFigure.addActionListener(new MainMenuListener());
    }

    class MainMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case ("Close"): {
                    if (mainProgramFrame.isVisible()) {
                        mainProgramFrame.dispose();
                    }
                    break;
                }

                case ("Save"): {
                    saveFileForm();
                    break;
                }

                case ("Load"): {
                    loadFileForm();
                    break;
                }

                case ("New"): {
                    FigureGenerator figureGenerator = new FigureGenerator();
                    break;
                }
            }
            //  System.out.println(e.getActionCommand());
        }

    }

    public void generateFigureComponents() {
        figureHeight = new JTextArea("100");
        figureWidth = new JTextArea("100");
        figures = new JComboBox();
        colors = new JComboBox();
        figures.addItem("rectangle");
        figures.addItem("square");
        figures.addItem("circle");
        figures.addItem("triangle");

        colors.addItem("red");
        colors.addItem("green");
        colors.addItem("blue");
        colors.addItem("yellow");

        figuresLabel = new JLabel("Figures:");
        colorsLabel = new JLabel("Colors:");
        figureHeightLabel = new JLabel("Figure height:");
        figureWidthLabel = new JLabel("Figure width:");

        figuresLabel.setSize(150, 20);
        figures.setSize(100, 20);
        figureWidthLabel.setSize(150, 20);
        figureWidth.setSize(100, 20);
        figureHeightLabel.setSize(150, 20);
        figureHeight.setSize(100, 20);
        colorsLabel.setSize(150, 20);
        colors.setSize(100, 20);

        add(figuresLabel);
        add(figures);
        add(figureWidthLabel);
        add(figureWidth);
        add(figureHeightLabel);
        add(figureHeight);
        add(colorsLabel);
        add(colors);

        //  System.out.println(this.getWidth());
        figuresLabel.setLocation(this.getWidth() - 230, 50);
        figures.setLocation(getWidth() - 140, 50);
        figureWidthLabel.setLocation(getWidth() - 230, 90);
        figureWidth.setLocation(getWidth() - 140, 90);
        figureHeightLabel.setLocation(getWidth() - 230, 130);
        figureHeight.setLocation(getWidth() - 140, 130);
        colorsLabel.setLocation(getWidth() - 230, 170);
        colors.setLocation(getWidth() - 140, 170);

        figures.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                switch (figures.getSelectedItem().toString()) {
                    case ("circle"): {
                        figureWidth.setEditable(false);
                        figureWidth.setBackground(Color.gray);
                        figureHeightLabel.setText("Circle radius:");
                        break;
                    }

                    case ("square"): {
                        figureWidth.setEditable(false);
                        figureWidth.setBackground(Color.gray);
                        figureHeightLabel.setText("Square height:");
                        break;
                    }

                    case ("rectangle"): {
                        figureWidth.setEditable(true);
                        figureWidth.setBackground(Color.white);
                        figureHeightLabel.setText("Figure height:");
                        break;
                    }

                    case ("triangle"): {
                        figureWidth.setEditable(false);
                        figureWidth.setBackground(Color.gray);
                        figureHeightLabel.setText("Triangle height:");
                        break;
                    }
                }

            }

        });

    }

    private void loadFileForm() {
        JFileChooser fileChooser = new JFileChooser("C:\\");
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                loadFile(file.getPath());
            } catch (Exception ex) {

            }
        }
    }

    private void saveFileForm() {
        JFileChooser fileChooser = new JFileChooser("C:\\");
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {

                saveFile(file.getPath());
            } catch (Exception e) {

            }

        }
    }

    private void loadFile(String pathFile) throws FileNotFoundException {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to load figures on actual panel?", "Information message", dialogButton);
        if (dialogResult == 1) {
            Graphics g = mainPanelHandler.getGraphics();
            
            list.clear();
            this.paintComponent(g);
            

        }
        
       


        Scanner openFile = null;
        List<String> list2 = new ArrayList<>();
        File selectedFile = new File(pathFile);

        openFile = new Scanner(selectedFile);
        // System.out.println(openFile.next());

        if (openFile.hasNext() == false) {
            System.out.println("File is empty.");
            return;
        }
        System.out.println("");
        do {
            String[] x = (openFile.nextLine().split(" "));

            for (int i = 0; i < x.length; i++) {
                switch (x[0]) {
                    case ("rectangle"): {
                        Point p = new Point(Integer.parseInt(x[4]), Integer.parseInt(x[5]));
                        int width = Integer.parseInt(x[2]);
                        int height = Integer.parseInt(x[3]);
                        String color = x[1];
                        String name = x[0];
                        list.add(new Rectangle(name, p, color, width, height));
                        //list.add(new Rectangle("rectangle", e.getPoint(), colors.getSelectedItem().toString(), Integer.parseInt(figureWidth.getText()), Integer.parseInt(figureHeight.getText())));
                        Rectangle rect = (Rectangle) list.get(list.size() - 1);
                        rect.drawFigure();

                        break;
                    }

                    case ("square"): {
                        Point p = new Point(Integer.parseInt(x[3]), Integer.parseInt(x[4]));
                        int height = Integer.parseInt(x[2]);

                        String color = x[1];
                        String name = x[0];
                        list.add(new Square(name, p, color, height));
                        Square square = (Square) list.get(list.size() - 1);

                        square.drawFigure();
                        break;
                    }

                    case ("triangle"): {
                        Point p = new Point(Integer.parseInt(x[3]), Integer.parseInt(x[4]));
                        int height = Integer.parseInt(x[2]);

                        String color = x[1];
                        String name = x[0];
                        list.add(new Triangle(name, p, color, height));
                        //                        list.add(new Triangle("triangle", e.getPoint(), colors.getSelectedItem().toString(), Integer.parseInt(figureHeight.getText())));
                        Triangle triangle = (Triangle) list.get(list.size() - 1);

                        triangle.drawFigure();

                        break;
                    }

                    case ("circle"): {
                        Point p = new Point(Integer.parseInt(x[3]), Integer.parseInt(x[4]));
                        int height = Integer.parseInt(x[2]);

                        String color = x[1];
                        String name = x[0];
                        //list.add(new Circle("circle", e.getPoint(), colors.getSelectedItem().toString(), Integer.parseInt(figureHeight.getText())));
                        list.add(new Circle(name, p, color, height));
                        Circle circle = (Circle) list.get(list.size() - 1);

                        circle.drawFigure();

                        break;
                    }

                }
            }

        } while (openFile.hasNext() == true);
        
      

    }

    private void saveFile(String pathFile) throws IOException {
        FileWriter writer = null;

        writer = new FileWriter(pathFile + ".txt", true);

        int i = 0;
        do {
            Figures x = list.get(i);
            switch (x.name) {
                case ("square"): {

                    Square sq = (Square) x;
                    writer.write(sq.name + " " + sq.color + " " + sq.height + " " + sq.p.x + " " + sq.p.y + "\n");

                    break;
                }

                case ("circle"): {
                    Circle cir = (Circle) x;
                    writer.write(cir.name + " " + cir.color + " " + cir.radius + " " + cir.p.x + " " + cir.p.y + "\n");
                    break;
                }

                case ("triangle"): {
                    Triangle tr = (Triangle) x;
                    writer.write(tr.name + " " + tr.color + " " + tr.height + " " + tr.p.x + " " + tr.p.y + "\n");
                    break;
                }

                case ("rectangle"): {
                    Rectangle rec = (Rectangle) x;
                    writer.write(rec.name + " " + rec.color + " " + rec.width + " " + rec.height + " " + rec.p.x + " " + rec.p.y + "\n");
                    break;
                }

            }
            i++;
        } while (list.size() > i);

        writer.close();
       // list.clear();
        System.out.println(".");
    }
}
