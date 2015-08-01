package me.gbwl.pc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="config")
public class Config {

	private Integer id;
	private String	keyword;
	private String	values;
	private String	des;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Config [id=").append(id).append(", keyword=")
				.append(keyword).append(", values=").append(values)
				.append(", des=").append(des).append("]");
		return builder.toString();
	}
}
