package me.gbwl.pc.base;

import me.gbwl.pc.service.JLSCService;
import me.gbwl.pc.service.KeywordsService;
import me.gbwl.pc.service.TbPostService;
import me.gbwl.pc.service.TyPostService;

import org.springframework.context.ApplicationContext;

public class ContentHolder
{
  public static ApplicationContext context;
  public static Constant constant;
  public static TbPostService tbPostService;
  public static TyPostService tyPostService;
  public static JLSCService jlscService;
  public static KeywordsService keywordsService;
}