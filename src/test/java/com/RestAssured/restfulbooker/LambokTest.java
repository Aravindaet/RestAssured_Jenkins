package com.RestAssured.restfulbooker;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.RestfulBooker.Lambok.CreateBooking;
import com.RestfulBooker.PayloadGeneration.CreateBookingPayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LambokTest {

	ObjectMapper objectMapper = new ObjectMapper();

	int bookingId;

	@Test(priority = 0, description = "To verify create booking API", enabled = false)
	public void createNewBooking() throws Exception {

		CreateBooking requestPayload = CreateBookingPayload.bookingPayload();
		Response response = given().contentType(ContentType.JSON).body(requestPayload).when()
				.post("https://restful-booker.herokuapp.com/booking");

		Assert.assertEquals(response.statusCode(), 200);

		System.out.println("Request Payload is" + ":" + requestPayload);

		// System.out.println("Actual Response is"+" "+": "+response.asString());

		ObjectMapper objectMapper = new ObjectMapper();

		/*
		 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		 * false); CreateBooking
		 * createBooking=objectMapper.readValue(response.getBody().asString(),
		 * CreateBooking.class); System.out.println(createBooking);
		 */

		JsonNode rootNode = objectMapper.readTree(response.getBody().asString());
		System.out.println("Values got from root node are" + rootNode);

		JsonNode bookingDetails = rootNode.get("booking");
		System.out.println("Booking details are" + bookingDetails);

		String bookingId = rootNode.get("bookingid").toString();

		System.out.println("Booking Id got from response is" + ":" + bookingId);

		/*
		 * BookingDates1
		 * bookingDates1=objectMapper.treeToValue(bookingDatesNode,BookingDates1.class);
		 * System.out.println(bookingDates1);
		 */

		CreateBooking responsePayload = objectMapper.treeToValue(bookingDetails, CreateBooking.class);

		System.out.println("Response Payload is" + ":" + responsePayload);

		Assert.assertEquals(requestPayload, responsePayload);

		System.out.println("Request Payload is" + " :" + requestPayload);
		System.out.println("Response Payload is" + " :" + responsePayload);

	}

	// Simplified test case
	@Test(priority = 1, description = "To verify create booking API")
	public void createBooking() throws Exception {
		CreateBooking requestPayload = CreateBookingPayload.bookingPayload();
		Response response = given().contentType(ContentType.JSON).body(requestPayload).when()
				.post("https://restful-booker.herokuapp.com/booking");

		System.out.println("Response payload is" + response.asString());

		JsonNode rootNode = objectMapper.readTree(response.getBody().asString());
		JsonNode bookingDetails = rootNode.get("booking");
		CreateBooking responsePayload = objectMapper.treeToValue(bookingDetails, CreateBooking.class);

		/*
		 * String Id = rootNode.get("bookingid").toString(); bookingId =
		 * Integer.parseInt(Id); System.out.println(bookingId);
		 */

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(responsePayload, requestPayload);

		System.out.println("Request Payload is" + " :" + requestPayload);
		System.out.println("Response Payload is" + " :" + responsePayload);

	}

	@Test(priority = 2, description = "To get the booking details using bookingId")
	public void getBookingDetailsUsingBookingId() throws Exception {
		CreateBooking requestPayload = CreateBookingPayload.bookingPayload();
		Response createBookingResponse = given().contentType(ContentType.JSON).body(requestPayload).when()
				.post("https://restful-booker.herokuapp.com/booking");

		JsonNode rootNode = objectMapper.readTree(createBookingResponse.getBody().asString());

		String Id = rootNode.get("bookingid").toString();
		bookingId = Integer.parseInt(Id);

		System.out.println("Booking ID got from 2nd test is" + bookingId);

		JsonNode bookingDetails = rootNode.get("booking");
		CreateBooking postMethodBookingDetailsResponsePayload = objectMapper.treeToValue(bookingDetails,
				CreateBooking.class);

		// Calling get method after creating booking
		Response getBookingResponse = given().when().get("https://restful-booker.herokuapp.com/booking/" + bookingId);

		Assert.assertEquals(getBookingResponse.header("Content-Type"), "application/json; charset=utf-8");

		CreateBooking getMethodBookingDetailsResponsePayload = objectMapper
				.readValue(getBookingResponse.getBody().asString(), CreateBooking.class);

		System.out.println("Post Method Booking Details" + " " + postMethodBookingDetailsResponsePayload);

		System.out.println("Get Method Booking Details" + " " + getMethodBookingDetailsResponsePayload);

		Assert.assertEquals(getMethodBookingDetailsResponsePayload, postMethodBookingDetailsResponsePayload);

	}

	@Test(priority = 3, description = "To update the booking details using booking Id")
	public void updateBookingDetails() throws IOException {
		CreateBooking requestPayload = CreateBookingPayload.bookingPayload();
		Response createBookingresponse = given().contentType(ContentType.JSON).body(requestPayload).when()
				.post("https://restful-booker.herokuapp.com/booking");

		JsonNode rootNode = objectMapper.readTree(createBookingresponse.getBody().asString());

		String Id = rootNode.get("bookingid").toString();
		bookingId = Integer.parseInt(Id);

		CreateBooking updateRequestPayload = CreateBookingPayload.bookingDeatailsUpdatePayload();

		Response response = given().auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON)
				.body(updateRequestPayload).when().put("https://restful-booker.herokuapp.com/booking/" + bookingId);

		System.out.println("Request sent for updating booking Id is" + " " + updateRequestPayload.toString());
		System.out.println("Response got after updating booking Id is" + " " + response.asString());

		CreateBooking updateResponsePayload = objectMapper.readValue(response.getBody().asString(),
				CreateBooking.class);

		Assert.assertEquals(response.getStatusCode(), 200);

		Assert.assertEquals(updateRequestPayload, updateResponsePayload);

	}

	@Test(description = "To partially update the booking details using PATCH method")
	public void partialBookingUpdate() throws Exception {
		CreateBooking requestPayload = CreateBookingPayload.bookingPayload();
		Response createBookingresponse = given().contentType(ContentType.JSON).body(requestPayload).when()
				.post("https://restful-booker.herokuapp.com/booking");

		JsonNode rootNode = objectMapper.readTree(createBookingresponse.getBody().asString());

		String Id = rootNode.get("bookingid").toString();
		bookingId = Integer.parseInt(Id);

		System.out.println("Booking ID got from partial update booking test is" + " " + bookingId);

		CreateBooking partialBookingUpdateRequest = CreateBookingPayload.partialUpdatePayload();

		Response response = given().auth().preemptive().basic("admin", "password123").contentType(ContentType.JSON)
				.body(partialBookingUpdateRequest).when()
				.patch("https://restful-booker.herokuapp.com/booking/" + bookingId);

		CreateBooking partialBookingUpdateResponse = objectMapper.readValue(response.getBody().asString(),
				CreateBooking.class);

		Assert.assertEquals(response.statusCode(), 200);

		Assert.assertEquals(partialBookingUpdateRequest, partialBookingUpdateResponse);

	}

	@Test(description = "To Delete the booking details using DELETE method")
	public void deleteBookingDetails() throws Exception {
		CreateBooking requestPayload = CreateBookingPayload.bookingPayload();
		Response createBookingresponse = given().contentType(ContentType.JSON).body(requestPayload).when()
				.post("https://restful-booker.herokuapp.com/booking");

		JsonNode rootNode = objectMapper.readTree(createBookingresponse.getBody().asString());

		String Id = rootNode.get("bookingid").toString();
		bookingId = Integer.parseInt(Id);

		System.out.println("Booking ID got from partial update booking test is" + " " + bookingId);

		Response response = given().auth().preemptive().basic("admin", "password123")

				.when().delete("https://restful-booker.herokuapp.com/booking/" + bookingId);

		System.out.println("Response got from delete request is" + response.asString());

		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertEquals(response.header("Content-Type"), "text/plain; charset=utf-8");
		Assert.assertEquals(response.header("Content-Length"), "7");
		Assert.assertEquals(response.getBody().asString(), "Created");

	}

}
