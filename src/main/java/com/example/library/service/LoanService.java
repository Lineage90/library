package com.example.library.service;

import com.example.library.entity.Loan;
import com.example.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getLoanHistoryByMemberId (Long memberId) {
        return loanRepository.findByMemberIdAndReturnStatusOrderByLoanDateAsc(memberId, true);
    }
}
