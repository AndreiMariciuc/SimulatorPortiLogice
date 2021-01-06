package port;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public abstract class Port {
    PVector portLocation;
    private  boolean value;
    Color color;

    public Port(PVector portLocation) {
        this.portLocation = portLocation;
        setValue(true);
    }

    public abstract void show(PApplet sketch);

    public abstract boolean contains(int x, int y);

    public void setValue(boolean value) {
        this.value = value;
        if(!value)
            color = new Color(0);
        else
            color = new Color(189, 74, 103);
    }

    public boolean getValue() {
        return value;
    }

    public void setPortLocation(float x, float y) {
        this.portLocation.x = x;
        this.portLocation.y = y;
    }

    public PVector getPortLocation() {
        return portLocation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
