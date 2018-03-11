package ru.rsreu.Zhilenko0804;

import com.prutzkow.resourcebundledemo.Resourcer;

public class Client implements Runnable {

	private final static int WAITING_TIME = 100;
	private int id;
	private CallCenter callCenter = new CallCenter();

	public int getId() {
		return id;
	}

	public Client(CallCenter callCenter, int id) {
		this.callCenter = callCenter;
		this.id = id;
	}

	public void run() {
		Operator operator = null;
		try {
			while (operator == null) {
				System.out.printf(Resourcer.getString("callCenter.call"),
						this.id);
				operator = callCenter.tryCall(this, WAITING_TIME);
			}
			System.out.printf(Resourcer.getString("client.calling"), this.id,
					operator.getId());
			operator.talk();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (operator != null) {
				System.out.printf(Resourcer.getString("client.endCalling"),
						this.id, operator.getId());
				callCenter.endCall(operator);
			}
		}
	}
}
