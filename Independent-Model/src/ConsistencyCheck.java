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
		String Row = "4";
		for (int i = 1; i < 5; i++) {
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
			Thread.sleep(5000);
			int lineno = 2;
			long lineCount = Files.lines(Paths.get("Server1.log")).count();
	
			while(lineCount < lineno+1) {
				System.out.println("waiting to get updated");
				lineCount = Files.lines(Paths.get("Server1.log")).count();
	
			}
			String current = Files.readAllLines(Paths.get("Server1.log")).get(2);
			String previous = Files.readAllLines(Paths.get("Server1.log")).get(1);
			int[] data = new int[10];
			Queue<QP> q1 = new LinkedList<>();
			Queue<QP> qwrites = new LinkedList<>();
			Queue<Integer> q2 = new LinkedList<>();
			Queue<QP> que = new LinkedList<>();
			Queue<QP> order = new LinkedList<>();
			while(true) {
				lineCount = Files.lines(Paths.get("Server1.log")).count();
	
				// while(lineCount < lineno+2) {
				// 	System.out.println("waiting to get updated");
				// 	lineCount = Files.lines(Paths.get("Server1.log")).count();
	
				// }
				if (lineCount < lineno +2)
					break;
				lineno++;
				String[] arrOfStr = current.split(" ",-1);
				if(arrOfStr[0].equals(Process)&&arrOfStr[2].equals("Write") && arrOfStr[1].equals("Exit") && arrOfStr[4].equals(row)){
					boolean found = false;
					System.out.println(arrOfStr);
						QP qt = new QP(Integer.parseInt(arrOfStr[6]),Integer.parseInt(arrOfStr[8]));
						qwrites.add(qt);
	
				}
				if(arrOfStr[2].equals("Read") && arrOfStr[1].equals("Exit") && arrOfStr[4].equals("6")){
					System.out.println(arrOfStr[0] +"->  :(  "+arrOfStr[4]+" , "+arrOfStr[6]);
					boolean found = false;
					QP temp;
					for (QP item: order) {
						temp = item;
						if(item.getData() == Integer.parseInt(arrOfStr[6]) && item.getClock() == Integer.parseInt(arrOfStr[8])){
							found = true;
							break;
						}
					}
					if(!found){
						QP qt = new QP(Integer.parseInt(arrOfStr[6]),Integer.parseInt(arrOfStr[8]));
						order.add(qt);
						que.add(qt);
	
					}
					if(arrOfStr[0].equals(Process)){
						QP qt = new QP(Integer.parseInt(arrOfStr[6]),Integer.parseInt(arrOfStr[8]));
						q1.add(qt);
	
						int c =0;
						boolean f = false;
						for (QP item: que){
							c++;
							if(item.getData() == Integer.parseInt(arrOfStr[6]) && item.getClock() == Integer.parseInt(arrOfStr[8])){
								f = true;
								//System.out.println("hehehe "+c+"  "+item);
								break;
							}
						}
						for (int i = 0; i < c-1; i++) {
							que.remove();
						}
						if(!f){
							System.out.println("BREAK");
							System.out.println(Process+"Queue: order: ");
							for (QP item: order){
								System.out.print(item.getData()+", ");
							}
							System.out.println();

							System.out.println(Process+"Queue: reads q "); 
							for (QP item: q1){
								System.out.print(item.getData()+", ");
							}
							System.out.println();
							
							System.out.println(Process+"Queue: qwrites ");
							for (QP item: qwrites){
								System.out.print(item.getData()+", ");
							}
							System.out.println();
							break;
						}
					}
	
					// System.out.println("Queue: order " + order); 
					// System.out.println("Queue: que " + que); 
	
	
					
				}
				// for (String a: arrOfStr)
				// 	System.out.println(a);
				current = Files.readAllLines(Paths.get("Server1.log")).get(lineno);
				
			}
			System.out.println(Process+"Queue: order: ");
			for (QP item: order){
				System.out.print(item.getData()+", ");
			}
			System.out.println();

			System.out.println(Process+"Queue: reads q "); 
			for (QP item: q1){
				System.out.print(item.getData()+", ");
			}
			System.out.println();
			
			System.out.println(Process+"Queue: qwrites ");
			for (QP item: qwrites){
				System.out.print(item.getData()+", ");
			}
			System.out.println();
			
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
