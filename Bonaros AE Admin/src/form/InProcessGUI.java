package form;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class InProcessGUI extends javax.swing.JPanel {
	
	private String uname = "root";
	private String pass = "Jo6c!pi7papaxen";
	
	private String url = "jdbc:mysql://localhost:3306/users?useTimezone=true&serverTimezone=UTC";
	private static JTable table;
	private TableRowSorter sorter = new TableRowSorter();
	private static int selectedColumn = 0;
	private static int logTableSelected = 0;
	private Vector<String> row = new Vector<>();
	private Vector<Vector> vList = new Vector<>();
	private Vector<Vector> logData;
	private File targetFile;
	private ArrayList<JTable> selectedTable = new ArrayList<>();
	
	public String getComputerName() {
		
	    Map<String, String> env = System.getenv();
	    if (env.containsKey("COMPUTERNAME"))
	        return env.get("COMPUTERNAME");
	    else if (env.containsKey("HOSTNAME"))
	        return env.get("HOSTNAME");
	    else
	        return "Unknown Computer";
	}
	
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 10; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        width = Math.max(width, table.getColumnModel().getColumn(column).getPreferredWidth());
	        if(width > 1500)
	            width=1500;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	
public void ExcelWrite(JTable table,String file) throws WriteException {
		
 		WritableWorkbook workbook = null;

        try {

        	workbook = Workbook.createWorkbook(new File(file));

            // create an Excel sheet
            WritableSheet excelSheet = workbook.createSheet("JTable Data", 0);
           
            TableModel m = table.getModel();
            for(int i = 0; i < m.getColumnCount(); i++){
 
            Label label1 = new Label(i, 0, m.getColumnName(i));
            excelSheet.addCell(label1);
            }
            
            for(int i=0;i<=table.getRowCount()-1;i++) {
            	for(int j=0;j<=table.getColumnCount()-1;j++) {
            		
            		Label myLabel = new Label(j,i+1,table.getValueAt(i, j).toString());
            		excelSheet.addCell(myLabel);
            		
            	}
            }
            
            workbook.write();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
	        
       }
        
		

 	}
	
	
	public InProcessGUI()  {
		initComponents();

	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch(ClassNotFoundException e) {
    		e.printStackTrace();    		
    	}
    	
    	try {
    		String pcName = "logs_"+this.getComputerName().toLowerCase().replace("-", "_");
    		String query = "select* from "+pcName;
    		Connection con =  DriverManager.getConnection(url,uname,pass);
    		Statement statement = con.createStatement();
    		ResultSet result = statement.executeQuery(query);
    		ResultSetMetaData rsmt = result.getMetaData();
    		
    		int c = rsmt.getColumnCount();
    		Vector column = new Vector(c);
    		for(int i=2;i<=c;i++) {
    			
    			column.add(rsmt.getColumnName(i));
    		}
    		Vector data = new Vector();
    		Vector row = new Vector();
    		
    		while(result.next()) {
    			row = new Vector(c);
    			for(int i=2;i<=c;i++) {
    				row.add(result.getString(i));
    				
    			}
    			data.add(row);
    		}
    		
    		
    		DefaultTableModel model = new DefaultTableModel(data,column) {

    		    @Override
    		    public boolean isCellEditable(int row, int column) {
    		    	
    		    		return false;
    		    }
    		};
    		
    		
    		sorter.setModel(model);
    	    table = new javax.swing.JTable(model);
        	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        	table.setRowSorter(sorter);
        	table.setRowHeight(30);
    		
        	JComboBox<String> jComboBox = new JComboBox<>(column);
        	jComboBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					selectedColumn = jComboBox.getSelectedIndex();
					
				}
        		
        	});
        	
        	JTextField searchField = new JTextField();
        	searchField.setPreferredSize(new Dimension(60,30));
        	JLabel searchlabel = new JLabel("Αναζήτηση:");
        	searchlabel.setFont(new Font("Arial",Font.BOLD,12));
        	Box searchBox = Box.createHorizontalBox();
        	selectedColumn = jComboBox.getSelectedIndex();
        	searchField.getDocument().addDocumentListener(new DocumentListener() {
        		
        		
                @Override
                public void insertUpdate(DocumentEvent e) {
                   search(searchField.getText());
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                   search(searchField.getText());
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                   search(searchField.getText());
                }
                public void search(String str) {
                   if (str.length() == 0) {
                      sorter.setRowFilter(null);
                   } else {
                	  
                      sorter.setRowFilter(RowFilter.regexFilter(str,selectedColumn));
                   }
                }
             });
        	
        	
        	try {
        		
        		String q = "select pc_name from alllogs_table";
        		Connection con1 =  DriverManager.getConnection(url,uname,pass);
        		Statement statement1 = con1.createStatement();
        		ResultSet result1 = statement1.executeQuery(q);
        		
        		
        		while(result1.next()) {
        			row = new Vector(2);
        			for(int i=1;i<2;i++) {
        				
        				row.add(result1.getString(i));
        				
        			}
        			
        		}
        		
        		
        		for(int i=0;i<row.size();i++) {
        			logData = new Vector(32);
        			try {
        				String log = "logs_"+row.get(i).toString().toLowerCase().replace("-", "_");
        	    		String ql = "select* from "+log;
        	    		
        	    		Connection con2 = DriverManager.getConnection(url,uname,pass);
        	    		Statement st2 = con2.createStatement();
        	    		ResultSet rs2 = st2.executeQuery(ql);
        	    		
        	    		while(rs2.next()) {
        	    			 
        	    			Vector temp = new Vector();
        	    			for(int j=2;j<=32;j++) {
        	    				temp.add(rs2.getString(j));
        	    			}
        	    			logData.add(temp);
        	    			
        	    		}
        	    		vList.add(logData);
        	    		
        			}catch(SQLException e2) {
        				e2.printStackTrace();
        			}
        		}
        		
        		
        		
        		
        	}catch(SQLException e1) {
        		e1.printStackTrace();
        	}
        	
        	
        	JComboBox<String> jlogTableBox = new JComboBox<>(row);
        	jlogTableBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					logTableSelected = jlogTableBox.getSelectedIndex();
					
					
					model.setDataVector(vList.get(logTableSelected), column);
    	    		
    	    		
    	    		JTable table1 = new JTable(model);
    	    		
    	    		selectedTable.add(table1);
    	    		
    	    		table1.setRowHeight(30);
    	    		table1.setRowSorter(sorter);
    	    		table1.getTableHeader().setFont(new Font("Segoe UI",Font.PLAIN,12));
    	    		table1.setFont(new Font("ARIAL", Font.PLAIN, 12));
    	    		//table1.setPreferredSize(new Dimension(872,1000));
    	    		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    	    		resizeColumnWidth(table1);
    	    		pane.getViewport().add(table1);
    	    		int horizontalPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
    	        	//Vertical Policy
    	        	int vericalPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
    	        	
    	        	pane.setHorizontalScrollBarPolicy(horizontalPolicy);
    	        	pane.setVerticalScrollBarPolicy(vericalPolicy);
    	        	//pane.setPreferredSize(new Dimension(850,1000));
					
					
					
				}
        		
        	});
    		
        	
        	
        	JLabel tablesearchlabel = new JLabel("Αναζήτηση σε pc:");
        	tablesearchlabel.setFont(new Font("Arial",Font.BOLD,12));

        	searchBox.add(searchlabel);
        	searchBox.add(Box.createRigidArea(new Dimension(10,0)));
        	searchBox.add(searchField);
        	searchBox.add(Box.createRigidArea(new Dimension(10,0)));
        	searchBox.add(jComboBox);
        	searchBox.add(Box.createRigidArea(new Dimension(100,0)));
        	searchBox.add(tablesearchlabel);
        	searchBox.add(Box.createRigidArea(new Dimension(10,0)));
        	searchBox.add(jlogTableBox);
        	searchBox.setPreferredSize(new Dimension(200,30));
        	searchBox.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        	//JScrollPane Settings
        	
        	pane = new javax.swing.JScrollPane(table);
        	//Horizontal Policy
        	int horizontalPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
        	//Vertical Policy
        	int vericalPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
        	pane.setHorizontalScrollBarPolicy(horizontalPolicy);
        	pane.setVerticalScrollBarPolicy(vericalPolicy);
        	
        	
        	
        	pane.setPreferredSize(new Dimension(150,10));
        	
        	jLabel1 = new javax.swing.JLabel();
        	jTextField1 = new javax.swing.JTextField();

	        jLabel1.setBackground(new java.awt.Color(155, 156, 237));
	        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
	        jLabel1.setText("    ΣΕ ΕΠΕΞΕΡΓΑΣΙΑ");
	        jLabel1.setOpaque(true);
	        export = new javax.swing.JButton();
	        export.setText("ΕΞΑΓΩΓΗ"); 
	        refresh = new javax.swing.JButton();
	        refresh.setText("ΑΝΑΝΕΩΣΗ");
	        Box buttonBox = Box.createHorizontalBox();
	        buttonBox.add(Box.createRigidArea(new Dimension(20,20)));
	        buttonBox.add(export);
	        buttonBox.add(Box.createRigidArea(new Dimension(20,20)));
	        buttonBox.add(refresh);
	         
	         
	        jTextField1.addActionListener(new java.awt.event.ActionListener() {
	                public void actionPerformed(java.awt.event.ActionEvent evt) {
	                    jTextField1ActionPerformed(evt);
	                }
	            });
	            
	            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	            
	          
	            
	            this.setLayout(layout);
    		
	            layout.setHorizontalGroup(
		                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)                
		                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))       
		                .addComponent(buttonBox)
		                .addGap(30,30,30)
		                .addComponent(searchBox,30,700,800)
		                .addComponent(pane,10,600,1000));
		                                
		            layout.setVerticalGroup(
		                layout.createSequentialGroup()  
		                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addGroup(layout.createSequentialGroup()
		                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE))
		                    .addComponent(buttonBox)
		                    .addGap(30,30,30)
		                    .addComponent(searchBox,30,30,30)
		                    .addGap(30,30,30)
		                    .addComponent(pane,10,200,750)));

    		
		            
		            
		            export.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int rand = ThreadLocalRandom.current().nextInt(0,10000);
							String sRand = String.valueOf(rand);
						
							Calendar cal = Calendar.getInstance();
				            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
				            String formatted = format1.format(cal.getTime());
								try {
									JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\user\\git\\bonarosaeProgramm\\Bonaros AE");
									int ts = logTableSelected-1;
									if(logTableSelected==0) {
										ts=0;
									}
									targetFile = new File(formatted+sRand);
									 excelFileChooser.setDialogTitle("Save As ..");
									 FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
							        excelFileChooser.setFileFilter(fnef);
							        excelFileChooser.setSelectedFile(targetFile);
							        int chooser = excelFileChooser.showSaveDialog(null);
						            //Check if save button has been clicked
						            if (chooser == JFileChooser.APPROVE_OPTION) {
						            	ExcelWrite(selectedTable.get(ts),formatted+sRand+".xls");
									
						            }
								} catch (WriteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							
						}
		            	
		            });
		            
		            
		            refresh.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							vList.removeAll(vList);
							try {
				        		Vector row1 = new Vector<>();
				        		String q = "select pc_name from alllogs_table";
				        		Connection con1 =  DriverManager.getConnection(url,uname,pass);
				        		Statement statement1 = con1.createStatement();
				        		ResultSet result1 = statement1.executeQuery(q);
				        		
				        		
				        		while(result1.next()) {
				        			row1 = new Vector(2);
				        			for(int i=1;i<2;i++) {
				        				
				        				row1.add(result1.getString(i));
				        				
				        			}
				        			
				        		}
				        		
				        		
				        		for(int i=0;i<row1.size();i++) {
				        			
				        			logData = new Vector(32);
				        			try {
				        				String log = "logs_"+row1.get(i).toString().toLowerCase().replace("-", "_");
				        	    		String ql = "select* from "+log;
				        	    		
				        	    		Connection con2 = DriverManager.getConnection(url,uname,pass);
				        	    		Statement st2 = con2.createStatement();
				        	    		ResultSet rs2 = st2.executeQuery(ql);
				        	    		
				        	    		while(rs2.next()) {
				        	    			 
				        	    			Vector temp = new Vector();
				        	    			for(int j=2;j<32;j++) {
				        	    				temp.add(rs2.getString(j));
				        	    			}
				        	    			logData.add(temp);
				        	    			
				        	    		}
				        	    		vList.add(logData);
				        	    		
				        	    		
				        	    		
				        	    		model.setDataVector(vList.get(logTableSelected), column);
				        	    		
				        	    		
				        	    		JTable table1 = new JTable(model);
				        	    		
				        	    		table1.setRowHeight(30);
				        	    		table1.getTableHeader().setFont(new Font("Segoe UI",Font.PLAIN,12));
				        	    		table1.setFont(new Font("ARIAL", Font.PLAIN, 12));
				        	    		//table1.setPreferredSize(new Dimension(872,1000));
				        	    		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				        	    		resizeColumnWidth(table1);
				        	    		table1.setRowSorter(sorter);
				        	    		pane.getViewport().add(table1);
				        	    		int horizontalPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
				        	        	//Vertical Policy
				        	        	int vericalPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
				        	        	
				        	        	pane.setHorizontalScrollBarPolicy(horizontalPolicy);
				        	        	pane.setVerticalScrollBarPolicy(vericalPolicy);
				        	    		
				        	    		
				        	    		
				        	    		
				        	    		
				        	    		
				        			}catch(SQLException e2) {
				        				e2.printStackTrace();
				        			}
				        		}
				        		
				        		
				        		
				        		
				        		}catch(SQLException e1) {
				        			e1.printStackTrace();
				        		}
				        	
							
							}
		            	
		            	});
		            
		           
    		
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    		
	}
	 private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
	        // TODO add your handling code here:
	    }
	
	 	private javax.swing.JLabel jLabel1;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JButton export;
	    private javax.swing.JTable users;
	    private javax.swing.JButton refresh;
	    private javax.swing.JButton deleteButton;
	    private javax.swing.JScrollPane pane;

}
