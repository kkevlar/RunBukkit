package com.flipturnapps.bukkitguigit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.flipturnapps.kevinLibrary.helper.PropertyManager;
import com.flipturnapps.kevinLibrary.newgui.KJTextArea;


public class GitFrame extends JFrame {

	private JPanel contentPane;
	private StepPanel panel_step1;
	private StepPanel panel_step2;
	private StepPanel panel_step3;
	private StepPanel panel_step4;
	private StepPanel panel_step5;
	private StepPanel panel_step6;
	private GitButton btnFindRepo;
	private GitButton btnPullRemote;
	private JComboBox comboBox;
	private GitButton btnGetChoices;
	private GitButton btnCheckout;
	private GitButton btnPull;
	private KJTextArea textArea;
	private JPanel panel;
	private StepPanel panel_step7;
	private StepPanel panel_step8;
	private GitButton btnRunTheServer;
	private GitButton btnStageAllChanges;
	private GitButton btnCommitChanges;
	private JTextField textField_commitmessage;
	private JTextField textField_username;
	private JPasswordField passwordField;
	private GitButton btnPush;
	private JLabel lblUn;
	private JLabel lblPass;
	private GitButton btnStop;
	private JCheckBox chckbxSavepass;
	private GitPropertyManager properties;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GitFrame frame = new GitFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GitFrame() {
		guessCommitMessage();
		properties = new GitPropertyManager();
		try {
			properties.read();
		} catch (IOException e) {
			
		}
		
		Executor exe = new Executor(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_top_box = new JPanel();
		contentPane.add(panel_top_box);
		panel_top_box.setLayout(new BoxLayout(panel_top_box, BoxLayout.Y_AXIS));
		
		panel_step1 = new StepPanel(1,"Initialize");
		panel_top_box.add(panel_step1);
		
		btnFindRepo = new GitButton(GitButton.TXT_FIND_REPO,0, exe);
		panel_step1.add(btnFindRepo);
		
		panel_step2 = new StepPanel(2,"Pull");
		panel_top_box.add(panel_step2);
		
		btnReset = new GitButton(GitButton.TXT_RESET,1, exe);
		panel_step2.add(btnReset);
		
		btnPullRemote = new GitButton(GitButton.TXT_PULL,1, exe);
		panel_step2.add(btnPullRemote);
		panel_step3 = new StepPanel(3,"Choose Branch");
		panel_top_box.add(panel_step3);
		
		btnGetChoices = new GitButton(GitButton.TXT_GET_CHOICES,2, exe);
		panel_step3.add(btnGetChoices);
		
		comboBox = new JComboBox();
		panel_step3.add(comboBox);
		
		btnCheckout = new GitButton(GitButton.TXT_CHECKOUT,3, exe);
		panel_step3.add(btnCheckout);
		panel_step4 = new StepPanel(4,"Pull Again");
		panel_top_box.add(panel_step4);
		
		btnPull = new GitButton(GitButton.TXT_PULL,4, exe);
		panel_step4.add(btnPull);
		panel_step5 = new StepPanel(5,"Run");
		panel_top_box.add(panel_step5);
		
		btnRunTheServer = new GitButton(GitButton.TXT_RUN_THE_SERVER,5,exe);
		panel_step5.add(btnRunTheServer);
		
		btnStop = new GitButton(GitButton.TXT_STOP, 6,exe);
		panel_step5.add(btnStop);
		panel_step6 = new StepPanel(6,"Stage Changes");
		panel_top_box.add(panel_step6);
		
		btnStageAllChanges = new GitButton(GitButton.TXT_STAGE_ALL_CHANGES,7,exe);
		panel_step6.add(btnStageAllChanges);
		panel_step7 = new StepPanel(7,GitButton.TXT_COMMIT_CHANGES);
		panel_top_box.add(panel_step7);
		
		textField_commitmessage = new JTextField();
		panel_step7.add(textField_commitmessage);
		textField_commitmessage.setColumns(10);
		
		btnCommitChanges = new GitButton(GitButton.TXT_COMMIT_CHANGES,8,exe);
		panel_step7.add(btnCommitChanges);
		panel_step8 = new StepPanel(8,GitButton.TXT_PUSH);
		panel_top_box.add(panel_step8);
		
		lblUn = new JLabel("UN:");
		panel_step8.add(lblUn);
		
		textField_username = new JTextField();
		panel_step8.add(textField_username);
		textField_username.setColumns(6);
		
		lblPass = new JLabel("Pass:");
		panel_step8.add(lblPass);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(6);
		panel_step8.add(passwordField);
		
		chckbxSavepass = new JCheckBox("SavePass?");
		panel_step8.add(chckbxSavepass);
		
		btnPush = new GitButton(GitButton.TXT_PUSH,10,exe);
		panel_step8.add(btnPush);
		
		JPanel panel_bot = new JPanel();
		contentPane.add(panel_bot);
		panel_bot.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel_bot.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		
		textArea = new KJTextArea();
		textArea.setEditable(false);
		panel.add(textArea);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		
		panel_bot.add(textArea.setUpDefault());
		textArea.autoScrollDown();
		
	}

	public KJTextArea getTextArea() {
		return textArea;
	}

	public void addToCombo(String substring) {
		if(!substring.contains(" "))
		comboBox.addItem(substring);
		
	}

	public String getComboChoice() {
		return comboBox.getSelectedItem().toString();
	}

	

	public PropertyManager getProperties() {
		return properties;
	}

	public void guessCommitMessage() 
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd--hhmm");
		System.out.println(cal.getTime());
		// Output "Wed Sep 26 14:23:28 EST 2012"

		String formatted = format1.format(cal.getTime());
		System.out.println(formatted);
		// Output "2012-09-26"

		try {
			System.out.println(format1.parse(formatted));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Output "Wed Sep 26 00:00:00 EST 2012"
	}

}
