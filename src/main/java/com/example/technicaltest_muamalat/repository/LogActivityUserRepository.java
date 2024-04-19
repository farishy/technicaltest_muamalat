package com.example.technicaltest_muamalat.repository;


import com.example.technicaltest_muamalat.model.LogActivityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LogActivityUserRepository extends JpaRepository<LogActivityUser, Integer> {
    LogActivityUser findLogActivityUserById(int id);
    @Query(value = "SELECT * FROM mt_log_activity_user where user_id = ? ", nativeQuery = true)
    List<LogActivityUser> findLogActivityUserByUserId(int userId);
}
