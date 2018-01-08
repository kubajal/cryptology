package cryptography;
class Permutation {
	
	int[] pi, in;
	
	public Permutation() {

		pi = new int[16];
		in = new int[16];
		
		pi[0] = 0;
		pi[1] = 4;
		pi[2] = 8;
		pi[3] = 12;
		pi[4] = 1;
		pi[5] = 5;
		pi[6] = 9;
		pi[7] = 13;
		pi[8] = 2;
		pi[9] = 6;
		pi[10] = 10;
		pi[11] = 14;
		pi[12] = 3;
		pi[13] = 7;
		pi[14] = 11;
		pi[15] = 15;
		
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
