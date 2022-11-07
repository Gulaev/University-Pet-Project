package com.gulaev.service;

import com.gulaev.dao.AudienceDao;
import com.gulaev.models.Audience;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AudienceServiceTest {

    private AudienceDao audienceDaoMock = mock(AudienceDao.class);

    @Test
    void getByAudienceId() {
        Audience audience =
                new Audience(1, 1, 1,100,true,false);
        List<Audience> audiences = new ArrayList<>();
        audiences.add(audience);
        when(audienceDaoMock.getById(1)).thenReturn(audience);
        AudienceService audienceService = new AudienceService(audienceDaoMock);
        assertEquals(audience, audienceService.getByAudienceId(1));
        verify(audienceDaoMock, times(1)).getById(1);
    }

    @Test
    void getEmptyAudience() {
        Audience audience =
                new Audience(1, 1, 1,100,true,false);
        Audience audience1 =
                new Audience(1, 1, 1,100,true,true);
        List<Audience> audiences =new ArrayList<>();
        List<Audience> audiencesOut =new ArrayList<>();
        audiences.add(audience); audiences.add(audience1); audiencesOut.add(audience1);
        when(audienceDaoMock.loadAll()).thenReturn(audiences);
        AudienceService audienceService = new AudienceService(audienceDaoMock);
        assertEquals(audiencesOut, audienceService.getEmptyAudience());
    }

    @Test
    void updateAudienceById() {
        Audience audience =
                new Audience(1, 1, 1,100,true,false);
        AudienceService audienceService = new AudienceService(audienceDaoMock);
        audienceService.updateAudienceById(1,1,1,100,true, false);
        verify(audienceDaoMock, times(1)).update(1, audience);
    }
}