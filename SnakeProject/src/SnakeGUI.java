/*
 * CS420 Final Project
 * Author: Christian Bazoian
 * Author: Kathleen Hess
 */


/*NOTE
 * Basic functionality so far. GUI can get more complex, but lets
 * get it working with MYSQL first. Based on the selected check box,
 * when the search button is pressed, it will need to send a query to
 * the database and return the results to the text box in the GUI.
 */


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

//import frameDemoClass.FrameDemo.BoxButtonSelector;





class SnakeGUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Declare variables here
	private JPanel main;
	private JMenuItem aboutMenuItem, exitMenuItem;



	public JTextArea textArea;

	//Labels
	private JLabel mainLabel;
	private JLabel comboLabel;
	private JLabel animalIDLabel;
	private JLabel animalTypeLabel;
	private JLabel animalBreedLabel;
	private JLabel animalGenderLabel;
	private JLabel animalDOBLabel;
	private JLabel modifyAnimalLabel;
	private JLabel supplierIDLabel;
	private JLabel supplierNameLabel;
	private JLabel supplierLocationLabel;
	private JLabel supplierDateJoinedLabel;
	private JLabel modifySupplierLabel;
	private JLabel animalSupplierIDLabel;
	private JLabel animalBlankLabel;
	private JLabel supplierBlankLabel;
	private JLabel resultsTextAreaLabel;
	private JLabel animalLocationTypeLabel;
	private JLabel animalLocationLocationLabel;
	private JLabel supplierInventoryLabel;


	//Buttons
	private JButton searchButton;
	private JButton addAnimalButton;
	private JButton deleteAnimalButton;
	private JButton addSupplierButton;
	private JButton deleteSupplierButton;
	private JButton searchLocationButton;
	private JButton searchSupplierInventoryButton;
	private JButton animalCount;


	//combo boxes
	private JComboBox searchCombo;;

	//text fields
	private JTextField animalIDField;
	private JTextField animalTypeField;
	private JTextField animalBreedField;
	private JTextField animalGenderField;
	private JTextField animalDOBField;
	private JTextField supplierIDField;
	private JTextField supplierNameField;
	private JTextField supplierLocationField;
	private JTextField supplierDateJoinedField;
	private JTextField animalSupplierIDField;
	private JTextField animalLocationTypeField;
	private JTextField animalLocationLocationField;
	private JTextField supplierInventoryField;


	private Driver driver;

	public SnakeGUI() // constructor
	{
		createUserInterface();  // call createUserInterface()
	}




	public void createUserInterface()
	{

		// Returns the contentPane object for this frame.  All programs need this
		Container contentPane = getContentPane();

		// Enable explicit positioning of GUI components   All programs need this
		contentPane.setLayout(new BorderLayout());

		// Add an Icon to the JFrame
		Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");	
		setIconImage(icon);

		//make a new border
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border blackline = BorderFactory.createLineBorder(Color.BLUE);


		//Make Menu bar
		JMenuBar menuBar = createMenuBar();
		setJMenuBar(menuBar);
		menuBar.setBorder(blackline);


		//connect to mysql database
		driver = new Driver();  





		//============== Main Panel Setup ==============================================

		main = new JPanel(null);  // make the main panel


		contentPane.add(main);


		//create the main label for the gui
		mainLabel= new JLabel("Reptiles and Suppliers Database Form");
		mainLabel.setBounds(260,5,220,30);
		mainLabel.setBorder(raisedetched);
		main.add(mainLabel);














		//Create a combo box to select what to search for
		// Create a combobox
		String[] items = {"Animals", "Suppliers", "Both"};
		searchCombo = new JComboBox(items);
		searchCombo.setBounds(10,70,200,20);
		searchCombo.setBorder(loweredbevel);
		searchCombo.setToolTipText("Choose an option");
		comboLabel = new JLabel("Search");
		comboLabel.setBounds(10,45,75,20);
		//comboLabel.setBorder(loweredbevel);
		comboLabel.setToolTipText("Choose an option");
		main.add(searchCombo);
		main.add(comboLabel);

		//Add action listener to combo box
		searchCombo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				Object contents = searchCombo.getSelectedItem();
				//textArea.append("\n" + "Choose " + contents);

			}
		});




		//search Button set up
		searchButton = new JButton("Search");
		searchButton.setBounds(10,100,100,30); 
		main.add(searchButton);


		// add action Listener to search button
		searchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//make a temporary combo box and get the source of the action event


				//Create a string and store the choice that the action event returned
				String choice = (String)searchCombo.getSelectedItem();

				//print the choice to the console
				System.out.println(choice); 

				if(choice == "Animals")
				{
					//clear text area
					textArea.setText("");

					//return animals from database 
					driver.selectA();

					textArea.setText(driver.text);
					System.out.println(driver.text);

				}

				if(choice == "Suppliers")
				{
					//clear text area
					textArea.setText("");

					driver.selectS();

					textArea.setText(driver.text);
					System.out.println(driver.text);
				}

				if(choice == "Both")
				{
					//cear text area
					textArea.setText("");
					driver.selectSa();

					textArea.setText(driver.text);
					System.out.println(driver.text);
				}

			}  	
		});


		//----------------animal location finder Form set up-----------------------------------------
		//animal type field
		animalLocationTypeField = new JTextField("");
		animalLocationTypeField.setBounds(300,80,100,20);
		animalLocationTypeField.setToolTipText("Enter Animal Type");
		animalLocationTypeLabel = new JLabel("Type");
		animalLocationTypeLabel.setBounds(300,55,75,20);
		animalLocationTypeLabel.setBorder(loweredbevel);
		animalLocationTypeLabel.setToolTipText("Choose an option");

		main.add(animalLocationTypeField);
		main.add(animalLocationTypeLabel);


		//animal location finder set up
		animalLocationLocationField = new JTextField("");
		animalLocationLocationField.setBounds(400,80,100,20);
		animalLocationLocationField.setToolTipText("Enter Animal Location");
		animalLocationLocationLabel = new JLabel("Location");
		animalLocationLocationLabel.setBounds(400,55,75,20);
		animalLocationLocationLabel.setBorder(loweredbevel);
		animalLocationLocationLabel.setToolTipText("Choose an option");

		main.add(animalLocationLocationField);
		main.add(animalLocationLocationLabel);



		//search button set up
		searchLocationButton = new JButton("Search");
		searchLocationButton.setBounds(300,100,80,30); 
		main.add(searchLocationButton);


		// add action Listener to searchLocation button
		searchLocationButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				String type = animalLocationTypeField.getText();
				String location = animalLocationLocationField.getText();



				//clear text area
				textArea.setText("");

				//check for null text fields
				if(type == "" || type == null)
				{
					textArea.setText("Please enter an animal Type");

				}
				else if (location == "" || location == null)
				{
					textArea.setText("Please enter an animal location");
				}
				else
				{
					textArea.setText("");
					
					//put users input into this method which generates a mysql querry and executes it
					driver.animalLocation(type, location);
					
					textArea.setText(driver.text);

					//clear text fields
					animalLocationTypeField.setText("");
					animalLocationLocationField.setText("");
				}
			}  	
		});




		//-------------end Form find animal location --------------------------------------------------------------------------

		//----------------------Form supplier inventory stock ----------------------------------------------------------------

		//supplier inventory stock set up
		supplierInventoryField = new JTextField("");
		supplierInventoryField.setBounds(550,80,100,20);
		supplierInventoryField.setToolTipText("Enter Supplier name");
		supplierInventoryLabel = new JLabel("Supplier");
		supplierInventoryLabel.setBounds(550,55,75,20);
		supplierInventoryLabel.setBorder(loweredbevel);
		supplierInventoryLabel.setToolTipText("Choose an option");

		main.add(supplierInventoryField);
		main.add(supplierInventoryLabel);


		//search button set up
		searchSupplierInventoryButton = new JButton("Search");
		searchSupplierInventoryButton.setBounds(560,100,80,30); 
		main.add(searchSupplierInventoryButton);


		// add action Listener to searchSupplierInventory button
		searchSupplierInventoryButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				String supplier = supplierInventoryField.getText();


				//clear text area
				textArea.setText("");

				//check for null text fields
				if(supplier == "" || supplier == null)
				{
					textArea.setText("Please enter a supplier name");

				}

				else
				{
					textArea.setText("");
					
					
					//put users input into this method which generates a mysql querry and executes it
					driver.supplierA(supplier);
					
					textArea.setText(driver.text);


					//clear text fields
					supplierInventoryField.setText("");
				}
			}  	
		});

		// -------------------------end form supplier inventory stock---------------------------------------------------------------------

		//-----------------------------animal count button ------------------------------------------------------------------------------------


		//animal count button set up
		animalCount = new JButton("Animals");
		animalCount.setBounds(560,250,80,30); 
		main.add(animalCount);


		// add action Listener to searchSupplierInventory button
		animalCount.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				textArea.setText("");
				driver.animalCount();
				textArea.setText(driver.text);
				
				
			}
		});



		//-----------------------------end animal count button ------------------------------------------------------------------

		//----------------Form for add/delete animal------------------------------------------------------------------
		//make a border around the category by making a blank JLabel and adding a border
		animalBlankLabel = new JLabel("");
		animalBlankLabel.setBounds(10,205,400,150);
		animalBlankLabel.setBorder(raisedetched);
		main.add(animalBlankLabel);

		modifyAnimalLabel = new JLabel("Animal");
		modifyAnimalLabel.setBounds(20,185,100,20);
		//modifyAnimalLabel.setBorder(loweredbevel);
		main.add(modifyAnimalLabel);

		//set up for JTextField for animal ID 
		animalIDField = new JTextField("");
		animalIDField.setBounds(20,220,100,20);
		animalIDField.setToolTipText("Enter Animal ID");
		animalIDLabel = new JLabel("ID");
		animalIDLabel.setBounds(20,235,75,20);
		animalIDLabel.setBorder(loweredbevel);
		animalIDLabel.setToolTipText("Choose an option");

		main.add(animalIDField);
		main.add(animalIDLabel);

		//set up for JTextField Animal Type 
		animalTypeField = new JTextField("");
		animalTypeField.setBounds(140,220,100,20);
		animalTypeField.setToolTipText("Enter animal type");
		animalTypeLabel = new JLabel("Type");
		animalTypeLabel.setBounds(140,235,75,20);
		animalTypeLabel.setBorder(loweredbevel);
		animalTypeLabel.setToolTipText("Choose an option");

		main.add(animalTypeField);
		main.add(animalTypeLabel);


		//set up for JTextField for animal breed 
		animalBreedField = new JTextField("");
		animalBreedField.setBounds(260,220,100,20);
		animalBreedField.setToolTipText("Enter Animal Breed");
		animalBreedLabel = new JLabel("Breed");
		animalBreedLabel.setBounds(260,235,75,20);
		animalBreedLabel.setBorder(loweredbevel);
		animalBreedLabel.setToolTipText("Choose an option");

		main.add(animalBreedField);
		main.add(animalBreedLabel);


		//set up for JTextField for animal gender 
		animalGenderField = new JTextField("");
		animalGenderField.setBounds(20,260,100,20);
		animalGenderField.setToolTipText("Enter Animal gender");
		animalGenderLabel = new JLabel("Gender");
		animalGenderLabel.setBounds(20,275,75,20);
		animalGenderLabel.setBorder(loweredbevel);
		animalGenderLabel.setToolTipText("Choose an option");

		main.add(animalGenderField);
		main.add(animalGenderLabel);

		//set up for JTextField for animal date of birth 
		animalDOBField = new JTextField("");
		animalDOBField.setBounds(140,260,100,20);
		animalDOBField.setToolTipText("Enter Animal Date of birth");
		animalDOBLabel = new JLabel("DOB");
		animalDOBLabel.setBounds(140,275,75,20);
		animalDOBLabel.setBorder(loweredbevel);
		animalDOBLabel.setToolTipText("Choose an option");

		main.add(animalDOBField);
		main.add(animalDOBLabel);



		//set up for JTextField for supplier ID
		animalSupplierIDField = new JTextField("");
		animalSupplierIDField.setBounds(260,260,100,20);
		animalSupplierIDField.setToolTipText("Enter Animal's Supplier ID");
		animalSupplierIDLabel = new JLabel("Supplier ID");
		animalSupplierIDLabel.setBounds(260,275,75,20);
		animalSupplierIDLabel.setBorder(loweredbevel);
		animalSupplierIDLabel.setToolTipText("Choose an option");

		main.add(animalSupplierIDField);
		main.add(animalSupplierIDLabel);

		//add animal Button set up
		addAnimalButton = new JButton("Add");
		addAnimalButton.setBounds(20,310,80,30); 
		main.add(addAnimalButton);


		// add action Listener to addAnimal button
		addAnimalButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				//get user's input from text fields
				String AID = animalIDField.getText() ;
				String type = animalTypeField.getText();
				String breed = animalBreedField.getText();
				String gender = animalGenderField.getText();
				String DOB = animalDOBField.getText();

				String SID = animalSupplierIDField.getText();

				//clear text area
				textArea.setText("");

				//check for null text fields
				if(AID == "" || AID == null)
				{
					textArea.setText("Please enter an animal ID");

				}
				else if (type == "" || type == null)
				{
					textArea.setText("Please enter an animal Type");
				}
				else if(breed == "" || breed == null)
				{
					textArea.setText("Please enter an animal breed");
				}
				else if(gender == "" || gender == null)
				{
					textArea.setText("Please enter an animal gender");
				}
				else if(DOB == "" || DOB == null)
				{
					textArea.setText("Please enter a date of birth");
				}
				else
				{
					//put users input into this method which generates a mysql querry and executes it
					driver.addAnimal(AID, type, breed, gender, DOB);

					//add animal/supplier pair to database too
					driver.addSupplierAnimal(SID, AID);


					//clear text fields
					animalIDField.setText("");
					animalTypeField.setText("");
					animalBreedField.setText("");
					animalGenderField.setText("");
					animalDOBField.setText("");
					animalSupplierIDField.setText("");

					//display success message
					textArea.setText("Animal Added Successfully.");
				}



			}  	
		});




		//delete animal Button set up
		deleteAnimalButton = new JButton("Delete");
		deleteAnimalButton.setBounds(110,310,80,30); 
		main.add(deleteAnimalButton);


		// add action Listener to deleteAnimal button
		deleteAnimalButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				//get user's input from text fields
				String AID = animalIDField.getText() ;
				String type = animalTypeField.getText();
				String breed = animalBreedField.getText();
				String gender = animalGenderField.getText();
				String DOB = animalDOBField.getText();





				//clear text area
				textArea.setText("");


				//check for null text fields
				if(AID == "" || AID == null)
				{
					textArea.setText("Please enter an animal ID");

				}
				else if (type == "" || type == null)
				{
					textArea.setText("Please enter an animal Type");
				}
				else if(breed == "" || breed == null)
				{
					textArea.setText("Please enter an animal breed");
				}
				else if(gender == "" || gender == null)
				{
					textArea.setText("Please enter an animal gender");
				}
				else if(DOB == "" || DOB == null)
				{
					textArea.setText("Please enter a date of birth");
				}
				else
				{
					//clear text fields
					animalIDField.setText("");
					animalTypeField.setText("");
					animalBreedField.setText("");
					animalGenderField.setText("");
					animalDOBField.setText("");
					animalSupplierIDField.setText("");

					//put users input into this method which generates a mysql querry and executes it
					driver.deleteA(AID);

					//display success message
					textArea.setText("Animal Deleted");
				}

			}  	
		});



		//-----------------end add/delete animal form ---------------------------------------------------------------







		//---------add/delete supplier form-------------------------------------------------------------------

		//make a border around the category by making a blank JLabel and adding a border
		supplierBlankLabel = new JLabel("");
		supplierBlankLabel.setBounds(10,400,400,160);
		supplierBlankLabel.setBorder(raisedetched);
		main.add(supplierBlankLabel);


		modifySupplierLabel = new JLabel("Supplier");
		modifySupplierLabel.setBounds(20,380,100,20);
		//modifySupplierLabel.setBorder(loweredbevel);
		main.add(modifySupplierLabel);

		//set up for JTextField for animal ID 
		supplierIDField = new JTextField("");
		supplierIDField.setBounds(20,415,100,20);
		supplierIDField.setToolTipText("Enter Supplier ID");
		supplierIDLabel = new JLabel("ID");
		supplierIDLabel.setBounds(20,430,75,20);
		supplierIDLabel.setBorder(loweredbevel);
		supplierIDLabel.setToolTipText("Choose an option");

		main.add(supplierIDField);
		main.add(supplierIDLabel);

		//set up for JTextField Animal Type 
		supplierNameField = new JTextField("");
		supplierNameField.setBounds(140,415,100,20);
		supplierNameField.setToolTipText("Enter supplier name");
		supplierNameLabel = new JLabel("Name");
		supplierNameLabel.setBounds(140,430,75,20);
		supplierNameLabel.setBorder(loweredbevel);
		supplierNameLabel.setToolTipText("Choose an option");

		main.add(supplierNameField);
		main.add(supplierNameLabel);


		//set up for JTextField for animal breed 
		supplierLocationField = new JTextField("");
		supplierLocationField.setBounds(260,415,100,20);
		supplierLocationField.setToolTipText("Enter supplier Location");
		supplierLocationLabel = new JLabel("Location");
		supplierLocationLabel.setBounds(260,430,75,20);
		supplierLocationLabel.setBorder(loweredbevel);
		supplierLocationLabel.setToolTipText("Choose an option");

		main.add(supplierLocationField);
		main.add(supplierLocationLabel);


		//set up for JTextField for animal gender 
		supplierDateJoinedField = new JTextField("");
		supplierDateJoinedField.setBounds(20,450,100,20);
		supplierDateJoinedField.setToolTipText("Enter Date Joined");
		supplierDateJoinedLabel = new JLabel("Date Joined");
		supplierDateJoinedLabel.setBounds(20,465,75,20);
		supplierDateJoinedLabel.setBorder(loweredbevel);
		supplierDateJoinedLabel.setToolTipText("Choose an option");

		main.add(supplierDateJoinedField);
		main.add(supplierDateJoinedLabel);





		//add supplier Button set up
		addSupplierButton = new JButton("Add");
		addSupplierButton.setBounds(20,510,80,30); 
		main.add(addSupplierButton);


		// add action Listener to addSupplier button
		addSupplierButton.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent ae) 
			{
				//get users input from text fields
				String SID = supplierIDField.getText();
				String name = supplierNameField.getText();
				String location = supplierLocationField.getText();
				String dateJoined = supplierDateJoinedField.getText();

				//clear text area
				textArea.setText("");

				//check to make sure text fields are not empty
				if(SID == "" || SID == null)
				{
					textArea.setText("Please enter a supplier ID");
				}
				else if(name == "" || name == null)
				{
					textArea.setText("Please enter a supplier name");
				}

				else if(location == "" || location == null)
				{
					textArea.setText("Please enter a supplier location");
				}
				else if(dateJoined == "" || dateJoined == null)
				{
					textArea.setText("Please enter the date the supplier joined");
				}
				else
				{
					//put user input into this method which will generate a mysql querry from it and execute it
					driver.addSupplier(SID, name, location, dateJoined);


					//clear text fields
					supplierIDField.setText("");
					supplierNameField.setText("");
					supplierLocationField.setText("");
					supplierDateJoinedField.setText("");

					//display success message
					textArea.setText("Supplier added");
				}



			}  	
		});




		//delete animal Button set up
		deleteSupplierButton = new JButton("Delete");
		deleteSupplierButton.setBounds(110,510,80,30 );
		main.add(deleteSupplierButton);


		// add action Listener to deleteSupplier button
		deleteSupplierButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				//get users input from text fields
				String SID = supplierIDField.getText();
				String name = supplierNameField.getText();
				String location = supplierLocationField.getText();
				String dateJoined = supplierDateJoinedField.getText();

				//clear text area
				textArea.setText("");

				//check to make sure text fields are not empty
				if(SID == "" || SID == null)
				{
					textArea.setText("Please enter a supplier ID");
				}
				else if(name == "" || name == null)
				{
					textArea.setText("Please enter a supplier name");
				}

				else if(location == "" || location == null)
				{
					textArea.setText("Please enter a supplier location");
				}
				else if(dateJoined == "" || dateJoined == null)
				{
					textArea.setText("Please enter the date the supplier joined");
				}
				else
				{

					//TODO make a delete method in driver class

					//clear text fields
					supplierIDField.setText("");
					supplierNameField.setText("");
					supplierLocationField.setText("");
					supplierDateJoinedField.setText("");

					driver.deleteS(SID);

					//display success message
					textArea.setText("Supplier Deleted");
				}
			}  	
		});


		//-----------end add/delete supplier form ---------------------------------------------------------------------






		//text area set up


		resultsTextAreaLabel = new JLabel("Results");
		resultsTextAreaLabel.setBounds(470, 277, 60, 20);
		main.add(resultsTextAreaLabel);





		// Create a text area for results
		textArea = new JTextArea();
		textArea.setBorder(loweredbevel);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setAutoscrolls(true);
		textArea.setToolTipText("Your Results");
		textArea.setEditable(false);

		//make the text area scroll down when no more words fit
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(470,300,300,250);
		main.add(scroll);






		/*
		 * The following code sets up both the size of the window
		 * and centers the window on the screen. The Toolkit class
		 * is the abstract superclass of all actual implementations 
		 * of the Abstract Window Toolkit.  The getScreenSize( ) method
		 * gets the size of the screen. On systems with multiple 
		 * displays, the primary display is used.
		 */

		int width = 800;
		int height = 650;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);
		setResizable(false);

		setTitle("  Snake GUI ");
		setSize(width, height);
		setVisible(true);
	}//end class createUserInterface












	/**
	 * Sets up the menu bar with File and Edit menus.
	 */

	public JMenuBar createMenuBar ()
	{
		MenuListener menuListener = new MenuListener ();

		JMenu fileMenu = new JMenu ("File");

		aboutMenuItem = new JMenuItem ("About...");
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 
				ActionEvent.CTRL_MASK));
		aboutMenuItem.addActionListener (menuListener);

		exitMenuItem = new JMenuItem ("Exit");
		exitMenuItem.setAccelerator (KeyStroke.getKeyStroke(KeyEvent.VK_Q, 
				ActionEvent.CTRL_MASK));
		exitMenuItem.addActionListener (menuListener);


		fileMenu.addSeparator ();
		fileMenu.add (aboutMenuItem);
		fileMenu.addSeparator ();
		fileMenu.add (exitMenuItem);

		JMenuBar bar = new JMenuBar ();
		bar.add (fileMenu);
		return bar;
	}







	/**
	 * An inner class to handle window events.
	 */
	public class WindowCloser extends WindowAdapter
	{
		//--------------------------------------------------------------
		//  Terminates the program when the window is closed.
		//--------------------------------------------------------------
		public void windowClosing (WindowEvent event)
		{
			exitMenuItem.doClick ();
		}
	}











	public class MenuListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Handles action events caused by menu selections.
		//--------------------------------------------------------------
		public void actionPerformed (ActionEvent event)
		{
			if (event.getActionCommand ().equals ("Exit"))
				System.exit (0);

			else if (event.getActionCommand ().equals ("About..."))
			{
				JOptionPane.showMessageDialog (null,
						"Snake GUI\n" +
								"Author: Christian Bazoian and Kathleen Hess\n" + 
						"Version 1.0.0.0");
			}


		}
	}		















	public static void main (String args[])
	{
		SnakeGUI application = new SnakeGUI();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end main
}//end class NewGUI