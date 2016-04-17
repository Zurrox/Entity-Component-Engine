package uk.ac.brighton.uni.ch629.ecengine.rendering;

public class Graphics {
    public Color currentColor = Color.BLACK;

    public void setColor(final Color col) {
        currentColor = col;
    }

    public void drawRect(int minX, int minY, int maxX, int maxY) {
    }

    public void drawRect(final Color color, int minX, int minY, int maxX, int maxY) {
        setColor(color);
        drawRect(minX, minY, maxX, maxY);
    }
}
