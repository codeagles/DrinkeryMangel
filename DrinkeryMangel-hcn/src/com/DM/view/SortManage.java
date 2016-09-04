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
	private String[] column={"���","��ϵ����"};
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
		this.setTitle("��ϵ����");
		setBounds(400, 200, 500, 400);
		//�ϱ��������ʵ��
		sortJP = new JPanel();	
		sortJP.setLayout(new GridLayout(2,3,5,5));
		IDJL = new JLabel("��ţ�");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		IDJTF = new JTextField(10);
		nameJL = new JLabel("��ϵ��");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		nameJTF = new JTextField(10);
		addJB = new JButton("��Ӳ�ϵ");
		delJB = new JButton("ɾ����ϵ");
		sortJP.add(IDJL);
		sortJP.add(IDJTF);
		sortJP.add(addJB);
		sortJP.add(nameJL);
		sortJP.add(nameJTF);
		sortJP.add(delJB);	
		sortJP.setBorder(new TitledBorder("��ϵ"));
		//���������
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
		resultJP.setBorder(new TitledBorder("��ϵ���"));
		//�˳���ť�ڷ�
		buttonJP = new JPanel();
		exitJB = new JButton("�˳�");
		buttonJP.add(exitJB);
		this.setUndecorated(true); // ȥ�����ڵ�װ��
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
	//��ӱ�������
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
	//��Ӱ�ť������
	class AddAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Integer id = Integer.valueOf(IDJTF.getText().trim());
			String name = nameJTF.getText().trim();
			
			if(!SortDao.selectSortByID(id).isEmpty()){
				JOptionPane.showMessageDialog(null, "�ñ���Ѵ��ڣ�������ӣ�");
				return ;
			}
			int i = SortDao.insertSort(id, name);
			if(i==1){
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
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
	//ɾ����ť������
	class DelAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			int id = Integer.valueOf(IDJTF.getText().trim());
			String name =nameJTF.getText().trim();
			int i= SortDao.deleteSort(id, name);
			if(i==1){
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
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
