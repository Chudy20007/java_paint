package l1z1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Figures {

    int width;
    int height;

    public Rectangle(String name, Point p, String color, int wid, int hei) {
        super(name, p, color);
        width = wid;
        height = hei;
    }

    @Override
    public double drawFigure() {

        Graphics g = mainPanelHandler.getGraphics();

        switch (this.color) {
            case ("red"): {
                g.setColor(Color.red);
                break;
            }

            case ("green"): {
                g.setColor(Color.green);
                break;
            }

            case ("blue"): {
                g.setColor(Color.blue);
                break;
            }

            case ("yellow"): {
                g.setColor(Color.yellow);
                break;
            }
        }

        g.drawRect(this.p.x, this.p.y, this.width, this.height);
        return 0;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}
