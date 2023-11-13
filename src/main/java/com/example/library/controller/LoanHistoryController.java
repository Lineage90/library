package com.example.library.controller;

import com.example.library.entity.Loan;
import com.example.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan-history")
public class LoanHistoryController {

    private final LoanService loanService;

    @Autowired
    public LoanHistoryController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{memberId}")
    public List<Loan> getLoanHistoryByMemberId(@PathVariable Long memberId) {
        return loanService.getLoanHistoryByMemberId(memberId);
    }
}
