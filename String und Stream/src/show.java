import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class show {

	public static void main(String[] args) throws IOException {
		show_method("C:\\Users\\Fenris\\Desktop\\input.txt");
	}
	
	static void show_method(String path) throws IOException{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line=reader.readLine())!=null) {
				if(line.startsWith("<show>")) {
					java.lang.String output = line.substring(6);
					System.out.println(output);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
