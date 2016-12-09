package dataAndUtil;

import java.awt.BasicStroke;
import java.awt.Color;
 
public class DrawingDTO {
    private SettingsEnum drawType;
    private Color color;
    private BasicStroke stroke;
    private Boolean isFill;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    
    public DrawingDTO(SettingsEnum drawType, Color color, BasicStroke stroke, Boolean isFill, int startX, int startY, int endX, int endY) {
        this.drawType = drawType;
        this.color       = color;
        this.stroke   = stroke;
        this.isFill   = isFill;
        this.startX   = startX;
        this.startY   = startY;
        this.endX       = endX;
        this.endY       = endY;
    }
    
    public void setStartPoint(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }
    
    public void setEndPoint(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
    }
 
    public SettingsEnum getDrawType() {
        return drawType;
    }
 
    public Color getColor() {
        return color;
    }
 
    public int getStartX() {
        return startX;
    }
 
    public int getStartY() {
        return startY;
    }
 
    public int getEndX() {
        return endX;
    }
 
    public int getEndY() {
        return endY;
    }
 
    public BasicStroke getStroke() {
        return stroke;
    }
 
    public Boolean getIsFill() {
        return isFill;
    }    
}
