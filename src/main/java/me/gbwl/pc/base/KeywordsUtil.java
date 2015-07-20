package me.gbwl.pc.base;

import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.gbwl.pc.model.TianyaKeyword;
import me.gbwl.pc.model.TiebaKeyword;

public class KeywordsUtil {

	private int tiebaKeywordsCount = 0;
	private int tianyaKeywordCount = 0;
	private String[] tiebaKeywords = null;
	private Set<String> tianyaKeywords = null;
	
	
	public String[] getTiebaUrls() {
		if (ContentHolder.tiebaKeywordService.getCount() != tiebaKeywordsCount || this.tiebaKeywords == null) {
			init();
		}
		return this.tiebaKeywords;
	}
	
	public Set<String> getTianyaKeywords() {
		if (ContentHolder.tianyaKeywordService.getCount() != tianyaKeywordCount || tianyaKeywords == null) {
			init();
		}
		return tianyaKeywords;
	}
	
	private synchronized void init() {
		if (ContentHolder.tiebaKeywordService.getCount() != tiebaKeywordsCount || this.tiebaKeywords == null) {
			Set<String> tiebaKeywords = new HashSet<String>();
			List<TiebaKeyword> list = ContentHolder.tiebaKeywordService.getAll();
			if (list != null && list.size() > 0) {
				for (TiebaKeyword tk : list) {
					try {
						tiebaKeywords.add("http://tieba.baidu.com/f/search/res?isnew=1&kw=&qw=myKeywords&un=&rn=10&pn=0&sd=&ed=&ie=gbk&sm=1&only_thread=1".replace("myKeywords", URLEncoder.encode(tk.getKeywords(), "UTF-8")));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				this.tiebaKeywords = tiebaKeywords.toArray(new String[0]);
				tiebaKeywordsCount = list.size();
			}
		}
		
		if (ContentHolder.tianyaKeywordService.getCount() != tianyaKeywordCount || tianyaKeywords == null) {
			tianyaKeywords = new HashSet<String>();
			List<TianyaKeyword> list = ContentHolder.tianyaKeywordService.getAll();
			if (list != null && list.size() > 0) {
				for (TianyaKeyword tk : list) {
					tianyaKeywords.add("http://tieba.baidu.com/f/search/res?isnew=1&kw=&qw=myKeywords&un=&rn=10&pn=0&sd=&ed=&ie=gbk&sm=1&only_thread=1".replace("myKeywords", tk.getKeywords()));
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
