package com.tay.ContactMessageManagement.repository;


import com.tay.ContactMessageManagement.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("FROM ContactMessage cm WHERE cm.subject LIKE %:searchParam%")
    List<ContactMessage> findAllBySubjectLike(String searchParam);

    @Query("FROM ContactMessage WHERE creationDateTime BETWEEN :startDateTime AND :endDateTime")
    List<ContactMessage> findAllBetweenDates(LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query(value = "SELECT * FROM t_contact_message " +
           "WHERE TO_TIMESTAMP(TO_CHAR(creation_date_time, 'HH:MI'), 'HH24:MI') " +
           "BETWEEN TO_TIMESTAMP(:startTime, 'HH24:MI') " +
           "AND " +
           "TO_TIMESTAMP(:endTime, 'HH24:MI')", nativeQuery = true)
    List<ContactMessage> findAllBetweenTimes(String startTime, String endTime);

    List<ContactMessage> findByUser_Id(Long userId);
}
