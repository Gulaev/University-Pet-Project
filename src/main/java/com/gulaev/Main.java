package com.gulaev;

import com.gulaev.config.SpringConfig;
import com.gulaev.dao.*;
import com.gulaev.repository.Audience;
import com.gulaev.repository.Group;
import com.gulaev.repository.Lesson;

import com.gulaev.repository.Speciality;
import com.gulaev.repository.Student;
import com.gulaev.repository.Subject;
import com.gulaev.repository.Teacher;
import com.gulaev.service.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;


@Slf4j
public class Main {


  @Autowired
  private ApplicationContext context;

  public static void main(String[] args) {

    Configuration configuration = new Configuration().addAnnotatedClass(Audience.class)
        .addAnnotatedClass(Student.class).addAnnotatedClass(Subject.class)
        .addAnnotatedClass(Group.class).addAnnotatedClass(Lesson.class)
        .addAnnotatedClass(Teacher.class).addAnnotatedClass(Speciality.class);

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    try {

      session.beginTransaction();

      //session.createQuery("FROM Lesson").getResultList().forEach(System.out::println);
      session.createQuery("FROM Group").getResultList().forEach(System.out::println);
    } catch (Exception e) {

    } finally {
      session.close();

    }

  }
}