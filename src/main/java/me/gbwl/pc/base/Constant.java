package me.gbwl.pc.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.gbwl.pc.util.StringUtil;

import org.springframework.beans.factory.annotation.Value;

public class Constant {

	@Value(value = "${tiebaURL}")
	private String tiebaURL;

	@Value(value = "${serverHost}")
	private String serverHost;

	@Value(value = "${userName}")
	private String userName;

	@Value(value = "${userPassword}")
	private String userPassword;

	@Value(value = "${validate}")
	private String validate;
	
	@Value(value = "${emails}")
	private String emails;

	@Value(value = "${displayName}")
	private String displayName;

	@Value(value = "${toAddress}")
	private String toAddress;

	@Value(value = "${subject}")
	private String subject;

	@Value(value = "${delayTime}")
	private int delayTime;

	@Value(value = "${retryTime}")
	private int retryTime;

	@Value(value = "${tianyaURL}")
	private String tianyaURL;

	@Value(value = "${tianyaFirst}")
	private boolean tianyaFirst;
	@Value(value = "${tiebaFirst}")
	private boolean tiebaFirst;

	@Value(value = "${tianyaLongTime}")
	private long tianyaLongTime;

	@Value(value = "${dataDayAgo}")
	private long dataDayAgo;
	@Value(value = "${tiebaAgo}")
	private long tiebaAgo;
	@Value(value = "${tianyaAgo}")
	private long tianyaAgo;

	@Value(value = "${isTieBaRun}")
	private boolean isTieBaRun;

	@Value(value = "${isTianYaRun}")
	private boolean isTianYaRun;
	@Value(value = "${isSendEmail}")
	private boolean isSendEmail;
	@Value(value = "${tianYaThreadCount}")
	private Integer tianYaThreadCount;
	@Value(value = "${tieBaThreadCount}")
	private Integer	tieBaThreadCount;
	@Value(value = "${tieBaDetailThreadCount}")
	private Integer tieBaDetailThreadCount;
	@Value(value = "${tianYaDetailThreadCount}")
	private Integer tianYaDetailThreadCount;
	
	@Value(value = "${tieBaDetailRun}")
	private boolean tieBaDetailRun;
	@Value(value = "${tianYaDetailRun}")
	private boolean tianYaDetailRun;
	
	@Value(value = "${isPush}")
	private boolean isPush;
	@Value(value = "${jpushMasterSecret}")
	private String jpushMasterSecret;
	@Value(value = "${jpushAppKey}")
	private String jpushAppKey;
	@Value(value = "${jpushAlias}")
	private String jpushAlias;
	
	@Value(value = "${isJLSCRun}")
	private boolean isJLSCRun;
	@Value(value = "${jlscURL}")
	private String jlscURL;
	@Value(value = "${jlscThreadCount}")
	private Integer jlscThreadCount;

