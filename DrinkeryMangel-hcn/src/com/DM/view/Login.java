package com.DM.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Login extends JFrame {
	private JLabel imgJL,usernameJL,passwordJL;
	private JPanel imgJP,infoJP,buttonJP,orderJP;
	private JTextField usernameJTF,passwordJTF;
	private JButton confirmJB,closeJB;
	public Login(){	
		this.setTitle("酒店管理系统登录");
		setSize(600, 400);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 6 + 80, height / 8 + 70);
		imgJP = new JPanel();
		infoJP = new JPanel();
		buttonJP = new JPanel();
		orderJP = new JPanel();
		ImageIcon bg = new ImageIcon("img/land_background.jpg");
		
		imgJL = new JLabel(bg);
		usernameJL = new JLabel();
		passwordJL = new JLabel();
		imgJP.add(imgJL);
		
		
		orderJP.add(imgJP,new BorderLayout().NORTH);
		
		this.add(orderJP);
		setVisible(true);
	}
	public static void main(String args[]){
		new Login();
	}

}