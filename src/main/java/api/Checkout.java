package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static io.restassured.RestAssured.given;

/**
 * class checkout page the request from file in directory resources
 */
public class Checkout {
	/**
	 * Complete of URL
	 */
	String completeURL = "https://sandbox.kredivo.com/kredivo/v2/checkout_url";
	/**
	 *  read file from directory
	 */
	File file = getFileFromResources("requestbody.json");

	/**
	 * to Get Response
	 * @return
	 */
	public Response requestCheckout() {
		return given().contentType(ContentType.JSON).body(file).when().post(completeURL);
	}

	/**
	 * get file from classpath, resources folder
 	 */
	private File getFileFromResources(String fileName) {

		ClassLoader classLoader = getClass().getClassLoader();

		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return new File(resource.getFile());
		}

	}

	/**
	 * to print file
	 * @param file
	 * @throws IOException
	 */
	private static void printFile(File file) throws IOException {

		if (file == null) return;

		try (FileReader reader = new FileReader(file);
		     BufferedReader br = new BufferedReader(reader)) {

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}

	public void getID(){
		String id = file.toString();
		System.out.println("id" + id);
	}

}
