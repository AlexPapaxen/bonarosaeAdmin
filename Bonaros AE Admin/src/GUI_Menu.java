import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class GUI_Menu extends JFrame {
	
	private final JLabel settings = new JLabel("Settings");
	private final JLabel settingsName = new JLabel();	
	private final JPanel southpanel = new JPanel();
	private final JPanel westpanel = new JPanel();	
	private final ArrayList<MenuItem> subMenu = new ArrayList<>();
	

	
	public GUI_Menu(Icon icon,String menuName, MenuItem...subMenu) {
		
	
	 
	JScrollPane menuScroll = new JScrollPane();
	 //Initialize westpanel	
	 westpanel.setBackground(Color.LIGHT_GRAY);
	 westpanel.setPreferredSize(new Dimension(300,800));
	 westpanel.add(menuScroll);
	 //Initialize southpanel
	 southpanel.setPreferredSize(new Dimension(800,10));
	 
	 //Edit westpanel
	 
	 settings.setIcon(icon);
	 settingsName.setText(menuName);
	 this.setSize(new Dimension(Integer.MAX_VALUE,45));
	 this.setMaximumSize((new Dimension(Integer.MAX_VALUE,45)));
	 this.setMinimumSize((new Dimension(Integer.MAX_VALUE,45)));
	 
	 for(int i=0;i<subMenu.length;i++) {
		 
		 this.subMenu.add(subMenu[i]);
	 }
	 
	 
	 
	 
		
		
		
	}
	public static void main(String[] args) {
		//new GUI_Admin();

	}
	
	private boolean showing = false;
	
	private void formMousePressed(java.awt.event.MouseEvent evt) {
		if(showing) {
			hideMenu();
			showing = false;
		} else {
			showMenu();
			showing = true;
		}
		
	}
	private void showMenu() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<subMenu.size();i++) {
					sleep();
					subMenu.get(i).setEnabled(true);
				}
				
			}
			
		}).start();
	}
	public void hideMenu() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=subMenu.size()-1;i>=0;i--) {
					sleep();
					subMenu.get(i).setEnabled(false);
				}
				
			}
			
		}).start();
	}
	
	public void sleep() {
		try {
			Thread.sleep(20);
		} catch(Exception e) {
			
		}
	}
	
	public ArrayList<MenuItem> getSubMenu(){
		return subMenu;
	}

}
