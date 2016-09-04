package com.DM.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.DM.db.DestDao;
import com.DM.db.FormDao;
import com.DM.db.ItemDao;
import com.DM.db.MenuDao;
import com.DM.model.tb_dest;
import com.DM.model.tb_menu;
import com.DM.model.tb_order_form;
import com.DM.model.tb_order_item;

public class MainView extends JFrame {
	private JSplitPane jsplitpane;
	private JPanel leftJP, rightJP, timeJP, orderJP, buttonJP, centerJP,
			bottomJP, totalJP, topJP, groupJP;
	private JTable leftJT, rightJT;
	private JScrollPane leftJSP, rightJSP;
	private JLabel deskIDJL, thingJL, eatnameJL, unitJL, numberJL,
			ordermoneyJL, cashJL, timeJL, designJL, startJL, todayJL, workIDJL,
			wokerJL, payJL;
	private JLabel bgJL, moneyJL, JL1, JL2, JL3, JL4;// JL1~3 =��Ԫ�� JL4ռλ
	private JButton startJB, addJB, designJB, cennelJB, orderJB, menuJB,
			sortJB, deskJB, dayJB, monthJB, yearJB, updatepwdJB, usermanageJB,
			closeJB;
	private JTextField codeJTF, eatnameJTF, unitJTF, numberJTF, ordermoneyJTF,
			payJTF, cashJTF;
	private JComboBox deskJCB;
	private final static int WIDTH = 35;
	private final static int HEIGHT = 35;
	private ButtonGroup buttonGroup;
	private JRadioButton JRB1, JRB2;
	private String[] columnleft = { "", "��Ʒ���", "��Ʒ����", "��λ", "����", "���" };

	private String[] colunmright = { "������", "��̨", "��̨ʱ��" };
	public String num1;
	public Double money1 = (double) 0;
	public String order_num_static;

	@SuppressWarnings("unused")
	private Object[][] getSelectDesk(List<tb_order_form> list) {
		Object[][] result_form = new Object[list.size()][colunmright.length];
		for (int i = 0; i < list.size(); i++) {
			tb_order_form tof = list.get(i);
			result_form[i][0] = tof.getNum();
			result_form[i][1] = tof.getDesk_num();
			result_form[i][2] = tof.getDatetime();
		}
		return result_form;
	}

	private Object[][] getSelect(List<tb_order_item> list) {
		Object[][] result = new Object[list.size()][columnleft.length];
		for (int i = 0; i < list.size(); i++) {

			
			tb_order_item toi = list.get(i);
			result[i][1] = toi.getMenu_num();
			result[i][2] = toi.getMenuname();
			result[i][3] = toi.getUnit();
			result[i][4] = toi.getAmount();
			result[i][5] = toi.getTotal();

		}
		return result;
	}

