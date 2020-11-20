package Fussbalmanager;

public class person {
	public String name;
	public int age; 
	
	public person(String n, int a) {
		name = n;
		age =a;
	}
	public String getName(){
		return name;
		}
	public void setName(String n){
		name = n;
		}       
	public int getAge(){
		return age;
		}
	public void setAge(int a){
		age = a;
		}       
}
