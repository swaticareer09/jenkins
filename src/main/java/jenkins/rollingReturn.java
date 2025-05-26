package jenkins;

import org.testng.annotations.Test;


public class rollingReturn extends baselib {
	@Test(priority = 1)
	public void verifyHeaders() throws Exception {
		test.assignCategory("rollingReturn");
		test.info("This test case 1:- This case verifies the header .");
		rollingReturnCalculator mu = new rollingReturnCalculator();
		mu.verifyHeaders();
	}

	

}