package cryptography;

import java.util.Random;

public class Cryptography {
	
	public String L1string;
	public String L2string;
	
	public void linearAttack(int K) {
		
		L1string = new String();
		L2string = new String();
		for(int q = 20000; q < 20001; q++) {


			int[][] count = new int[16][16];
			int[] tab = new int[1000000];
			Random generator = new Random();
			int x = 0, y = 0;
			
			SPN spn = new SPN(K);
			
			for(int p = 0; p < q; p++) {
				
				while(tab[x] != 0) {
					x = generator.nextInt(1 << 16);
				}
				tab[x] = 1;
				y = spn.encrypt(x);
				for(int L1 = 0; L1 < 16; L1++) {

					for(int L2 = 0; L2 < 16; L2++) {
					
						int V14  = L1 ^ (y & 0b1111000000000000) >>> 12;
						int V94 = L2 ^ ((y & 0b11110000) >>> 4);
						int U14 = spn.S.inverse(V14) << 12;
						int U94 = spn.S.inverse(V94) << 4;
						int z = spn.bit(x, 16) ^ spn.bit(U14, 1)  ^ spn.bit(U94, 9);
						if(z == 0)
							count[L1][L2]++;
					}
				}
			}
			
			int max = -1, L1max = -1, L2max = -1;
			
			for(int i = 0; i < 16; i++) {
				
				for(int j = 0; j < 16; j++) {
					
					if(count[i][j] > max) {
						max = count[i][j];
						L1max = i;
						L2max = j;
					}
				}
			}

			L1string = Integer.toBinaryString(L1max);
			L2string = Integer.toBinaryString(L2max);
			while(L1string.length() != spn.l) {
				L1string =  "0" + L1string;
			}
			while(L2string.length() != spn.l) {
				L2string =  "0" + L2string;
			}

			System.out.println("Anzahl der Paare: " + q + ", (L1, L2) = (" + L1string + " " + L2string + ")");
		}
	}
	
	public static void main(String[] args) {
		System.out.printf("SPN");
	}
}


