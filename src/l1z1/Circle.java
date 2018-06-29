package l1z1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circle extends Figures {

    int radius;

    public Circle(String name, Point p, String color, int r) {
        super(name, p, color);
        radius = r;
    }

    @Override
    public double drawFigure() {
        int x = this.p.x - (this.radius / 2);
        int y = this.p.y - (this.radius / 2);
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

        g.fillOval(x, y, this.radius, this.radius);
        return 0;
    }

}
