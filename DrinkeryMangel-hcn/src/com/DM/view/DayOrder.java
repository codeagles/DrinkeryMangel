package com.DM.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.DM.db.FormDao;
import com.DM.model.tb_order_form;

public class DayOrder extends JFrame {
	private JLabel yearJL, monthJL, dayJL;
	private JButton okJB,excleJB;
	private JTable jtable;
	private JComboBox yearJCB, monthJCB, dayJCB;
	private JScrollPane jscrollpane;
	private JPanel toolJP, tableJP;
	private int[] year = { 2000, 2012, 2013, 2014, 2015, 2016 };
	private int[] month = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	//=======================================================================================
	//=======================================================================================
	private String[] colunm = {"","编号","台号","开台时间","消费金额"};
	@SuppressWarnings("unused")
	private Object[][] getSelectDay(List<tb_order_form>list){
		Object[][] result =new Object[list.size()][colunm.length];
		for(int i=0;i<list.size();i++){
			tb_order_form tof = list.get(i);
			result[i][1]=tof.getNum();
			result[i][2]=tof.getDesk_num();
			result[i][3]=tof.getDatetime();
			result[i][4]=tof.getMoney();		
		}
		return result;
	} 
	
	static String date ="";
	public DayOrder() {
		setTitle("日报表");
		setBounds(300, 100, 600, 400);
		toolJP = new JPanel();
		yearJL = new JLabel("年");
		monthJL = new JLabel("月");
		dayJL = new JLabel("日");
		yearJCB = new JComboBox();
		for (int i = 0; i < year.length; i++) {
			yearJCB.addItem(year[i]);
		}
		monthJCB = new JComboBox();
		for (int i = 1; i < 13; i++) {
			if(i<10){
				monthJCB.addItem("0"+i);
			}else{
				monthJCB.addItem(i);
			}
			
		}
		
		
		dayJCB = new JComboBox();

		monthJCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int yearCount = (Integer) yearJCB.getSelectedItem();
				StringBuffer month_buffer =new StringBuffer(monthJCB.getSelectedItem().toString());
				String month1 ="";
				if(monthJCB.getSelectedIndex()<10){
					month1 = month_buffer.delete(0, 1).toString();
				}
				
				int monthCount = Integer.valueOf(month1);
				
				int d = 0;
				if (yearCount % 4 == 0 && yearCount % 100 != 0
						|| yearCount % 400 == 0) {
					if (monthCount == 2) {
						while (d < 29){
							d++;
							if(d<10){
								dayJCB.addItem("0"+d);
							}else{
							dayJCB.addItem(d);
							}
						}
					} else {
						while (d < month[monthCount - 1]) {
							d++;
							if(d<10){
								dayJCB.addItem("0"+d);
							}else{
							dayJCB.addItem(d);
							}
						}
					}

				} else {
					while (d < month[monthCount - 1]) {
						d++;
						dayJCB.addItem(d);
					}
				}
			}
		});
		excleJB = new JButton("导出Excle");
		okJB = new JButton("确定");
		toolJP.add(yearJCB);
		toolJP.add(yearJL);
		toolJP.add(monthJCB);
		toolJP.add(monthJL);
		toolJP.add(dayJCB);
		toolJP.add(dayJL);
		toolJP.add(okJB);
		toolJP.add(excleJB);
		toolJP.setBorder(new TitledBorder("日期"));
		//
		tableJP = new JPanel();
		jscrollpane = new JScrollPane();
		jscrollpane.setPreferredSize(new Dimension(550,250));
		jtable = new JTable();
		//表格==========================================================
		Object[][] result = getSelectDay(FormDao.selectFormByOver());
		DefaultTableModel model = new DefaultTableModel(result, colunm);
		String[] data ={"总计"};
		model.addRow(data);
		jtable.setModel(model);
		double aValue =FormDao.selectFormSum(date);
		jtable.setValueAt(aValue, jtable.getRowCount()-1, jtable.getColumnCount()-1);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		jtable.setDefaultRenderer(Object.class, r);
		
		
//		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//表格==========================================================
		jscrollpane.setViewportView(jtable);
		tableJP.add(jscrollpane);
		tableJP.setBorder(new TitledBorder("日结账报表"));
		this.add(toolJP, BorderLayout.NORTH);
		this.add(tableJP, BorderLayout.CENTER);
		
		
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG  );
		this.setVisible(true);
		excleJB.addActionListener(new ExcleAction());
		okJB.addActionListener(new OkIBAction());
	}
	//按钮监听器
	class OkIBAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			StringBuffer year_buffer = new StringBuffer(yearJCB.getSelectedItem().toString());
			String year =year_buffer.delete(0, 2).toString();
			System.out.println(year);
			String month = monthJCB.getSelectedItem().toString();
			System.out.println(month);
			String day = dayJCB.getSelectedItem().toString();
			System.out.println(day);
			 date =year+month+day;
			System.out.println(date);
			Object[][] result = getSelectDay(FormDao.selectFormByDay(date));
			DefaultTableModel model = new DefaultTableModel(result, colunm);
			String[] data ={"总计"};
			model.addRow(data);
			jtable.setModel(model);
			double aValue =FormDao.selectFormSum(date);
			jtable.setValueAt(aValue, jtable.getRowCount()-1, jtable.getColumnCount()-1);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			jtable.setDefaultRenderer(Object.class, r);
			
		}
	}
	//导出表格事件监听
	class ExcleAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			WritableWorkbook book = null;
			Label num = new Label(1,0,"编号");
			Label desk_num = new Label(2,0,"餐台号");
			Label datetime = new Label(3,0,"开台时间");
			Label money = new Label(4,0,"消费额");
			
	        try {
	            book = Workbook.createWorkbook(new File("C:/Users/Hou/Desktop/日结账.xls"));
	            WritableSheet sheet = book.createSheet("日结账", 0);
	            sheet.addCell(num);
	            sheet.addCell(desk_num);
	            sheet.addCell(datetime);
	            sheet.addCell(money);
	            List<tb_order_form> dayList=FormDao.selectFormByDay(date);
	            if(dayList!=null && !dayList.isEmpty()){
	            	int i;
	                for( i=1; i<dayList.size()+1; i++){
	                    sheet.addCell(new Label(1, i, dayList.get(i-1).getNum()));
	                    sheet.addCell(new Label(2, i, dayList.get(i-1).getDesk_num()));
	                    sheet.addCell(new Label(3, i, dayList.get(i-1).getDatetime()));
	                    sheet.addCell(new Number(4, i, dayList.get(i-1).getMoney()));
	                    System.out.println(i);
	                }
	              
	                
	            	double money_sum =FormDao.selectFormSum(date);
	            	sheet.addCell(new Label(0,i,"总计"));
	                sheet.addCell(new Number(4,i,money_sum));
	            }
	            
	            // 写入数据并关闭文件
	            book.write();
	        } catch (Exception e1) {
	            System.out.println(e1);
	        }finally{
	            if(book!=null){
	                try {
	                    book.close();
	                } catch (Exception e2) {
	                    e2.printStackTrace();
	                } 
	            }
	        }
	    JOptionPane.showMessageDialog(null, "导出成功！");
			
		}
	}
	
	public static void main(String[] args) {
		new DayOrder();
	}

}
