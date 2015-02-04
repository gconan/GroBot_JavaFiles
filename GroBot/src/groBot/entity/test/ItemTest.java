package groBot.entity.test;

import static org.junit.Assert.*;
import groBot.entity.Item;

import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Blob;

public class ItemTest {
	
	private Item testItem;
	private final String name = "name";
	private final String sPrice = "sPrice";
	private final String desc = "desc";
	private final String owner = "owner";

	@Before
	public void createUserForTest() {
		testItem = new Item(name, sPrice, desc, owner, null);
	}
	@Test
	public void testName() {
		assertTrue(this.name.equals(testItem.getName()));
	}
	@Test
	public void testsPrice() {
		Item item = new Item(name, sPrice, desc, owner, null);
		assertTrue(item.getPrice().equals(testItem.getPrice()));
	}
	@Test
	public void testDesc() {
		Item item = new Item(name, sPrice, desc, owner, null);
		assertTrue(item.getDescription().equals(testItem.getDescription()));
	}
	@Test
	public void testOwner() {
		Item item = new Item(name, sPrice, desc, owner, null);
		assertTrue(item.getOwner().equals(testItem.getOwner()));
	}
	@Test
	public void testblob() {
		Item item = new Item(name, sPrice, desc, owner, null);
		assertTrue(item.getName().equals(testItem.getName()));
	}
	@Test
	public void testKeywords() {
		Item item = new Item(name, sPrice, desc, owner, null);
		assertTrue(item.getName().equals(testItem.getName()));
	}
	
	@After
	public void DestroyUserForTest() {
		testItem = null;
	}
	

}
