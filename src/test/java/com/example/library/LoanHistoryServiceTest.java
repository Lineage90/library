package com.example.library;

import com.example.library.entity.Loan;
import com.example.library.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LoanHistoryServiceTest {
    @Autowired
    private LoanService loanHistoryService;

    @Test
    public void testGetLoanHistoryByMemberId() {

        List<Loan> loanHistory = loanHistoryService.getLoanHistoryByMemberId(memberId);

    }
}
