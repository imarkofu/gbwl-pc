package me.gbwl.pc.base;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import me.gbwl.pc.service.JLSCService;
import me.gbwl.pc.service.TbPostService;
import me.gbwl.pc.service.TianyaKeywordService;
import me.gbwl.pc.service.TiebaKeywordService;
import me.gbwl.pc.service.TyPostService;
import me.gbwl.pc.util.BlackKeyHelper;
import me.gbwl.pc.util.SpringUtil;

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
    ContentHolder.constant = (Constant)ContentHolder.context.getBean("constant", Constant.class);
    ContentHolder.tbPostService = (TbPostService)ContentHolder.context.getBean("tbPostService", TbPostService.class);
    ContentHolder.tyPostService = (TyPostService)ContentHolder.context.getBean("tyPostService", TyPostService.class);
    ContentHolder.jlscService = ContentHolder.context.getBean("jlscService", JLSCService.class);
    ContentHolder.tiebaKeywordService = ContentHolder.context.getBean("tiebaKeywordService", TiebaKeywordService.class);
    ContentHolder.tianyaKeywordService = ContentHolder.context.getBean("tianyaKeywordService", TianyaKeywordService.class);
    BlackKeyHelper.loadBlackKey();
    SpringUtil.getInstance();
    logger.info("--------------init servlet end-------------");
  }
}