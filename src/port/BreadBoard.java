package port;


import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class BreadBoard {
    Rectangle bounds;

    final int ERROR = 5;

    ArrayList<Port> pins;
    protected PApplet sketch;

    public BreadBoard(PApplet sketch) {
        this.sketch = sketch;
        pins = new ArrayList<>();
        bounds = new Rectangle(40, 40, this.sketch.width - 80, this.sketch.height - 150);
    }

    public void show() {
        sketch.fill(new Color(28, 25, 25, 59).getRGB());
        sketch.strokeWeight(10);
        sketch.stroke(new Color(0, 0, 0).getRGB());
        sketch.rect(bounds.x, bounds.y, bounds.width, bounds.height, 10);

        for (var it : pins)
            it.show(sketch);
    }

    public void add(Port port) {
        pins.add(port);
    }

    public void addIfContains(int x, int y) {
        if (notExistsPort(x, y)) {
            if (Math.abs(bounds.x - x) < ERROR)
                add(new InputPort(new PVector(bounds.x, y)));
            else if (Math.abs(bounds.x + bounds.width - x) < ERROR)
                add(new OutputPort(new PVector(bounds.x + bounds.width, y)));
        }
    }

    private boolean notExistsPort(int x, int y) {
        for (var it : pins)
            if (it.contains(x, y))
                return false;
        return true;
    }

    public void switchIfContains(int x, int y) {
        for (var it : pins)
            if (it instanceof InputPort && it.contains(x, y)) {
                it.setValue(!it.getValue());
                break;
            }
    }

    public InputPort containsInput(int x, int y) {
        for (var it : pins)
            if (it instanceof InputPort && it.contains(x, y))
                return (InputPort) it;
        return null;
    }

    public OutputPort containsOutput(int x, int y) {
        for (var it : pins)
            if (it instanceof OutputPort && it.contains(x, y))
                return (OutputPort) it;
        return null;
    }

    public void deletePins() {
        pins = new ArrayList<>();
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
