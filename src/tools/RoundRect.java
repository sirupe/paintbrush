package tools;

import java.awt.Graphics2D;

import dataAndUtil.DrawingDTO;
 
public class RoundRect implements Draw {
 
    @Override
    public void draw(Graphics2D g, DrawingDTO drawingDTO) {
        if(drawingDTO.getIsFill()) {
            g.fillRoundRect(    
                drawingDTO.getStartX(), 
                drawingDTO.getStartY(), 
                Math.abs(drawingDTO.getEndX() - drawingDTO.getStartX()), 
                Math.abs(drawingDTO.getEndY() - drawingDTO.getStartY()), 
                50, 50
            );
        } else {
            g.drawRoundRect(
                drawingDTO.getStartX(), 
                drawingDTO.getStartY(), 
                Math.abs(drawingDTO.getEndX() - drawingDTO.getStartX()), 
                Math.abs(drawingDTO.getEndY() - drawingDTO.getStartY()), 
                50, 50
            );
        }
    }
}
