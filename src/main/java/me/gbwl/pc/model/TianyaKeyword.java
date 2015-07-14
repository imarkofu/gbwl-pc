package me.gbwl.pc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tianya_keyword")
public class TianyaKeyword {

	private Integer id;
	private String	keyword;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TianyaKeyword [id=").append(id).append(", keyword=")
				.append(keyword).append("]");
		return builder.toString();
	}
}
