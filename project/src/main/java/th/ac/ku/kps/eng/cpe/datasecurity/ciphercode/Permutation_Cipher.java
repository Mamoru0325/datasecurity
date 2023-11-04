package th.ac.ku.kps.eng.cpe.datasecurity.ciphercode;

/* 
   Permutation Cipher 
   Tyler Phillips 
   phillity@iu.edu 
*/

import java.util.Random;


public class Permutation_Cipher {
	/* pi permutation map */
	private int pi[];
	
	/* pi_inv permutation map */
	private int pi_inv[];
	
	private Random random = new Random();
	
	private int diff;
	
	/* e_k(x) = pi(x_i) */
	public String encrypt(String x)
	{
		
		// Split x into substrings
		int cnt = (int)Math.ceil((double)x.length() / pi.length);
		String[] x_substrings = new String[cnt];
		
		if(x.length() % pi.length != 0 ) {
			diff = cnt - (x.length() % pi.length);
		}
		
		for(int i = 0, idx = 0; i < x.length(); i+=pi.length)
		{

			x_substrings[idx] = x.substring(i, Math.min(i + pi.length, x.length()));
			if(x_substrings[idx].length() != cnt) {
				
				StringBuilder randomString = new StringBuilder(diff);
				
				for(int j = 0 ; j < diff ; j++) {
					int randomNumber = random.nextInt(26);
					char randomLowercaseLetter = (char) ('a' + randomNumber);
					randomString.append(randomLowercaseLetter);
				}
				x_substrings[idx] = x_substrings[idx] + randomString.toString();
			}
			idx++;

		}
		
		
		// Initialize output ciphertext string
		String y = "";
		
		// Perform encryption
		for(int i = 0; i < x_substrings.length; i++)
		{
			for(int j = 0; j < x_substrings[0].length(); j++)
			{
				y += x_substrings[i].charAt(pi[j]-1);
			}
		}
		
		return y;
	}
	
	/* d_k(y) = pi_inv(y_i) */
	public String decrypt(String y)
	{
		// Split y into substrings
		int cnt = (int)Math.ceil((double)y.length() / pi_inv.length);
		String[] y_substrings = new String[cnt];
		for(int i = 0, idx = 0; i < y.length(); i+=pi_inv.length)
		{
			y_substrings[idx++] = y.substring(i, Math.min(i + pi_inv.length, y.length()));
		}
		
		// Initialize output plaintext string
		String x = "";
		
		// Perform decryption
		for(int i = 0; i < y_substrings.length; i++)
		{
			for(int j = 0; j < y_substrings[0].length(); j++)
			{
				x += y_substrings[i].charAt(pi_inv[j]-1);
			}
		}

		return x;
	}
	
	/* Permutation_Cipher constructor */
	public Permutation_Cipher(int[] input_pi) 
	{ 
		pi = input_pi;
		pi_inv = new int[pi.length];
		for(int i = 0; i < pi.length; i++) 
			pi_inv[pi[i]-1] = i+1;
			
	}
}
