package tools;
 
import java.awt.Graphics2D;
 
import dataAndUtil.DrawingDTO;
 
public class Circle implements Draw {
    @Override
    public void draw(Graphics2D g, DrawingDTO drawingDTO) {
        if(drawingDTO.getIsFill()) {
            g.fillOval(
                drawingDTO.getStartX(), 
                drawingDTO.getStartY(), 
                Math.abs(drawingDTO.getEndX() - drawingDTO.getStartX()), 
                Math.abs(drawingDTO.getEndY() - drawingDTO.getStartY())
            );
        } else {
            g.drawOval(
                drawingDTO.getStartX(), 
                drawingDTO.getStartY(), 
                Math.abs(drawingDTO.getEndX() - drawingDTO.getStartX()), 
                Math.abs(drawingDTO.getEndY() - drawingDTO.getStartY())
            );
        }
    }
}