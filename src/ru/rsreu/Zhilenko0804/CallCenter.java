package ru.rsreu.Zhilenko0804;

import java.util.ArrayList;
import java.util.List;

import com.prutzkow.resourcebundledemo.Resourcer;

public class CallCenter {
	private final static int NUMBER_TRYING = 2;
	List<Operator> operators = new ArrayList<Operator>();

	public CallCenter(List<Operator> operators) {
		this.operators.addAll(operators);
	}

	public CallCenter() {
	}

	public synchronized Operator tryCall(Client client, int waitingTime) {
		int numberTrying = 0;
		boolean success = false;
		while (!success) {
			for (Operator operator : operators) {
				if (searchFreeOperator(operator, client)) {
					return operator;
				}
			}
			if (!success) {
				try {
					numberTrying++;
					if (checkCountTrying(numberTrying, waitingTime, client)) {
						return null;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public synchronized void endCall(Operator operator) {
		operator.setClient(null);
		operators.add(operator);
		notify();
	}

	private synchronized boolean checkCountTrying(int numberTrying,
			int waitingTime, Client client) throws InterruptedException {
		if (numberTrying == NUMBER_TRYING) {
			System.out.printf(Resourcer.getString("callCenter.putDown"),
					client.getId());
			wait(waitingTime);
			return true;

		} else {
			System.out.printf(Resourcer.getString("callCenter.waitOperator"),
					client.getId());
			wait(waitingTime);
		}
		return false;

	}

	private boolean searchFreeOperator(Operator operator, Client client) {
		if (operator.getClient() == null) {
			operator.setClient(client);
			operators.remove(operator);
			return true;
		}
		return false;
	}

}
