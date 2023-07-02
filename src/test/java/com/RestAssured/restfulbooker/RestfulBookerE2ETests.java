package com.RestAssured.restfulbooker;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.RestAssured.PayloadData.RequestBodyData;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestfulBookerE2ETests {

	String bookingId;
	String requestpayload;
	String token;

	@Test
	public void createNewBooking() throws Exception {
		Map<String, Object> payload = RequestBodyData.createBookingData();
		requestpayload = RequestBodyData.requestData();
		System.out.println("New Booking data payload is" + requestpayload);

		Response response = given().contentType(ContentType.JSON).body(requestpayload).when()
				.post("https://restful-booker.herokuapp.com/booking");

		JSONObject jo = new JSONObject(response.asString());
		System.out.println(jo);

		Assert.assertEquals(response.statusCode(), 200);
		bookingId = jo.get("bookingid").toString();

		System.out.println("*************" + bookingId);

	}

	@Test
	public void getBookingData() {

		Response response = given()

				.when().get("https://restful-booker.herokuapp.com/booking");

		// JSONObject jo = new JSONObject(response.toString());

		JSONArray jsonArray = new JSONArray(response.asString());

		boolean status = false;
		for (int i = 0; i < jsonArray.length(); i++) {
			String bookingid = jsonArray.get(i).toString();
			if (bookingid.contains(bookingId)) {
				status = true;
				break;
			}
		}

		Assert.assertTrue(status);
		// System.out.println(jsonArray.get(bookingId));

		/*
		 * for (int i = 0; i < jo.getJSONArray("").length(); i++) { String bookingId =
		 * jo.getJSONArray("").getJSONObject(i).get("bookingid").toString();
		 * System.out.println(bookingId);
		 * 
		 * }
		 */

	}

	@Test
	public void getBookingDetailsById() {

		Response response = given()

				.when().get("https://restful-booker.herokuapp.com/booking/" + bookingId);

		Assert.assertEquals(response.statusCode(), 200);
		// JSONObject jo = new JSONObject(response.asString());
		String responsePayload = response.asString();
		JSONAssert.assertEquals(requestpayload, responsePayload, JSONCompareMode.NON_EXTENSIBLE);

	}

	@Test
	public void createToken() {
		Map<String, String> payload = RequestBodyData.tokenData();

		System.out.println(payload);

		Response response = given().contentType(ContentType.JSON).body(payload)

				.when().post("https://restful-booker.herokuapp.com/auth");

		JSONObject jo = new JSONObject(response.asString());
		token = jo.getString("token").toString();

	}

	@Test()
	public void updateBookingDetails() {
		Map<String, Object> payload = RequestBodyData.updateBookingData();
//		token = "abc123";

		Response response = given().header("cookie", token).contentType(ContentType.JSON).body(payload)

				.when().put("https://restful-booker.herokuapp.com/booking/" + bookingId);
		System.out.println("Response got from update request is" + response.asString());

	}
	
	
	
	

}
