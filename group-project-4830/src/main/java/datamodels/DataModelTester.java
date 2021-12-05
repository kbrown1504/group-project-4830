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
	
	@Test
	public void testBookListingAll() {
		BookListing a = new BookListing(0, 0, "Assertions", "Paul Watson", 9871234213323L, 20.33, 15, 4, "Blah blah description");
		assertEquals(a.getID(), 0);
		assertEquals(a.getOrderID(), 0);
		assertEquals(a.getTitle(), "Assertions");
		assertEquals(a.getAuthor(), "Paul Watson");
		assertEquals(a.getISBN(),9871234213323L);
		assertEquals(a.getPrice(), 20.33, 0);
		assertEquals(a.getSellerID(), 15);
		assertEquals(a.getCondition(), 4);
		assertEquals(a.getInfo(), "Blah blah description");
		a.setOrderID(1);
		a.setTitle("Desertions");
		a.setAuthor("Walle Paulford");
		a.setISBN(9871234567890L);
		a.setPrice(78.33);
		a.setSellerID(12);
		a.setCondition(3);
		a.setInfo(null);
		assertEquals(a.getOrderID(), 1);
		assertEquals(a.getTitle(), "Desertions");
		assertEquals(a.getAuthor(), "Walle Paulford");
		assertEquals(a.getISBN(),9871234567890L);
		assertEquals(a.getPrice(), 78.33, 0);
		assertEquals(a.getSellerID(), 12);
		assertEquals(a.getCondition(), 3);
		assertEquals(a.getInfo(), null);
		a.setCondition(1);
		assertEquals(a.getConditionStr(), "Poor");
		a.setCondition(2);
		assertEquals(a.getConditionStr(), "Average");
		a.setCondition(3);
		assertEquals(a.getConditionStr(), "Good");
		a.setCondition(4);
		assertEquals(a.getConditionStr(), "Very Good");
		a.setCondition(5);
		assertEquals(a.getConditionStr(), "Like New");
		a.setCondition(0);
		assertEquals(a.getConditionStr(), "None Specified");
	}

	@Test
	public void testOrderAll() {
		Order a = new Order(0, 10, "123 Whynot Lane");
		assertEquals(a.getID(), 0);
		assertEquals(a.getBuyerID(), 10);
		assertEquals(a.getShippingAddress(), "123 Whynot Lane");
		a.setBuyerID(5);
		assertEquals(a.getBuyerID(), 5);
		a.setShippingAddress("456 Sure Way");
		assertEquals(a.getShippingAddress(), "456 Sure Way");
	}
}
