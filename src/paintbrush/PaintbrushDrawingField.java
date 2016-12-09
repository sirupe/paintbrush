package paintbrush;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

import dataAndUtil.DrawingDTO;
import dataAndUtil.SettingsEnum;
 
public class PaintbrushDrawingField extends Panel {
    private PaintbrushFrame paintbrushFrame;    // 상태를 얻어올 Frame 객체 필드
    
    private Image image;                        // 그림 그려줄 Image 영역 필드
    private Graphics2D imageGraphics;            // Image에 실제 그림을 그려주는 Graphics 객체
    private DrawingDTO drawingDTO;                // 도형 및 선의 점 위치정보 저장하는 DTO
    private ArrayList<DrawingDTO> lineHistory;    // DTO의 이력들이 저장되는 ArrayList
                                                // (선인 경우 지속적으로 DTO가 생성되어 저장, 도형인 경우에는 하나만 저장됨.)
    private Stack<ArrayList<DrawingDTO>> imageStackBack;
            // 마우스를 떼는 것을 기준으로 하나의 도형정보가 저장됨
    private Stack<ArrayList<DrawingDTO>> imageStackForward;
            // 뒤로가기를 눌렀을 때 Back에서 pop, Forward 에 push
    
    private boolean isDragged;    // 드래그상태를 확인하는 플래그
    
    private int pressX;            // 처음 클릭시의 X좌표
    private int pressY;            // 처음 클릭시의 Y좌표
    private int draggingX;        // 드래그 중 변하는 X좌표
    private int draggingY;        // 드래그 중 변하는 Y좌표
    private int releasedX;        // 마지막 마우스 뗐을 때의 X좌표
    private int releasedY;        // 마지막 마우스 뗐을 때의 Y좌표
    
    // 생성자에서 각종 필드 생성, 컴포넌트들의 bounds 세팅. this는 Panel
    public PaintbrushDrawingField(PaintbrushFrame paintbrushFrame) {
        this.paintbrushFrame   = paintbrushFrame;
        
        this.lineHistory        = new ArrayList<DrawingDTO>();
        this.imageStackBack       = new Stack<ArrayList<DrawingDTO>>();
        this.imageStackForward = new Stack<ArrayList<DrawingDTO>>();
        
        this.setBounds(
            SettingsEnum.DRAWING_PANEL_POSITION_X.getSize(), 
            SettingsEnum.DRAWING_PANEL_POSITION_Y.getSize(), 
            SettingsEnum.DRAWING_PANEL_WIDTH.getSize(), 
            SettingsEnum.DRAWING_PANEL_HEIGHT.getSize()
        );
        
        this.setBackground(Color.white);
        this.addEvent();
        
        this.paintbrushFrame.add(this);
        
        this.image = createImage(
            SettingsEnum.DRAWING_PANEL_WIDTH.getSize(), 
            SettingsEnum.DRAWING_PANEL_HEIGHT.getSize()
        );
        this.imageGraphics = (Graphics2D) this.image.getGraphics();
    }// 생성자
    
    
    // 마우스 클릭, 드래그, 뗐을 때 실행해줄 메소드 구현
    public void addEvent() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            // 클릭시 그림정보를 비워준다. 클릭좌표값을 설정한다.
            @Override
            public void mousePressed(MouseEvent e) {
                drawingDTO = null;
                pressX = e.getX();
                pressY = e.getY();
            }// mousePressed()
            