	public MainView() {
		this.setTitle("��������ϵͳ");
		JFrame frame = new JFrame();
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setBounds(width / 6, height / 8 - 80, 1000, 700);
		jsplitpane = new JSplitPane();
		jsplitpane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		jsplitpane.setDividerLocation(755);
		jsplitpane.setOneTouchExpandable(true);
		jsplitpane.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		getContentPane().add(jsplitpane, BorderLayout.CENTER);
		// ϵͳ�����ͼƬ
		ImageIcon bg = new ImageIcon("img/top.jpg");
		topJP = new JPanel();
		bgJL = new JLabel(bg);
		topJP.add(bgJL);
		bgJL.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		// frame.getLayeredPane().add(bgJL,new Integer(Integer.MAX_VALUE));
		// ��ȡframe�����ϲ����Ϊ�������䱳����ɫ��JPanel������͸���ķ�����
		JPanel jp = (JPanel) frame.getContentPane();
		jp.setOpaque(false);
		// ������
		leftJP = new JPanel();
		leftJP.setLayout(new BorderLayout());

		leftJSP = new JScrollPane();
		leftJSP.setPreferredSize(new Dimension(400, 250));
		// Object[][] result
		// =getSelect(ItemDao.selectItemByNum(codeJTF.getText().trim().toString()));
		leftJT = new JTable();
		leftJSP.setViewportView(leftJT);

		startJL = new JLabel();
		startJL.setText("�˵��б�:");
		leftJP.add(startJL, BorderLayout.NORTH);
		leftJP.add(leftJSP, BorderLayout.CENTER);
		jsplitpane.setLeftComponent(leftJP);
		// �Ҳ����
		rightJP = new JPanel();
		rightJP.setLayout(new BorderLayout());
		designJL = new JLabel("�����б�");
		rightJP.add(designJL, BorderLayout.NORTH);
		rightJSP = new JScrollPane();
		rightJSP.setPreferredSize(new Dimension(200, 400));

		Object[][] result_form = getSelectDesk(FormDao.selectForm());
		rightJT = new JTable(result_form, colunmright);
		rightJSP.setViewportView(rightJT);
		// rightJT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		rightJT.setDefaultRenderer(Object.class, r);
		rightJP.add(rightJSP, BorderLayout.CENTER);
		jsplitpane.setRightComponent(rightJP);
		// �м���Ϣ�����centerJP
		centerJP = new JPanel();
		FlowLayout centerFL = new FlowLayout();
		centerFL.setHgap(6);
		centerJP.setLayout(centerFL);
		deskIDJL = new JLabel("̨�ţ�");
		deskJCB = new JComboBox();
		List<tb_dest> list = DestDao.selectDesk();
		for (int i = 0; i < list.size(); i++) {
			tb_dest td = list.get(i);
			deskJCB.addItem(td.getNum());
		}
		deskJCB.setMaximumRowCount(5);// �����������������
		thingJL = new JLabel("��Ʒ:(");

		buttonGroup = new ButtonGroup();
		groupJP = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		groupJP.setLayout(flowLayout);
		JRB1 = new JRadioButton();
		JRB1.setText("���  /");
		buttonGroup.add(JRB1);

		JRB2 = new JRadioButton();

		JRB2.setText("������   )");
		JRB2.setSelected(true);
		buttonGroup.add(JRB2);
		codeJTF = new JTextField(5);
		eatnameJL = new JLabel("��Ʒ���ƣ�");
		eatnameJTF = new JTextField(5);
		eatnameJTF.setEditable(false);
		unitJL = new JLabel("��λ:");
		unitJTF = new JTextField(5);
		unitJTF.setEditable(false);
		numberJL = new JLabel("������");
		numberJTF = new JTextField(5);
		numberJTF.setHorizontalAlignment(SwingConstants.CENTER);
		// startJB, designJB, cennelJB
		startJB = new JButton("����");
		designJB = new JButton("���");
		addJB = new JButton("�Ӳ�");
		cennelJB = new JButton("ȡ��");
		centerJP.add(deskIDJL);
		centerJP.add(deskJCB);
		centerJP.add(thingJL);
		centerJP.add(JRB1);
		centerJP.add(JRB2);
		centerJP.add(codeJTF);
		centerJP.add(eatnameJL);
		centerJP.add(eatnameJTF);
		centerJP.add(unitJL);
		centerJP.add(unitJTF);
		centerJP.add(numberJL);
		centerJP.add(numberJTF);
		centerJP.add(designJB);
		centerJP.add(addJB);
		centerJP.add(startJB);
		centerJP.add(cennelJB);
		centerJP.setBorder(new TitledBorder(""));
		// ������Ϣ������orderJP
		orderJP = new JPanel();
		GridLayout orderGL = new GridLayout(4, 3);
		orderGL.setHgap(5);
		orderGL.setVgap(5);
		orderJP.setLayout(orderGL);
		JL1 = new JLabel("Ԫ");
		JL2 = new JLabel("Ԫ");
		JL3 = new JLabel("Ԫ");
		JL4 = new JLabel();
		ordermoneyJL = new JLabel("���ѽ�");
		ordermoneyJL.setHorizontalAlignment(SwingConstants.CENTER);
		ordermoneyJTF = new JTextField();
		ordermoneyJTF.setEditable(true);
		payJL = new JLabel("ʵ�ս�");
		payJL.setHorizontalAlignment(SwingConstants.CENTER);
		payJTF = new JTextField();
		payJTF.setEditable(true);
		orderJB = new JButton("����");
		ImageIcon moneyimg = new ImageIcon("img/rmb.jpg");
		moneyimg.setImage(moneyimg.getImage().getScaledInstance(MainView.WIDTH,
				MainView.HEIGHT, Image.SCALE_DEFAULT));
		moneyJL = new JLabel(moneyimg);
		cashJL = new JLabel("�����");
		cashJL.setHorizontalAlignment(SwingConstants.CENTER);
		cashJTF = new JTextField();
		cashJTF.setEditable(false);
		orderJP.add(ordermoneyJL);
		orderJP.add(ordermoneyJTF);
		orderJP.add(JL1);
		orderJP.add(payJL);
		orderJP.add(payJTF);
		orderJP.add(JL2);
		orderJP.add(moneyJL);
		orderJP.add(orderJB);
		orderJP.add(JL4);
		orderJP.add(cashJL);
		orderJP.add(cashJTF);
		orderJP.add(JL3);
		orderJP.setBorder(new TitledBorder("����"));
		// ���½ǰ�ť���
		// menuJB, sortJB,
		// deskJB, dayJB, monthJB, yearJB, updatepwdJB, usermanageJB, closeJB;
		ImageIcon day_img = new ImageIcon("img/day.png");
		ImageIcon month_img = new ImageIcon("img/month.png");
		ImageIcon exit_img = new ImageIcon("img/exit.jpg");
		// ImageIcon top_img = new ImageIcon("img/day.png");
		ImageIcon sort_img = new ImageIcon("img/sort.jpg");// ��ϵ����
		ImageIcon menu_img = new ImageIcon("img/menu.jpg");// ��Ʒ����
		ImageIcon desk_img = new ImageIcon("img/desk.jpg");
		ImageIcon password_img = new ImageIcon("img/password.jpg");
		ImageIcon user_img = new ImageIcon("img/user.jpg");
		ImageIcon year_img = new ImageIcon("img/year.png");
		buttonJP = new JPanel();
		GridLayout buttonGL = new GridLayout(3, 3);
		buttonGL.setHgap(2);
		buttonGL.setVgap(2);
		buttonJP.setLayout(buttonGL);
		menuJB = new JButton(menu_img);
		dayJB = new JButton(day_img);
		updatepwdJB = new JButton(password_img);
		sortJB = new JButton(sort_img);
		monthJB = new JButton(month_img);
		usermanageJB = new JButton(user_img);
		deskJB = new JButton(desk_img);
		yearJB = new JButton(year_img);
		closeJB = new JButton(exit_img);
		buttonJP.add(menuJB);
		buttonJP.add(dayJB);
		buttonJP.add(updatepwdJB);
		buttonJP.add(sortJB);
		buttonJP.add(monthJB);
		buttonJP.add(usermanageJB);
		buttonJP.add(deskJB);
		buttonJP.add(yearJB);
		buttonJP.add(closeJB);
		buttonJP.setBorder(new TitledBorder("������"));
		// ʱ��timeJP
		java.util.Calendar c = java.util.Calendar.getInstance();
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String st = format.format(c.getTime());
		todayJL = new JLabel(st);
		todayJL.setFont(new Font("����", Font.BOLD, 14));
		todayJL.setHorizontalAlignment(SwingConstants.CENTER);
		timeJP = new JPanel();
		timeJP.setLayout(new GridLayout(4, 1));
		timeJL = new JLabel();
		timeJL.setFont(new Font("����", Font.BOLD, 14));
		timeJL.setForeground(new Color(255, 0, 0));
		timeJL.setHorizontalAlignment(SwingConstants.CENTER);
		new Time().start();
		workIDJL = new JLabel("Ա����ţ�");
		workIDJL.setHorizontalAlignment(SwingConstants.CENTER);
		wokerJL = new JLabel("123");
		wokerJL.setHorizontalAlignment(SwingConstants.CENTER);
		timeJP.add(todayJL);
		timeJP.add(timeJL);
		timeJP.add(workIDJL);
		timeJP.add(wokerJL);
		timeJP.setBorder(new TitledBorder("ϵͳ��Ϣ"));
		// ������������� ��ӵ�
		bottomJP = new JPanel();
		bottomJP.setLayout(new GridLayout(1, 3));
		bottomJP.add(timeJP);
		bottomJP.add(orderJP);
		bottomJP.add(buttonJP);
		// �м���Ϣ��������������������������
		totalJP = new JPanel();
		totalJP.setLayout(new BorderLayout());
		totalJP.add(centerJP, BorderLayout.NORTH);
		totalJP.add(bottomJP, BorderLayout.CENTER);
		this.add(topJP, BorderLayout.NORTH);

		this.add(totalJP, BorderLayout.SOUTH);
		this.setResizable(false);
		this.setVisible(true);
		menuJB.addActionListener(new MenuAction());
		sortJB.addActionListener(new SortAction());
		deskJB.addActionListener(new DeskAction());
		closeJB.addActionListener(new CloseAction());
		dayJB.addActionListener(new DayOrderAction());
		monthJB.addActionListener(new MonthOrderAction());
		yearJB.addActionListener(new YearOrderAction());
		// ������������ע��
		codeJTF.addActionListener(new SelMenuAction());
		designJB.addActionListener(new StartAction());
		cennelJB.addActionListener(new DelAction());
		startJB.addActionListener(new StartDeskAction());
		addJB.addActionListener(new AddAction());
		rightJT.addMouseListener(new StartDeskTableAction());
		deskJCB.addActionListener(new JCBAction());
		orderJB.addActionListener(new OrderAction());
		payJTF.addKeyListener(new PayCashAction());

	}

