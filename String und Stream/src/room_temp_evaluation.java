import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class room_temp_evaluation {

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> sValue = new ArrayList<Integer>();
		ArrayList<String> sRoom = new ArrayList<String>();
		String path = "C:\\Users\\Fenris\\Desktop\\input.txt";
		eveluate_room_temperature(path,sValue,sRoom);
	}

	static void eveluate_room_temperature(String path,ArrayList<Integer> sValue,ArrayList<String> sRoom) throws IOException {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		try {
			BufferedReader evaluate = new BufferedReader(new FileReader(path));
			String room_temperature;
			
			
			int i=0;
			while ((room_temperature=evaluate.readLine())!=null) {
					String[] sHalve;
					sHalve = room_temperature.split(":");
					sRoom.add(i, sHalve[0]);
					sValue.add(i,Integer.parseInt(sHalve[1]));
				i++;
			}
			evaluate.close();
			for(int j=0;j<sValue.size();j++) {
				if(sValue.get(j)>19) {
					indexes.add(j);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		output_room_temperature(sValue,sRoom, indexes);
		average_temp(sValue);
	}
	static void output_room_temperature(ArrayList<Integer> sValue,ArrayList <String> sRoom, ArrayList<Integer> indexes) {
		for(int i=0;i<indexes.size();i++) {
			int open=indexes.get(i);
			System.out.println("Fenster öffnen in " + sRoom.get(open)+". Jetzige Temperatur: "+sValue.get(open));
		}

	}
	static void average_temp(ArrayList<Integer> sValue) {
		int sum_temp = 0;
		for(int i=0;i<sValue.size();i++) {
			sum_temp = sum_temp + sValue.get(i);
		}
		System.out.println("Die Durchschnittstemperatur aller Räume beträgt: "+ sum_temp/(sValue.size()));
	}

}
