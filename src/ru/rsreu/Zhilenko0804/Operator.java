package ru.rsreu.Zhilenko0804;

import java.util.Random;

public class Operator {

	private int id;
	private Client client;

	public Operator(int id) {
		this.id = id;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return this.client;
	}

	public int getId() {
		return id;
	}

	public void talk() {
		try {
			Thread.sleep(new Random().nextInt(300));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
