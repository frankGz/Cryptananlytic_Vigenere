package foundation;

import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


import util.CryptoTools;

public class VigGetKey {
	public static void main(String[] args) throws Exception {
		int keyLen = 9;
		LinkedList<Integer> resultSet = new LinkedList<Integer>(); // store each key letter
		Map<Integer, Double> map;
		
		byte[] ct = CryptoTools.fileToBytes("data/RPT1.ct");

		//loop over each letter in key
		for(int i = 0; i < keyLen; i++) {
			map = new HashMap<>();
			//get the subset of a position encryp with same key letter
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int pos = i; pos < ct.length; pos += keyLen)
			{
				baos.write(ct[pos]);
			}
			byte[] subset = baos.toByteArray();
			
			//do a ceasar decry
			byte[] test = new byte[subset.length];			
			int[] frequences;
			int a,j,k;
			for(a = 0; a < 26; a++) {
				
				//decrypt the ciphertext into a test byte array
				for(j=0;j<subset.length;j++) {
					test[j] = (byte) (((subset[j] - 'A')- a + 26) % 26 + 'A');
				}
				
				//Compute the frequencies of all letters in the produced test array.
				frequences = CryptoTools.getFrequencies(test);
				
				int sum = 0;
				for(k = 0; k < 26; k++) {
					sum += CryptoTools.ENGLISH[k] * frequences[k];
				}
				
				System.out.println((char) (a+'A') + " -->" + sum);
				map.put(a, (double)sum);
				//largest is the shift
			}
			
			Map.Entry<Integer, Double> maxEntry = map.entrySet().stream().max(Map.Entry.comparingByValue()).get();
			System.out.println(i + " : " + (char)(maxEntry.getKey() + 'A') + " --> " + maxEntry.getValue());
			resultSet.add(maxEntry.getKey()+ 'A');
			
		}
		System.out.println("Final resault: ");
		for(int c : resultSet) {
			System.out.print((char) c);
		}
	}
}
