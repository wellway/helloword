package com.gc;

import java.io.Serializable;

/**
* @ClassName: Im_users
* @Description:
* @author yalonz
* @date 2020年1月15日
*
*/
public class Im_users implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private long				id;
	private String				username;
	private String				usercode;
	private String				mobile;
	private String				job;
	private String				depart;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("-----------11111111-----------");
	}
}
