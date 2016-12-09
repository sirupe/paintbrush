package tools;

import java.awt.Graphics2D;

import dataAndUtil.DrawingDTO;
 
public class Triangle implements Draw {
 
    @Override
    public void draw(Graphics2D g, DrawingDTO drawingDTO) {
        int [] triangleX = {
                drawingDTO.getStartX(),
                drawingDTO.getStartX() + (Math.abs(drawingDTO.getStartX() - drawingDTO.getEndX()) / 2),
                drawingDTO.getEndX()
            };
        
        int [] triangleY = {
                drawingDTO.getEndY(),
                drawingDTO.getStartY(),
                drawingDTO.getEndY()
            };
        
        if(drawingDTO.getIsFill()) {
            g.fillPolygon(triangleX, triangleY, 3);
        } else {
            g.drawPolygon(triangleX, triangleY, 3);
        }    
    }
}
