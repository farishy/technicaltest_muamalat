package com.example.technicaltest_muamalat.repository;

import com.example.technicaltest_muamalat.model.TipeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipeLogRepository extends JpaRepository<TipeLog, Integer> {
    TipeLog findTipeLogById(int id);
}
