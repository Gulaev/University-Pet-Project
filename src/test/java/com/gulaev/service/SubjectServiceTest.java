package com.gulaev.service;

import com.gulaev.dao.SpecialityDao;
import com.gulaev.dao.SubjectDao;
import com.gulaev.models.Speciality;
import com.gulaev.models.Subject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubjectServiceTest {

    private SubjectDao subjectDaoMock = mock(SubjectDao.class);
    private SpecialityDao specialityDaoMock = mock(SpecialityDao.class);

    @Test
    void getSubjectById() {
        Subject subject = new Subject(1, "History", "History Course", 1, 1);
        when(subjectDaoMock.getById(1)).thenReturn(subject);
        SubjectService subjectService = new SubjectService(subjectDaoMock, specialityDaoMock);
        assertEquals(subjectService.getSubjectById(1), subject);
        verify(subjectDaoMock, times(1)).getById(1);
    }

    @Test
    void addNewSubject() {
        List<Speciality> specialities = new ArrayList<>();
        Speciality speciality = new Speciality(1, "Math", 1, 1);
        specialities.add(speciality);

        Subject subject = new Subject(1, "History", "History Course", 1, 1);
        SubjectService subjectService = new SubjectService(subjectDaoMock, specialityDaoMock);
        when(specialityDaoMock.loadAll()).thenReturn(specialities);
        subjectService.addNewSubject("History", "History Course", "Math", 1);
        verify(subjectDaoMock, times(1))
                .addNewSubject("History", "History Course", 1, 1);

    }

    @Test
    void updateSubjectById() {
        Subject subject = new Subject(1, "Math", "Math Description",1 ,1);
        SubjectService subjectService = new SubjectService(subjectDaoMock, specialityDaoMock);
        subjectService.updateSubjectById(1, "Math", "Math Description",1,1);
        verify(subjectDaoMock, times(1)).update(1, subject);

    }
}