	public String[] getTiebaURL() {
		if ((this.tiebaURL == null) || ("".equals(this.tiebaURL.trim())))
			return new String[0];
		if (this.tiebaURL.indexOf("|") == -1) {
			return new String[] { this.tiebaURL };
		}
		return this.tiebaURL.split("\\|");
//		if (this.tiebaURL.indexOf("|") == -1) {
//			return new String[] { this.tiebaURL + "&pn=0"/*,
//					this.tiebaURL + "&pn=50", this.tiebaURL + "&pn=100"/*,
//					this.tiebaURL + "&pn=150", this.tiebaURL + "&pn=200",
//					this.tiebaURL + "&pn=250", this.tiebaURL + "&pn=300",
//					this.tiebaURL + "&pn=350", this.tiebaURL + "&pn=400",
//					this.tiebaURL + "&pn=450", this.tiebaURL + "&pn=500",
//					this.tiebaURL + "&pn=550"*/ };
//		}
//
//		String[] arr = this.tiebaURL.split("\\|");
//		String[] result = new String[arr.length * 1];
//		for (int i = 0; i < arr.length; i++) {
//			String[] str = { arr[i] + "&pn=0"/*, arr[i] + "&pn=50",
//					arr[i] + "&pn=100", arr[i] + "&pn=150", arr[i] + "&pn=200",
//					arr[i] + "&pn=250", arr[i] + "&pn=300", arr[i] + "&pn=350",
//					arr[i] + "&pn=400", arr[i] + "&pn=450", arr[i] + "&pn=500",
//					arr[i] + "&pn=550"*/ };
//			System.arraycopy(str, 0, result, i * 1, str.length);
//		}
//		return result;
	}
	public String getServerHost() {
		return this.serverHost;
	}
	public String getUserName() {
		return this.userName;
	}
	public String getUserPassword() {
		return this.userPassword;
	}
	public String getValidate() {
		return this.validate;
	}
	public String getDisplayName() {
		return this.displayName;
	}
	public String getToAddress() {
		return this.toAddress;
	}
	public String getSubject() {
		return this.subject;
	}
	public int getDelayTime() {
		return this.delayTime;
	}
	public int getRetryTime() {
		return this.retryTime;
	}
	public String[] getTianyaURL() {
		if ((this.tianyaURL == null) || ("".equals(this.tianyaURL.trim())))
			return new String[0];
		if (this.tianyaURL.indexOf("|") == -1) {
			return new String[] { this.tianyaURL };
		}
		return this.tianyaURL.split("\\|");
	}
	public String[] getJLSCURL() {
		if (StringUtil.isEmpty(jlscURL) || "".equals(jlscURL.trim()))
			return new String[0];
		if (jlscURL.indexOf("|") == -1) 
			return new String[]{jlscURL};
		return jlscURL.split("\\|");
	}
	public String getOriginalTianyaURL() {
		return this.tianyaURL;
	}
	public boolean isTianyaFirst() {
		return this.tianyaFirst;
	}
	public boolean isTiebaFirst() {
		return tiebaFirst;
	}
	public long getTianyaLongTime() {
		return this.tianyaLongTime;
	}
	public long getDataDayAgo() {
		return this.dataDayAgo;
	}
	public boolean isTieBaRun() {
		return this.isTieBaRun;
	}
	public boolean isTianYaRun() {
		return this.isTianYaRun;
	}
	public boolean isSendEmail() {
		return isSendEmail;
	}
	public void setSendEmail(boolean isSendEmail) {
		this.isSendEmail = isSendEmail;
	}
	public Integer getTianYaThreadCount() {
		return tianYaThreadCount;
	}
	public Integer getTieBaThreadCount() {
		return tieBaThreadCount;
	}
	public Integer getTieBaDetailThreadCount() {
		return tieBaDetailThreadCount;
	}
	public long getTiebaAgo() {
		return tiebaAgo;
	}
	public Integer getTianYaDetailThreadCount() {
		return tianYaDetailThreadCount;
	}
	public long getTianyaAgo() {
		return tianyaAgo;
	}
	
	List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	int position = 0;
	public Map<String, String> getEmail() {
		if (list == null || list.size() <= 0) {
			String[] emailArr = emails.split("\\|\\|");
			if (emailArr != null) {
				for (String emailStr : emailArr) {
					String[] arr = emailStr.split("\\|");
					if (arr != null && arr.length == 3) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("stmp", arr[0]); map.put("email", arr[1]); map.put("pass", arr[2]);
						list.add(map);
					}
				}
			}
		}
		if (position+1 == list.size()) {
			position = 0;
		} else {
			position ++;
		}
		return list.get(position);
	}
	public boolean isTieBaDetailRun() {
		return tieBaDetailRun;
	}
	public boolean isTianYaDetailRun() {
		return tianYaDetailRun;
	}
	public boolean isPush() {
		return isPush;
	}
	public void setPush(boolean isPush) {
		this.isPush = isPush;
	}
	public String getJpushMasterSecret() {
		return jpushMasterSecret;
	}
	public String getJpushAppKey() {
		return jpushAppKey;
	}
	public String[] getJpushAlias() {
		return jpushAlias.split(",");
	}
	public boolean isJLSCRun() {
		return isJLSCRun;
	}
	public Integer getJlscThreadCount() {
		return jlscThreadCount;
	}
}