import java.sql.*;

import javax.swing.JTextArea;



public class Driver {
	//variables
	private Connection myConn; 
	private Statement myStat; 
	private ResultSet myRs; 
	private String SQL; 
	protected String text; 
	
	
	public Driver(){ //Constructor
		
		try {
			// getConnection parameters:  connection which includes DB name, user-id, password
			myConn = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/snake", "root", "Cb10111991!");
			// create a statement
			myStat = myConn.createStatement(); 
			} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		text = " "; 
	}

	// methods that interact with the DB
	
	/**
	 * Updates the DB with the value in SQL.
	 */
	public void update(){
		try {
			myStat.executeUpdate(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Executes a SQL query in the DB
	 */
	public void query(){
		try {
			myRs = myStat.executeQuery(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Methods that alter records to the DB
	
	/**
	 * Adds an animal record into the db.
	 * @param AID
	 * @param type
	 * @param breed
	 * @param gender
	 * @param DOB entered as xxxx/xx/xx
	 */
	public void addAnimal(String AID, String type, String breed, String gender, String DOB){
		SQL = "INSERT INTO Animal (AID, Type, Breed, Gender, DOB) VALUES ('" 
		+ AID +  "', '" + type + "', '" + breed + "', '" 
				+ gender + "', '" + DOB +"')";
		//System.out.println(SQL);
		
		update(); 
	}
	
	/**
	 * Adds a supplier record into the database.
	 * @param SID
	 * @param name
	 * @param location
	 * @param dateJoined entered as xxxx/xx/xx
	 */
	public void addSupplier(String SID, String name, String location, String dateJoined){
		SQL = "INSERT INTO Supplier (SID, Name, Location, DateJoined) VALUES ('" 
		+ SID +  "', '" + name + "', '" + location + "', '" 
				+ dateJoined + " ')";
		//System.out.println(SQL);
		update(); 
	}
	
	/**
	 * Deletes an animal record in the database by AID
	 * Will delete from Animal & SupplierAnimal tables. 
	 * 
	 */
	public void deleteA(String AID){
		SQL = "DELETE FROM Animal "
				 + "WHERE AID = '" + AID + " '";
		update(); 
		SQL = "DELETE FROM SupplierAnimal "
				 + "WHERE AID = '" + AID + " '";
		update(); 
	}
	
	/**
	 * Deletes a supplier record in the database by SID
	 * Will delete from supplier & SupplierAnimal tables. 
	 * 
	 */
	public void deleteS(String SID){
		SQL = "DELETE FROM Supplier "
				 + "WHERE SID = '" + SID + " '";
		update(); 
		SQL = "DELETE FROM SupplierAnimal "
				 + "WHERE SID = '" + SID + " '";
		update(); 
	}
	
	/**
	 * Adds a new SupplierAnimal record into the database.  
	 * @param SID
	 * @param AID
	 */
	public void addSupplierAnimal(String SID, String AID){
		SQL = "INSERT INTO SupplierAnimal (SID, AID) VALUES ('" 
		+ SID +  "', '" + AID + "')";
		//System.out.println(SQL);
		update(); 
	}
	
	//methods that run queries.
	/**
	 * Returns all animal records. 
	 */
	public void selectA(){
		SQL = "SELECT * FROM Animal"; 
		query();
		text = "";
		// process the result set, returns results of query
		try {
			while (myRs.next()){
			String result = myRs.getString("AID") + " " + myRs.getString("Type") 
							+ " " + myRs.getString("Breed") + " " + myRs.getString("Gender")
							+ " " + myRs.getString("DOB") + "\n";
			text += result;
			
			//System.out.println(text);
		} 
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns all supplier records. 
	 */
	public void selectS(){
		SQL = "SELECT * FROM Supplier"; 
		query(); 
		text = "";
		// process the result set, returns results of query
		try {
			while (myRs.next()){
				String result = myRs.getString("SID") + " " + myRs.getString("Name") 
						+ " " + myRs.getString("Location") + " " + myRs.getString("DateJoined") + "\n";
				text +=result;
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns all supplierAnimal records. 
	 */
	public void selectSa(){
		SQL = "select * from SupplierAnimal"; 
		query();
		text = "";
		// process the result set, returns results of query
		try {
			while (myRs.next()){
				String result = myRs.getString("RN") + " " + myRs.getString("SID") 
						+ " " + myRs.getString("AID") + "\n";
				
				text += result;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns all animals owned by supplier selected
	 * @param args
	 */
	public void supplierA(String supplier){
		SQL = "SELECT Animal.AID, Type, Breed " 
		 + "FROM Animal, SupplierAnimal " 
		 + "WHERE Animal.AID = SupplierAnimal.AID and SID = '" + supplier + "'";
		query();
		text = "";
		// process the result set, returns results of query
		try {
			while (myRs.next()){
				String result = myRs.getString("Animal.AID") + " " + myRs.getString("Type") 
						+ " " + myRs.getString("Breed") + "\n";
				
				text += result;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns how many of one type of animal is in one location. 
	 * @param type of animal 
	 * @param location of supplier
	 */
	public void animalLocation(String type, String location){
		SQL = "SELECT count(*) FROM Animal " 
		 + "WHERE type = '" + type 
		 + "' and AID in ("
		 + "SELECT AID "
		 + "FROM SupplierAnimal, Supplier "
		 + "WHERE SupplierAnimal.SID = Supplier.SID and "
		 + "Location = '" + location + "')"; 
		
		query(); 
		text = "";
		
		// process the result set, returns results of query
				try {
					while (myRs.next()){
						String result = myRs.getString("count(*)") + "\n";
						text += result;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	/**
	 * Returns the suppliers, what types they have, and how many
	 * of each type. 
	 */
	public void animalCount(){
		SQL = "Select SID, Type, count(Type) as num" 
				+ " FROM SupplierAnimal, Animal"
				+ " WHERE Animal.AID = SupplierAnimal.AID"
				+ " GROUP BY SID, Type"; 
		query(); 
		text = "";
		 //process the result set, returns results of query
		try {
			while (myRs.next()){
				String result = myRs.getString("SID") + " " + myRs.getString("Type") + " " 
						+ myRs.getString("num") + "\n";
				
				text += result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		//test run methods
		Driver test = new Driver();  
		//test.addAnimal("A010","Snake", "Fancy Corn Snake", "M", "2010/02/28" ); 
		//test.addSupplier("S003", "Miller", "Salem", "2013/11/02");
		//test.addSupplierAnimal("S003", "A010"); 
		//test.selectA();
		//test.selectS(); 
		//test.selectSa(); 
		//test.supplierA("S001");
		//test.animalLocation("Snake", "Salem");
		//test.animalCount(); 
		//System.out.println(test.SQL);
		//test.deleteA("A009");
		//test.deleteS("S001");
		//System.out.println(test.SQL);
		
}
	

}
