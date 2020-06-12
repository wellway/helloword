package com.threaddemo.asyFuture;

//包装器Wrapper，来将worker和回调器包装一下。
public class Wrapper {
	private Object		params;
	private Worker		worker;
	private Listener	listener;

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Listener getListener() {
		return listener;
	}

	public void addListener(Listener listener) {
		this.listener = listener;
	}

}
