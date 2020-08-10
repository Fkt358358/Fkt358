package com.xiaoshu.entity;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentVo extends Student {

	private String sname;
	
	private String num;
	
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "StudentVo [sname=" + sname + ", num=" + num + ", end=" + end + ", start=" + start + "]";
	}

	
	


	
}
