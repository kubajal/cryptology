package cryptography;

class SPN{

	SBox S;
	Permutation P;
	int[] K, w, u, v;
	int rounds = 5, l = 4, m = 4;
	
	public SPN(int Kgen) {

		P = new  Permutation();
		S = new SBox();
		K = new int[rounds + 1];
		w = new int[rounds + 1];
		u = new int[rounds + 1];
		v = new int[rounds + 1];
		
		int K_mask = ((0b1 << m*l) - 1)  << m*l;
		
		for(int i = 1; i < rounds + 1; i++) {
			
			K[i] = (Kgen & K_mask) >> m*(l - i + 1);
			K_mask =  K_mask >>> l;
			S = new SBox();
		}
	}
	
	public int bit(int x, int i) {
		int tmp = x & (1 << (16 - i));
		return tmp >>> (16 - i);
	}
	
	public int encrypt(int message) {
		
		for(int i = 0; i < rounds + 1; i++) {
			u[i] = w[i] = v[i] = 0;
		}
		
		w[0] = message;
		int y = 0;
		for(int r = 1; r < rounds; r++) {
			
			u[r] = w[r - 1] ^ K[r];
			int mask = ((1 << l) - 1) << m*(l-1);
			for(int i = 0; i < m; i++) {
				
				int tmp = u[r] & mask;
				tmp = tmp >>> m*(l - i - 1);
				mask = mask >>> l;
				tmp = S.get(tmp);
				tmp = tmp << (m - i - 1)*l;
				v[r] = v[r] | tmp;
			}
			
			mask = 0b1;
			for(int i = 0; i < 16; i++) {
				if((v[r] & mask)  != 0) {
					w[r] = w[r] | (1 << P.get(i));
				}
				mask = mask << 1;
			}
			
		}
		
		y = v[rounds-1] ^ K[rounds];
		
		return y;
	}

}