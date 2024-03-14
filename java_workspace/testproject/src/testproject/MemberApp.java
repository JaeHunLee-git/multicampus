package testproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MemberApp extends JFrame{
	JPanel p_west;
	JTextField t_id;
	JPasswordField t_pass;
	JPanel p_preview; //이미지 미리보기 영역
	JButton bt_file; //이미지 미리보기 파일 선택 창 띄우기 버튼
	JTextField t_email; //이메일
	JButton bt_regist; //등록버튼 
	
	JPanel p_center; //가운데 BorderLayout 영역 
	JPanel p_header; //검색영역
	JComboBox category; //검색 카테고리 
	JTextField t_keyword; // 검색어 
	JButton bt_search; //검색 버튼
	
	JPanel p_content; //JTable 나올곳
	JTable table;
	JScrollPane scroll;
	MemberController memberController;
	JFileChooser chooser;
	ImageIcon icon;
	String filePath; //프로필 사진의 경로 
	
	public MemberApp() {
		p_west = new JPanel();
		t_id = new JTextField();
		t_pass = new JPasswordField();
		
		p_preview = new JPanel() {
			public void paint(Graphics g) {
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, 190, 200);
				
				if(icon ==null) {
					g.setFont(new Font("맑은 고딕", Font.BOLD, 25));
					g.setColor(Color.RED);
					g.drawString("프로필 사진", 20, 100);
				}else {
					g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);	
				}
			}
		};
		
		bt_file = new JButton("파일선택");
		t_email = new JTextField();
		URL url = this.getClass().getClassLoader().getResource("add.png");
		bt_regist = new JButton(getScaledSize(new ImageIcon(url)));
		
		
		p_center = new JPanel();
		p_header = new JPanel();
		category = new JComboBox();
		t_keyword = new JTextField();
		bt_search = new JButton("Search");
		
		p_content = new JPanel();
		table = new JTable(memberController = new MemberController());
		scroll = new JScrollPane(table);
		chooser = new JFileChooser("D:/js_workspace/images");
		
		//서쪽 스타일 
		p_west.setPreferredSize(new Dimension(200, 800));
		Dimension d = new Dimension(190, 40);
		t_id.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		p_preview.setPreferredSize(new Dimension(190, 200));
		bt_file.setPreferredSize(d);
		t_email.setPreferredSize(d);
		bt_regist.setPreferredSize(new Dimension(60, 45));
		
		//서쪽 조립
		p_west.add(t_id);
		p_west.add(t_pass);
		p_west.add(p_preview);
		p_west.add(bt_file);
		p_west.add(t_email);
		p_west.add(bt_regist);
		
		//종합 조립 
		add(p_west, BorderLayout.WEST);
		
		//이벤트 연결 
		 bt_file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preview();
			}
		});
		
		 bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		 
		setSize(900, 800);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public ImageIcon getScaledSize(ImageIcon src) {
		Image image = src.getImage().getScaledInstance(60, 45, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(image);
		return newIcon;
	}
	
	public void preview() {
		int result = chooser.showOpenDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			icon=new ImageIcon(filePath = file.getAbsolutePath());
			p_preview.repaint();
		}
	}
	
	public void regist() {

	}
	
	public static void main(String[] args) {
		new MemberApp();
	}

}
