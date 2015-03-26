package me.gbwl.pc.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ty_post")
public class TyPost
{
  private Integer id;
  private String pId;
  private String pTitle;
  private String pAuthor;
  private Integer pClickCount;
  private Integer pReplyCount;
  private Date pReplyTime;
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
  public String getpAuthor() {
    return this.pAuthor;
  }
  public void setpAuthor(String pAuthor) {
    this.pAuthor = pAuthor;
  }
  public Integer getpClickCount() {
    return this.pClickCount;
  }
  public void setpClickCount(Integer pClickCount) {
    this.pClickCount = pClickCount;
  }
  public Integer getpReplyCount() {
    return this.pReplyCount;
  }
  public void setpReplyCount(Integer pReplyCount) {
    this.pReplyCount = pReplyCount;
  }
  public Date getpReplyTime() {
    return this.pReplyTime;
  }
  public void setpReplyTime(Date pReplyTime) {
    this.pReplyTime = pReplyTime;
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
    return "TyPost [id=" + this.id + ", pId=" + this.pId + ", pTitle=" + this.pTitle + 
      ", pAuthor=" + this.pAuthor + ", pClickCount=" + this.pClickCount + 
      ", pReplyCount=" + this.pReplyCount + ", pReplyTime=" + this.pReplyTime + 
      ", pName=" + this.pName + ", isMatch=" + this.isMatch + "]";
  }
}