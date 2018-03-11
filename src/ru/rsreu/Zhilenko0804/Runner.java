package ru.rsreu.Zhilenko0804;

import java.util.ArrayList;
import java.util.List;

public class Runner {
	private final static int NUMBER_OF_OPERATORS = 5;
	private final static int NUMBER_OF_CLIENTS = 9;

	private Runner() {
	}

	public static void main(String[] args) {

		List<Operator> operators = new ArrayList<Operator>();
		for (int i = 1; i < NUMBER_OF_OPERATORS; i++) {
			operators.add(new Operator(i));
		}
		CallCenter callCenter = new CallCenter(operators);
		for (int i = 1; i < NUMBER_OF_CLIENTS; i++) {
			Thread thread = new Thread(new Client(callCenter, i));
			thread.start();
		}
	}

}
