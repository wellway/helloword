package com.httpdemo.entity;

import java.io.Serializable;

public class QuoteRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	
	int time;
	int date;
	String code;
	int market;
	String name;
	double previousClose;
	double open;
	double high;
	double low;
	double close;
	double value;//交易额
	double volume;//交易量

	
	
	double buy2;
	double buy2Volume;
	double buy3;
	double buy3Volume;
	double buy4;
	double buy4Volume;
	double buy5;
	double buy5Volume;
	

	
	
	
	double sell2;
	double sell2Volume;
	double sell3;
	double sell3Volume;
	double sell4;
	double sell4Volume;
	double sell5;
	double sell5Volume;
	

	double totalBuyVolume;
	double totalSellVolume;
	
	double peRatio;
	
	double netChange;
	double percentChange;
	double delta;
	double turnover;	
	double liangBi;
	double avg5Volume;
	double lastVolume;
	double innerVolume;
	double outerVolume;
	double averagePrice;
	String secucode;
	public int index; 
	public int minute; // 分钟
	public int periodType; // 交易段

	//个股分类
	public Integer securityType ; 
    public Integer lstSectCode;
    //流通股份
    double pfShareQty;
    //总股本
    double totalShareQty;
    public int volFix;
    //是否交易日
    public boolean isTrade;
    //成交笔数
    public int numTrades;
    
	public boolean isTrade() {
		return isTrade;
	}

	public void setTrade(boolean isTrade) {
		this.isTrade = isTrade;
	}

	public int getVolFix() {
		return volFix;
	}

	public void setVolFix(int volFix) {
		this.volFix = volFix;
	}

	public double getPfShareQty() {
		return pfShareQty;
	}

	public void setPfShareQty(double pfShareQty) {
		this.pfShareQty = pfShareQty;
	}

	public Integer getSecurityType() {
		return securityType;
	}

	public void setSecurityType(Integer securityType) {
		this.securityType = securityType;
	}

	public Integer getLstSectCode() {
		return lstSectCode;
	}

	public void setLstSectCode(Integer lstSectCode) {
		this.lstSectCode = lstSectCode;
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getPeriodType() {
		return periodType;
	}

	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}

	public String getSecucode()
	{
		return secucode;
	}
	
	public void setSecucode(String secucode)
	{
		this.secucode = secucode;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(double previousClose) {
		this.previousClose = previousClose;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	
	//证券状态
    /**
     *  第1位：‘S’表示启动（开市前）时段，‘C’表示集合竞价时段，‘T’表示连续交易时段，‘B’表示休市时段，‘E’表示闭市时段，‘P’表示产品停牌。
     *	第2位：‘0’表示未连续停牌，‘1’表示连续停牌。无意义填空格。
     *	第3位：‘0’表示未上市，‘1’表示已上市。
     */
    public String state;
   
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public double getNetChange() {
		return netChange;
	}
	public void setNetChange(double netChange) {
		this.netChange = netChange;
	}
	public double getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(double percentChange) {
		this.percentChange = percentChange;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public double getPeRatio() {
		return peRatio;
	}
	public void setPeRatio(double peRatio) {
		this.peRatio = peRatio;
	}
	public double getLiangBi() {
		return liangBi;
	}
	public void setLiangBi(double liangBi) {
		this.liangBi = liangBi;
	}
	public double getAvg5Volume() {
		return avg5Volume;
	}
	public void setAvg5Volume(double avg5Volume) {
		this.avg5Volume = avg5Volume;
	}
	public double getLastVolume() {
		return lastVolume;
	}
	public void setLastVolume(double lastVolume) {
		this.lastVolume = lastVolume;
	}
	public double getInnerVolume() {
		return innerVolume;
	}
	public void setInnerVolume(double innerVolume) {
		this.innerVolume = innerVolume;
	}
	public double getOuterVolume() {
		return outerVolume;
	}
	public void setOuterVolume(double outerVolume) {
		this.outerVolume = outerVolume;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	@Override
	public String toString(){
		return "Quote[code="+code+",name="+name+"]";
	}
	public double getTotalBuyVolume() {
		return totalBuyVolume;
	}
	public void setTotalBuyVolume(double totalBuyVolume) {
		this.totalBuyVolume = totalBuyVolume;
	}
	public double getTotalSellVolume() {
		return totalSellVolume;
	}
	public void setTotalSellVolume(double totalSellVolume) {
		this.totalSellVolume = totalSellVolume;
	}
	
	public int getMarket() {
		return market;
	}
	public void setMarket(int market) {
		this.market = market;
	}

	public double getTotalShareQty() {
		return totalShareQty;
	}

	public void setTotalShareQty(double totalShareQty) {
		this.totalShareQty = totalShareQty;
	}

	
	public int getNumTrades() {
		return numTrades;
	}

	public void setNumTrades(int numTrades) {
		this.numTrades = numTrades;
	}
	
}