            // 드래그시 드래그플래그를 true로 바꿔준다. 드래그좌표값을 변경한다. 
            // Frame에서 설정값을 얻어와 ..........
            @Override
            public void mouseDragged(MouseEvent e) {
                isDragged = true;
                draggingX = e.getX();
                draggingY = e.getY();
                
                if(paintbrushFrame.getNowTools() == SettingsEnum.DRAW_PEN || paintbrushFrame.getNowTools() == SettingsEnum.DRAW_ERASE) {
                    drawingDTO = new DrawingDTO(
                        paintbrushFrame.getNowTools(), 
                        paintbrushFrame.getNowColor(), 
                        paintbrushFrame.getNowStroke(),
                        paintbrushFrame.isFill(),
                        pressX, pressY, draggingX, draggingY
                    );
                    
                    lineHistory.add(drawingDTO);
                    paintbrushFrame.getNowTools().getDrawTool().draw(imageGraphics, drawingDTO);
 
                    pressX = draggingX;
                    pressY = draggingY;
                    
                } else {
                    drawingDTO = new DrawingDTO(
                        paintbrushFrame.getNowTools(), 
                        paintbrushFrame.getNowColor(), 
                        paintbrushFrame.getNowStroke(),
                        paintbrushFrame.isFill(),
                        pressX, pressY, draggingX, draggingY
                    );
                }// if-else
                repaint();
            }// mouseDragged()
            
            @Override
            public void mouseReleased(MouseEvent e) {
                isDragged = false;
                releasedX = e.getX();
                releasedY = e.getY();
                
                drawingDTO = new DrawingDTO(
                        paintbrushFrame.getNowTools(), 
                        paintbrushFrame.getNowColor(), 
                        paintbrushFrame.getNowStroke(),
                        paintbrushFrame.isFill(),
                        pressX, pressY, 
                        releasedX, releasedY);
                
                if(paintbrushFrame.getNowTools() == SettingsEnum.DRAW_PEN || paintbrushFrame.getNowTools() == SettingsEnum.DRAW_ERASE) {
                    imageStackBack.push(lineHistory);
                    lineHistory = new ArrayList<DrawingDTO>();
                } else {
                    ArrayList<DrawingDTO> arrayList = new ArrayList<>();
                    arrayList.add(drawingDTO);
                    imageStackBack.push(arrayList);
                }// if-else : 펜이나 지우개인 경우 / 아닌경우
                
                repaint();
            }// mouseReleased()
        };//MouseAdapter 생성자
        
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }// addEvent()
    
    public void initImage() {
        this.imageGraphics.setColor(Color.white);
        this.imageGraphics.fillRect(
                0, 
                0, 
                SettingsEnum.DRAWING_PANEL_WIDTH.getSize(), 
                SettingsEnum.DRAWING_PANEL_HEIGHT.getSize()
                );
    }// initImage()
    
    @Override
    public void paint(Graphics g) {
        if(isDragged) {
            g.drawImage(this.image, 0, 0, this);            
        } else {
            this.initImage();
            
            for(ArrayList<DrawingDTO> arrayList : this.imageStackBack) {
                for(DrawingDTO dto : arrayList) {
                    this.graphicsDrawing(dto);
                }// forEach : DrawingDTO
            }// forEach : ArrayList
            
            g.drawImage(this.image, 0, 0, this);
        }// if-else
    }// paint()
    
    @Override
    public void update(Graphics g) {
        this.initImage();
        
        for(ArrayList<DrawingDTO> arrayList : this.imageStackBack) {
            for(DrawingDTO dto : arrayList) {
                this.graphicsDrawing(dto);
            }// forEach : DrawingDTO
        }// forEach : ArrayList
        
        if(paintbrushFrame.getNowTools() == SettingsEnum.DRAW_PEN || paintbrushFrame.getNowTools() == SettingsEnum.DRAW_ERASE) {
            for(DrawingDTO dto : this.lineHistory) {
                this.graphicsDrawing(dto);
            }// forEach - DrawingDTO
        }// if - 펜이나 지우개라면
        
        this.graphicsDrawing(this.drawingDTO);
        this.paint(g);
    }// update()
 
    public void graphicsDrawing(DrawingDTO dto) {
        this.imageGraphics.setColor(dto.getColor());
        this.imageGraphics.setStroke(dto.getStroke());
        dto.getDrawType().getDrawTool().draw(this.imageGraphics, dto);
    }// graphicsDrawing()
    
    public Stack<ArrayList<DrawingDTO>> getImageStackBack() {
        return this.imageStackBack;
    }// getImageStackBack()
 
    public Stack<ArrayList<DrawingDTO>> getImageStackForward() {
        return this.imageStackForward;
    }// getImageStackForward()
}