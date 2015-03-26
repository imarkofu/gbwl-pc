package me.gbwl.pc.main;

import me.gbwl.pc.main.pageProcessor.TopTaobaoPageProcessor;
import us.codecraft.webmagic.Spider;

public class TopTaobao {
	public static void main(String[] args) {
		String[] urls = {
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=0",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=30",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=60",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=90",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=120",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=150",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=180",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=210",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=240",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=270",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=300",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=330",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=360",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=390",
				"http://top.etao.com/level3.php?spm=0.0.0.0.HgPZnz&cat=16&level3=&show=focus&up=true&offset=420" };

		Spider.create(new TopTaobaoPageProcessor()).thread(1).addUrl(urls)
				.start();
	}
}