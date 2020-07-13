import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class container1 implements hello{
	static int status;
	int[] dbstatus = new int[]{ 0, 0, 0, 0};
	int[] dbstatus1 = new int[]{ 0, 0, 0, 0};
	String msg =",";
	String insert;
	 int i=0;
	 int id=0;
	 int l=-100;
	 int k=-1;
	 String name;
	 int ii=10;
	  Queue<String> queue = new LinkedList<>();
	public Student getStudents(int k,int j) throws Exception, ClassNotFoundException {  
			List<Student> list = new ArrayList<Student>();   
	      // JDBC driver name and database URL 
	      String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
	      
  
	      
	      Connection conn = null; 
	      Statement stmt = null;  

	      //Register JDBC driver 
	        
	      //Open a connection
	      System.out.println("Connecting to a selected database..."); 
	      conn = DriverManager.getConnection(DB_URL, "newuser", "password"); 
	      System.out.println("Connected database successfully...");  
	      
	      //Execute a query 
	      System.out.println("Creating statement..."); 
	      	String sql;
	      stmt = conn.createStatement(); 
	      if(j==0) {
	      sql = "SELECT * FROM samplermi where sno = " + Integer.toString(k) + " and name = 'Mani';";
	      }
	      else if(j==1) {
		  sql = "SELECT * FROM samplermi where sno = " + Integer.toString(k) + " and name = 'bhanu';";
		      }
	      else if(j==2) {
		  sql = "SELECT * FROM samplermi where sno = " + Integer.toString(k) + " and name = 'abhinav';";
		      }
	      else {
		  sql = "SELECT * FROM samplermi where sno = " + Integer.toString(k) + " and name = 'shrestha';";
		      }
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      
	      //Extract data from result set 
	      Student student = new Student(); 
	      
	      student.setID(-1);
	      if(rs.next()) {
	         // Retrieve by column name 
	         int id  = rs.getInt("sno");	
	         String name = rs.getString("name"); 
	         String branch = rs.getString("branch"); 
	         
	         int percent = rs.getInt("percentage"); 
	         String email = rs.getString("email");  
	         
	         // Setting the values 
	        
	         student.setID(id); 
	         student.setName(name); 
	         student.setBranch(branch); 
	         student.setPercent(percent); 
	         student.setEmail(email); 
	         list.add(student); 
	      }
	      
	      rs.close();
	      conn.close();
	      return student;     
	      
	      }
	public void addStudent(int id,int k)throws Exception, ClassNotFoundException {
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	    	  this.id=id;
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
	      
 
	      i++;
	      Connection conn = null; 
	      Statement stmt = null;  

	      //Register JDBC driver 
	        
	      //Open a connection
	      System.out.println("Connecting to a selected database..."); 
	      conn = DriverManager.getConnection(DB_URL, "newuser", "password"); 
	      System.out.println("Connected database successfully...");  
	      
	      //Execute a query 
	      System.out.println("Creating statement...");
	     
	      
	      String name = "Mani";
	      String branch = "cse";
	      int percent = i;
	      
	      String email = "mani.gmail";
	      
	      stmt = conn.createStatement();
	      //ResultSet rs = stmt.executeQuery(sql);  
	      insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values('"+id+"','"+name+"','"+branch+"','"+percent+"','"+email+"')";
	      int count=stmt.executeUpdate(insert);
	      conn.close();
//	      dbstatus[2] =0;
	      status = 0;
	      System.out.println(count);     

	}
	
	
	
	
	public void updateStudent(int id,int k)throws Exception, ClassNotFoundException {
		
		String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	      try {
	    	  Class.forName(JDBC_DRIVER); 
	    	  this.k=k;
	      }
	      catch(ClassNotFoundException e) {
	    	System.out.println("SWWWWWERRRRRR");
	    	e.printStackTrace();
	      }
	      String DB_URL = "jdbc:mysql://localhost:3306/rmi";  
	      
 
	    ii++;  
	      Connection conn = null; 
	      Statement stmt = null;  

	      //Register JDBC driver 
	        
	      //Open a connection
	      System.out.println("Connecting to a selected database..."); 
	      conn = DriverManager.getConnection(DB_URL, "newuser", "password"); 
	      System.out.println("Connected database successfully...");  
	      
	      //Execute a query 
	      System.out.println("Updating statement...");
	     

	      
	      stmt = conn.createStatement();
	      //ResultSet rs = stmt.executeQuery(sql);  
	      insert = "UPDATE samplermi SET percentage = " + Integer.toString(ii) + " where sno = " + Integer.toString(k)+" ;";
	      int count=stmt.executeUpdate(insert);

//	      dbstatus[2] =0;
	      status = 0;
	      System.out.println(count);     

	}
	
	
	
	
    @Override
    public void sendMessage(String s) throws RemoteException {
        System.out.println(s);
        msg = s;
        status++;
    }

    
    
    public String insert_container() throws RemoteException{
    	
    	String name = "Mani";
	    String branch = "cse";
	    int percent = i;
	    
	    String email = "mani.gmail";
    	
	    String insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values('"+id+"','"+name+"','"+branch+"','"+percent+"','"+email+"')";
    	
	    return insert;
    }
    public String insert_container1() throws RemoteException{

    	
    	insert = "UPDATE samplermi SET percentage = " + Integer.toString(ii) + " where sno = " + Integer.toString(k)+" and name = \""+ this.name + "\" ;";
    	return insert;
    }
    @Override
    public String getMessage() throws RemoteException {
    	
    	status--;
        return "Your message is: " + msg;
        
    }
    @Override
    public void notify(int i) throws RemoteException{
    	
    	dbstatus[i]--;
    
    }
    
    
    public int dbstatus(int i) throws RemoteException{
    	
    	return dbstatus[i];
    
    }
    
    @Override
    public void set_dbstatus(int i, int val) throws RemoteException{
    	
    	dbstatus[i]=val;
    
    }
    
    @Override
    public void notify1(int i) throws RemoteException{
    	
    	dbstatus1[i]--;
    
    }
    
    
    public int dbstatus1(int i) throws RemoteException{
    	
    	return dbstatus1[i];
    
    }
    
    
    @Override
    public void set_dbstatus1(int i, int val) throws RemoteException{
    	
    	dbstatus1[i]=val;
    
    }
    
    
    @Override
    public String get_insert() throws RemoteException{
    	
    	return insert;
    
    }
    
    
    public int dbstatus() throws RemoteException{
    	
    	int status = 0;
    	
    	for (int i=0; i<4; i++) {
    		status = status + dbstatus[i];
    	}
    	
    	status = status - dbstatus[2];

    	return status;
    }
    
    public int getStatus() throws RemoteException{
    	return status;
    }
    public void setStatus() throws RemoteException{
    	status = 1;
    }
    
    public void request_write(int id, int k) throws RemoteException{
    	this.id = id;
    	i++;
    	String name = "Mani";
	    String branch = "cse";
	    int percent = i;  
	    String email = "mani.gmail";
	    
	    insert = "INSERT INTO samplermi(sno, name, branch, percentage, email) values('"+id+"','"+name+"','"+branch+"','"+percent+"','"+email+"')";
	    queue.add(insert);
    	    	
    		
    }
    public void request_update(int y, int k, String name) throws RemoteException{
    	this.k = k;
    	ii*=2;
	    insert = "UPDATE samplermi SET percentage = " + Integer.toString(ii) + " where sno = " + Integer.toString(k)+" and name = \""+ name + "\" ;";
	    this.name=name;
	    if(y==50) {
	    	k=-1;
	    }
    	queue.add(insert);	
    }
    public void request_write_others(String insert) throws RemoteException{
    	queue.add(insert); 	
    }
    
    public void request_update_others(String insert) throws RemoteException{
    	queue.add(insert);
    }
    
    public Queue<String> queue() throws RemoteException{
    	return this.queue;
    }
    public void clearqueue() throws RemoteException{
    	queue.clear();
    }

	public int getRandupd() throws RemoteException{
		return k;
	}
	public void setRandupd(int kval) throws RemoteException{
		this.k = kval;
	}
    public String getRandname() {
    	return this.name;
    }
    public void setRandname(String name) {
    	this.name = name;
    }
}
