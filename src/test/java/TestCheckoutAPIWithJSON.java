import api.Checkout;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.assertFalse;

public class TestCheckoutAPIWithJSON extends TestMain {
	Checkout chekoutPagewithJSON = new Checkout();
	Response response = chekoutPagewithJSON.requestCheckout();
	String directURL = "https://sandbox.kredivo.com/kredivo/v2/signin?tk=X+nd1e5md9fEthr2X+YiscOghejdItqjf4PpZgS7tyHiijoPGdeQLEaREJ8qzcNMft9zfWJqL0byoYuM6QHCHgXvBNJEBSMkvwSQgidLM4s=";

	@Test(description = "Response Checkout")
	public void testCheckoutPageWithJSON() {
		chekoutPagewithJSON.requestCheckout();
	}

	@Test(description = "message success")
	public void testCheckoutSuccessWithJSON() {
		int statusCode = response.getStatusCode();
		String status = response.jsonPath().get("status");
		String redirectURL = response.jsonPath().get("redirect_url");
		Assert.assertEquals(status, "OK");
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(directURL, directURL);
		assertFalse(directURL.isEmpty());
	}
	@Test(description = "Error with Server_Key")
	public void testCheckoutErrorWithJSON() {
		String status = response.jsonPath().get("status");
		Assert.assertEquals(status, "ERROR");
	}

	@Test(description = "Timeout")
	public void testCheckoutGateway_TimeoutWithJSON() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 504);
	}

	@Test(description = "Server Error")
	public void testCheckoutServer_ErrorWithJSON() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 500);
	}
}