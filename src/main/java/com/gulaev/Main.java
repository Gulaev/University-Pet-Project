package com.gulaev;

import com.gulaev.config.SpringConfig;
import com.gulaev.dao.*;
import com.gulaev.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;


public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  @Autowired private ApplicationContext context;

  public static void main(String[] args) {

  }
}