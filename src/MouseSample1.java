import java.awt.Button;
        import java.awt.Color;
        import java.awt.FlowLayout;
        import java.awt.Frame;
        import java.awt.Label;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;


public class MouseSample1 extends Frame { //Frame 클래스 상속
    int i = 0;
    Button button;
    Label label;

    public MouseSample1() {
        //콤포넌트 생성
        button = new Button("클릭");
        label = new Label("0");

        //콤포넌트를 프레임에 배치
        this.setLayout(new FlowLayout());
        this.add(button);
        this.add(label);

        //버튼 콤포넌트에 이벤트 핸들러 장착
        button.addMouseListener(new MouseAdapter() { //클래스 이름없이 어뎁터 클래스 생성
            @Override
            public void mouseClicked(MouseEvent e) { //마우스 클릭이벤트에서 동작할 내용 재정의
                label.setText(Integer.toString(i+=1));
                //클릭시 값이 계속 증가해야하므로 누적작업수행
                //레이블에 문자형태로 표시되도록 형변환지정.
            }
        });

        //마우스를 해당 프레임 영역 안에 위치시키면 배경색이 녹생으로 벼경되고,
        //마우스가 해당 프레임 여역밖으로 나가면 빨간생이 되도록 구현
        //프레임에 이벤트 핸들러 장착
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                //마우스가 해당 컴포넌트 영역 안으로 들어올때 발생
                //MouseSample1.this.setBackground(new Color(0,255,0));
                setBackground(new Color(0,255,0)); //프레임 배경색 변경
                System.out.println(this);
                System.out.println(MouseSample1.this);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                ////마우스가 해당 컴포넌트 영역 밖으로 나갈때 발생
                //MouseSample1.this.setBackground(new Color(255,0,0));
                setBackground(new Color(255,0,0));  //프레임 배경색 변경
            }
        });

    }

    public static void main(String[] args) {
        MouseSample1 test = new MouseSample1();
        test.setSize(300,100);
        test.setVisible(true);

    }
}