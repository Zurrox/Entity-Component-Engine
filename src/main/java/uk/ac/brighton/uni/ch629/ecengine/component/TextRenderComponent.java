package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;

public class TextRenderComponent extends Component {
    String text = "";
    int size = 10;
    String fontName = "Courier New";

    public TextRenderComponent(Entity parent) {
        super(parent);
    }

    public TextRenderComponent(Entity parent, String text) {
        this(parent);
        this.text = text;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(Graphics2D graphics) {
        Vector2d pos = parent.getTransform().getPos();
        graphics.setFont(new Font(fontName, Font.PLAIN, size));
        graphics.drawString(text, (float) pos.x, (float) pos.y);
    }
}