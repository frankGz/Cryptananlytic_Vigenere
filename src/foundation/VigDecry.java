package foundation;

import util.CryptoTools;

public class VigDecry {

	public static void main(String[] args) throws Exception {
		byte[] ct = CryptoTools.fileToBytes("data/RPT1.ct");
		byte[] key = "CRYOGENIC".getBytes();
		byte[] pt = new byte[ct.length];
		
		for(int i = 0; i < ct.length; i += key.length) {
			for(int j = 0; j < key.length; j++) {
				if(i+j < ct.length) {
					pt[i+j] =  (byte) ((ct[i+j] - key[j] + 26) % 26 + 'A');
				}
			}
		}
		String string = new String(pt);
		System.out.println(string);

	}

}
