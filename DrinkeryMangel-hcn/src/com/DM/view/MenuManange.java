package com.DM.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import org.omg.PortableInterceptor.ACTIVE;

import com.DM.db.MenuDao;
import com.DM.db.SortDao;
import com.DM.model.tb_menu;
import com.DM.model.tb_sort;
import com.DM.view.SortManage.ExitAction;

public class MenuManange extends JFrame {
	private JLabel IDJL, nameJL, unitJL, codeJL, sortJL, unitpriceJL;
	private JTextField IDJTF, nameJTF, unitJTF, unitpriceJTF, codeJTF;
	private JComboBox sortJCB;
	private JButton addJB, delJB, updateJB, exitJB;
	private JScrollPane resultJSP;
	private JTable jtable;
	private JPanel conditionJP, buttonJP1, buttonJP2, addJP, resultJP;
	private String[] column = { "���", "����", "������", "��ϵ", "��λ", "����" };

	private Object[][] getSelect(List<tb_menu> list) {
		Object[][] result = new Object[list.size()][column.length];
		for (int i=0; i < list.size(); i++) {
			tb_menu tm = list.get(i);
			result[i][0] = tm.getNum();
			result[i][1] = tm.getName();
			result[i][2] = tm.getCode();
			result[i][3] = tm.getSort_name();
			result[i][4] = tm.getUnit();
			result[i][5] = tm.getUnit_price();
		}
		return result;

	}

