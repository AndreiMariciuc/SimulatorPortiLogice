package gates;

import port.GatesPort;
import port.Port;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public abstract class Gate {
    String name;
    protected PApplet sketch;

    final int SCALE = 20;
    final int WIDTH = 70, HEIGHT = 70;
    final int ARC_RECT = 10;
    final int TEXT_SIZE = 17;

    GatesPort input1, input2, output;
    PVector gateLocation;
    private boolean isDragged = false;

    Color fillColor;

    public Gate(PApplet sketch, PVector gateLocation, String name) {
        this.sketch = sketch;
        this.gateLocation = gateLocation;
        this.name = name;
    }

    public void show() {
        update();
        sketch.stroke(new Color(42, 13, 52).getRGB());
        sketch.strokeWeight(4);
        sketch.fill(fillColor.getRGB());
        sketch.rect(gateLocation.x, gateLocation.y, WIDTH, HEIGHT, ARC_RECT, ARC_RECT, ARC_RECT, ARC_RECT);
        sketch.fill(new Color(255, 255, 255).getRGB());
        sketch.textSize(TEXT_SIZE);
        sketch.text(name, gateLocation.x + WIDTH / 2 - TEXT_SIZE - 7, gateLocation.y + HEIGHT / 2);

        if(input1 != null && input2 != null) {
            input1.setPortLocation(gateLocation.x, gateLocation.y + HEIGHT / 4);
            input1.show(sketch);
        }

        if(input2 != null) {
            input2.setPortLocation(gateLocation.x, gateLocation.y + 3 * HEIGHT / 4);
            input2.show(sketch);
        } else {
            input1.setPortLocation(gateLocation.x, gateLocation.y + HEIGHT / 2);
            input1.show(sketch);
        }

        output.setPortLocation(gateLocation.x + WIDTH, gateLocation.y + HEIGHT / 2);
        output.show(sketch);
    };

    public abstract void update();

    public boolean contains(int x, int y) {
        if(x >= gateLocation.x && x <= gateLocation.x + WIDTH && y >= gateLocation.y && y <= gateLocation.y + HEIGHT)
            return true;
        return false;
    }

    public void setGateLocation(int x, int y) {
        this.gateLocation.x = x;
        this.gateLocation.y = y;
    }

    public GatesPort getInput1() {
        return input1;
    }

    public GatesPort getInput2() {
        return input2;
    }

    public GatesPort getOutput() {
        return output;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setDragged(boolean dragged) {
        isDragged = dragged;
    }

    public boolean isDragged() {
        return isDragged;
    }

    public Port containsOutputPort(int x, int y) {
        if(output != null)
            if(output.contains(x, y))
                return output;
        return null;
    }

    public Port containsInputPort(int x, int y) {
        if(input1 != null)
            if(input1.contains(x, y))
                return input1;
        if(input2 != null)
            if(input2.contains(x, y))
                return input2;
        return null;
    }
}
