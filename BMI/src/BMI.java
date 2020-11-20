
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BMI {

	
	public static void main(String[] args) throws IOException {
		int number_lines;
		String NName;
		String VName;
		double n;//größe
		double g;//gewicht
				
		//Eingabe
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Fenris\\Desktop\\bmi.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Fenris\\Desktop\\bmi_out.txt"));
			number_lines=Integer.parseInt(reader.readLine());
			writer.write(number_lines);
			for(int i=0;i<number_lines;i++) {
				String input = reader.readLine();
				String[] sHalve;
				sHalve = input.split(" ");
				NName=sHalve[0];
				VName=sHalve[1];
				n=Double.parseDouble(sHalve[2])/100;
				g=Double.parseDouble(sHalve[3]);
				calculate_bmi(NName,VName,n,g,writer);
			}
			writer.close();
			reader.close();
			
	}
	static void calculate_bmi(String NName,String VName,double n, double g, BufferedWriter writer) throws IOException {
		//Berechnung der Teilbereiche
			double e=g/(n*n);
		//Prüfung der Werte mithilfe von Werten aus dem Internet
			if(e<20) {
				System.out.println(NName+VName+" ist untergewichtig. BMI = "+e);
				}
			if(20<e && e<=25){
				System.out.println(NName+" "+VName+" ist Normalgewichtig. BMI = "+e);
			}
			if(25<e && e<=30){
				System.out.println(NName+" "+VName+" ist übergewichtig. BMI = "+e);
			}
			if(30<e && e<=40){
				System.out.println(NName+" "+VName+" ist adipös. BMI = "+e);
			}
			if(40<e){
				System.out.println(NName+" "+VName+" ist stark adipös. BMI = "+e);
		  }	
			output_results(NName,VName,n,g,writer,e);
	}
	
	static void output_results(String NName,String VName,double n, double g, BufferedWriter writer, double e) throws IOException {
		writer.write(NName);
		writer.write(VName);
		writer.write((int) n);
		writer.write((int) g);
		writer.write((int) e);
	}
}

