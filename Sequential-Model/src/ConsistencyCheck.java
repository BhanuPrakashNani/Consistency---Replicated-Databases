import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

public class ConsistencyCheck {
    public static void main(String[] args) throws IOException, InterruptedException {
		String Row = "0";
		for (int i = 1; i < 6; i++) {
                String PStr = "P"+i+":";
                CheckT chk = new CheckT(PStr, Row);
                Thread t = new Thread(chk);
                t.start();
            }
            
    }
}

class CheckT implements Runnable {
    String Process;
    String row;

    CheckT(String proc, String rw){
        this.Process = proc;
        this.row = rw;
    }

    @Override
    public void run(){
        try {
			String file_name ="LOG.log";
			Thread.sleep(5000);
			int lineno = 2;
			long lineCount = Files.lines(Paths.get(file_name)).count();
	
			while(lineCount < lineno+1) {
				System.out.println("waiting to get updated");
				lineCount = Files.lines(Paths.get(file_name)).count();
	
			}
			String current = Files.readAllLines(Paths.get(file_name)).get(2);
			String previous = Files.readAllLines(Paths.get(file_name)).get(1);
			int[] data = new int[10];
			Queue<QP> q1 = new LinkedList<>();
			Queue<QP> qwrites = new LinkedList<>();
			Queue<Integer> q2 = new LinkedList<>();
			Queue<QP> que = new LinkedList<>();
			Queue<QP> order = new LinkedList<>();
			while(true) {
				lineCount = Files.lines(Paths.get(file_name)).count();
	
				// while(lineCount < lineno+2) {
				// 	System.out.println("waiting to get updated");
				// 	lineCount = Files.lines(Paths.get(file_name)).count();
	
				// }
				if (lineCount < lineno +2)
					break;
				lineno++;
				String[] arrOfStr = current.split(" ",-1);
				if(arrOfStr[0].equals(Process)&&arrOfStr[2].equals("Write") && arrOfStr[1].equals("Exit") && arrOfStr[4].equals(row)){
					QP qt = new QP(Integer.parseInt(arrOfStr[6]),Integer.parseInt(arrOfStr[8]));
					qwrites.add(qt);
	
				}
				current = Files.readAllLines(Paths.get(file_name)).get(lineno);
				
			}
			String filename = new String(Process+".txt");

			try {
	            FileWriter logwtr = new FileWriter(filename,true);
	            BufferedWriter bw = new BufferedWriter(logwtr);
	            PrintWriter pw = new PrintWriter(bw);
	            System.out.println("LOGGIGN");

				pw.println(Process+"Queue: qwrites ");
				for (QP item: qwrites){
					pw.print("("+item.getData()+", "+item.getClock()
					+")   ,  ");
				}
	            pw.flush();
	            logwtr.close();
	            
//		 		      System.out.println("Successfully wrote to the file.");
	          } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
			
      }
    catch(Exception e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
    }
}
}
/**
 * InnerConsistencyCheck
 */
class QP {
	int data;
	int clock;

	QP(int a, int b){
		data = a;
		clock = b;
	}

	public int getClock() {
		return clock;
	}

	public int getData() {
		return data;
	}
}
