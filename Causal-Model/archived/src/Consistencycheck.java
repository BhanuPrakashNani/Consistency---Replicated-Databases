import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import javax.swing.plaf.synth.SynthOptionPaneUI;

import java.util.*;



public class Consistencycheck{
	public static void main(String[] args) {
		
		
		int[] vals1 = new int[10];
		int[] vals2 = new int[10];
		int[] vals3 = new int[10];
		int[] vals4 = new int[10];
		
		int[]write = new int[10];
		
	    try {
	        File myObj = new File("process2.log");
	        Scanner myReader = new Scanner(myObj);
	        
	        while (myReader.hasNextLine()) {
	       
	          
	          String data = myReader.nextLine();
	          char[] arr = data.toCharArray();
	          
	          
	          
	          if(arr[4]=='I') {
	        	  
	        	  String[] parts = data.split("\'");
	        	  int sno = Integer.parseInt(String.valueOf(parts[1]));
	        	  String s = parts[7];
	        	  if(parts[3]=="Mani") {
	        		  vals1[sno] = Integer.parseInt(String.valueOf(s));  
	        	  }
	        	  else if(parts[3]=="bhanu") {
	        		  vals2[sno] = Integer.parseInt(String.valueOf(s));  
	        	  }
	        	  else if(parts[3]=="abhinav") {
	        		  vals3[sno] = Integer.parseInt(String.valueOf(s));  
	        	  }
	        	  else if(parts[3]=="shrestha") {
	        		  vals4[sno] = Integer.parseInt(String.valueOf(s));  
	        	  }
	        	  
	        		  
	        	  
	        	  
	        	  System.out.println("Done1"); 
	        	  }
	        	  
	          else if(arr[4]=='U') {
	        	  String[] parts = data.split(" ");
	        	  int sno = Integer.parseInt(String.valueOf(parts[10]));
	        	  int per = Integer.parseInt(String.valueOf(parts[6]));
	        	  
	        	  if(parts[3]=="\"mani\"") {
	        		  vals1[sno] = Integer.parseInt(String.valueOf(per));  
	        	  }
	        	  else if(parts[14]=="\"bhanu\"") {
	        		  vals2[sno] = Integer.parseInt(String.valueOf(per));  
	        	  }
	        	  else if(parts[14]=="\"abhinav\"") {
	        		  vals3[sno] = Integer.parseInt(String.valueOf(per));  
	        	  }
	        	  else if(parts[14]=="\"shrestha\"") {
	        		  vals4[sno] = Integer.parseInt(String.valueOf(per));  
	        	  }
	        	  
	        	 
	        	  System.out.println("Done2");
	        	  
	          }

	          else if((arr[4])=='E' && arr[5]=='x' && arr[9]=='r') {
	        	  String[] parts = data.split(" ");
	        	  	if(  String.valueOf(parts[1]).contentEquals("Exit") && String.valueOf(parts[2]).contentEquals("read") && String.valueOf(parts[5]).contentEquals("percent:")) {
	        	  		int decider = Integer.parseInt(String.valueOf(parts[6]));
	        	  		int sno = Integer.parseInt(String.valueOf(parts[4]));
	        	  		
	        	  		
	        	  		
	        	  		if(parts[8]=="mani") {
	        	  			if(vals1[sno]!=decider) {
		        	  			System.out.println("Error for "+ data);
		        	  			System.out.println(vals1[sno]	);
		        	  			return;
		        	  		}
	        	  		}
	        	  		if(parts[8]=="bhanu") {
	        	  			if(vals2[sno]!=decider) {
		        	  			System.out.println("Error for "+ data);
		        	  			System.out.println(vals2[sno]	);
		        	  			return;
		        	  		}
	        	  		}
	        	  		if(parts[8]=="abhinav") {
	        	  			if(vals3[sno]!=decider) {
		        	  			System.out.println("Error for "+ data);
		        	  			System.out.println(vals1[3]	);
		        	  			return;
		        	  		}
	        	  		}
	        	  		if(parts[8]=="shrestha") {
	        	  			if(vals4[sno]!=decider) {
		        	  			System.out.println("Error for "+ data);
		        	  			System.out.println(vals1[3]	);
		        	  			return;
		        	  		}
	        	  		}
	        	  		
	        	  		
	        	  		System.out.println("Done3");
	        	  	}
	          }
	          
	          
	          
	              
	    } 
	     System.out.println("Done");
	        
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	   
		
		
		
		
		
		
	}
}