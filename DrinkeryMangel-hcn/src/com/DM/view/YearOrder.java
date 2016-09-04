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
	private String[] column ={"����","1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��"};
	private Object[][] result={};
	public YearOrder() {
		setTitle("�±���");
		
		setBounds(300, 100, 600, 400);
		toolJP = new JPanel();
		yearJL = new JLabel("��");
		monthJL = new JLabel("��");
		yearJCB = new JComboBox();
		for (int i = 0; i < year.length; i++) {
			yearJCB.addItem(year[i]);
		}
		monthJCB = new JComboBox();
		for (int i = 1; i < 13; i++) {
			monthJCB.addItem(i);

		}
	
		
		okJB = new JButton("ȷ��");
		excleJB = new JButton("����Excle");
		toolJP.add(yearJCB);
		toolJP.add(yearJL);
		toolJP.add(monthJCB);
		toolJP.add(monthJL);
		toolJP.add(okJB);
		toolJP.add(excleJB);
		toolJP.setBorder(new TitledBorder("����"));
		//
		tableJP = new JPanel();
		jscrollpane = new JScrollPane();
		jscrollpane.setPreferredSize(new Dimension(550,250));
		//���==========================================================
		jtable = new JTable(result,column);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//���==========================================================
		jscrollpane.setViewportView(jtable);
		tableJP.add(jscrollpane);
		tableJP.setBorder(new TitledBorder("�½��˱���"));
		this.add(toolJP, BorderLayout.NORTH);
		this.add(tableJP, BorderLayout.CENTER);
		
		
		this.setUndecorated(true); // ȥ�����ڵ�װ��
		this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG  );
		this.setVisible(true);
		
	}
	//�������excle
//	class ExcleAction implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			WritableWorkbook book = null;
//			Label num = new Label(1,0,"���");
//			Label desk_num = new Label(2,0,"��̨��");
//			Label datetime = new Label(3,0,"��̨ʱ��");
//			Label money = new Label(4,0,"���Ѷ�");
//			
//	        try {
//	            book = Workbook.createWorkbook(new File("C:/Users/Hou/Desktop/�ս���.xls"));
//	            WritableSheet sheet = book.createSheet("�ս���", 0);
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
//	            	sheet.addCell(new Label(0,i,"�ܼ�"));
//	                sheet.addCell(new Number(4,i,money_sum));
//	            }
//	            
//	            // д�����ݲ��ر��ļ�
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
//	    JOptionPane.showMessageDialog(null, "�����ɹ���");
//			
//		}
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new YearOrder();
	}

}
