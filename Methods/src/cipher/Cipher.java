package cipher;

public class Cipher {

	public static String caesar_encode(int key, String sPlain) {
		String sCipher="";
		char[] arr= sPlain.toCharArray();

		for(int i= 0; i < arr.length; i++) {
			//GROSS
			if( arr[i]>='A' && arr[i]<= 'Z') {
				arr[i]= (char)((int)arr[i]+key);
				if(arr[i]>'Z') {
					arr[i] -=26;
				}

				//klein
			}else if( arr[i]>='a' && arr[i]<= 'z') {
				arr[i]= (char)((int)arr[i]+key);
				if(arr[i]>'z') {
					arr[i] -=26;
				}
			}
		}

		sCipher= String.copyValueOf(arr);
		return sCipher;

	}

	public static String caesar_decode(int key, String sCipher) {
		return caesar_encode(26-key, sCipher);
	}

}
