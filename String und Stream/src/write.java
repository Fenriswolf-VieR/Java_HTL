import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class write {
	
	public static void main (String []args) {
		write_method();
	}
	
	static void write_method() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Fenris\\Desktop\\input.txt"));
			//writer.write("<show>Writer works too.\n");
			writer.write("<show>I see no god up here !!!\n");
			writer.write("<show>Besides me !!!\n");
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
