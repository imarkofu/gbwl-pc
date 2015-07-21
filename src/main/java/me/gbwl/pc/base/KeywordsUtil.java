package me.gbwl.pc.base;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.gbwl.pc.model.Keywords;

public class KeywordsUtil {

	private int tiebaKeywordsCount = 0;
	private int tianyaKeywordCount = 0;
	private String[] tiebaUrls = null;
	private Set<String> tiebaKeywords = null;
	private Set<String> tianyaKeywords = null;
	
	
	public String[] getTiebaUrls() {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIEBA) != tiebaKeywordsCount || this.tiebaUrls == null) {
			init();
		}
		return this.tiebaUrls;
	}
	
	public Set<String> getTiebaKeywords() {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIEBA) != tiebaKeywordsCount || this.tiebaKeywords == null) {
			init();
		}
		return this.tiebaKeywords;
	}
	
	public Set<String> getTianyaKeywords() {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIANYA) != tianyaKeywordCount || tianyaKeywords == null) {
			init();
		}
		return tianyaKeywords;
	}
	
	private synchronized void init() {
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIEBA) != tiebaKeywordsCount || this.tiebaUrls == null || this.tiebaKeywords == null) {
			Set<String> tiebaKeywords = new HashSet<String>();
			List<Keywords> list = ContentHolder.keywordsService.getAll(Keywords.TYPE_TIEBA);
			if (list != null && list.size() > 0) {
				for (Keywords tk : list) {
					try {
						tiebaKeywords.add(tk.getKeywords());
//						tiebaKeywords.add();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				this.tiebaKeywords = tiebaKeywords;
				this.tiebaUrls = new String[tiebaKeywords.size()];
				int i = 0;
				for (String k : tiebaKeywords) {
					try {
						this.tiebaUrls[i++] = "http://tieba.baidu.com/f/search/res?isnew=1&kw=&qw=myKeywords&un=&rn=10&pn=0&sd=&ed=&ie=gbk&sm=1&only_thread=1".replace("myKeywords", URLEncoder.encode(k, "UTF-8"));
					} catch (Exception e) { }
				}
				tiebaKeywordsCount = list.size();
			}
		}
		
		if (ContentHolder.keywordsService.getCount(Keywords.TYPE_TIANYA) != tianyaKeywordCount || tianyaKeywords == null) {
			tianyaKeywords = new HashSet<String>();
			List<Keywords> list = ContentHolder.keywordsService.getAll(Keywords.TYPE_TIANYA);
			if (list != null && list.size() > 0) {
				for (Keywords tk : list) {
					tianyaKeywords.add(tk.getKeywords());
				}
				tianyaKeywordCount = list.size();
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
