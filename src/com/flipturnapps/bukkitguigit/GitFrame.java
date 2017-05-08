package com.flipturnapps.bukkitguigit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.flipturnapps.kevinLibrary.helper.FileHelper;
import com.flipturnapps.kevinLibrary.helper.PropertyManager;
import com.flipturnapps.kevinLibrary.newgui.KJTextArea;
import com.flipturnapps.kevinLibrary.newgui.PropertyTextField;


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
	private PropertyTextField textField_username;
	private JPasswordField passwordField;
	private GitButton btnPush;
	private JLabel lblUn;
	private JLabel lblPass;
	private GitButton btnStop;
	private JCheckBox chckbxSavepass;
	private GitPropertyManager properties;
	private JButton btnReset;
	private JPanel panel_1;
	private JLabel lblRemoteAddress;
	private PropertyTextField textField_remoteAddr;
	private JPanel panel_2;
	private JLabel label;
	private PropertyTextField propertyTextField;
	private JLabel lblEmail;
	private JLabel lblName;
	private PropertyTextField textField_name;
	private PropertyTextField textField_email;
	private GitButton btnSetName;
	private GitButton btnSetemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
				File thisDir = null;
				
				for(File f : thisDir.listFiles())
				{
					if(f.getName().equals("properties.zzz"))
					{
						f.renameTo(new File(FileHelper.fileInDir(FileHelper.getAppDataDir("flipturnapps", "Gitbukkit"), "properties.zzz")));
					}
				}
				}
				catch (Exception ex)
				{
					
				}
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
		
		panel_1 = new JPanel();
		panel_top_box.add(panel_1);
		
		lblRemoteAddress = new JLabel("Remote Address: ");
		panel_1.add(lblRemoteAddress);
		
		textField_remoteAddr = new PropertyTextField(properties, GitPropertyManager.PROPKEY_REMOTE);
		panel_1.add(textField_remoteAddr);
		textField_remoteAddr.setColumns(30);
		
		panel_2 = new JPanel();
		panel_top_box.add(panel_2);
		
		lblName = new JLabel("Name: ");
		panel_2.add(lblName);
		
		textField_name = new PropertyTextField(properties, GitPropertyManager.PROPKEY_NAME);
		panel_2.add(textField_name);
		textField_name.setColumns(15);
		
		lblEmail = new JLabel("Email: ");
		panel_2.add(lblEmail);
		
		textField_email = new PropertyTextField(properties, GitPropertyManager.PROPKEY_EMAIL);
		panel_2.add(textField_email);
		textField_email.setColumns(15);
		
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
		
		btnSetName = new GitButton(GitButton.TXT_SET_NAME,7,exe);
		panel_step6.add(btnSetName);
		
		btnSetemail = new GitButton(GitButton.TXT_SET_EMAIL,8,exe);
		panel_step6.add(btnSetemail);
		
		btnStageAllChanges = new GitButton(GitButton.TXT_STAGE_ALL_CHANGES,9,exe);
		panel_step6.add(btnStageAllChanges);
		
		panel_step7 = new StepPanel(7,GitButton.TXT_COMMIT_CHANGES);
		panel_top_box.add(panel_step7);
		
		textField_commitmessage = new JTextField();
		panel_step7.add(textField_commitmessage);
		textField_commitmessage.setColumns(20);
		
		btnCommitChanges = new GitButton(GitButton.TXT_COMMIT_CHANGES,10,exe);
		panel_step7.add(btnCommitChanges);
		panel_step8 = new StepPanel(8,GitButton.TXT_PUSH);
		panel_top_box.add(panel_step8);
		
		lblUn = new JLabel("UN:");
		panel_step8.add(lblUn);
		
		textField_username = new PropertyTextField(properties,GitPropertyManager.PROPKEY_USERNAME);
		panel_step8.add(textField_username);
		textField_username.setColumns(6);
		
		lblPass = new JLabel("Pass:");
		panel_step8.add(lblPass);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(6);
		String presetPass = properties.getProperty("password");
		if(presetPass != null)
			passwordField.setText(presetPass);
		panel_step8.add(passwordField);
		
		chckbxSavepass = new JCheckBox("SavePass?");
		if(properties.getProperty("save-pass?") != null)
		{
			boolean check = Boolean.parseBoolean(properties.getProperty("save-pass?"));
			chckbxSavepass.setSelected(check);
		}
		panel_step8.add(chckbxSavepass);
		
		btnPush = new GitButton(GitButton.TXT_PUSH,11,exe);
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

SimpleDateFormat sdfDate = new SimpleDateFormat("yy-MM-dd-HHmm");//dd/MM/yyyy
Date now = new Date();
String strDate = sdfDate.format(now);
this.textField_commitmessage.setText(strDate);
	}

	public String getCommitMessageText() {
		return textField_commitmessage.getText();
	}

	public String getRemoteLocationText() {
		return this.textField_remoteAddr.getText();
	}

	public String getUsernameText() {
		return textField_username.getText();
	}
	public String getPasswordText() {
		return this.passwordField.getText();
	}
	public String getRemoteText() {
		return textField_remoteAddr.getText();
	}
	public boolean getShouldSavePassword() {
		return this.chckbxSavepass.isSelected();
	}

	public String getNameText() {
		return textField_name.getText();
	}
	
	public String getEmailText() {
		return textField_email.getText();
	}
}
