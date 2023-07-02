package com.RestAssured.Details;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class BookingDates {
	private String checkin;
	private String checkout;

	SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
	static Faker faker = new Faker();

	public BookingDates() {
		checkin = readCheckInDate();
		checkout = readCheckOutDate();

	}

	private String readCheckOutDate() {
		String checkout = formatter.format(faker.date().future(5, TimeUnit.DAYS));
		return checkout;
	}

	private String readCheckInDate() {
		String checkin = formatter.format(faker.date().past(20, TimeUnit.DAYS));
		return checkin;
	}

	public String getCheckin() {
		return checkin;
	}

	public String getCheckout() {
		return checkout;
	}

//	Bookingdate
}
