package com.xiaoshu.entity;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.xiaoshu.entity.Stu;

public class StuVo extends Stu {

	private String sname;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start;
	 
	 private Integer num;
	 
	 
	
	

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}


	@Override
	public String toString() {
		return "StuVo [sname=" + sname + ", end=" + end + ", start=" + start + ", num=" + num + "]";
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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	
	
}
