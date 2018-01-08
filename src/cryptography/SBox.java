package cryptography;


class SBox {
	
	int[] pi, in;
	
	public SBox() {

		pi = new int[16];
		in = new int[16];
		
		pi[0] = 8;
		pi[1] = 4;
		pi[2] = 2;
		pi[3] = 1;
		pi[4] = 12;
		pi[5] = 6;
		pi[6] = 3;
		pi[7] = 13;
		pi[8] = 10;
		pi[9] = 5;
		pi[10] = 15;
		pi[11] = 7;
		pi[12] = 14;
		pi[13] = 11;
		pi[14] = 9;
		pi[15] = 0;
		
		for(int i = 0; i < 16; i++) {
			in[pi[i]] = i;
		}
	}
	
	public Integer get(int key) {
		return pi[key];
	}
	
	public Integer inverse(int key) {
		return in[key];
	}
}