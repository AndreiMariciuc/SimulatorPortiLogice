package gui;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Button {
    final int ARC = 10;

    String text;
    Rectangle rectangle;

    boolean selected;

    Color colorNotSelected = new Color(88, 87, 87);
    Color colorSelected = new Color(158, 158, 158);


    public Button(String text, int x, int y, int width, int height) {
        this.text = text;
        this.rectangle = new Rectangle(x, y, width, height);
        selected = false;
    }

    public void show(PApplet sketch) {
        sketch.strokeWeight(10);
        sketch.stroke(new Color(0, 0, 0).getRGB());
        if(this.selected == false)
            sketch.fill(colorNotSelected.getRGB());
        else
            sketch.fill(colorSelected.getRGB());
        sketch.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, ARC);

        sketch.fill(new Color(255, 255, 255).getRGB());
        sketch.textSize(20);
        sketch.text(text, rectangle.x + rectangle.width / 2 - 20, rectangle.y + rectangle.height/2 );
    }

    public void setSelected() {
        this.selected = !this.selected;
    }

    boolean contains(int x, int y) {
        return x >= rectangle.x && x <= (rectangle.x + rectangle.width)
                && y >= rectangle.y && y <= (rectangle.y + rectangle.height);
    }
}
