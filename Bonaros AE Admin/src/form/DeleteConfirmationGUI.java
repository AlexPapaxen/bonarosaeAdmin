package form;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class DeleteConfirmationGUI extends JFrame {
	
	private JFrame powerFrame=  new JFrame("Διαγραφή Χρήστη");
	private JPanel panel = new JPanel();
	private JButton yesButton = new JButton("ΝΑΙ");
	private boolean yesFlag = false;
	private JButton noButton = new JButton("ΟΧΙ");
	private JLabel message = new JLabel("Είστε βέβαιος για τη διαγραφή του χρήστη; ");
	private JTable table = new JTable();
	private final ArrayList<Boolean> trueList = new ArrayList<>();
	
	
	
	
	public DeleteConfirmationGUI(JButton yes, JButton no) {
		
		yes.setPreferredSize(new Dimension(100,10));
		yes.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		no.setPreferredSize(new Dimension(100,10));
		no.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		//Στοίχηση κουμπιών
		yesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		noButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//Στοίχηση ετικέτας
		message.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Box box = Box.createHorizontalBox();
		box.add(yes);
		box.add(Box.createRigidArea(new Dimension(20,20)));
		box.add(no);
		
		
		Box labelbox = Box.createVerticalBox();
		
		
		labelbox.add(Box.createRigidArea(new Dimension(200,20)));
		labelbox.add(message);
		
		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				yesFlag = true;
				trueList.add(yesFlag);
				powerFrame.dispose();
				
			}
			
			
		});
		
		
		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				powerFrame.dispose();
				
			}
			
		});
		
		
		panel.add(labelbox);
		panel.add(box);
		panel.setPreferredSize(new Dimension(400,100));
		
		
		powerFrame.add(panel);
		
		powerFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        powerFrame.pack();
        powerFrame.setLocationRelativeTo(null);
        powerFrame.setVisible(true);
        powerFrame.setResizable(false);

		
	}






	public boolean isYes() {
		return yesFlag;
	}




	public ArrayList<Boolean> getTrueList() {
		return trueList;
	}






	
	
	

}
