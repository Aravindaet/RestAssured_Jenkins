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
public class BookingDates1 {
	
	@JsonProperty("checkin")
	private String checkin;
	@JsonProperty("checkout")
	private String checkout;
	
}
