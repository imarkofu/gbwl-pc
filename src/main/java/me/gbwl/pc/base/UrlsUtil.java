package me.gbwl.pc.base;

import java.util.List;

import me.gbwl.pc.model.Urls;

public class UrlsUtil {

	private int tiebaUrlsCount	= 0;
	private int tianyaUrlsCount	= 0;
	private int jlscUrlsCount	= 0;
	private int jlscQUrlsCount	= 0;
	private String[] tiebaUrls	= null;
	private String[] tianyaUrls	= null;
	private String[] jlscUrls	= null;
	private String[] jlscQUrls	= null;
	
	public String[] getTiebaUrls() {
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_TIEBA) != tiebaUrlsCount || tiebaUrls == null) {
			init();
		}
		return tiebaUrls;
	}
	public String[] getTianyaUrls() {
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_TIANYA) != tianyaUrlsCount || tianyaUrls == null) {
			init();
		}
		return tianyaUrls;
	}
	public String[] getJLSCUrls() {
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_JLSC) != jlscUrlsCount || jlscUrls == null) {
			init();
		}
		return jlscUrls;
	}
	public String[] getJLSCQUrls() {
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_JLSCQ) != jlscQUrlsCount || jlscQUrls == null) {
			init();
		}
		return jlscQUrls;
	}
	
	
	private synchronized void init() {
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_TIEBA) != tiebaUrlsCount || tiebaUrls == null) {
			List<Urls> urls = ContentHolder.urlsService.getAll(Urls.URLS_TYPE_TIEBA);
			if (urls == null || urls.size() <= 0) {
				this.tiebaUrls = new String[0];
			} else {
				String[] t = new String[urls.size()];
				for (int i = 0; i < urls.size(); i ++) {
					try {
						t[i] = urls.get(i).getUrl();
					} catch (Exception e) { }
				}
				this.tiebaUrls = t;
			}
			tiebaUrlsCount = tiebaUrls.length;
		}
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_TIANYA) != tianyaUrlsCount || tianyaUrls == null) {
			List<Urls> urls = ContentHolder.urlsService.getAll(Urls.URLS_TYPE_TIANYA);
			if (urls == null || urls.size() <= 0) {
				this.tianyaUrls = new String[0];
			} else {
				String[] t = new String[urls.size()];
				for (int i = 0; i < urls.size(); i ++) {
					try {
						t[i] = urls.get(i).getUrl();
					} catch (Exception e) { }
				}
				this.tianyaUrls = t;
			}
			tianyaUrlsCount = tianyaUrls.length;
		}
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_JLSC) != jlscUrlsCount || jlscUrls == null) {
			List<Urls> urls = ContentHolder.urlsService.getAll(Urls.URLS_TYPE_JLSC);
			if (urls == null || urls.size() <= 0) {
				this.jlscUrls = new String[0];
			} else {
				String[] t = new String[urls.size()];
				for (int i = 0; i < urls.size(); i ++) {
					try {
						t[i] = urls.get(i).getUrl();
					} catch (Exception e) { }
				}
				this.jlscUrls = t;
			}
			jlscUrlsCount = jlscUrls.length;
		}
		if (ContentHolder.urlsService.getCount(Urls.URLS_TYPE_JLSCQ) != jlscUrlsCount || jlscQUrls == null) {
			List<Urls> urls = ContentHolder.urlsService.getAll(Urls.URLS_TYPE_JLSCQ);
			if (urls == null || urls.size() <= 0) {
				this.jlscQUrls = new String[0];
			} else {
				String[] t = new String[urls.size()];
				for (int i = 0; i < urls.size(); i ++) {
					try {
						t[i] = urls.get(i).getUrl();
					} catch (Exception e) { }
				}
				this.jlscQUrls = t;
			}
			jlscQUrlsCount = jlscQUrls.length;
		}
	}
	
	private UrlsUtil() {}
	private static class Tools {
		private static final UrlsUtil urlsUtil = new UrlsUtil();
	}
	public static UrlsUtil getInstance() {
		return Tools.urlsUtil;
	}
}
