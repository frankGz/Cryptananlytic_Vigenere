package foundation;

import java.io.ByteArrayOutputStream;

import util.CryptoTools;

public class VigGetKeyLen
{

	public static void main(String[] args) throws Exception
	{
		byte[] ct = CryptoTools.fileToBytes("data/RPT1.ct");
		for (int keyLen = 2; keyLen < 100; keyLen++)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int pos = 0; pos < ct.length; pos += keyLen)
			{
				baos.write(ct[pos]);
			}
			byte[] subset = baos.toByteArray();
			double ic = CryptoTools.getIC(subset);
			System.out.println(keyLen + " --> " + ic);
			// 0.04 GARBAGE  0.06+ GOOD!
			// 出现重复，第一个为准
			
			/*
			int[] f = CryptoTools.getFrequencies(subset);
			for (int i = 0; i < f.length; i++)
			{
				System.out.println((char) (i+'A') + " -->" + f[i]/(double) subset.length);
			}
			*/
		}
		
		

	}

}