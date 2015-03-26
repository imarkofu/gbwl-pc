package me.gbwl.pc.main.thread;

import me.gbwl.pc.main.pageProcessor.TieBaDetailPageProcessor;
import me.gbwl.pc.scheduler.MyScheduler;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;

public class PullPostDetailThread extends Thread
{
  private static final Logger logger = Logger.getLogger(PullPostDetailThread.class);
  private String id;

  public PullPostDetailThread(String id)
  {
    this.id = id;
  }

  public void run()
  {
    logger.info(this.id);
    Spider.create(new TieBaDetailPageProcessor())
      .addUrl(new String[] { 
      "http://tieba.baidu.com/p/" + this.id })
      .thread(1).start();
  }

  public static void main(String[] args) {
    Spider spider = Spider.create(new TieBaDetailPageProcessor())
      .addUrl(new String[] { 
      "http://tieba.baidu.com/p/3352537001" })
      .thread(1).setExitWhenComplete(false)
      .setScheduler(new MyScheduler("taobao="));
    spider.start();
    try {
      Thread.sleep(10000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    spider.addUrl(new String[] { "http://tieba.baidu.com/p/3352537001" });
    spider.addUrl(new String[] { "http://tieba.baidu.com/p/3352537001" });
    spider.addUrl(new String[] { "http://tieba.baidu.com/p/3352537003" });
    spider.addUrl(new String[] { "http://tieba.baidu.com/p/3352537004" });
    spider.addUrl(new String[] { "http://tieba.baidu.com/p/3352537005" });
    spider.addUrl(new String[] { "http://tieba.baidu.com/p/3352537006" });
  }
}