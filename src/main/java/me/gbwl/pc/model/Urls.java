package me.gbwl.pc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="urls")
public class Urls {

	private Integer id;
	private String	name;
	private String	url;
	private Integer type;
	
	public static final Integer URLS_TYPE_TIEBA		= 1;
	public static final Integer URLS_TYPE_TIANYA	= 2;
	public static final Integer URLS_TYPE_JLSC		= 4;
	public static final Integer URLS_TYPE_JLSCQ		= 3;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Urls [id=").append(id).append(", name=").append(name)
				.append(", url=").append(url).append(", type=").append(type)
				.append("]");
		return builder.toString();
	}
}
