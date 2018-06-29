package l1z1;

import java.awt.Color;
import java.awt.Point;

public abstract class Figures {

    String name;
    Point p;
    String color;
    MainPanel mainPanelHandler = MainPanel.mainPanelHandler;

    public Figures(String name, Point p, String color) {
        this.name = name;
        this.p = p;
        this.color = color;
    }

    public abstract double drawFigure();

}
