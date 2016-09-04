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

public class MonthOrder extends JFrame {
	private JLabel yearJL, monthJL;
	private JButton okJB,excleJB;
	private JTable jtable;
	private JComboBox yearJCB, monthJCB;
	private JScrollPane jscrollpane;
	private JPanel toolJP, tableJP;
	private int[] year = { 2000, 2012, 2013, 2014, 2015, 2016 };
	private String[] column ={"日期","开台总数","消费总额","平均消费额","最大消费额","最小消费额"};
	private Object[][] getSelectMonth(List<tb_order_form>list){
		Object[][] result =new Object[list.size()][column.length];
		for(int i=0;i<list.size();i++){
			tb_order_form tof = list.get(i);
			result[i][1]=tof.getCount();
			result[i][2]=tof.getMoney_sum();
			result[i][3]=tof.getMoney_avg();
			result[i][4]=tof.getMoney_max();
			result[i][5]=tof.getMoney_min();	
		}
		return result;
	} 
	static String date ="";
	public MonthOrder() {
		setTitle("月报表");
		
		setBounds(300, 100, 600, 400);
		toolJP = new JPanel();
		yearJL = new JLabel("年");
		monthJL = new JLabel("月");
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
	
		
		okJB = new JButton("确定");
		excleJB = new JButton("导出Excle");
		toolJP.add(yearJCB);
		toolJP.add(yearJL);
		toolJP.add(monthJCB);
		toolJP.add(monthJL);
		toolJP.add(okJB);
		toolJP.add(excleJB);
		toolJP.setBorder(new TitledBorder("日期"));
		//
		tableJP = new JPanel();
		jscrollpane = new JScrollPane();
		jscrollpane.setPreferredSize(new Dimension(550,250));
		//表格==========================================================
		
		jtable = new JTable();
		//jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//表格==========================================================
		jscrollpane.setViewportView(jtable);
		tableJP.add(jscrollpane);
		tableJP.setBorder(new TitledBorder("月结账报表"));
		this.add(toolJP, BorderLayout.NORTH);
		this.add(tableJP, BorderLayout.CENTER);
		
		
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG  );
		this.setVisible(true);
		this.setResizable(false);
		okJB.addActionListener(new OkIBAction());
	}
	//按钮监听器
	class OkIBAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			StringBuffer year_buffer = new StringBuffer(yearJCB.getSelectedItem().toString());
			String year =year_buffer.delete(0, 2).toString();
			String month = monthJCB.getSelectedItem().toString();
			 date =year+month;
			System.out.println(date);
			Object[][] result =getSelectMonth(FormDao.selectFormByMonth(date));
			DefaultTableModel model = new DefaultTableModel(result, column);
			String[] data ={"总计"};
			model.addRow(data);
			jtable.setModel(model);
			List<tb_order_form> list =FormDao.selectFormByMonth(date);
			double sum = 0;
			int count = 0;
			double avg = 0;
			double max = 0;
			double min = 0;
			for(int i=0;i<list.size();i++){
				tb_order_form s = list.get(i);
				count =s.getCount();
				sum =s.getMoney_sum();
				avg =s.getMoney_avg();
				max =s.getMoney_max();
				min=s.getMoney_min();
			}
			jtable.setValueAt(count, jtable.getRowCount()-1, 1);
			jtable.setValueAt(sum, jtable.getRowCount()-1, 2);
			jtable.setValueAt(avg, jtable.getRowCount()-1, 3);
			jtable.setValueAt(max, jtable.getRowCount()-1, 4);
			jtable.setValueAt(min, jtable.getRowCount()-1, 5);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			jtable.setDefaultRenderer(Object.class, r);
			
		}
	}
	//导出Excle
	class ExcleAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			WritableWorkbook book = null;
			Label num = new Label(1,0,"日期");
			Label desk_num = new Label(2,0,"餐台号");
			Label datetime = new Label(3,0,"开台时间");
			Label money = new Label(4,0,"消费额");
			
	        try {
	            book = Workbook.createWorkbook(new File("C:/Users/Hou/Desktop/月结账.xls"));
	            WritableSheet sheet = book.createSheet("月结账", 0);
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
			new MonthOrder();

	}

}
