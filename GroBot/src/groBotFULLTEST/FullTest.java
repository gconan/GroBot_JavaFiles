package groBotFULLTEST;

import groBot.entity.test.ItemTest;
import groBot.entity.test.UserTest;
import groBot.servlet.test.RegistrationServletTest;
import groBot.servlet.test.SecureServletTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
// by FYICenter.com





// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  UserTest.class, 
  SecureServletTest.class,
  ItemTest.class,
  RegistrationServletTest.class}
)

/**
 * Run Junit here and this class will run all of our test classes in a suite at one time
 * This helps us see all of the tests in on area, and when test functionality increases, 
 * we will most likely need some tests to run together.
 * @author conangammel
 *
 */
public class FullTest {
}
