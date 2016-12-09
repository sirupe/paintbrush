package performed;
 
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
import paintbrush.PaintbrushFrame;
 
public class ColorPerformed extends KeyAdapter implements ChangeListener {
    private PaintbrushFrame frame;
    
    public ColorPerformed(PaintbrushFrame frame) {
        this.frame = frame;
    } // 생성자
    
    @Override
    public void stateChanged(ChangeEvent e) {
        for(int i = 0, leng = this.frame.getColorCode().length; i < leng; i++) {
            this.frame.getColorCode()[i].setText(((Integer)this.frame.getColorSlider()[i].getValue()).toString());
        }
        
        this.frame.setNowColor();
    }// stateChanged
    
    @Override
    public void keyReleased(KeyEvent e) {
        for(int i = 0, leng = this.frame.getColorCode().length; i < leng; i++) {
            String colorCode = this.frame.getColorCode()[i].getText();
            
            if(!colorCode.equals("")) {
                int value = Integer.parseInt(colorCode);
                
                if(value > 255) {
                    this.frame.getColorCode()[i].setText("255");
                }// if 2
                
                this.frame.getColorSlider()[i].setValue(value);
            }// if 1
        } // for
        
        this.frame.setNowColor();
    }// keyReleased
}
