package com.flipturnapps.bukkitguigit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.flipturnapps.kevinLibrary.newgui.KJTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;


public class GitFrame extends JFrame {

	private JPanel contentPane;
	private StepPanel panel_step1;
	private StepPanel panel_step2;
	private StepPanel panel_step3;
	private StepPanel panel_step4;
	private StepPanel panel_step5;
	private StepPanel panel_step6;
	private GitButton btnFindRepo;
	private GitButton btnCloneNew;
	private GitButton btnPullRemote;
	private JComboBox comboBox;
	private GitButton btnGetChoices;
	private GitButton btnCheckout;
	private GitButton btnPull;
	private KJTextArea textArea;
	private JPanel panel;
	private StepPanel panel_step7;
	private StepPanel panel_step8;
	private JButton btnRunTheServer;
	private JButton btnStageAllChanges;
	private JButton btnCommitChanges;
	private JTextField textField;

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
		Executor exe = new Executor(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
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
		
		btnCloneNew = new GitButton(GitButton.TXT_CLONE_NEW,1, exe);
		panel_step1.add(btnCloneNew);
		panel_step2 = new StepPanel(2,"Pull");
		panel_top_box.add(panel_step2);
		
		btnPullRemote = new GitButton(GitButton.TXT_PULL,2, exe);
		panel_step2.add(btnPullRemote);
		panel_step3 = new StepPanel(3,"Choose Branch");
		panel_top_box.add(panel_step3);
		
		btnGetChoices = new GitButton(GitButton.TXT_GET_CHOICES,3, exe);
		panel_step3.add(btnGetChoices);
		
		comboBox = new JComboBox();
		panel_step3.add(comboBox);
		
		btnCheckout = new GitButton(GitButton.TXT_CHECKOUT,4, exe);
		panel_step3.add(btnCheckout);
		panel_step4 = new StepPanel(4,"Pull Again");
		panel_top_box.add(panel_step4);
		
		btnPull = new GitButton(GitButton.TXT_PULL,5, exe);
		panel_step4.add(btnPull);
		panel_step5 = new StepPanel(5,"Run");
		panel_top_box.add(panel_step5);
		
		btnRunTheServer = new JButton("Run The Server");
		panel_step5.add(btnRunTheServer);
		panel_step6 = new StepPanel(6,"Stage Changes");
		panel_top_box.add(panel_step6);
		
		btnStageAllChanges = new JButton("Stage ALL Changes");
		panel_step6.add(btnStageAllChanges);
		panel_step7 = new StepPanel(7,"Commit Changes");
		panel_top_box.add(panel_step7);
		
		textField = new JTextField();
		panel_step7.add(textField);
		textField.setColumns(10);
		
		btnCommitChanges = new JButton("Commit Changes");
		panel_step7.add(btnCommitChanges);
		panel_step8 = new StepPanel(8,"Push");
		panel_top_box.add(panel_step8);
		
		JPanel panel_bot = new JPanel();
		contentPane.add(panel_bot);
		panel_bot.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel_bot.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		
		
		textArea = new KJTextArea();
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

}