	public MenuManange() {
		setTitle("��Ʒ����");
		setBounds(300, 100, 600, 400);
		// ��Ϣ�������
		conditionJP = new JPanel();
		conditionJP.setLayout(new GridLayout(2, 6, 0, 5));
		IDJL = new JLabel("��ţ�");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		IDJTF = new JTextField();
		nameJL = new JLabel("���ƣ�");
		nameJL.setHorizontalAlignment(SwingConstants.CENTER);
		nameJTF = new JTextField();
		unitJL = new JLabel("��λ��");
		unitJL.setHorizontalAlignment(SwingConstants.CENTER);
		unitJTF = new JTextField();
		codeJL = new JLabel("�����룺");
		codeJL.setHorizontalAlignment(SwingConstants.CENTER);
		codeJTF = new JTextField();
		sortJL = new JLabel("��ϵ��");
		sortJL.setHorizontalAlignment(SwingConstants.CENTER);
		sortJCB = new JComboBox();
		List<tb_sort> list = SortDao.selectSort();
		for (int i = 0; i < list.size(); i++) {
			tb_sort ts = list.get(i);
			sortJCB.addItem(ts.getName());
		}

		unitpriceJL = new JLabel("���ۣ�");
		unitpriceJL.setHorizontalAlignment(SwingConstants.CENTER);
		unitpriceJTF = new JTextField();
		conditionJP.add(IDJL);
		conditionJP.add(IDJTF);
		conditionJP.add(nameJL);
		conditionJP.add(nameJTF);
		conditionJP.add(unitJL);
		conditionJP.add(unitJTF);
		conditionJP.add(codeJL);
		conditionJP.add(codeJTF);
		conditionJP.add(sortJL);
		conditionJP.add(sortJCB);
		conditionJP.add(unitpriceJL);
		conditionJP.add(unitpriceJTF);
		buttonJP1 = new JPanel();
		updateJB = new JButton("�޸Ĳ�Ʒ");
		addJB = new JButton("��Ӳ�Ʒ");
		delJB = new JButton("ɾ����Ʒ");
		buttonJP1.add(addJB);
		buttonJP1.add(updateJB);
		buttonJP1.add(delJB);
		addJP = new JPanel();
		addJP.setBorder(new TitledBorder("��Ϣ"));
		addJP.setLayout(new BorderLayout());
		addJP.add(conditionJP, BorderLayout.CENTER);
		addJP.add(buttonJP1, BorderLayout.SOUTH);
		// ������
		resultJP = new JPanel();
		resultJP.setBorder(new TitledBorder(""));
		resultJSP = new JScrollPane();
		resultJSP.setPreferredSize(new Dimension(550, 180));
		Object[][] result = getSelect(MenuDao.selectMenu());
		jtable = new JTable(result,column);
		resultJSP.setViewportView(jtable);
		resultJP.add(resultJSP);
		//�ñ�����������ʾ
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtable.setDefaultRenderer(Object.class,   r);
		// buttonJP2���
		buttonJP2 = new JPanel();
		exitJB = new JButton("�˳�");
		buttonJP2.add(exitJB);

		this.add(buttonJP2, BorderLayout.SOUTH);
		this.add(resultJP, BorderLayout.CENTER);
		this.add(addJP, BorderLayout.NORTH);

		this.setUndecorated(true); // ȥ�����ڵ�װ��
		this.getRootPane().setWindowDecorationStyle(
				JRootPane.INFORMATION_DIALOG);
		this.setVisible(true);
		this.setResizable(false);
		exitJB.addActionListener(new ExitAction());
		addJB.addActionListener(new AddAction());
		jtable.addMouseListener(new JTableAction());
		updateJB.addActionListener(new UpdateAction());
		delJB.addActionListener(new DelAction());
	}
	//��� ��������
	class JTableAction extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = jtable.getSelectedRow();
			IDJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
			nameJTF.setText(jtable.getValueAt(selRow, 1).toString().trim());
			codeJTF.setText(jtable.getValueAt(selRow, 2).toString().trim());
			sortJCB.setSelectedItem(jtable.getValueAt(selRow, 3).toString().trim());
			unitJTF.setText(jtable.getValueAt(selRow, 4).toString().trim());
			unitpriceJTF.setText(jtable.getValueAt(selRow, 5).toString().trim());
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);		
		}
	}
	//�޸ļ�����ʵ��
	class UpdateAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String num = IDJTF.getText().trim();
			int selRow = jtable.getSelectedRow();
			String tablenum = jtable.getValueAt(selRow, 0).toString().trim();
			String name = nameJTF.getText().trim();
			Integer unit_price = Integer.valueOf(unitpriceJTF.getText().trim());
			String unit = unitJTF.getText().trim();
			String code = codeJTF.getText().trim();
			String sortname = sortJCB.getSelectedItem().toString();
			int i = MenuDao.updatetMenu(num, sortname, name, code, unit, unit_price,tablenum);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�����");
				IDJTF.setText("");
				nameJTF.setText("");
				unitpriceJTF.setText("");
				unitJTF.setText("");
				codeJTF.setText("");
			}
			Object[][] result = getSelect(MenuDao.selectMenu());
			jtable = new JTable(result,column);
			resultJSP.setViewportView(jtable);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
			jtable.addMouseListener(new JTableAction());
			
		}
	}

	// ��� ������ ʵ��
	class AddAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String num = IDJTF.getText().trim();
			String name = nameJTF.getText().trim();
			Integer unit_price = Integer.valueOf(unitpriceJTF.getText().trim());
			String unit = unitJTF.getText().trim();
			String code = codeJTF.getText().trim();
			String sort_name = sortJCB.getSelectedItem().toString();
			int i = MenuDao.insertMenu(num, sort_name, name, code, unit,
					unit_price);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				IDJTF.setText("");
				nameJTF.setText("");
				unitpriceJTF.setText("");
				unitJTF.setText("");
				codeJTF.setText("");
				
			}
			Object[][] result = getSelect(MenuDao.selectMenu());
			jtable = new JTable(result,column);
			resultJSP.setViewportView(jtable);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
			jtable.addMouseListener(new JTableAction());
		}
	}
	//ɾ�������� ʵ��
	class DelAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String num = IDJTF.getText().trim();
			int i = MenuDao.deleteMenu(num);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				IDJTF.setText("");
				nameJTF.setText("");
				unitpriceJTF.setText("");
				unitJTF.setText("");
				codeJTF.setText("");
			}
			Object[][] result = getSelect(MenuDao.selectMenu());
			jtable = new JTable(result,column);
			resultJSP.setViewportView(jtable);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
			jtable.addMouseListener(new JTableAction());
			
		}
	}
	class ExitAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);

		}
	}

	public static void main(String[] args) {
		new MenuManange();

	}

}
