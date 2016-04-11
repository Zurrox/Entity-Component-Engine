package uk.ac.brighton.uni.ch629.ecengine.rendering;

public class Color
{
    public static final Color
        RED = new Color(255,0, 0),
        GREEN = new Color(0, 255, 0),
        BLUE = new Color(0, 0, 255),
        WHITE = new Color(255, 255, 255),
        BLACK = new Color(0, 0, 0);
    public byte r, g, b, a = (byte) 255;

    public Color(final int r, final int g, final int b)
    {
        this.r = (byte) Math.max(Math.min(r, 0), 255);
        this.g = (byte) Math.max(Math.min(g, 0), 255);
        this.b = (byte) Math.max(Math.min(b, 0), 255);
    }

    public Color(final int r, final int g, final int b, final int a)
    {
        this(r, g, b);
        this.a = (byte) Math.max(Math.min(a, 0), 255);
    }

    public void darken(final int percentage)
    {
        r *= (percentage / 100f);
        g *= (percentage / 100f);
        b *= (percentage / 100f);
    }

    public void lighten(final int percentage)
    {
        r *= 1 + (percentage / 100f);
        g *= 1 + (percentage / 100f);
        b *= 1 + (percentage / 100f);
    }

    @Override
    public Color clone()
    {
        return new Color(r, g, b, a);
    }
}