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

import com.DM.db.DestDao;
import com.DM.model.tb_dest;

public class DeskManage extends JFrame {
	private JLabel numJL, seatingJL;
	private JTextField numJTF, seatingJTF;
	private JScrollPane resultJSP;
	private JTable jtable;
	private JButton addJB, delJB, exitJB;
	private JPanel resultJP, conditionJP, buttonJP;
	private String[] column = { "台号", "座位数" };

	private Object[][] getSelectDest(List<tb_dest> list) {
		Object[][] result = new Object[list.size()][column.length];
		for (int i = 0; i < list.size(); i++) {
			tb_dest td = list.get(i);
			result[i][0] = td.getNum();
			result[i][1] = td.getSeating();
		}
		return result;
	}

	public DeskManage() {
		this.setTitle("台号管理");
		setBounds(400, 200, 500, 400);
		//
		conditionJP = new JPanel();
		conditionJP.setLayout(new GridLayout(1, 6, 5, 5));
		numJL = new JLabel("台号：");
		numJL.setHorizontalAlignment(SwingConstants.CENTER);
		numJTF = new JTextField(10);
		seatingJL = new JLabel("座位数：");
		seatingJL.setHorizontalAlignment(SwingConstants.CENTER);
		seatingJTF = new JTextField(10);
		addJB = new JButton("添加");
		delJB = new JButton("删除");
		conditionJP.add(numJL);
		conditionJP.add(numJTF);
		conditionJP.add(seatingJL);
		conditionJP.add(seatingJTF);
		conditionJP.add(addJB);
		conditionJP.add(delJB);
		conditionJP.setBorder(new TitledBorder("管理"));
		// 结果预览
		resultJP = new JPanel();
		resultJSP = new JScrollPane();
		Object[][] result = getSelectDest(DestDao.selectDesk());
		
		jtable = new JTable(result,column);
		resultJSP.setPreferredSize(new Dimension(450, 230));
		resultJSP.setViewportView(jtable);
		resultJP.add(resultJSP);
		resultJP.setBorder(new TitledBorder("台号浏览"));
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		jtable.setDefaultRenderer(Object.class,   r);
		// 退出按钮摆放
		buttonJP = new JPanel();
		exitJB = new JButton("退出");
		buttonJP.add(exitJB);
		this.add(conditionJP, BorderLayout.NORTH);
		this.add(resultJP, BorderLayout.CENTER);
		this.add(buttonJP, BorderLayout.SOUTH);

		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(
				JRootPane.INFORMATION_DIALOG);
		this.setVisible(true);
		this.setResizable(false);
		exitJB.addActionListener(new ExitAction());
		addJB.addActionListener(new AddAction());
		delJB.addActionListener(new DelDestAction());
		jtable.addMouseListener(new JTableAction());
	}

	//
	class ExitAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);

		}
	}

	// 添加表格事件
	class JTableAction extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = jtable.getSelectedRow();
			numJTF.setText(jtable.getValueAt(selRow, 0).toString());
			seatingJTF.setText(jtable.getValueAt(selRow, 1).toString());
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
		}
	}

	// 添加台号
	class AddAction  implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String num = numJTF.getText().trim();
			Integer seating = Integer.valueOf(seatingJTF.getText().trim());
			if(!DestDao.selectDeskByNum(num).isEmpty()){
				JOptionPane.showMessageDialog(null, "该台号已存在");
				return;
			}
			int i = DestDao.insertDest(num, seating);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				numJTF.setText("");
				seatingJTF.setText("");
			}
			Object[][] result = getSelectDest(DestDao.selectDesk());
			jtable = new JTable(result,column);
			resultJSP.setViewportView(jtable);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
			jtable.addMouseListener(new JTableAction());
		}
	}

	// 删除台号
	class DelDestAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String num = numJTF.getText().trim();
			Integer seating = Integer.valueOf(seatingJTF.getText().trim());
			int i = DestDao.deleteDest(num, seating);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "删除成功！");
			}
			Object[][] result = getSelectDest(DestDao.selectDesk());
			jtable = new JTable(result,column);
			resultJSP.setViewportView(jtable);
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			jtable.setDefaultRenderer(Object.class,   r);
			jtable.addMouseListener(new JTableAction());

		}
	}

	public static void main(String[] args) {
		new DeskManage();

	}

}
