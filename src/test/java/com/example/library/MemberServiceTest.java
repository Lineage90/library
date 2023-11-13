package com.example.library;

import com.example.library.entity.Member;
import com.example.library.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void testMemberRegistration() {
        // Given
        Member member = new Member();
        member.setName("Test Member");
        member.setGender("Male");
        member.setResidentRegistrationNumber("YYMMDD-1234567");
        member.setPhoneNumber("010-1234-5678");
        member.setAddress("Test Address");

        // When
        Member registeredMember = memberService.saveMember(member);

        // Then
        assertNotNull(registeredMember);
        assertNotNull(registeredMember.getId());
        assertEquals("Test Member", registeredMember.getName());
        // Add more assertions as needed
    }
}
