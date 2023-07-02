package com.RestAssured.Details;

import com.github.javafaker.Faker;

public class BookingDetails {

	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private String additionalneeds;
	private BookingDates bookingdates;

	static Faker faker = new Faker();

	public BookingDetails() {
		firstname = readFirstName();
		lastname = readLastName();
		totalprice = readTotalPrice();
		depositpaid = readDepositPaid();
		additionalneeds = readAdditionalNeeds();
	}

	private boolean readDepositPaid() {
		return true;
	}

	private String readAdditionalNeeds() {
		String additionalneeds = "Breakfast";
		return additionalneeds;
	}

	private int readTotalPrice() {
		int totalprice = faker.number().numberBetween(100, 150);
		return totalprice;
	}

	private String readLastName() {
		String lastname = faker.name().lastName();
		return lastname;
	}

	private String readFirstName() {
		String firstname = faker.name().firstName();
		return firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public boolean getDepositpaid() {
		return depositpaid;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public BookingDates getBookingdates() {
		return bookingdates;
	}

}
