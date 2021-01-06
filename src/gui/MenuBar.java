package gui;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class MenuBar {
    ArrayList<Button> buttons;

    final String[] BUTTON_NAME = {"WIRE", "I_O", "NOT", "AND", "NAND", "OR", "NOR", "XOR", "NXOR", "DELETE"};
    PApplet sketch;

    public MenuBar(Rectangle bounds, PApplet sketch) {
        this.sketch = sketch;
        buttons = new ArrayList<>();
        for(int i=0; i<BUTTON_NAME.length; i++)
            buttons.add(new Button(BUTTON_NAME[i], bounds.x  + bounds.width * i, bounds.y, bounds.width, bounds.height));
    }

    public void show() {
        for(var button: buttons)
            button.show(sketch);
    }

    public String selectButton(int x, int y) {
        for(var button: buttons)
            if(button.contains(x, y)) {
                if(button.selected == true) {
                    button.setSelected();
                    return null;
                }
                for(var it: buttons) {
                    if(it != button && it.selected == true)
                        it.setSelected();
                }
                button.setSelected();
                return button.text;
            }
        return null;
    }
}
