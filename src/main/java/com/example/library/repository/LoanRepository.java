package com.example.library.repository;

import com.example.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMemberIdAndReturnStatus(Long memberId, boolean returnStatus);

    List<Loan> findByMemberIdAndReturnStatusOrderByLoanDateAsc(Long memberId, boolean b);
}
