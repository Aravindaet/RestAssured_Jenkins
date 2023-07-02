package com.RestfulBooker.PayloadGeneration;

import com.RestfulBooker.Lambok.TokenGeneration;

public class TokenGenerationPayload {

	public static TokenGeneration createToken() {
		return TokenGeneration.builder().username("admin").password("password123").build();

	}

}
