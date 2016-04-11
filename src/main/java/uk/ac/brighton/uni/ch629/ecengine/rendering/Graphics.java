package uk.ac.brighton.uni.ch629.ecengine.rendering;

import static org.lwjgl.opengl.GL11.*;

public class Graphics
{
    public Color currentColor = Color.BLACK;
    public void setColor(final Color col)
    {
        currentColor = col;
        glColor4b(col.r, col.g, col.b, col.a);
    }

    public void drawRect(int minX, int minY, int maxX, int maxY)
    {
        glBegin(GL_QUADS);
        {
            glVertex2i(minX, minY);
            glVertex2i(maxX, minY);
            glVertex2i(maxX, maxY);
            glVertex2i(minX, maxY);
        }
        glEnd();
    }

    public void drawRect(final Color color, int minX, int minY, int maxX, int maxY)
    {
        setColor(color);
        drawRect(minX, minY, maxX, maxY);
    }
}
