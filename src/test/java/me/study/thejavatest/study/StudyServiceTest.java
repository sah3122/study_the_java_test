package me.study.thejavatest.study;

import me.study.thejavatest.domain.Member;
import me.study.thejavatest.member.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {
// mock 객체 만들기 1 @ExtendWith(MockitoExtension.class)
//    @Mock
//    MemberService memberService;
//
//    @Mock
//    StudyRepository studyRepository;

    @Test// mock 객체 만들기 3 @ExtendWith(MockitoExtension.class)
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        // mock 객체 만들기 2
        //MemberService memberService = mock(MemberService.class);

        //StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }

}