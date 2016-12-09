package performed;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
 
import javax.swing.JButton;
 
import paintbrush.PaintbrushFrame;
 
public class EtcButtonsActionPerformed implements ActionListener{
    private PaintbrushFrame frame;
    
    public EtcButtonsActionPerformed(PaintbrushFrame frame) {
        this.frame = frame;
    }
    
    public void buttonSet(String buttonStr) {
        JButton[] etcButtons = this.frame.getEtcButton();
        for(JButton button : etcButtons) {
            if(button.getName().equals(buttonStr)) {
                button.setBorderPainted(true);
            } else {
                button.setBorderPainted(false);    
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource().toString().contains("back")) {
                this.buttonSet("back");
                this.frame.getField().getImageStackForward().push(this.frame.getField().getImageStackBack().pop());
                this.frame.getField().repaint();
            } else if(e.getSource().toString().contains("forward")) {
                this.buttonSet("forward");
                this.frame.getField().getImageStackBack().push(this.frame.getField().getImageStackForward().pop());
                this.frame.getField().repaint();
            } else {
                this.buttonSet("clearButton");
                this.frame.getField().getImageStackBack().clear();
                this.frame.getField().getImageStackForward().clear();
                this.frame.getField().repaint();
            }
            
        } catch(EmptyStackException exception) {}
    }
 
}
