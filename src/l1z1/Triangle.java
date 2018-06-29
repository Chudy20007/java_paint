package l1z1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Triangle extends Figures {

    int height;

    public Triangle(String name, Point p, String color, int hei) {
        super(name, p, color);
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

       g.drawLine(this.p.x,this.p.y,this.p.x+this.height,this.p.y);
       g.drawLine(this.p.x,this.p.y+this.height,this.p.x+this.height,this.p.y);
       g.drawLine(this.p.x,this.p.y,this.p.x,this.p.y+this.height);
        return 0;
    }

}
