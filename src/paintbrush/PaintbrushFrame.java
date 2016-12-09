package paintbrush;
 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
 
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import dataAndUtil.JTextFieldLimit;
import dataAndUtil.SettingsEnum;
import performed.ColorPerformed;
import performed.EtcButtonsActionPerformed;
import performed.StrokeAndFillPerformed;
import performed.ToggleButtonsActionPerformed;
 
@SuppressWarnings("serial")
public class PaintbrushFrame extends JFrame {
    private JPanel toolsPanel;
    private ButtonGroup toolButtonGroup;
    private JToggleButton[] toolToggleButton;
    private JButton[] etcButton;
    private GridLayout gridLayout;
    
    private JPanel rgbPanel;
    private JCheckBox checkBox;
    private JSlider strokeSlider;
    
    private JSlider[] colorSlider;
    private JTextField[] colorCode;
    private JPanel colorPanel;
    private JPanel rgbSliderPanel;
    private JPanel colorCodePanel;
        
    private Color nowColor;
    private SettingsEnum nowTools;
    private BasicStroke nowStroke;
    private boolean isFill;
    
    private PaintbrushDrawingField field;
    
    public PaintbrushFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        this.setLayout(null);
        
        this.setBounds(
            SettingsEnum.FRAME_POSITION_X.getSize(),
            SettingsEnum.FRAME_POSITION_Y.getSize(),
            SettingsEnum.FRAME_WIDTH.getSize(),
            SettingsEnum.FRAME_HEIGHT.getSize()
        );    
        
        this.toolsPanelSetting();
        this.rgbPanelSetting();
        
        this.nowColor = new Color(0, 0, 0);
        this.nowTools = SettingsEnum.DRAW_PEN;
        this.nowStroke = new BasicStroke(1);
        this.isFill = false;
        
        this.setVisible(true);
        
