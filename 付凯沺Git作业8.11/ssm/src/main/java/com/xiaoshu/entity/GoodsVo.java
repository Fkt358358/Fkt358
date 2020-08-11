package com.xiaoshu.entity;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GoodsVo extends Goods {

	private String tname;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start;

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

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "GoodsVo [tname=" + tname + ", end=" + end + ", start=" + start + "]";
	}

}
