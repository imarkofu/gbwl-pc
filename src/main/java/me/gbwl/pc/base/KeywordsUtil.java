package me.gbwl.pc.base;

import java.net.URLEncoder;
import java.util.List;

import me.gbwl.pc.model.Keywords;

public class KeywordsUtil {

	private int tiebaKeywordsCount = 0;
	private int tianyaKeywordCount = 0;
	private String[] tiebaUrls = null;
	private String[] tiebaKeywords = null;
	private String[] tianyaKeywords = null;
	
	
	public String[] getTiebaUrls() {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIEBA) != tiebaKeywordsCount || this.tiebaUrls == null) {
			init();
		}
		return this.tiebaUrls;
	}
	
	public boolean isTiebaKeywords(String content) {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIEBA) != tiebaKeywordsCount || this.tiebaKeywords == null) {
			init();
		}
		if (tiebaKeywords != null && tiebaKeywords.length > 0) {
			for (String k : tiebaKeywords) {
				if (content.indexOf(k) != -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean getTianyaKeywords(String content) {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIANYA) != tianyaKeywordCount || tianyaKeywords == null) {
			init();
		}
		if (tianyaKeywords != null && tianyaKeywords.length > 0) {
			for (String k : tianyaKeywords) {
				if (content.indexOf(k) != -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	private synchronized void init() {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIEBA) != tiebaKeywordsCount || this.tiebaUrls == null || this.tiebaKeywords == null) {
			
			List<Keywords> list = ContentHolder.keywordsService.getAll(Keywords.TYPE_TIEBA);
			if (list != null && list.size() > 0) {
				String[] tiebaKeywords = new String[list.size()];
				int i = 0;
				for (Keywords tk : list) {
					try {
						tiebaKeywords[i++] =tk.getKeywords();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				this.tiebaKeywords = tiebaKeywords;
				this.tiebaUrls = new String[tiebaKeywords.length];
				i = 0;
				for (String k : tiebaKeywords) {
					try {
						this.tiebaUrls[i++] = "http://tieba.baidu.com/f/search/res?isnew=1&kw=&qw=myKeywords&un=&rn=10&pn=0&sd=&ed=&ie=gbk&sm=1&only_thread=1".replace("myKeywords", URLEncoder.encode(k, "UTF-8"));
					} catch (Exception e) { }
				}
				tiebaKeywordsCount = list.size();
			} else {
				tiebaKeywords = new String[0];
				tiebaUrls = new String[0];
				tiebaKeywordsCount = 0;
			}
		}
		
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIANYA) != tianyaKeywordCount || tianyaKeywords == null) {
			List<Keywords> list = ContentHolder.keywordsService.getAll(Keywords.TYPE_TIANYA);
			if (list != null && list.size() > 0) {
				String[] keywords = new String[list.size()];
				int i = 0;
				for (Keywords tk : list) {
					try {
						keywords[i++] =tk.getKeywords();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				tianyaKeywords = keywords;
				tianyaKeywordCount = list.size();
			} else {
				tianyaKeywords = new String[0];
				tianyaKeywordCount = 0;
			}
		}
	}
	
	public static KeywordsUtil getInstance() {
		return Tools.keywordsUtil;
	}
	private static class Tools {
		private static KeywordsUtil keywordsUtil = new KeywordsUtil();
	}
	private KeywordsUtil() { }
}
