package tools;

import java.awt.Graphics2D;

import dataAndUtil.DrawingDTO;
 
public class Pen implements Draw{
 
    @Override
    public void draw(Graphics2D g, DrawingDTO drawingDTO) {
        g.setColor(drawingDTO.getColor());
        g.drawLine(
            drawingDTO.getEndX(), 
            drawingDTO.getEndY(),
            drawingDTO.getStartX(), 
            drawingDTO.getStartY()
        );
    }    
}
