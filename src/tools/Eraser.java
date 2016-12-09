package tools;

import java.awt.Color;
import java.awt.Graphics2D;
 
import dataAndUtil.DrawingDTO;
 
public class Eraser implements Draw {
    @Override
    public void draw(Graphics2D g, DrawingDTO drawingDTO) {
        g.setColor(Color.white);
        g.drawLine(
            drawingDTO.getStartX(), 
            drawingDTO.getStartY(), 
            drawingDTO.getEndX(), 
            drawingDTO.getEndY()
        );
    }
}
