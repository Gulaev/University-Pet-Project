package com.gulaev.service;

import com.gulaev.dao.GroupDao;
import com.gulaev.models.Group;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    private GroupDao groupDaoMock = mock(GroupDao.class);

    @Test
    void getGroupById() {
        Group group = new Group(1,1);
        when(groupDaoMock.getById(1)).thenReturn(group);
        GroupService groupService = new GroupService(groupDaoMock);
        assertEquals(groupService.getGroupById(1), group);
        verify(groupDaoMock, times(1)).getById(1);
    }

    @Test
    void updateGroupById() {
        Group group = new Group(1, 1);
        GroupService groupService = new GroupService(groupDaoMock);
        groupService.updateGroupById(1,1);
        verify(groupDaoMock, times(1)).update(1,group);

    }
}