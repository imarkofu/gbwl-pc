package me.gbwl.pc.base;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.gbwl.pc.model.TianyaKeyword;
import me.gbwl.pc.model.TiebaKeyword;

public class KeywordsUtil {

	private int tiebaKeywordsCount = 0;
	private int tianyaKeywordCount = 0;
	private Set<String> tiebaKeywords = null;
	private Set<String> tianyaKeywords = null;
	
	
	public Set<String> getTiebaKeywords() {
		if (ContentHolder.tiebaKeywordService.getCount() != tiebaKeywordsCount || tiebaKeywords == null) {
			init();
		}
		return tiebaKeywords;
	}
	
	public Set<String> getTianyaKeywords() {
		if (ContentHolder.tianyaKeywordService.getCount() != tianyaKeywordCount || tianyaKeywords == null) {
			init();
		}
		return tianyaKeywords;
	}
	
	private synchronized void init() {
		if (ContentHolder.tiebaKeywordService.getCount() != tiebaKeywordsCount || tiebaKeywords == null) {
			tiebaKeywords = new HashSet<String>();
			List<TiebaKeyword> list = ContentHolder.tiebaKeywordService.getAll();
			if (list != null && list.size() > 0) {
				for (TiebaKeyword tk : list) {
					tiebaKeywords.add(tk.getKeywords());
				}
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
