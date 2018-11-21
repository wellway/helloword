package com.jsonentity;

import java.io.Serializable;

public class TagRec implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;// 行情指标参数下拉框的index，从1开始
	/**
	 * 指标值 ，用1或者0表示 比如MACD指标 有MACD金叉，MACD死叉，MACD顶背离，MACD底背离，MACD红二波
	 * 则在QuoteRec.Result当secucode=002153，指标名称为macd时，List里面应当有5个值
	 * result[002153.SZ][MACD][0].info =MACD金叉 val=1 表示当前具有金叉形态，val=0 表示不具有金叉形态
	 */
	private int val;
	private String info;// 指标文字说明

	public TagRec(String key, int val, String info) {
		this.key = key;
		this.val = val;
		this.info = info;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
