
public class Student implements java.io.Serializable {

	private int id, percent, clock;   
	   private String name, branch, email;    
	  
	   public int getId() { 
	      return id; 
	   } 
	   public int getClock() { 
		      return clock; 
		   } 
	   public String getName() { 
	      return name; 
	   } 
	   public String getBranch() { 
	      return branch; 
	   } 
	   public int getPercent() { 
	      return percent; 
	   } 
	   public String getEmail() { 
	      return email; 
	   } 
	   public void setID(int id) { 
	      this.id = id; 
	   } 
	   public void setClock(int c) { 
		      this.clock = c; 
	   } 
	   public void setName(String name) { 
	      this.name = name; 
	   } 
	   public void setBranch(String branch) { 
	      this.branch = branch; 
	   } 
	   public void setPercent(int percent) { 
	      this.percent = percent; 
	   } 
	   public void setEmail(String email) { 
	      this.email = email; 
	   } 
}
