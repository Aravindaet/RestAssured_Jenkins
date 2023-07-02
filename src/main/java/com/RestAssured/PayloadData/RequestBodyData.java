package com.RestAssured.PayloadData;

import java.util.HashMap;
import java.util.Map;

import com.RestAssured.Details.BookingDates;
import com.RestAssured.Details.BookingDetails;
import com.RestAssured.Details.TokenDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestBodyData {

	public static void main(String[] args) throws JsonProcessingException {
//		System.out.println(createBookingData());
		RequestBodyData RequestBodyData = new RequestBodyData();
		System.out.println(bookingDates());
//		requestData();
		System.out.println(createBookingData());

	}

	public static Map<String, Object> bookingDates() {
		HashMap<String, Object> bookingDateData = new HashMap<String, Object>();
		BookingDates bookingDates = new BookingDates();
		bookingDateData.put("checkin", bookingDates.getCheckin());
		bookingDateData.put("checkout", bookingDates.getCheckout());
		return bookingDateData;
	}

	public static Map<String, Object> createBookingData() {
		HashMap<String, Object> createBookingPayload = new HashMap<String, Object>();
		BookingDetails bookingDetails = new BookingDetails();
		createBookingPayload.put("firstname", bookingDetails.getFirstname());
		createBookingPayload.put("lastname", bookingDetails.getLastname());
		createBookingPayload.put("totalprice", bookingDetails.getTotalprice());
		createBookingPayload.put("depositpaid", bookingDetails.getDepositpaid());
		createBookingPayload.put("bookingdates", bookingDates());
		createBookingPayload.put("additionalneeds", bookingDetails.getAdditionalneeds());
		return createBookingPayload;
	}

	public static String requestData() throws JsonProcessingException {
		ObjectMapper ObjectMapper = new ObjectMapper();
		String mapperpayload = ObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createBookingData());
		return mapperpayload;
	}

	public static Map<String, String> tokenData() {
		HashMap<String, String> tokenPayload = new HashMap<String, String>();
		TokenDetails tokenDetails = new TokenDetails();
		tokenPayload.put("username", tokenDetails.getUserName());
		tokenPayload.put("password", tokenDetails.getPassword());
		return tokenPayload;
	}

	public static Map<String, Object> updateBookingData() {
		HashMap<String, Object> updateBookingPayload = new HashMap<String, Object>();
		BookingDetails bookingDetails = new BookingDetails();
		updateBookingPayload.put("totalprice", bookingDetails.getTotalprice());
		return updateBookingPayload;

	}

}
