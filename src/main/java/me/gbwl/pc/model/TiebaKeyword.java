package me.gbwl.pc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tieba_keyword")
public class TiebaKeyword {

	private Integer id;
	private String	keywords;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TiebaKeyword [id=").append(id).append(", keywords=")
				.append(keywords).append("]");
		return builder.toString();
	}
}
