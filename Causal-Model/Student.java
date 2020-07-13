import java.	util.*;
public class Student implements java.io.Serializable {

	private int id, percent, clock;
	private String name, branch, email;
	private int[] v_clock = new int[5];

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
	   public int getV_clock(int i){
	   		return v_clock[i];
	   }
	   public void setV_clock(int i, int j){
	   		this.v_clock[i]=j;
	   }
}
