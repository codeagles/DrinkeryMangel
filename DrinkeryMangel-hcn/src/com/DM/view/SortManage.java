package com.DM.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.DM.db.SortDao;
import com.DM.model.tb_sort;

public class SortManage extends JFrame {
	private JPanel sortJP,buttonJP,resultJP;
	private JLabel IDJL,nameJL;
	private JTable jtable;
	private JScrollPane resultJSP;
	private JTextField IDJTF,nameJTF;
	private JButton addJB,delJB,exitJB;
	private String[] column={"编号","菜系名称"};
	private Object[][] getSelectSort(List<tb_sort>list){
		Object[][] result = new Object[list.size()][column.length];
		for(int i =0;i<list.size();i++){
		tb_sort ts = list.get(i);
		result[i][0]=ts.getId();
		result[i][1]=ts.getName();
		}
		return result;
	}
	public SortManage(){
		this.setTitle("菜系管理");
		setBounds(400, 200, 500, 400);
		//上边栏设计与实现
		sortJP = new JPanel();	
		sortJP.setLayout(new GridLayout(2,3,5,5));
		IDJL = new JLabel("编号：");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		IDJTF = new JTextField(10);
		nameJL = new JLabel("菜系：");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		nameJTF = new JTextField(10);
		addJB = new JButton("添加菜系");
		delJB = new JButton("删除菜系");
		sortJP.add(IDJL);
		sortJP.add(IDJTF);
		sortJP.add(addJB);
		sortJP.add(nameJL);
		sortJP.add(nameJTF);
		sortJP.add(delJB);	
		sortJP.setBorder(new TitledBorder("菜系"));
		//结果面板设计
		resultJP = new JPanel();
		resultJSP = new JScrollPane();
		Object[][] result =getSelectSort(SortDao.selectSort());
		jtable = new JTable(result,column);
		resultJSP.setPreferredSize(new Dimension(400,200));
		resultJSP.setViewportView(jtable);
		resultJP.add(resultJSP);
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtable.setDefaultRenderer(Object.class,   r);
		resultJP.setBorder(new TitledBorder("菜系浏览"));
		//退出按钮摆放
		buttonJP = new JPanel();
		exitJB = new JButton("退出");
		buttonJP.add(exitJB);
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG  );
		this.add(sortJP,BorderLayout.NORTH);
		this.add(resultJP,BorderLayout.CENTER);
		this.add(buttonJP,BorderLayout.SOUTH);
		this.setVisible(true);
		this.setResizable(false);
		addJB.addActionListener(new AddAction());
		jtable.addMouseListener(new JTableListener());
		delJB.addActionListener(new DelAction());
		exitJB.addActionListener(new ExitAction());
	}
	//添加表格监听器
	class JTableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = jtable.getSelectedRow();
			IDJTF.setText(jtable.getValueAt(selRow, 0).toString());
			nameJTF.setText(jtable.getValueAt(selRow, 1).toString());
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
		}
		
			
		
		
	}
	//添加按钮监听器
	class AddAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Integer id = Integer.valueOf(IDJTF.getText().trim());
			String name = nameJTF.getText().trim();
			
			if(!SortDao.selectSortByID(id).isEmpty()){
				JOptionPane.showMessageDialog(null, "该编号已存在！不可添加！");
				return ;
			}
			int i = SortDao.insertSort(id, name);
			if(i==1){
				JOptionPane.showMessageDialog(null, "添加成功！");
				IDJTF.setText("");
				nameJTF.setText("");
			}
			
			Object[][] result =getSelectSort(SortDao.selectSort());
			jtable = new JTable(result,column);
			resultJSP.setViewportView(jtable);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
		}
		
	}
	//删除按钮监听器
	class DelAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			int id = Integer.valueOf(IDJTF.getText().trim());
			String name =nameJTF.getText().trim();
			int i= SortDao.deleteSort(id, name);
			if(i==1){
				JOptionPane.showMessageDialog(null, "删除成功！");
			}
			Object[][] result =getSelectSort(SortDao.selectSort());
			jtable = new JTable(result,column);
			resultJSP.setViewportView(jtable);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
		}
		
	}
	//
	class ExitAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			
		}
	}
	public static void main(String[] args) {
		new SortManage();

	}

}
