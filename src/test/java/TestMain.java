import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;

public abstract class TestMain {
	/**
	 * get logging test
	 */
	@BeforeSuite
	public void setConfig() {
		if (RestAssured.filters().isEmpty()) {
			RestAssured.filters(new ResponseLoggingFilter());
			RestAssured.filters(new RequestLoggingFilter());
		}

	}
}
