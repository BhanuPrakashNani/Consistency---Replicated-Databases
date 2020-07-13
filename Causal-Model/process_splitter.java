import java.util.*;
import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; // Import the Scanner class to read text files


public class process_splitter{
    public static void main(String[] args) {
        try {
              File myObj = new File("Writers.log");
              Scanner myReader = new Scanner(myObj);
              while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char[] ch = data.toCharArray();

                if(ch[1] =='1') {


                try {
                      FileWriter logwtr = new FileWriter("process1.log",true);
                      BufferedWriter bw = new BufferedWriter(logwtr);
                      PrintWriter pw = new PrintWriter(bw);
                      System.out.println("LOGGING");

                      pw.println(data);

//                    logwtr.append();
                         pw.flush();
                      logwtr.close();

                      System.out.println("Successfully finished writing to the file.");
                    }
                 catch (IOException e) {
                      System.out.println("An error occurred.");
                      e.printStackTrace();
                    }
                }




                else if(ch[1] =='2') {


                try {
                      FileWriter logwtr = new FileWriter("process2.log",true);
                      BufferedWriter bw = new BufferedWriter(logwtr);
                      PrintWriter pw = new PrintWriter(bw);
                      System.out.println("LOGGING");

                      pw.println(data);

//                    logwtr.append();
                         pw.flush();
                      logwtr.close();

                      System.out.println("Successfully finished writing to the file.");
                    }
                 catch (IOException e) {
                      System.out.println("An error occurred.");
                      e.printStackTrace();
                    }
                }


                else if(ch[1] =='3') {


                try {
                      FileWriter logwtr = new FileWriter("process3.log",true);
                      BufferedWriter bw = new BufferedWriter(logwtr);
                      PrintWriter pw = new PrintWriter(bw);
                      System.out.println("LOGGING");

                      pw.println(data);

//                    logwtr.append();
                         pw.flush();
                      logwtr.close();

                      System.out.println("Successfully finished writing to the file.");
                    }
                 catch (IOException e) {
                      System.out.println("An error occurred.");
                      e.printStackTrace();
                    }
                }

                else if(ch[1] =='4') {


                try {
                      FileWriter logwtr = new FileWriter("process4.log",true);
                      BufferedWriter bw = new BufferedWriter(logwtr);
                      PrintWriter pw = new PrintWriter(bw);
                      System.out.println("LOGGING");

                      pw.println(data);

//                    logwtr.append();
                         pw.flush();
                      logwtr.close();

                      System.out.println("Successfully finished writing to the file.");
                    }
                 catch (IOException e) {
                      System.out.println("An error occurred.");
                      e.printStackTrace();
                    }
                }

                else if(ch[1] =='5') {


                try {
                      FileWriter logwtr = new FileWriter("process5.log",true);
                      BufferedWriter bw = new BufferedWriter(logwtr);
                      PrintWriter pw = new PrintWriter(bw);
                      System.out.println("LOGGING");

                      pw.println(data);

//                    logwtr.append();
                         pw.flush();
                      logwtr.close();

                      System.out.println("Successfully finished writing to the file.");
                    }
                 catch (IOException e) {
                      System.out.println("An error occurred.");
                      e.printStackTrace();
                    }

                }

                else {
                    System.out.println(ch[1]);
                }


              }
              myReader.close();
            }


        catch (FileNotFoundException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
    }
}
