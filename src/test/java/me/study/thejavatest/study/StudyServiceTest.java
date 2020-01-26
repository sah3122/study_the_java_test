package me.study.thejavatest.study;

import me.study.thejavatest.domain.Member;
import me.study.thejavatest.domain.Study;
import me.study.thejavatest.member.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        StudyService studyService = new StudyService(memberService, studyRepository);
        // mock 객체 만들기 2
        //MemberService memberService = mock(MemberService.class);

        //StudyRepository studyRepository = mock(StudyRepository.class);

        Study study = new Study(10, "java");

        Member member = new Member();
        member.setId(1L);
        //Optional<Member> optionalMember = memberService.findById(1L); // empty optional
        when(memberService.findById(1L)).thenReturn(Optional.of(member)); // stubbing
        when(memberService.findById(any())).thenReturn(Optional.of(member))
                .thenReturn(Optional.of(member)); // chaining stubbing

        studyService.createNewStudy(1L, study);
        doThrow(new IllegalArgumentException()).when(memberService).validate();

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate();
        });
        assertNotNull(studyService);

        verify(memberService, times(1)).notify(study);
        verify(memberService, times(1)).notify(member);
        verify(memberService, never()).validate();

        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);

        verifyNoInteractions(memberService);
    }

}