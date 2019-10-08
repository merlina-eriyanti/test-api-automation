package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static io.restassured.RestAssured.given;

public class CheckoutPageMap {
	String completeURL = "https://sandbox.kredivo.com/kredivo/v2/checkout_url";
	URL url = new URL(completeURL);

	public CheckoutPageMap() throws MalformedURLException {}

	/**
	 * to get Random ID
	 * @return
	 */
	public int random() {
		Random r = new Random( System.currentTimeMillis() );
		return ((7 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	/**
	 * Request Check out
	 * @return
	 */
	public Response reqCheckout(){

		/**
		 * item List
		 */
		List<Map<String, Object>> item = new ArrayList<>();

		Map<String, Object> item1 = new LinkedHashMap<>();
		item1.put("id", "BB12345678");
		item1.put("name", "iPhone 5S");
		item1.put("price", 6000000);
		item1.put("type", "Smartphone");
		item1.put("url", "http://merchant.com/cellphones/iphone5s_64g");
		item1.put("quantity", "1");
		item.add(item1);

		Map<String, Object> item2 = new LinkedHashMap<>();
		item2.put("id", "AZ14565678");
		item2.put("name", "Hailee Sneakers Blink Silver");
		item2.put("price", 250000);
		item2.put("type", "Sneakers");
		item2.put("url", "http://merchant.com/fashion/shoes/sneakers-blink-shoes");
		item2.put("quantity", "2");
		item2.put("parent_type","SELLER");
		item2.put("parent_id","SELLER456");
		item.add(item2);

		Map<String, Object> item3 = new LinkedHashMap<>();
		item3.put("id", "taxfee");
		item3.put("name", "Tax Fee");
		item3.put("price", 1000);
		item3.put("quantity", "1");
		item.add(item3);

		Map<String, Object> item4 = new LinkedHashMap<>();
		item4.put("id", "shippingfee");
		item4.put("name", "Shipping Fee");
		item4.put("price", 9000);
		item4.put("quantity", "1");
		item4.put("parent_type","SELLER");
		item4.put("parent_id","SELLER456");
		item.add(item4);

		String rand = UUID.randomUUID().toString();



		Map<String, Object> trdetails = new LinkedHashMap<>();
		trdetails.put("amount", 6505000);
		trdetails.put("order_id", "KD" + random());
		trdetails.put("items", item);

		/**
		 * sellers List
		 */
		List<Map<String, Object>> sellers = new ArrayList<>();

		Map<String, Object> seller1 = new LinkedHashMap<>();

		Map<String, Object> address = new LinkedHashMap<>();
		address.put("first_name", "irfan");
		address.put("last_name","Sutandro");
		address.put("address","Jalan Tentara Pelajar no 49");
		address.put("city", "Jakarta Utara");
		address.put("postal_code","12960");
		address.put("phone","08123456789");
		address.put("country_code","IDN");

		seller1.put("id", "SELLER123");
		seller1.put("name", "Sunrise");
		seller1.put("email", "sunrise@gmail.com");
		seller1.put("url", "https://onlineshop/seller/sunris");
		seller1.put("address", address);

		Map<String, Object> seller2 = new LinkedHashMap<>();

		Map<String, Object> address2 = new LinkedHashMap<>();
		address2.put("first_name", "Toto");
		address2.put("last_name","Wahyuni");
		address2.put("address","Jalan Krici raya IX");
		address2.put("city", "Jakarta Selatan");
		address2.put("postal_code","12960");
		address2.put("phone","08123456789");
		address2.put("country_code","IDN");

		seller2.put("id", "SELLER456");
		seller2.put("name", "Toko Bagus");
		seller2.put("email", "tokobagus@gmail.com");
		seller2.put("url", "https://onlineshop/seller/tokobagus");
		seller2.put("address", address2);

		sellers.add(seller1);
		sellers.add(seller2);


		/**
		 * customer details
		 */
		Map<String, Object> customer = new LinkedHashMap<>();
		customer.put("first_name","Oemang");
		customer.put("last_name","Tandra");
		customer.put("email","alie@satuduatiga.com");
		customer.put("phone","081513114262");

		/**
		 * billing details
		 */
		Map<String, Object> billing = new LinkedHashMap<>();
		billing.put("first_name","Oemang");
		billing.put("last_name","Tandra");
		billing.put("address","Jalan Teknologi Indonesia No. 25");
		billing.put("city","Jakarta");
		billing.put("postal_code","12960");
		billing.put("phone","081513114262");
		billing.put("country_code","IDN");

		/**
		 * billing details
		 */
		Map<String, Object> shipping = new LinkedHashMap<>();
		shipping.put("first_name","Oemang");
		shipping.put("last_name","Tandra");
		shipping.put("address","Jalan Teknologi Indonesia No. 25");
		shipping.put("city","Jakarta");
		shipping.put("postal_code","12960");
		shipping.put("phone","081513114262");
		shipping.put("country_code","IDN");


		/**
		 * billing details
		 */
		Map<String, Object> metadata = new LinkedHashMap<>();
		metadata.put("ip_address","192.168.88.1");
		metadata.put("user_agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");


		/**
		 body
		 */
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("server_key", "8tLHIx8V0N6KtnSpS9Nbd6zROFFJH7");
		body.put("payment_type", "30_days");
		body.put("tokenize_user", false);
		body.put("client_user_key", "randy@finaccel.co)");
		body.put("user_token", "XXXX-XXXX");

		body.put("transaction_details",trdetails );
		body.put("sellers", sellers);
		body.put("customer_details", customer);
		body.put("billing_address", billing);
		body.put("shipping_address", shipping);
		body.put("metadata", metadata);

		body.put("push_uri", "https://api.merchant.com/push");
		body.put("back_to_store_uri", "https://merchant.com");

		return given().contentType(ContentType.JSON).body(body).when().post(completeURL);

	}

}
