package hkust.cse.calendar.gui;

import hkust.cse.calendar.unit.user.User;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class UserSettings extends JFrame implements ActionListener {

	private User mUser;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email;
	private JPasswordField password;
	private JPasswordField newpassword;
	private JButton button;
	private JButton closeButton;
	private JButton signupButton;
	private Boolean adminMode = false;
	private Boolean inspect = false;
	
	public UserSettings(User user) {
		this(user, false, false);
	}
	
	public UserSettings(User user, Boolean admin) {
		this(user, admin, false);
	}
	
	public UserSettings(User user, Boolean admin, Boolean inspect) {
		this.mUser = user;
		this.adminMode = admin;
		this.inspect = inspect;
		
		setTitle("Settings");
		
		Container contentPane;
		contentPane = getContentPane();
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		JPanel messPanel = new JPanel();
		messPanel.add(new JLabel("Please fill in your infomation below and tyoe password to confirm your changed."));
		top.add(messPanel);


		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("User Name:     "));
		JLabel userName = new JLabel();
		namePanel.add(userName);
		top.add(namePanel);
		
		if(!adminMode) {
		
			JPanel pwPanel = new JPanel();
			pwPanel.add(new JLabel("Password:       "));
			password = new JPasswordField(15);
			pwPanel.add(password);
			top.add(pwPanel);
		
		}
		
		JPanel firstnamePanel = new JPanel();
		firstnamePanel.add(new JLabel("First Name:     "));
		firstName = new JTextField(15);
		firstnamePanel.add(firstName);
		top.add(firstnamePanel);
		
		JPanel lastnamePanel = new JPanel();
		lastnamePanel.add(new JLabel("Last Name:     "));
		lastName = new JTextField(15);
		lastnamePanel.add(lastName);
		top.add(lastnamePanel);

		JPanel emailPanel = new JPanel();
		emailPanel.add(new JLabel("Email:              "));
		email = new JTextField(15);
		emailPanel.add(email);
		top.add(emailPanel);
		
		if(!inspect) {
			
			JPanel newpwPanel = new JPanel();
			newpwPanel.add(new JLabel("New Password:"));
			newpassword = new JPasswordField(15);
			newpwPanel.add(newpassword);
			top.add(newpwPanel);
			
		}
		
		contentPane.add("North", top);
		
		if(!inspect) {
			
			JPanel butPanel = new JPanel();
			butPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			button = new JButton("OK");
			button.addActionListener(this);
			butPanel.add(button);
			
			closeButton = new JButton("Cancel");
			closeButton.addActionListener(this);
			butPanel.add(closeButton);
			
			contentPane.add("South", butPanel);
		}
		
		if(inspect) {
			
			messPanel.hide();
			firstName.disable();
			lastName.disable();
			email.disable();
			
		}
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		email.setText(user.getEmail());
		userName.setText(user.ID());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == button) {
			
			if(!adminMode) {
				
				if(!mUser.Password().equals(password.getText())) {
					JOptionPane.showMessageDialog(this, "The Password is not matched to the original one.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
			
			mUser.setFirstName(firstName.getText());
			mUser.setLastName(lastName.getText());
			mUser.setEmail(email.getText());
			
			if(!newpassword.getText().equals(""))
				mUser.Password(newpassword.getText());
			
			JOptionPane.showMessageDialog(this, "The change is completed!",
					"OK", JOptionPane.INFORMATION_MESSAGE);
			
			setVisible(false);
			dispose();
		}
		
		else if(arg0.getSource() == closeButton) {
			
			setVisible(false);
			dispose();
		}
	}

}
