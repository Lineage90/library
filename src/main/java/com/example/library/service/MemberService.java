package com.example.library.service;

import com.example.library.entity.Member;
import com.example.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    public Member saveMember(Member member) {

        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {

        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {

        return memberRepository.findById(id);
    }

    public Optional<Member> getMemberByResidentRegistrationNumber(String residentRegistrationNumber) {
        return memberRepository.findByResidentRegistrationNumber(residentRegistrationNumber);
    }

    public Optional<Member> getMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }
}
