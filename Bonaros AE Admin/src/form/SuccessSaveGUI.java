package form;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SuccessSaveGUI extends JFrame {
	
	private JButton okButton = new JButton("ΕΝΤΑΞΕΙ");
	private JFrame powerFrame=  new JFrame("Επιτυχής αποθήκευση");
	private JPanel panel = new JPanel();
	private JLabel message = new JLabel("Οι αλλαγές σας έχουν αποθηκευτεί ! ");
	

	public SuccessSaveGUI() {
		
		okButton.setPreferredSize(new Dimension(10,30));
		okButton.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		
		//Στοίχηση κουμπιών
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		//Στοίχηση ετικέτας
		message.setAlignmentX(Component.CENTER_ALIGNMENT);
		

		Box labelbox = Box.createVerticalBox();
		
		
		labelbox.setPreferredSize(new Dimension(280,70));
		labelbox.add(message);
		labelbox.add(Box.createRigidArea(new Dimension(20,20)));
		labelbox.add(okButton);
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				powerFrame.dispose();
				
			}
			
		});
		
		
		
		panel.add(labelbox);

		panel.setPreferredSize(new Dimension(400,100));
		
		//ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("bonaros.jpg"));
		powerFrame.add(panel);
		//powerFrame.setIconImage(logo.getImage());
		powerFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        powerFrame.pack();
        powerFrame.setLocationRelativeTo(null);
        powerFrame.setVisible(true);
        powerFrame.setResizable(false);
        

		
	}




}
