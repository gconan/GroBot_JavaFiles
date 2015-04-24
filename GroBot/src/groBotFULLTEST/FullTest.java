package groBotFULLTEST;

import groBot.entity.test.*;
import groBot.servlet.test.ScheduleServletTester;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
// by FYICenter.com





// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  GroBotsTester.class,
  WaterTester.class,
  ScheduleTester.class,
  UserTester.class,
  ScheduleServletTester.class,
  LightsTester.class}
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
