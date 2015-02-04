package groBot.entity.test;

import static org.junit.Assert.*;
import groBot.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User testUser;
	private final String password = "password";
	private final String email = "test@utexas.edu";

	@Before
	public void createUserForTest() {
		testUser = new User(this.password, this.email);
	}
	
	/**
	 * the hash function to create an access code should return the same code
	 * if the email and password are the same
	 */
	@Test
	public void testUserAccessCodeHashFunctionConsistency(){
		String initialAccessCode = testUser.getAccess_code();
		User testUser2 = new User(this.password, this.email);
		assertTrue(initialAccessCode.equals(testUser2.getAccess_code()));
	}
	
	/**
	 * check that the hash function for access code is unique enough that someone with the same password
	 * does not get the same access code
	 */
	@Test
	public void testUserAccessCodeUniqueness(){
		String newEmail = "";
		
		//change the even numbered characters in the email address
		//so that the email is still similiar, but different
		for(int i=0; i<newEmail.length(); i++){
			if(i%2==0){
				newEmail += 's'+i;
			}else{
				newEmail += this.email.charAt(i);
			}
		}
		
		
		assertFalse(newEmail.equals(this.email));
		
		User testUser2 = new User(this.password, newEmail);
		
		assertFalse(testUser.getAccess_code().equals(testUser2.getAccess_code()));
	}
	
	/**
	 * test getters and setters
	 * since its hard to have bugs here, we will test all of them in one testCase
	 */
	@Test
	public void testUserGettersandSetters(){
		assertTrue(this.password.equals(testUser.getPassword()));
		assertTrue(this.email.equals(testUser.getEmail()));
	}
	
	/**
	 * test activation
	 * should be false when created (before)
	 * after activation, should be true
	 */
	@Test
	public void testUserActivation(){
		//should not be activated
		assertFalse(testUser.getStatus());
		
		//false activate
		testUser.activate();
		assertTrue(testUser.getStatus());
	}
	
	/**
	 * test password reset
	 */
	@Test
	public void testUserPasswordReset(){
		//password should be correct
		assertTrue(this.password.equals(testUser.getPassword()));
		
		//change the password
		testUser.resetPassword();
		
		//ensure the password is not the same after reset
		assertFalse(this.password.equals(testUser.getPassword()));
		
	}
	
	/**
	 * test that isSameUser method is accurate
	 */
	@Test
	public void testUserisSameUser(){
		User tester = new User(null, this.email);
		tester.setPassword(this.password);	//just to check the setPassword
		
		assertTrue(testUser.isSameUser(tester));
	}
	
	@After
	public void destroyUser(){
		testUser=null;
	}

}
