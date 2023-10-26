package th.ac.ku.kps.eng.cpe.datasecurity.ciphercode;

/* 
   Shift (Caesar) Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

import java.util.HashMap;
import java.util.Map;

public class Shift_Cipher {
	/* Character to integer map */
	private Map<Character, Integer> char_to_int = new HashMap<Character, Integer>();
	
	/* Integer to character map */
	private Map<Integer, Character> int_to_char = new HashMap<Integer, Character>();
	
	/* e_k(x) = x_i + K_i mod 26 */
	public String encrypt(String x, int K)
	{
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x.length(); i++)
		{
		    int x_i = char_to_int.get(x.toLowerCase().charAt(i));
		    int y_i = (x_i + K) % 26;
		    y_i = (y_i + 26) % 26;
		    
		    y += int_to_char.get(y_i);
		}
		
		return y;
	}
	
	/* d_k(y) = y_i - K_i mod 26 */
	public String decrypt(String y, int K)
	{
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y.length(); i++)
		{
		    int y_i = char_to_int.get(y.toLowerCase().charAt(i));
		    int x_i = (y_i - K) % 26;
		    x_i = (x_i + 26) % 26;
		    
		    x += int_to_char.get(x_i);
		}
		
		return x;
	}
	
	/* Shift_Cipher constructor */
	public Shift_Cipher()
	{
		// Populate char_to_int and int_to_char maps
		for(int i = 0; i < 26; i ++)
		{
			char_to_int.put((char) ('a' + i), 0 + i);
			int_to_char.put(0 + i, (char) ('a' + i));
		}
	}
}
