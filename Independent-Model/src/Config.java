
public class Config {
	static boolean[] synchStart = {false,false,false,false,false};
	static int p1sleep=2000;
	static int p2sleep=2000;
	static int p3sleep;
	static int p4sleep=2000;
	static boolean SAFE = true;
	
	public static boolean getSAFE() {
		return SAFE;
	}
	public static void setSAFE(boolean val) {
		SAFE = val;
	}
}
