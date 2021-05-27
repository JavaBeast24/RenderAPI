package com.javabeast.rendering.api;

import java.awt.*;

public class Graph {

    private int[] points;

    private Color lineColor;
    private Color fallColor;
    private Color riseColor;

    private int with;
    private int height;

    private Color background;

    private float factor;
    private int distance;

    private int lineStrength = 1;

    private Color middleVertColor;
    private boolean drawMiddleVert = false;
    private int middleVertStrength = 1;

    private Color overSeventyFive;
    private Color overFifty;
    private Color overTwentyFive;
    private Color underTwentyFive;

    private Color outline;
    private int OutlineStrength = 1;

    public Graph(int[] points, Color lineColor, int with, int height, Color background, float factor, int distance){
        this.points = points;
        this.lineColor = lineColor;
        this.fallColor = lineColor;
        this.riseColor = lineColor;
        this.with = with;
        this.height = height;
        this.background = background;
        this.factor = factor;
        this.distance = distance;
        this.middleVertColor = lineColor;
    }

    public void setMiddleVertStrength(int middleVertStrength) {
        this.middleVertStrength = middleVertStrength;
    }

    public void setLineStrength(int lineStrength){
        this.lineStrength = lineStrength;
    }

    public void setOutlineStrength(int strength) {
        this.OutlineStrength = strength;
    }

    public void setRiseColor(Color color) {
        this.riseColor = color;
    }

    public void setFallColor(Color color){
        this.fallColor = color;
    }

    public void setDrawMiddleVert(boolean drawMiddleVert) {
        this.drawMiddleVert = drawMiddleVert;
    }

    public void setMiddleVertColor(Color color) {
        this.middleVertColor = color;
    }

    public void setOverSeventyFive(Color color) {
        this.overSeventyFive = color;
    }

    public void setOverFifty(Color color) {
        this.overFifty = color;
    }

    public void setOverTwentyFive(Color color) {
        this.overTwentyFive = color;
    }

    public void setUnderTwentyFive(Color color) {
        this.underTwentyFive = color;
    }

    public void setOutline(Color color){
        this.outline = color;
    }

    public void draw(Graphics g) {
        g.setColor(background);
        g.fillRect(0, 0, with, height);

        g.setColor(lineColor);

        Point lastPoint = new Point(1, Math.round(factor*points[0]));

        for(int i = 1; i < points.length; i++) {
            int x = distance*i;
            int y = Math.round(factor*points[i]);

            Point point = new Point(x, y);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(lineStrength));

            if(lastPoint.y > point.y) {
                g.setColor(fallColor);
            }else if(lastPoint.y < point.y) {
                g.setColor(riseColor);
            }else {
                g.setColor(lineColor);
            }

            if(point.y > (height / 4 * 3) && overSeventyFive != null){
                g.setColor(overSeventyFive);
            }else if(point.y > (height/4*2) && overFifty != null){
                g.setColor(overFifty);
            }else if(point.y > (height/4) && overTwentyFive != null){
                g.setColor(overTwentyFive);
            }else if(point.y < (height/4) && underTwentyFive != null){
                g.setColor(underTwentyFive);
            }

            g2d.drawLine(lastPoint.x, height-lastPoint.y, point.x, height-point.y);

            lastPoint = point;
        }

        if(drawMiddleVert) {
            int all = 0;

            for(int i:points){
                all += i;
            }

            int middleVert = all / points.length;

            g.setColor(middleVertColor);

            Graphics2D g2D = (Graphics2D) g;
            g2D.setStroke(new BasicStroke(this.middleVertStrength));
            g2D.drawLine(1, height-Math.round(middleVert*factor), with, Math.round(middleVert*factor));
        }

        if(outline != null) {
            g.setColor(outline);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setStroke(new BasicStroke(this.OutlineStrength));
            g2D.drawRect(0, 0, with, height);
        }
    }

}
