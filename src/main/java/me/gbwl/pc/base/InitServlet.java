package me.gbwl.pc.base;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import me.gbwl.pc.service.ConfigService;
import me.gbwl.pc.service.JLSCService;
import me.gbwl.pc.service.KeywordsService;
import me.gbwl.pc.service.TbPostService;
import me.gbwl.pc.service.TyPostService;
import me.gbwl.pc.service.UrlsService;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

public class InitServlet extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  private static final Logger logger = Logger.getLogger(InitServlet.class);

  public void init(ServletConfig config) throws ServletException
  {
    logger.info("--------------start init servlet-----------");
    super.init(config);
    ContentHolder.context = (WebApplicationContext)getServletConfig().getServletContext().getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.restful");
//    ContentHolder.constant = (Constant)ContentHolder.context.getBean("constant", Constant.class);
    ContentHolder.tbPostService = (TbPostService)ContentHolder.context.getBean("tbPostService", TbPostService.class);
    ContentHolder.tyPostService = (TyPostService)ContentHolder.context.getBean("tyPostService", TyPostService.class);
    ContentHolder.jlscService = ContentHolder.context.getBean("jlscService", JLSCService.class);
    ContentHolder.keywordsService = ContentHolder.context.getBean("keywordsService", KeywordsService.class);
    ContentHolder.urlsService = ContentHolder.context.getBean("urlsService", UrlsService.class);
    ContentHolder.configService = ContentHolder.context.getBean("configService", ConfigService.class);
//    BlackKeyHelper.loadBlackKey();
//    SpringUtil.getInstance();
    logger.info("--------------init servlet end-------------");
  }
}