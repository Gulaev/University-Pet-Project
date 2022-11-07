package com.gulaev.service;

import com.gulaev.dao.GroupAndLessonDao;
import com.gulaev.dao.GroupDao;
import com.gulaev.dao.LessonDao;
import com.gulaev.dao.StudentDao;
import com.gulaev.models.Group;
import com.gulaev.models.Lesson;
import com.gulaev.models.Student;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    private StudentDao studentDaoMock = mock(StudentDao.class);
    private GroupDao groupDaoMock = mock(GroupDao.class);
    private GroupAndLessonDao groupAndLessonDaoMock = mock(GroupAndLessonDao.class);
    private LessonDao lessonDaoMock = mock(LessonDao.class);
    private GroupAndLessonService groupAndLessonServiceMock = mock(GroupAndLessonService.class);

    @Test
    void getStudentById() {
        Student student = new Student(1, "John", "Doe", 1);
        when(studentDaoMock.getById(1)).thenReturn(student);
        StudentService studentService =
                new StudentService(studentDaoMock,groupDaoMock,groupAndLessonDaoMock,lessonDaoMock, groupAndLessonServiceMock);
        assertEquals(student, studentService.getStudentById(1));
        verify(studentDaoMock, times(1)).getById(1);
    }


    @Test
    void getStudentByGroups() {
        List<Student> students = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        List<Student> studentsOut = new ArrayList<>();
        students.add(new Student(1,"John","Doe",1));
        students.add(new Student(2,"John", "Doe", 2));
        studentsOut.add(new Student(1,"John","Doe", 1));
        groups.add(new Group(1,1));
        groups.add(new Group(2,1));
        when(studentDaoMock.loadAll()).thenReturn(students);
        when(groupDaoMock.loadAll()).thenReturn(groups);
        StudentService studentService = new StudentService(studentDaoMock,groupDaoMock, groupAndLessonDaoMock, lessonDaoMock, groupAndLessonServiceMock);
        assertEquals(studentsOut, studentService.getStudentByGroups(1));
    }


    @Test
    void getStudentByName() {
        Student student = new Student(1, "John", "Doe", 1);
        Student student1 = new Student(1, "Frank", "Doe", 1);
        List<Student> students = new ArrayList<>();
        List<Student> studentsOut = new ArrayList<>();
        students.add(student);
        students.add(student1);
        studentsOut.add(student);
        when(studentDaoMock.loadAll()).thenReturn(students);
        StudentService studentService = new StudentService(studentDaoMock, groupDaoMock, groupAndLessonDaoMock, lessonDaoMock, groupAndLessonServiceMock);
        assertEquals(studentsOut, studentService.getStudentByName("John"));
    }


    @Test
    void addNewStudent() {
        Student student = new Student(1, "John", "Doe", 1);
        StudentService studentService = new StudentService(studentDaoMock, groupDaoMock, groupAndLessonDaoMock, lessonDaoMock, groupAndLessonServiceMock);
        studentService.addNewStudent("John", "Doe", 1);
        verify(studentDaoMock, times(1)).addStudent("John", "Doe", 1);
    }


    @Test
    void updateStudentById() {
        Student student = new Student(1, "John", "Doe", 1);
        when(studentDaoMock.getById(1)).thenReturn(student);
        StudentService studentService = new StudentService(studentDaoMock, groupDaoMock, groupAndLessonDaoMock, lessonDaoMock, groupAndLessonServiceMock);
        studentService.updateStudentById(1,"John", "Doe", 1);
        verify(studentDaoMock, times(1)).update(1, student);
    }

    @Test
    void getLessonForStudentByStudentId() {
        Timestamp lessonStart = Timestamp.valueOf("2022-07-12 09:30:00.000");
        Timestamp lessonEnd = Timestamp.valueOf("2022-07-12 11:00:00.000");

        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson(1, "Math", lessonStart, lessonEnd,1,1));
        Student student = new Student(1, "John", "Doe", 1);
        when(studentDaoMock.getById(1)).thenReturn(student);
        when(groupAndLessonServiceMock.getAllLessonsByOneGroup(1)).thenReturn(lessons);
        assertEquals(groupAndLessonServiceMock.getAllLessonsByOneGroup(1), lessons);

    }
}