package form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class BoxPanel extends JPanel {

	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BoxPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Box box = Box.createHorizontalBox();
		JLabel name = new JLabel("Box");
		JLabel labelName = new JLabel("Label name");		
		JLabel lastModified = new JLabel("Last Modified");
		
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		 layout.setHorizontalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		            .addGroup(layout.createSequentialGroup()
		                .addGap(10, 10, 10)
		                .addComponent(labelName)
		                .addGap(15, 15, 15)
		                .addComponent(lastModified, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addContainerGap(229, Short.MAX_VALUE))
		        );
		        layout.setVerticalGroup(
		            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(layout.createSequentialGroup()
		                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addGap(10, 10, 10)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(labelName)
		                    .addComponent(lastModified, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                .addGap(0, 257, Short.MAX_VALUE))
		        );
		
		box.add(labelName);
		box.add(Box.createRigidArea(new Dimension(10,10)));
		box.add(lastModified);
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 564, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		frame.setVisible(true);
	}

}
