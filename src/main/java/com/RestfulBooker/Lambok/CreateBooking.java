package com.RestfulBooker.Lambok;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder=true)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateBooking {
	
	private String firstname;
	private String lastname;
	private int totalprice;	
	private boolean depositpaid;
	private String additionalneeds;
	private BookingDates1  bookingdates;

	

}
