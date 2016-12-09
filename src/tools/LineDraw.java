package tools;

import java.awt.Graphics2D;

import dataAndUtil.DrawingDTO;
 
public class LineDraw implements Draw {
 
    @Override
    public void draw(Graphics2D g, DrawingDTO drawingDTO) {
        g.setColor(drawingDTO.getColor());
        g.drawLine(
            drawingDTO.getStartX(), 
            drawingDTO.getStartY(), 
            drawingDTO.getEndX(), 
            drawingDTO.getEndY()
        );
    }
}
