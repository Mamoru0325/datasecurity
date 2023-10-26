package th.ac.ku.kps.eng.cpe.datasecurity.ciphercode;

/* 
   Vigenere Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

import java.util.HashMap;
import java.util.Map;

public class Vigenere_Cipher {
	/* Character to integer map */
	private Map<Character, Integer> char_to_int = new HashMap<Character, Integer>();
	
	/* Integer to character map */
	private Map<Integer, Character> int_to_char = new HashMap<Integer, Character>();
	/* e_k(x) = x_i + K_j mod 26 */
	/* j = i mod m where m is length of K */
	public String encrypt(String x, String K)
	{
		// Split x into substrings
		int cnt = (int)Math.ceil((double)x.length() / K.length());
		String[] x_substrings = new String[cnt];
		for(int i = 0, idx = 0; i < x.length(); i+=K.length())
		{
			x_substrings[idx++] = x.substring(i, Math.min(i + K.length(), x.length()));
		}
		
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x_substrings.length; i++)
		{
			for(int j = 0; j < x_substrings[i].length(); j++)
			{
				int x_i = char_to_int.get(x_substrings[i].toLowerCase().charAt(j));
			    int K_j = char_to_int.get(K.toLowerCase().charAt(j));
				int y_i = (x_i + K_j) % 26;
			    y_i = (y_i + 26) % 26;
		    
			    y += int_to_char.get(y_i);
			}
		}
		
		return y;
	}
	
	/* d_k(y) = y_i - K_j mod 26 */
	/* j = i mod m where m is length of K */
	public String decrypt(String y, String K)
	{
		// Split y into substrings
		int cnt = (int)Math.ceil((double)y.length() / K.length());
		String[] y_substrings = new String[cnt];
		for(int i = 0, idx = 0; i < y.length(); i+=K.length())
		{
			y_substrings[idx++] = y.substring(i, Math.min(i + K.length(), y.length()));
		}
		
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y_substrings.length; i++)
		{
			for(int j = 0; j < y_substrings[i].length(); j++)
			{
			    int y_i = char_to_int.get(y_substrings[i].toLowerCase().charAt(j));
			    int K_j = char_to_int.get(K.toLowerCase().charAt(j));
			    int x_i = (y_i - K_j) % 26;
			    x_i = (x_i + 26) % 26;
			    
			    x += int_to_char.get(x_i);
			}
		}
		
		return x;
	}
	
	/* Vigenere_Cipher constructor */
	public Vigenere_Cipher()
	{
		// Populate char_to_int and int_to_char maps
		for(int i = 0; i < 26; i ++)
		{
			char_to_int.put((char) ('a' + i), 0 + i);
			int_to_char.put(0 + i, (char) ('a' + i));
		}
	}
}
