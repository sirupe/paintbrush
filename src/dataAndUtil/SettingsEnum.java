package dataAndUtil;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
 
import tools.Circle;
import tools.Draw;
import tools.Eraser;
import tools.LineDraw;
import tools.Pen;
import tools.RectangleDraw;
import tools.RoundRect;
import tools.Triangle;
 
public enum SettingsEnum {
    SCREEN_WIDTH((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
    SCREEN_HEIGHT((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()),
    
    FRAME_WIDTH(1300),
    FRAME_HEIGHT(900),
    FRAME_POSITION_X((SCREEN_WIDTH.getSize() / 2) - (FRAME_WIDTH.getSize() / 2)),
    FRAME_POSITION_Y((SCREEN_HEIGHT.getSize() / 2) - (FRAME_HEIGHT.getSize() / 2)),
    
    TOOLPANEL_WIDTH((FRAME_WIDTH.getSize() / 100) * 65),
    TOOLPANEL_HEIGHT(FRAME_HEIGHT.getSize() / 100 * 10),
    TOOLPANEL_POSITION_X(0),
    TOOLPANEL_POSITION_Y(0),
    
    RGBPANEL_WIDTH(FRAME_WIDTH.getSize() - TOOLPANEL_WIDTH.getSize()),
    RGBPANEL_HEIGHT(TOOLPANEL_HEIGHT.getSize()),
    RGBPANEL_POSITION_X(TOOLPANEL_WIDTH.getSize()),
    RGBPANEL_POSITION_Y(0),
        
    CHECKBOX_WIDTH(70),
    CHECKBOX_HEIGHT(40),
    CHECKBOX_POSITION_X(20),
    CHECKBOX_POSITION_Y(5),
    
    STROKE_SLIDER_WIDTH(70),
    STROKE_SLIDER_HEIGHT(30),
    STROKE_SLIDER_POSITION_X(CHECKBOX_POSITION_X.getSize()),
    STROKE_SLIDER_POSITION_Y(45),
    
    RGBCOLOR_WIDTH(60),
    RGBCOLOR_HEIGHT(60),
    RGBCOLOR_POSITION_X(100),
    RGBCOLOR_POSITION_Y(15),
    
    COLORCODE_PANEL_WIDTH(50),
    COLORCODE_PANEL_HEIGHT(TOOLPANEL_HEIGHT.getSize()),
    COLORCODE_PANEL_POSITION_X(RGBCOLOR_WIDTH.getSize() + RGBCOLOR_POSITION_X.getSize() + 10),
    COLORCODE_PANEL_POSITION_Y(0),
    
    RGBSLIDER_PANEL_WIDTH(200),
    RGBSLIDER_PANEL_HEIGHT(TOOLPANEL_HEIGHT.getSize()),
    RGBSLIDER_PANEL_POSITION_X(COLORCODE_PANEL_POSITION_X.getSize() + COLORCODE_PANEL_WIDTH.getSize()),
    RGBSLIDER_PANEL_POSITION_Y(0),
    
    DRAWING_PANEL_WIDTH(FRAME_WIDTH.getSize()),
    DRAWING_PANEL_HEIGHT(FRAME_WIDTH.getSize() - TOOLPANEL_HEIGHT.getSize()),
    DRAWING_PANEL_POSITION_X(0),
    DRAWING_PANEL_POSITION_Y(TOOLPANEL_HEIGHT.getSize()),
    
    COLORCODE_FONT(new Font("consolas", Font.PLAIN, 18)),
    
    DRAW_PEN(new Pen()),
    DRAW_ERASE(new Eraser()),
    DRAW_LINE(new LineDraw()),
    DRAW_RECT(new RectangleDraw()),
    DRAW_OVAL(new Circle()),
    DRAW_ROUNDRECT(new RoundRect()),
    DRAW_TRIANGLE(new Triangle()),
    
    BUTTONKIND_ETC("etc"),
    BUTTONKIND_TOOLS("tools"),
    
    BUTTON_SIZE(new Dimension(10, 10));
    
    private int size;
    private Font font;
    private Draw drawTool;
    private Dimension dimension;
    private String buttonKind;
    
    private SettingsEnum() {}
    
    private SettingsEnum(int size) {
        this.size = size;
    }
    
    private SettingsEnum(Font font) {
        this.font = font;
    }
    
    private SettingsEnum(Draw drawTool) {
        this.drawTool = drawTool;
    }
 
    private SettingsEnum(Dimension dimension) {
        this.dimension = dimension;
    }
    
    private SettingsEnum(String buttonKind) {
        this.buttonKind = buttonKind;
    }
 
    public int getSize() {
        return size;
    }
 
    public Font getFont() {
        return font;
    }
 
    public Draw getDrawTool() {
        return drawTool;
    }
 
    public Dimension getDimension() {
        return dimension;
    }
 
    public String getButtonKind() {
        return buttonKind;
    }
}
