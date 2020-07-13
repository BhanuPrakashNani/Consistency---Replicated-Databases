
public class Student implements java.io.Serializable {

	private int id, percent;   
	   private String name, branch, email;    
	  
	   public int getId() { 
	      return id; 
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

