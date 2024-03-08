package com.sds.project0307.test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//메모장 편집기를 만들어보자
public class MemoApp extends JFrame implements ActionListener{
	JMenuBar bar; //메뉴들을 올려놓을 막대기
	
	JMenu m_file; //파일 
	JMenu m_edit; //편집
	JMenu m_style; //서식 
	JMenu m_view; //보기
	JMenu m_help; //도움말 
	
	//파일에 소속된 메뉴 아이템들 
	String[] itemTitle= {"새로만들기","새창","열기","저장","다른 이름으로 저장","페이지 설정","인쇄","끝내기"};
	JMenuItem[] item = new JMenuItem[itemTitle.length];
	JTextArea area;
	JScrollPane scroll;
	
	File file;
	FileInputStream fis; //바이트 기반 스트림 (1byte씩 만 이해하므로 영문만 이해)
	InputStreamReader is;
	
	FileWriter fw;
	
	
	//자바에서는 파일 탐색기를 가리켜 JFileChooser
	JFileChooser chooser;
	boolean isChanged=false; 
	
	public MemoApp() {
		bar = new JMenuBar();
		
		m_file = new JMenu("파일");
		m_edit = new JMenu("편집");
		m_style = new JMenu("서식");
		m_view = new JMenu("보기");
		m_help = new JMenu("도움말");
		
		for(int i=0;i<itemTitle.length;i++) {
			item[i] = new JMenuItem(itemTitle[i]);
			//파일 메뉴에 부착 
			m_file.add(item[i]);
			if(i==4 || i==6) {
				m_file.addSeparator();//구분선
			}
			
			//메뉴 아이템들과 리스너 연결 
			item[i].addActionListener(this);
		}
		
		//파일 탐색기를 생성해놓기 
		//생성해놓는다고 하여, 눈에 보여지는 것이 아니라, 메서드를 호출해야 창이 뜸
		chooser = new JFileChooser("D:/java_workspace/project0307/src/com/sds/project0307");
		
		//모든 메뉴를 바에 붙이자
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		
		//바는 개발자가 원하는 위치에 임의로 둘수없고 그 위치가 이미 정해져 있다
		this.setJMenuBar(bar); //프레임의 메서드로 위치를 지정
		
		//스크롤을 적용하고  싶은 컴포넌트를 생성자의 인스로 넣어줘야 함 
		scroll = new JScrollPane(area=new JTextArea());
		area.setFont(new Font("맑은 고딕", Font.BOLD, 25)); //area의 폰트 크기 조정 
		
		//조립 
		add(scroll);
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(is !=null) {
					try {
						is.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				if(fis !=null) {
					try {
						fis.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				System.exit(0);
			}
		});
		
		
		area.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				isChanged=true;
				MemoApp.this.setTitle(MemoApp.this.getTitle()+"*");
			}
		});
		
		//윈도우 설정 
		setSize(1400,800);
		setVisible(true);
		
	}
	
	//파일 열기 
	public void openFile() {
		//문서 파일을 대상으로 빨대를 꽂아서, 들이마시자!! 
		//파일 열기 탐색기 띄우기!!
		int result = chooser.showOpenDialog(this); //새창은 반드시 부모컨테이너가 존재해야 뜰수있다.
													//즉 새창은 단독으로 존재할 수 없다..
		System.out.println("유저의 선택 "+result);
		//result 값은 긍정 0, 취소1 인데, 이 결과를 개발자가 암기하지 말자!!
		//왜? 자바에서는 사람에게 직관적인 데이터를 표현하기 위해 상수를 지원함 상수는 의미가 부여되어있기
		//때문에 사람이 쓰기 편하다
		if(result == JFileChooser.APPROVE_OPTION ) {//열기를 눌렀다면..
			//유저가 선택한 파일이 무엇인지 파악?
			
			//유저가 선택한 파일의 정보를 File 클래스의 인스턴스로 반환
			file = chooser.getSelectedFile(); 
			System.out.println("유저가 선택한 파일"+file);
			
			//얻어진 File 정보를 이용하여, 입력스트림을 메모리에 올리자(즉 빨대 꽂자) 
			try {
				fis = new FileInputStream(file);//파일객체를 이용한 스트림
				is = new InputStreamReader(fis);
				
				//파일 스트림을 이용하여 바이트 단위로 마구 마구 읽어서 area 에 출력
				int data=-1;
				
				while(true) {
					data = is.read(); //1byte 읽어들임
					if(data==-1)break; //파일의 끝에 도달하면 루프 종료
					area.append(""+(char)data); //문서 파일의 데이터를 읽었으므로, char 형으로 변환가능
				}
				
				this.setTitle(file.getAbsolutePath());
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void saveFile() {
		//area의 값들을 파일 출력스트림으로 내려쓰기
		FileWriter fw=null;
		
		try {
			
			fw = new FileWriter(file);			
			fw.write(area.getText());
			isChanged=false;
			MemoApp.this.setTitle(MemoApp.this.getTitle());			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fw !=null) {
				try {
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		/*이벤트를 일으킨 컴포넌트를 이벤트 소스(source)라 한다. e객체로부터 이벤트 소스를 얻자*/
		Object obj = e.getSource();
		
		if(obj == item[2]) {
			openFile();			
		}else if(obj ==item[3]){
			saveFile();
		}
		
	}
	
	public static void main(String[] args) {
		new MemoApp();
	}
}
