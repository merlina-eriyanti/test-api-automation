import api.CheckoutPageMap;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestCheckoutAPIWithHashMap extends TestMain {

	CheckoutPageMap chekoutPagewithHashMap = new CheckoutPageMap();
	Response response = chekoutPagewithHashMap.reqCheckout();

	public TestCheckoutAPIWithHashMap() throws MalformedURLException {}

	@Test(description = "Verify Checkout")
	public void testCheckoutPage() {
		chekoutPagewithHashMap.reqCheckout();
	}

	@Test(description = "message success")
	public void testCheckoutSuccess() {
		int statusCode = response.getStatusCode();
		String status = response.jsonPath().get("status");
		Assert.assertEquals(status, "OK");
		Assert.assertEquals(statusCode, 200);
	}

	@Test(description = "Error with Server_Key")
	public void testCheckoutError() {
		String status = response.jsonPath().get("status");
		Assert.assertEquals(status, "ERROR");
	}

	@Test(description = "Timeout")
	public void testCheckoutGateway_Timeout() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 504);
	}

	@Test(description = "Server Error")
	public void testCheckoutServer_Error() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 500);
	}
}
