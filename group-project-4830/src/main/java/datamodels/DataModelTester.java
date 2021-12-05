package datamodels;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataModelTester {

	
	
	@Test
	public void testAccountAll() {
		Account a = new Account (0, "User", "Email", "Pass");
		assertEquals(a.getID(), 0);
		assertEquals(a.getUsername(), "User");
		assertEquals(a.getEmail(), "Email");
		assertEquals(a.getPassword(), "Pass");
		a.setUsername("User2");
		a.setEmail("Email2");
		a.setPassword("Pass2");
		assertEquals(a.getUsername(), "User2");
		assertEquals(a.getEmail(), "Email2");
		assertEquals(a.getPassword(), "Pass2");
	}

}
