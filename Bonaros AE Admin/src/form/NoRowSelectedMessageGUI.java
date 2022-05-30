package form;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class NoRowSelectedMessageGUI {
	
	private JFrame powerFrame=  new JFrame("Διαγραφή Χρήστη");
	private JPanel panel = new JPanel();
	private JButton okButton = new JButton("ΕΝΤΑΞΕΙ");
	private boolean yes = false;

	private JLabel message = new JLabel("Δεν έχετε επιλέξει καμία εγγραφή ");
	

	public NoRowSelectedMessageGUI() {
		 JDialog dialog = new JDialog(powerFrame,"Δεν έχετε επιλέξει χρήστη",ModalityType.APPLICATION_MODAL);
		okButton.setPreferredSize(new Dimension(10,30));
		okButton.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		
		//Στοίχηση κουμπιών
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
	
		//Στοίχηση ετικέτας
		message.setAlignmentX(Component.CENTER_ALIGNMENT);
		

		Box labelbox = Box.createVerticalBox();
		
		
		labelbox.setPreferredSize(new Dimension(180,70));
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
		dialog.add(panel);
		powerFrame.pack();
		//powerFrame.setIconImage(logo.getImage());
		dialog.pack();
        dialog.setSize(400,130);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(true);
        dialog.setVisible(true);
        

		
	}






	public boolean isYes() {
		return yes;
	}
	
	

}
