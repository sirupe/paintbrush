package performed;
 
import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
import paintbrush.PaintbrushFrame;
 
public class StrokeAndFillPerformed implements ActionListener, ChangeListener {
    private PaintbrushFrame frame;
    
    public StrokeAndFillPerformed(PaintbrushFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setFill(this.frame.getCheckBox().isSelected());
    }
 
    @Override
    public void stateChanged(ChangeEvent e) {
        this.frame.setNowStroke(new BasicStroke(this.frame.getStrokeSlider().getValue()));
    }    
}
