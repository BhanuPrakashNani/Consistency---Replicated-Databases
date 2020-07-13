import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


// ALGO:
//Read lines:
//	if previous == entry and current == entry:
//		strict failed
//	else if current == exit
//		if current type == write:
//			arr[id] = percent
//		else if current type == read:
//			if arr[id] != percent :
//				ERROR: Strict consistency failed 

public class ConsistencyCheck {
	public static void main(String[] args) throws IOException, InterruptedException {
		
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
		while(true) {
			lineCount = Files.lines(Paths.get("Server1.log")).count();

			while(lineCount < lineno+2) {
				System.out.println("waiting to get updated");
				lineCount = Files.lines(Paths.get("Server1.log")).count();

			}
			lineno++;
			String[] arrOfStr = current.split(" ",-1);
			String[] arrOfPrev = previous.split(" ", -1);
			if(arrOfPrev[1].equals("Entry") && arrOfStr[1].equals("Entry")){
				System.out.println("!!!Consistency failed!!!  lineno: "+lineno);
				killAllProcesses();
				break;
			}
			if(arrOfStr[1].equals("Exit")){
				System.out.println("HEHE");
				if(arrOfStr[2].equals("Write")){
					System.out.println("FOUND WRITE");
					data[Integer.parseInt(arrOfStr[4])] = Integer.parseInt(arrOfStr[6]); 
				}
				else if(arrOfStr[2].equals("Read")){
					if(data[Integer.parseInt(arrOfStr[4])] != Integer.parseInt(arrOfStr[6])){
						System.out.println("!!!Consistency failed!!!  lineno: "+lineno);
						killAllProcesses();
						break;
					}
				}
			}
			for (String a: arrOfStr)
				System.out.println(a);
			previous = current;
			current = Files.readAllLines(Paths.get("Server1.log")).get(lineno);
			
		}
		
	}

public static void killAllProcesses() {
	Config.SAFE = false;
}
}
