package me.gbwl.pc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jlsc")
public class JLSC {

	private Integer id;
	private String	pId;
	private String	pTitle;
	private String	pFrom;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpTitle() {
		return pTitle;
	}
	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}
	public String getpFrom() {
		return pFrom;
	}
	public void setpFrom(String pFrom) {
		this.pFrom = pFrom;
	}
	@Override
	public String toString() {
		return "JLSC [id=" + id + ", pId=" + pId + ", pTitle=" + pTitle
				+ ", pFrom=" + pFrom + "]";
	}
}
