package com.suanfa;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author yalongz
 *
 */
public class BuySellPoint {
	public static void main(String[] args) {
		BuySellPoint buy = new BuySellPoint();
		int[] prices = new int[] { 7, 6, 5, 2, 1, 9 };
		Point point = buy.maxProfit(prices);
		System.out.println("" + point);
		
		List<String> list = new ArrayList<String>();
		list.size();
		String str = "sss";
		str.length();
	}

	/**
	 * 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。如果你最多只允许完成一次交易(例如,一次买卖股票),设计一个算法来找出最大利润。
	 * 
	 * @param prices
	 * @return
	 */
	public Point maxProfit(int prices[]) {
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		Point point = new Point();
		int start = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minprice) {
				minprice = prices[i];
				start = i;
			} else
				if (prices[i] - minprice > maxprofit) {
					maxprofit = prices[i] - minprice;
					point.end = i;
					point.start = start;
					point.maxprofit = maxprofit;
				}
		}
		return point;
	}

	class Point {
		private int	start;
		private int	end;
		private int	maxprofit;

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getMaxprofit() {
			return maxprofit;
		}

		public void setMaxprofit(int maxprofit) {
			this.maxprofit = maxprofit;
		}

		@Override
		public String toString() {
			return "Point [start=" + start + ", end=" + end + ", maxprofit=" + maxprofit + "]";
		}

	}
}
