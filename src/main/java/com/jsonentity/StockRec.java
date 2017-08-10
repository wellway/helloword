package com.jsonentity;

import java.io.Serializable;

public class StockRec implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String code;
	public String stockName;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockRec other = (StockRec) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	protected StockRec clone() {
		StockRec stock = null;
		try {
			stock = (StockRec) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return stock;
	}
}
