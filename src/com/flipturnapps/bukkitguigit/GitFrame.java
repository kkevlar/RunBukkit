package com.flipturnapps.bukkitguigit;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;

public class GitFrame extends JFrame {

	private JPanel contentPane;
	private StepPanel panel_step1;
	private StepPanel panel_step2;
	private StepPanel panel_step3;
	private StepPanel panel_step4;
	private StepPanel panel_step5;
	private StepPanel panel_step6;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_top = new JPanel();
		contentPane.add(panel_top);
		panel_top.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_top_box = new JPanel();
		panel_top.add(panel_top_box, BorderLayout.NORTH);
		panel_top_box.setLayout(new BoxLayout(panel_top_box, BoxLayout.Y_AXIS));
		
		panel_step1 = new StepPanel(1,"Initialize");
		panel_top_box.add(panel_step1);
		panel_step2 = new StepPanel(2,"Initialize");
		panel_top_box.add(panel_step2);
		panel_step3 = new StepPanel(3,"Initialize");
		panel_top_box.add(panel_step3);
		panel_step4 = new StepPanel(4,"Initialize");
		panel_top_box.add(panel_step4);
		panel_step5 = new StepPanel(5,"Initialize");
		panel_top_box.add(panel_step5);
		panel_step6 = new StepPanel(6,"Initialize");
		panel_top_box.add(panel_step6);
		
		JPanel panel_bot = new JPanel();
		contentPane.add(panel_bot);
		panel_bot.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_bot.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		scrollPane.setViewportView(textArea);
	}

}
