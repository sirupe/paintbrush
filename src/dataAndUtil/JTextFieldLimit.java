package dataAndUtil;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
 
@SuppressWarnings("serial")
public class JTextFieldLimit extends PlainDocument {
    private int limit;
    
    // JTextFiled 에 글자수제한 설정이 불가능하기 때문에
    // PlainDocument 를 상속받아 필요 메소드를 구현한 클래스를 사용한다.
    public JTextFieldLimit(int limit) {
        this.limit = limit;
    }
    
    // 이 메소드가 PlainDocument 로부터 오버라이드 해야 하는 메소드.
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if(str == null) {
            return;
        }
        
        if(getLength() + str.length() <= limit) {
            super.insertString(offs, str, a);
        }
    }
}
