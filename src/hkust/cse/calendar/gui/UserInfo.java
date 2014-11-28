package hkust.cse.calendar.gui;

import hkust.cse.calendar.unit.user.User;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserInfo extends JFrame {

	public UserInfo(User user) {
		
		setTitle("User Info");
		
		Container contentPane;
		contentPane = getContentPane();
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		

		Box box = Box.createVerticalBox();
		JLabel label1 = new JLabel("User Name : ");
		label1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		box.add(label1);

		JLabel label2 = new JLabel("First Name : ");
		label2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		box.add(label2);

		JLabel label3 = new JLabel("Last Name : ");
		label3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		box.add(label3);

		JLabel label4 = new JLabel("Email : ");
		label3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		box.add(label4);
		add(box);
	
		Box box2 = Box.createHorizontalBox();
		JLabel label5 = new JLabel(user.ID());
		label5.setAlignmentX(Component.LEFT_ALIGNMENT);
		box2.add(label5);
		
		JLabel label6 = new JLabel(user.getFirstName());
		label5.setAlignmentX(Component.LEFT_ALIGNMENT);
		box2.add(label6);
		
		JLabel label7 = new JLabel(user.getLastName());
		label5.setAlignmentX(Component.LEFT_ALIGNMENT);
		box2.add(label7);
		
		JLabel label8 = new JLabel(user.getEmail());
		label5.setAlignmentX(Component.LEFT_ALIGNMENT);
		box2.add(label8);
		add(box2);
		/*
		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("User Name		  :" + user.ID() , SwingConstants.RIGHT));
		JLabel userName = new JLabel();
		namePanel.add(userName);
		top.add(namePanel);
		
		
		JPanel firstnamePanel = new JPanel();
		firstnamePanel.add(new JLabel("First Name		 :" + user.getFirstName(), SwingConstants.RIGHT));
		JLabel firstname = new JLabel();
		firstnamePanel.add(firstname);
		top.add(firstnamePanel);
		
		JPanel lastnamePanel = new JPanel();
		lastnamePanel.add(new JLabel("Last Name		  :" + user.getLastName(), SwingConstants.RIGHT));
		top.add(lastnamePanel);

		JPanel emailPanel = new JPanel();
		emailPanel.add(new JLabel("Email			  :" + user.getEmail(), SwingConstants.RIGHT));
		top.add(emailPanel);
		
		contentPane.add("North", top);
		
		JPanel butPanel = new JPanel();
		butPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	
		
		contentPane.add("South", butPanel);
			*/
		contentPane.add("West", box);
		contentPane.add("East", box2);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		
		//userName.setText(user.ID());
		//firstname.setText(user.getFirstName());
	}
	

}
