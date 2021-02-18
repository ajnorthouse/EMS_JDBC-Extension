package com.cognixia.jump.jdbc.ems_extension.team2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class RunnerTest {

	@Test
	void testRunner() {
		int expected = 0;
		int result = Runner.startUp(new Scanner(System.in));
		
		assertEquals(expected, result);
	}

}
