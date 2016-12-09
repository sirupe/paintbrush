package tools;

import java.awt.Graphics2D;

import dataAndUtil.DrawingDTO;
 
public class RectangleDraw implements Draw{
 
    @Override
    public void draw(Graphics2D g, DrawingDTO drawingDTO) {
        if(drawingDTO.getIsFill()) {
            g.fillRect(
                drawingDTO.getStartX(), 
                drawingDTO.getStartY(), 
                Math.abs(drawingDTO.getEndX() - drawingDTO.getStartX()), 
                Math.abs(drawingDTO.getEndY() - drawingDTO.getStartY())
            );
        } else {
            g.drawRect(
                drawingDTO.getStartX(), 
                drawingDTO.getStartY(), 
                Math.abs(drawingDTO.getEndX() - drawingDTO.getStartX()), 
                Math.abs(drawingDTO.getEndY() - drawingDTO.getStartY())
            );
        }
    }
}
