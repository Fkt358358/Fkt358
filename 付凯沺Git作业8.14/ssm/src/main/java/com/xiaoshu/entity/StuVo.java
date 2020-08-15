package com.xiaoshu.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StuVo extends Stu {

	private String vname;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start;

	
	
	
	
	@Override
	public String toString() {
		return "StuVo [vname=" + vname + ", end=" + end + ", start=" + start + "]";
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

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

}