        // setVisible 보다 위에 위치하면 익셉션 발생.
        this.field = new PaintbrushDrawingField(this);
    } // 생성자
    
    public void toolsPanelSetting() {
        // 버튼들 이미지 이름
        ArrayList<String> toolsIconList = new ArrayList<String>();
        ArrayList<String> etcIconList   = new ArrayList<String>();
        File file = new File("iconlist.txt");
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String str = scan.nextLine();
                // TODO SettingsEnum을 변경하면 자동으로 변경되게 하고 싶음..
                if(str.contains("etc")) {
                    etcIconList.add(str.substring(0, str.length() - 3));
                } else {
                    toolsIconList.add(str.substring(0, str.length() - 5));
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        this.gridLayout          = new GridLayout(0, toolsIconList.size() + etcIconList.size());
        this.toolsPanel       = new JPanel();
        this.toolButtonGroup  = new ButtonGroup();
        this.toolToggleButton = new JToggleButton[toolsIconList.size()];
        this.etcButton          = new JButton[etcIconList.size()];    
        
        this.toolsPanel.setLayout(gridLayout);
            
        this.toolsPanel.setBounds(
            SettingsEnum.TOOLPANEL_POSITION_X.getSize(),
            SettingsEnum.TOOLPANEL_POSITION_Y.getSize(),
            SettingsEnum.TOOLPANEL_WIDTH.getSize(),
            SettingsEnum.TOOLPANEL_HEIGHT.getSize()
        );
        // TODO
        // 그리기 툴 버튼 설정
        for(int i = 0, leng = toolsIconList.size(); i < leng; i++) {
            String tools = toolsIconList.get(i);
            this.toolToggleButton[i] = new JToggleButton(new ImageIcon(tools));
            this.toolToggleButton[i].setName(tools.substring(0, tools.length() - 4));
            this.toolToggleButton[i].setSize(SettingsEnum.BUTTON_SIZE.getDimension());
            this.toolToggleButton[i].setBorderPainted(false);
            this.toolToggleButton[i].setContentAreaFilled(false);
            this.toolToggleButton[i].setFocusPainted(false);
            this.toolToggleButton[i].setToolTipText(tools.substring(0, tools.length() - 4));
            this.toolToggleButton[i].addActionListener(new ToggleButtonsActionPerformed(this));
            this.toolButtonGroup.add(this.toolToggleButton[i]);
            this.toolsPanel.add(this.toolToggleButton[i]);
        }
        
        // TODO
        // 기타 버튼 설정 (앞으로, 뒤로)
        for(int i = 0, leng = etcIconList.size(); i < leng; i++) {
            String etc = etcIconList.get(i);
            this.etcButton[i] = new JButton(new ImageIcon(etc));
            this.etcButton[i].setName(etc.substring(0, etc.length() - 4));
            this.etcButton[i].setSize(SettingsEnum.BUTTON_SIZE.getDimension());
            this.etcButton[i].setBorderPainted(false);
            this.etcButton[i].setContentAreaFilled(false);
            this.etcButton[i].setFocusPainted(false);
            this.etcButton[i].setToolTipText(etc.substring(0, etc.length() - 4));
            this.etcButton[i].addActionListener(new EtcButtonsActionPerformed(this));
            this.toolsPanel.add(this.etcButton[i]);
        }
        
        this.toolsPanel.setBackground(Color.orange);
        this.add(toolsPanel);
    } // toolsPanelSetting
    
    public void rgbPanelSetting() {
        this.rgbPanel         = new JPanel(null);
        this.colorCodePanel = new JPanel(new GridLayout(3, 0));
        this.colorPanel     = new JPanel(null);
        this.rgbSliderPanel = new JPanel(new GridLayout(3, 0));
        this.colorSlider     = new JSlider[3];
        this.colorCode         = new JTextField[3];
        this.strokeSlider    = new JSlider(0, 50, 0);
        this.checkBox        = new JCheckBox("FILL");
        
        this.rgbPanel.setBounds(
            SettingsEnum.RGBPANEL_POSITION_X.getSize(), 
            SettingsEnum.RGBPANEL_POSITION_Y.getSize(), 
            SettingsEnum.RGBPANEL_WIDTH.getSize(), 
            SettingsEnum.RGBPANEL_HEIGHT.getSize()
        );    
        
        this.checkBox.setBounds(
            SettingsEnum.CHECKBOX_POSITION_X.getSize(), 
            SettingsEnum.CHECKBOX_POSITION_Y.getSize(),
            SettingsEnum.CHECKBOX_WIDTH.getSize(),
            SettingsEnum.CHECKBOX_HEIGHT.getSize()
        );
        
        this.strokeSlider.setBounds(
            SettingsEnum.STROKE_SLIDER_POSITION_X.getSize(),
            SettingsEnum.STROKE_SLIDER_POSITION_Y.getSize(),
            SettingsEnum.STROKE_SLIDER_WIDTH.getSize(),
            SettingsEnum.STROKE_SLIDER_HEIGHT.getSize()
        );
        
        this.colorCodePanel.setBounds(
            SettingsEnum.COLORCODE_PANEL_POSITION_X.getSize(), 
            SettingsEnum.COLORCODE_PANEL_POSITION_Y.getSize(), 
            SettingsEnum.COLORCODE_PANEL_WIDTH.getSize(),
            SettingsEnum.COLORCODE_PANEL_HEIGHT.getSize());
        
        this.rgbSliderPanel.setBounds(
            SettingsEnum.RGBSLIDER_PANEL_POSITION_X.getSize(),
            SettingsEnum.RGBSLIDER_PANEL_POSITION_Y.getSize(),
            SettingsEnum.RGBSLIDER_PANEL_WIDTH.getSize(),
            SettingsEnum.RGBSLIDER_PANEL_HEIGHT.getSize()
        );
        
        this.colorPanel.setBounds(
            SettingsEnum.RGBCOLOR_POSITION_X.getSize(), 
            SettingsEnum.RGBCOLOR_POSITION_Y.getSize(), 
            SettingsEnum.RGBCOLOR_WIDTH.getSize(), 
            SettingsEnum.RGBCOLOR_HEIGHT.getSize()
        );
        
        this.rgbPanel.setBackground(Color.orange);
        this.colorPanel.setBackground(Color.black);
        
        // TODO
        // 색 선택 관련 설정
        ColorPerformed colorPerformed = new ColorPerformed(this);
        for(int i = 0, leng = this.colorSlider.length; i < leng; i++) {
            // JSlider min 0, max 255, 초기값 0
            this.colorSlider[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
            this.colorSlider[i].addChangeListener(colorPerformed);
            this.colorSlider[i].setBackground(this.rgbPanel.getBackground());
            
            // JTextField 최대글자수 3, 초기값 "0"
            this.colorCode[i] = new JTextField(new JTextFieldLimit(3),"0", 0);
            this.colorCode[i].addKeyListener(colorPerformed);
            this.colorCode[i].setFont(SettingsEnum.COLORCODE_FONT.getFont());
            
            this.colorCodePanel.add(this.colorCode[i]);
            this.rgbSliderPanel.add(this.colorSlider[i]);
        }// for
        
        // Stroke(두께), Fill(채우기) 관련 설정
        StrokeAndFillPerformed strokeAndFill = new StrokeAndFillPerformed(this);
        this.checkBox.setFont(new Font("consolas", Font.BOLD, 20));
        this.checkBox.setBackground(this.rgbPanel.getBackground());
        this.checkBox.addActionListener(strokeAndFill);
        this.strokeSlider.setBackground(this.rgbPanel.getBackground());
        this.strokeSlider.setToolTipText("stroke");
        this.strokeSlider.addChangeListener(strokeAndFill);
        
        this.rgbPanel.add(this.checkBox);
        this.rgbPanel.add(this.strokeSlider);
        this.rgbPanel.add(this.rgbSliderPanel);
        this.rgbPanel.add(this.colorPanel);
        this.rgbPanel.add(this.colorCodePanel);
        
        this.add(this.rgbPanel);
    }
    
    public void setNowColor() {
        this.nowColor = new Color(
                this.colorSlider[0].getValue(), 
                this.colorSlider[1].getValue(), 
                this.colorSlider[2].getValue()
                );
        this.colorPanel.setBackground(this.nowColor);
    }
    
    public void setNowTools(SettingsEnum nowTools) {
        this.nowTools = nowTools;
    }
    
    public Color getNowColor() {
        return nowColor;
    }
 
    public SettingsEnum getNowTools() {
        return nowTools;
    }
 
    public BasicStroke getNowStroke() {
        return nowStroke;
    }
 
    public JToggleButton[] getToolToggleButton() {
        return toolToggleButton;
    }
 
    public JSlider[] getColorSlider() {
        return colorSlider;
    }
 
    public JTextField[] getColorCode() {
        return colorCode;
    }
    
    public JCheckBox getCheckBox() {
        return checkBox;
    }
 
    public JSlider getStrokeSlider() {
        return strokeSlider;
    }
    
    public JButton[] getEtcButton() {
        return etcButton;
    }
    
    public PaintbrushDrawingField getField() {
        return field;
    }
 
    public boolean isFill() {
        return isFill;
    }
    
    public void setFill(boolean isFill) {
        this.isFill = isFill;
    }
    
    public void setNowStroke(BasicStroke nowStroke) {
        this.nowStroke = nowStroke;
    }
}
