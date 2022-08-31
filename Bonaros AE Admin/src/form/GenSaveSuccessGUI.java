package form;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class GenSaveSuccessGUI extends JFrame {
	
	private JButton okButton = new JButton("ΕΝΤΑΞΕΙ");
	private JFrame powerFrame=  new JFrame("ΑΠΟΘΗΚΕΥΣΗ ΑΛΛΑΓΩΝ");
	private JPanel panel = new JPanel();
	private JTextArea message = new JTextArea("Οι αλλαγές σας αποθηκεύτηκαν.\nΠαρακαλώ επαννεκινήστε για εφαρμογή ! ");
	private JDialog dialog = new JDialog(powerFrame,"Αποθήκευση αλλαγών",ModalityType.APPLICATION_MODAL);

	public GenSaveSuccessGUI() {
		
		okButton.setPreferredSize(new Dimension(50,80));
		okButton.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
		
		//�������� ��������
		okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		//�������� ��������
		message.setAlignmentX(Component.CENTER_ALIGNMENT);
		message.setFont(new Font("Arial",Font.BOLD,13));
		message.setEditable(false);
		message.setOpaque(true);
		Box labelbox = Box.createVerticalBox();
		
		
		labelbox.setPreferredSize(new Dimension(280,70));
		labelbox.add(message);
		//labelbox.add(Box.createRigidArea(new Dimension(10,10)));
		labelbox.add(okButton);
		
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				powerFrame.dispose();
				
			}
			
		});
		
		
		
		panel.add(labelbox);

		panel.setPreferredSize(new Dimension(400,150));
		dialog.add(panel);
		powerFrame.pack();
		
		dialog.pack();
        dialog.setSize(400,150);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setResizable(true);
        dialog.setVisible(true);
		
        

		
	}




}
