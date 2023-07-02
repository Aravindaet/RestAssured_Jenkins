package com.RestfulBooker.PayloadGeneration;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import com.RestfulBooker.Lambok.BookingDates1;
import com.RestfulBooker.Lambok.CreateBooking;
import com.github.javafaker.Faker;

public class CreateBookingPayload {

	public static void main(String[] args) {
		System.out.println(bookingPayload());
	}

	static SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
	static Faker faker = new Faker();

	public static CreateBooking bookingPayload() {
		return CreateBooking.builder().firstname(faker.name().firstName()).lastname(faker.name().lastName())
				.totalprice(faker.number().numberBetween(100, 1200)).depositpaid(false)
				.bookingdates(BookingDates1.builder().checkin(formatter.format(faker.date().past(20, TimeUnit.DAYS)))
						.checkout(formatter.format(faker.date().future(5, TimeUnit.DAYS))).build())
				.additionalneeds("Lunch").build();
	}

	public static CreateBooking bookingDeatailsUpdatePayload() {
		return CreateBooking.builder().firstname(faker.name().firstName()).lastname(faker.name().lastName())
				.totalprice(faker.number().numberBetween(1000, 1200)).depositpaid(true)
				.bookingdates(BookingDates1.builder().checkin(formatter.format(faker.date().past(20, TimeUnit.DAYS)))
						.checkout(formatter.format(faker.date().future(5, TimeUnit.DAYS))).build())
				.additionalneeds("Meals").build();
	}

	public static CreateBooking partialUpdatePayload() {
		return CreateBooking.builder().firstname(faker.name().firstName()).lastname(faker.name().lastName())
				.totalprice(faker.number().numberBetween(1000, 1200)).depositpaid(true)
				.bookingdates(BookingDates1.builder().checkin(formatter.format(faker.date().past(20, TimeUnit.DAYS)))
						.checkout(formatter.format(faker.date().future(5, TimeUnit.DAYS))).build())
				.additionalneeds("Dinner").build();
	}

	/*
	 * public static CreateBooking bookingPayload() { return CreateBooking.builder()
	 * .firstname("Aravinda") .lastname("ETI") .totalprice(100) .depositpaid(true)
	 * .additionalneeds("Lunch") .build(); }
	 * 
	 * public static BookingDates1 bookingDates() { return BookingDates1.builder()
	 * .checkin("2022-02-02") .checkout("2023-03-03") .build();
	 * 
	 * }
	 */

}