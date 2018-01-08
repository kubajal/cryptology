package test;

import static org.junit.jupiter.api.Assertions.*;

import cryptography.*;

import org.junit.jupiter.api.Test;

class TestSPN {

	@Test
	void test() {

		Cryptography c = new Cryptography();
		c.linearAttack(0b11010011011101001011010111000101);
		String L1 = c.L1string, L2 = c.L2string;
		assertEquals("1011", L1);
		assertEquals("1101", L2);
	}

}
