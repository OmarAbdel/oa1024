package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({InvalidDiscountTest.class, LadderNoJulyFourthTest.class, ChainsawJulyFourthTest.class, DewaltJackHammerLaborDayTest.class, RidgidJackHammerJulyFourthTest.class, RidgidJackHammerJulyFourthDiscountTest.class})
public class CheckoutTestSuite {

}
