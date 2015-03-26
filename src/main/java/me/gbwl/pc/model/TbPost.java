package me.gbwl.pc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_post")
public class TbPost
{
  private Integer id;
  private String pId;
  private String pTitle;
  private String pContent;
  private Integer pReplyCount;
  private String pLastUpdateDate;
  private String pName;
  private Integer isMatch;
  public static final Integer FALSE_MATCH = Integer.valueOf(0);
  public static final Integer TRUE_MATCH = Integer.valueOf(1);

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  public Integer getId() { return this.id; }

  public void setId(Integer id) {
    this.id = id;
  }
  public String getpId() {
    return this.pId;
  }
  public void setpId(String pId) {
    this.pId = pId;
  }
  public String getpTitle() {
    return this.pTitle;
  }
  public void setpTitle(String pTitle) {
    this.pTitle = pTitle;
  }
  public String getpContent() {
    return this.pContent;
  }
  public void setpContent(String pContent) {
    this.pContent = pContent;
  }
  public Integer getpReplyCount() {
    return this.pReplyCount;
  }
  public void setpReplyCount(Integer pReplyCount) {
    this.pReplyCount = pReplyCount;
  }
  public String getpLastUpdateDate() {
    return this.pLastUpdateDate;
  }
  public void setpLastUpdateDate(String pLastUpdateDate) {
    this.pLastUpdateDate = pLastUpdateDate;
  }
  public String getpName() {
    return this.pName;
  }
  public void setpName(String pName) {
    this.pName = pName;
  }
  public Integer getIsMatch() {
    return this.isMatch;
  }
  public void setIsMatch(Integer isMatch) {
    this.isMatch = isMatch;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("TbPost [id=").append(this.id).append(", pId=").append(this.pId)
      .append(", pTitle=").append(this.pTitle).append(", pContent=")
      .append(this.pContent).append(", pReplyCount=").append(this.pReplyCount)
      .append(", pLastUpdateDate=").append(this.pLastUpdateDate)
      .append(", pName=").append(this.pName).append(", isMatch=")
      .append(this.isMatch).append("]");
    return builder.toString();
  }
}