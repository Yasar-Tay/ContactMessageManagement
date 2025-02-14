package com.tay.ContactMessageManagement.repository.business;

import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
           "WHERE TO_TIMESTAMP(TO_CHAR(creation_date_time, 'HH:MI'), 'HH:MI') " +
           "BETWEEN TO_TIMESTAMP(:startTime, 'HH:MI') " +
           "AND " +
           "TO_TIMESTAMP(:endTime, 'HH:MI')", nativeQuery = true)
    List<ContactMessage> findAllBetweenTimes(String startTime, String endTime);

}
