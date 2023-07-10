package com.RestAssured.restfulbooker;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.RestAssured.PayloadData.RequestBodyData;
import com.RestfulBooker.Lambok.CreateBooking;
import com.RestfulBooker.Lambok.TokenGeneration;
import com.RestfulBooker.PayloadGeneration.CreateBookingPayload;
import com.RestfulBooker.PayloadGeneration.TokenGenerationPayload;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class JsonSchemaValidationTest {

	@Test(description = "To verify create booking json schema")
	public void createBookingSchemaValidation() {
		Map<String, Object> payload = RequestBodyData.createBookingData();
		given().contentType(ContentType.JSON).body(payload).when().post("https://restful-booker.herokuapp.com/booking")
				.then().statusCode(200).assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createBookingSchema.json"));
	}

	@Test(description = "To verify get booking json schema", enabled = false)
	public void getBookingSchemaValidation() {
		CreateBooking requestPayload = CreateBookingPayload.bookingPayload();
		Response response = given().contentType(ContentType.JSON).body(requestPayload).when()
				.post("https://restful-booker.herokuapp.com/booking");
		JSONObject jo = new JSONObject(response.asString());
		String bookingId = jo.get("bookingid").toString();
		System.out.println(bookingId);
//		int bookingId = Integer.parseInt(id);

		given().contentType(ContentType.JSON).when().get("https://restful-booker.herokuapp.com/booking/" + bookingId)
				.then().statusCode(200).assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getBookingSchema.json")).log().all();

	}

	@Test(description = "To verify the create token json schema")
	public void createTokenJsonSchemaValidation() {
		TokenGeneration request = TokenGenerationPayload.createToken();
		given().contentType(ContentType.JSON).body(request).when().post("https://restful-booker.herokuapp.com/auth")
				.then().statusCode(200).assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createTokenJsonSchema.json"));

	}

}
