package com.challenge.price;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.boot.SpringApplication.run;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockedStatic.Verification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootTest
class PriceApplicationTests {
	@Test
	void shouldReturnApplicationContext() {
		String[] args = {};
		GenericApplicationContext context = new GenericApplicationContext();

		try (MockedStatic<SpringApplication> utilities = mockStatic(SpringApplication.class)) {
			Verification verification = (Verification) run(PriceApplication.class, args);
			utilities.when(verification).thenReturn(context);
			assertAll(() -> PriceApplication.main(args));
			assertEquals(context, run(PriceApplication.class, args));
			assertAll(PriceApplication::new);
		}
	}
}