	// �������Զ�����
	public static synchronized String nextCode() {
		String str = new SimpleDateFormat("yyMMdd").format(new Date());
		String format = new SimpleDateFormat("HHmmss").format(new Date());
		return str + format;
	}

	// ʱ�����
	class Time extends Thread {
		public void run() {
			while (true) {
				Date date = new Date();
				timeJL.setText(date.toString().substring(11, 19));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/* �����������ʵ�� */
	// ��ϵ������
	class SortAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new SortManage();
		}
	}

	// ��Ʒ������
	class MenuAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new MenuManange();

		}
	}

	// ̨�ż�����
	class DeskAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new DeskManage();

		}
	}

	// �ս��˱���
	class DayOrderAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new DayOrder();

		}
	}

	// �½��˱���
	class MonthOrderAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new MonthOrder();

		}
	}

	// ����˱���
	class YearOrderAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new YearOrder();

		}
	}

	// ��ѯ��Ʒ����ʵ�֣�codeJTF ��������
	class SelMenuAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JRB1.isSelected()) {
				String num = codeJTF.getText().trim();
				if (num.length() != 0) {
					List<tb_menu> list = MenuDao.selectMenuByNum(num);
					for (int i = 0; i < list.size(); i++) {
						tb_menu tm = list.get(i);
						eatnameJTF.setText(tm.getName());
						unitJTF.setText(tm.getUnit());
					}
					if (numberJTF.getText().trim().length() == 0) {
						numberJTF.setText("1");
					}
					numberJTF.addFocusListener(new NumberAction());
				} else {
					eatnameJTF.setText("");
					unitJTF.setText("");
				}
			}
			if (JRB2.isSelected()) {
				String code = codeJTF.getText().trim();
				if (code.length() != 0) {
					List<tb_menu> list = MenuDao.selectMenuByCode(code);
					for (int i = 0; i < list.size(); i++) {
						tb_menu tm = list.get(i);
						eatnameJTF.setText(tm.getName());
						unitJTF.setText(tm.getUnit());
					}
					if (numberJTF.getText().trim().length() == 0) {
						numberJTF.setText("1");
					}
					numberJTF.addFocusListener(new NumberAction());
				} else {
					eatnameJTF.setText("");
					unitJTF.setText("");
				}
			}

		}
	}

	// ������ȡ�����¼�
	class NumberAction implements FocusListener {
		public void focusGained(FocusEvent e) {
			// ��ȡ����
			if (numberJTF.getText().length() != 0) {
				numberJTF.setText("");
			}
		}

		public void focusLost(FocusEvent e) {
			// ʧȥ����
			if (numberJTF.getText().trim().length() == 0) {
				numberJTF.setText("1");
			}
		}

	}

	// ʵ�ս�������� ʵ��ʵʱ���
	class PayCashAction extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();

			if (k == KeyEvent.VK_ENTER) {
				String p = payJTF.getText().toString().trim();
				String o = ordermoneyJTF.getText().toString().trim();
				String t = String.valueOf((Double.valueOf(p) - Double
						.valueOf(o)));
				cashJTF.setText(t);

			}
		}
	}

	// ��̨����ʵ��
	class StartDeskAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Double money = 0.0;
			List<tb_order_form> list = FormDao.selectFormByDeskNum(deskJCB
					.getSelectedItem().toString());
			for (int i = 0; i < list.size(); i++) {
				tb_order_form tof = list.get(i);
				money = tof.getMoney();

			}

			if (!FormDao.selectFormByDeskNum(
					deskJCB.getSelectedItem().toString()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "�������п��ˣ��뻻����");
				return;
			}

			String desknum = deskJCB.getSelectedItem().toString();
			num1 = String.valueOf(MainView.nextCode());
			String time = timeJL.getText().toString();
			// String str = new SimpleDateFormat("yy-MM-dd").format(new Date());
			Integer user_id = Integer.valueOf(wokerJL.getText().toString()
					.trim());
			int i = FormDao.insertForm(num1, desknum, time, money, user_id);
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "��̨�ɹ������ͣ�");
				Object[][] result_form = getSelectDesk(FormDao.selectForm());
				rightJT = new JTable(result_form, colunmright);
				rightJSP.setViewportView(rightJT);
				DefaultTableCellRenderer r = new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				rightJT.setDefaultRenderer(Object.class, r);
				rightJT.addMouseListener(new StartDeskTableAction());

				Object[][] result = getSelect(ItemDao
						.selectItemByOrder_Num(num1));
				leftJT = new JTable(result, columnleft);
				leftJSP.setViewportView(leftJT);
				DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				leftJT.setDefaultRenderer(Object.class, r1);
				designJB.setEnabled(true);
				addJB.setEnabled(false);

			}
		}

	}

	// ��˹���ʵ��
	class StartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JRB1.isSelected()) {

				Integer unitprice = 0;
				String menu_num = "";
				List<tb_menu> list = MenuDao.selectMenuByNum(codeJTF.getText()
						.trim());
				for (int i = 0; i < list.size(); i++) {
					tb_menu tm = list.get(i);
					menu_num = tm.getNum();
					unitprice = tm.getUnit_price();
				}
				String order_for_num = num1;
				Integer amount = Integer.valueOf(numberJTF.getText().trim());
				Integer total = (amount * unitprice);

				int i = ItemDao.insertItem(order_for_num, menu_num, amount,
						total);
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "�������");
					Object[][] result = getSelect(ItemDao
							.selectItemByOrder_Num(num1));
					leftJT = new JTable(result, columnleft);
					leftJSP.setViewportView(leftJT);
					DefaultTableCellRenderer r = new DefaultTableCellRenderer();
					r.setHorizontalAlignment(JLabel.CENTER);
					leftJT.setDefaultRenderer(Object.class, r);
					rightJT.addMouseListener(new StartDeskTableAction());
					ordermoneyJTF.setText("");
				}
			}
			if (JRB2.isSelected()) {
				// List<tb_order_item>list
				// =ItemDao.selectItemByCode(codeJTF.getText().toString().trim());
				Integer unitprice = 0;
				String menu_num = "";
				List<tb_menu> list1 = MenuDao.selectMenuByCode(codeJTF
						.getText().trim());
				for (int i = 0; i < list1.size(); i++) {
					tb_menu tm = list1.get(i);
					menu_num = tm.getNum();
					unitprice = tm.getUnit_price();
				}
				String order_for_num = num1;
				Integer amount = Integer.valueOf(numberJTF.getText().trim());
				Integer total = (amount * unitprice);

				int i = ItemDao.insertItem(order_for_num, menu_num, amount,
						total);
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "�������");
					Object[][] result = getSelect(ItemDao
							.selectItemByOrder_Num(num1));
					leftJT = new JTable(result, columnleft);
					leftJSP.setViewportView(leftJT);
					DefaultTableCellRenderer r = new DefaultTableCellRenderer();
					r.setHorizontalAlignment(JLabel.CENTER);
					leftJT.setDefaultRenderer(Object.class, r);
					rightJT.addMouseListener(new StartDeskTableAction());
					ordermoneyJTF.setText("");
				}
			}
		}
	}

	// �Ӳ˰�ť ������ʵ��
	class AddAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (JRB1.isSelected()) {
				Integer unitprice = 0;
				String menu_num = "";
				List<tb_menu> list = MenuDao.selectMenuByNum(codeJTF.getText()
						.trim());
				for (int i = 0; i < list.size(); i++) {
					tb_menu tm = list.get(i);
					menu_num = tm.getNum();
					unitprice = tm.getUnit_price();
				}
				String order_for_num = order_num_static;
				Integer amount = Integer.valueOf(numberJTF.getText().trim());
				Integer total = (amount * unitprice);

				int i = ItemDao.insertItem(order_for_num, menu_num, amount,
						total);
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "�������");
					Object[][] result = getSelect(ItemDao
							.selectItemByOrder_Num(order_for_num));
					leftJT = new JTable(result, columnleft);
					leftJSP.setViewportView(leftJT);
					DefaultTableCellRenderer r = new DefaultTableCellRenderer();
					r.setHorizontalAlignment(JLabel.CENTER);
					leftJT.setDefaultRenderer(Object.class, r);
					rightJT.addMouseListener(new StartDeskTableAction());
					ordermoneyJTF.setText("");
				}
			 }
			if (JRB2.isSelected()) {
				System.out.println(order_num_static);
				// if
				// (!ItemDao.selectItemByOrder_Num(order_num_static).isEmpty())
				// {

				Integer unitprice = 0;
				String menu_num = "";
				List<tb_menu> list1 = MenuDao.selectMenuByCode(codeJTF
						.getText().trim());
				for (int i = 0; i < list1.size(); i++) {
					tb_menu tm = list1.get(i);
					menu_num = tm.getNum();
					unitprice = tm.getUnit_price();
				}
				String order_for_num = order_num_static;
				Integer amount = Integer.valueOf(numberJTF.getText().trim());
				Integer total = (amount * unitprice);

				int i = ItemDao.insertItem(order_for_num, menu_num, amount,
						total);
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "������ͣ�����");
					Object[][] result = getSelect(ItemDao
							.selectItemByOrder_Num(order_num_static));
					leftJT = new JTable(result, columnleft);
					leftJSP.setViewportView(leftJT);
					DefaultTableCellRenderer r = new DefaultTableCellRenderer();
					r.setHorizontalAlignment(JLabel.CENTER);
					leftJT.setDefaultRenderer(Object.class, r);
					rightJT.addMouseListener(new StartDeskTableAction());
					ordermoneyJTF.setText("");
				}

			}
			// }
		}
	}

	// ������������
	class StartDeskTableAction extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int row = rightJT.getSelectedRow();
			order_num_static = rightJT.getValueAt(row, 0).toString().trim();
			String desknum = rightJT.getValueAt(row, 1).toString().trim();
			deskJCB.setSelectedItem(desknum);
			Object[][] result = getSelect(ItemDao
					.selectItemByOrder_Num(order_num_static));
			leftJT = new JTable(result, columnleft);
			leftJSP.setViewportView(leftJT);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			leftJT.setDefaultRenderer(Object.class, r);
			designJB.setEnabled(false);
			addJB.setEnabled(true);
			/*
			 * ��ȡ������в˵����� ѭ������������ӵĹ���
			 */
			money1 = 0.0;
			int count = leftJT.getRowCount();
			for (int i = 0; i < count; i++) {

				money1 += Double.valueOf(leftJT.getValueAt(i, 5).toString());

			}
			String ordermoney = money1.toString();
			ordermoneyJTF.setText(ordermoney);
			payJTF.setText("");
			cashJTF.setText("");
		}
	}

	// �����˵�������
	class JCBAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List<tb_dest> list = DestDao.selectDesk();
			for (int i = 0; i < list.size(); i++) {
				tb_dest td = list.get(i);
				deskJCB.addItem(td.getNum());
			}
			for(int i =0;i<rightJT.getRowCount();i++){
				if(rightJT.getValueAt(i, 1).toString().trim().equals(deskJCB.getSelectedItem())){
					rightJT.getSelectionModel().setSelectionInterval(i,i);
				}
			}
		}
	}

	// ���˼�����ʵ��
	class OrderAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (ordermoneyJTF.getText().isEmpty() || payJTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "������ʵ�ս��");
				return;
			}
			if (Double.valueOf(ordermoneyJTF.getText().trim().toString())
					- Double.valueOf(payJTF.getText().trim().toString()) > 0) {
				JOptionPane.showMessageDialog(null, "ʵ�ս����Ŀ����ȷ����������д");
				payJTF.setText("");
				return;
			}

			int row = rightJT.getSelectedRow();
			String num = rightJT.getValueAt(row, 0).toString().trim();
			int i = FormDao.updateForm(num, money1);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "�ѽ��˳ɹ�����ӭ�´ι��٣�~");
				Object[][] result_form = getSelectDesk(FormDao.selectForm());
				rightJT = new JTable(result_form, colunmright);
				rightJSP.setViewportView(rightJT);
				DefaultTableCellRenderer r = new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				rightJT.setDefaultRenderer(Object.class, r);
				rightJT.addMouseListener(new StartDeskTableAction());
				// ==============���˺���߱���������
				Object[][] result_item = {};
				leftJT = new JTable(result_item, columnleft);
				leftJSP.setViewportView(leftJT);
				DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
				r.setHorizontalAlignment(JLabel.CENTER);
				leftJT.setDefaultRenderer(Object.class, r1);
			}

		}
	}

	// ȡ��������ʵ��
	class DelAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int row = leftJT.getSelectedRow();

			String menu_num = leftJT.getValueAt(row, 1).toString().trim();
			int i = ItemDao.deleteItem(menu_num, order_num_static);
			if (i != 0) {
				JOptionPane.showMessageDialog(null, "��ȡ����");
			}
			Object[][] result = getSelect(ItemDao
					.selectItemByOrder_Num(order_num_static));
			leftJT = new JTable(result, columnleft);
			leftJSP.setViewportView(leftJT);
			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			leftJT.setDefaultRenderer(Object.class, r);
			rightJT.addMouseListener(new StartDeskTableAction());
			ordermoneyJTF.setText("");
		}
	}

	// �˳�ϵͳ
	class CloseAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}
	}

	public static void main(String[] args) {
		new MainView();

	}

}
