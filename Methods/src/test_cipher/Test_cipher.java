package test_cipher;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_cipher {

	//@Test
	//void test() {
	//	fail("Not yet implemented");
	// }

	@Test
	void test_caesar_encode01() {
		String ret= cipher.Cipher.caesar_encode(1, "ABC");
		assertTrue(ret.contentEquals("BCD"));
	}
	@Test
	void test_caesar_encode02() {
		String ret= cipher.Cipher.caesar_encode(1, "xyz");
		assertTrue(ret.contentEquals("yza"));
	}
	@Test
	void test_caesar_encode03() {
		String ret= cipher.Cipher.caesar_encode(26, "abz");
		assertTrue(ret.contentEquals("abz"));
	}
	@Test
	void test_caesar_decode01() {
		String ret= cipher.Cipher.caesar_decode(1, "BCD");
		assertTrue(ret.contentEquals("ABC"));
	}
	@Test
	void test_caesar_decode02() {
		String ret= cipher.Cipher.caesar_decode(1, "yza");
		assertTrue(ret.contentEquals("xyz"));
	}
}
