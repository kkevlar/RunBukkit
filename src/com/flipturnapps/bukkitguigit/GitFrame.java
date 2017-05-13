package com.flipturnapps.bukkitguigit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
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
	private Image image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {



		EventQueue.invokeLater(new Runnable() {
			public void run() {

				GitPropertyManager props = null;
				if(!new GitPropertyManager().getSaveFile().exists())
				{
					PropWalker walker = new PropWalker();
					walker.walk();

					if(walker.getPathFound() != null)
					{
						try {
							props = new GitPropertyManager(walker.getPathFound());
						} catch (IOException e1) {

						}
						try {
							props.write();
						} catch (Exception e) {

						}

						new File(walker.getPathFound()).delete();
					}
					else
						props = new GitPropertyManager();
				}
				else
					props = new GitPropertyManager();


				BufferedImage bgImage = null;
				try {
					bgImage = getBgImage();
				} catch (MalformedURLException e1) {
					
				}
				
				BufferedImage icon = null;
				try {
					icon = getIconImage();
				} catch (MalformedURLException e1) {
					
				}
				

				try {
					GitFrame frame = new GitFrame(props,bgImage);
					frame.setIconImage(icon);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			private BufferedImage getImage(URL url, File imageFile) {
				BufferedImage bgImage = null;
				
				if(imageFile.exists())
					try {
						bgImage = ImageIO.read(imageFile);
					} catch (IOException e) {

					}
				if(bgImage == null)
				{
					try
					{
						
						bgImage = ImageIO.read(url);
						ImageIO.write(bgImage, "png", imageFile);
					}
					catch(Exception ex)
					{


					}
				}
				return bgImage;
			}
			private BufferedImage getBgImage() throws MalformedURLException {
				URL url = new URL("https://docs.google.com/uc?id=0B5_wYgbEk-GZN2tac3lBSVh6QVk&export=download");
				File imageFile = new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(),"background.png"));
				
				return getImage(url,imageFile);
			}
			private BufferedImage getIconImage() throws MalformedURLException {
				
				File imageFile = new File(FileHelper.fileInDir(GitPropertyManager.getDataDir(),"icon.png"));
				URL freedGnomeUrl = new URL("https://drive.google.com/uc?export=download&id=0B5_wYgbEk-GZZ0tzRzVQRWdoMDQ");
				return getImage(freedGnomeUrl,imageFile);
			}
		});
	}

	/**
	 * Create the frame.
	 * @param props 
	 */
	public GitFrame(GitPropertyManager props,Image mcI) {


		properties = props;
		this.image = mcI;
		try {
			props.read();
		} catch (IOException e) {

		}

		Executor exe = new Executor(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);

		contentPane = new JPanel()
		{
			public void paintComponent (Graphics g)
			{
				//g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),null);
				g.drawImage(image, -350,-100,null);
				g.setColor(new Color(255,255,255,150));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				super.paintComponent(g);
			}
		};

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBackground(new Color(0,0,0,0));

		JPanel panel_top_box = new JPanel();
		panel_top_box.setBackground(new Color(0,0,0,0));
		contentPane.add(panel_top_box);
		panel_top_box.setLayout(new BoxLayout(panel_top_box, BoxLayout.Y_AXIS));

		panel_1 = new JPanel();
		panel_1.setBackground(new Color (0,0,0,0));
		panel_top_box.add(panel_1);

		lblRemoteAddress = new JLabel("Remote Address: ");
		panel_1.add(lblRemoteAddress);

		textField_remoteAddr = new PropertyTextField(properties, GitPropertyManager.PROPKEY_REMOTE);
		panel_1.add(textField_remoteAddr);
		textField_remoteAddr.setColumns(30);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color (0,0,0,0));
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
		chckbxSavepass.setBackground(new Color (0,0,0,0));
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
