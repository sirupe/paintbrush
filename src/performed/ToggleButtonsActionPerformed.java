package performed;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
 
import dataAndUtil.SettingsEnum;
import paintbrush.PaintbrushFrame;
 
public class ToggleButtonsActionPerformed implements ActionListener{
    private PaintbrushFrame frame;
    
    public ToggleButtonsActionPerformed(PaintbrushFrame frame) {
        this.frame = frame;
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        for(JToggleButton b : this.frame.getToolToggleButton()) {
            if(b.isSelected()) {
                b.setBorderPainted(true);
                b.setBorder(BorderFactory.createRaisedBevelBorder());
                switch(b.getName()) {
                case "pencil" :
                    this.frame.setNowTools(SettingsEnum.DRAW_PEN);
                    break;                                                                                        
                case "erase" :                                          
                    this.frame.setNowTools(SettingsEnum.DRAW_ERASE);
                    break;                                        
                case "line" :                                     
                    this.frame.setNowTools(SettingsEnum.DRAW_LINE);
                    break;                                        
                case "circle" :                                   
                    this.frame.setNowTools(SettingsEnum.DRAW_OVAL);
                    break;                                       
                case "square" :                                   
                    this.frame.setNowTools(SettingsEnum.DRAW_RECT);
                    break;                                        
                case "roundSquare" :                              
                    this.frame.setNowTools(SettingsEnum.DRAW_ROUNDRECT);
                    break;                                            
                case "triangle" :                                     
                    this.frame.setNowTools(SettingsEnum.DRAW_TRIANGLE);
                    break;
                default :
                }
            } else {
                b.setBorderPainted(false);
            }
        }
    }
}
