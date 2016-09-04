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

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.DM.db.FormDao;
import com.DM.model.tb_order_form;

public class YearOrder extends JFrame {

	private JLabel yearJL, monthJL;
	private JButton okJB,excleJB;
	private JTable jtable;
	private JComboBox yearJCB, monthJCB;
	private JScrollPane jscrollpane;
	private JPanel toolJP, tableJP;
	private int[] year = { 2000, 2012, 2013, 2014, 2105, 2016 };
	private String[] column ={"日期","1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
	private Object[][] result={};
	public YearOrder() {
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
			monthJCB.addItem(i);

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
		jtable = new JTable(result,column);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//表格==========================================================
		jscrollpane.setViewportView(jtable);
		tableJP.add(jscrollpane);
		tableJP.setBorder(new TitledBorder("月结账报表"));
		this.add(toolJP, BorderLayout.NORTH);
		this.add(tableJP, BorderLayout.CENTER);
		
		
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG  );
		this.setVisible(true);
		
	}
	//导出表格excle
//	class ExcleAction implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			WritableWorkbook book = null;
//			Label num = new Label(1,0,"编号");
//			Label desk_num = new Label(2,0,"餐台号");
//			Label datetime = new Label(3,0,"开台时间");
//			Label money = new Label(4,0,"消费额");
//			
//	        try {
//	            book = Workbook.createWorkbook(new File("C:/Users/Hou/Desktop/日结账.xls"));
//	            WritableSheet sheet = book.createSheet("日结账", 0);
//	            sheet.addCell(num);
//	            sheet.addCell(desk_num);
//	            sheet.addCell(datetime);
//	            sheet.addCell(money);
//	            List<tb_order_form> dayList=FormDao.selectFormByDay(date);
//	            if(dayList!=null && !dayList.isEmpty()){
//	            	int i;
//	                for( i=1; i<dayList.size()+1; i++){
//	                    sheet.addCell(new Label(1, i, dayList.get(i-1).getNum()));
//	                    sheet.addCell(new Label(2, i, dayList.get(i-1).getDesk_num()));
//	                    sheet.addCell(new Label(3, i, dayList.get(i-1).getDatetime()));
//	                    sheet.addCell(new Number(4, i, dayList.get(i-1).getMoney()));
//	                    System.out.println(i);
//	                }
//	              
//	                
//	            	double money_sum =FormDao.selectFormSum(date);
//	            	sheet.addCell(new Label(0,i,"总计"));
//	                sheet.addCell(new Number(4,i,money_sum));
//	            }
//	            
//	            // 写入数据并关闭文件
//	            book.write();
//	        } catch (Exception e1) {
//	            System.out.println(e1);
//	        }finally{
//	            if(book!=null){
//	                try {
//	                    book.close();
//	                } catch (Exception e2) {
//	                    e2.printStackTrace();
//	                } 
//	            }
//	        }
//	    JOptionPane.showMessageDialog(null, "导出成功！");
//			
//		}
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new YearOrder();
	}

}
