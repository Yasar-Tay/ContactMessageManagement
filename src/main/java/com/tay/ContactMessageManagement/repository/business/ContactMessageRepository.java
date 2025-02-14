package com.tay.ContactMessageManagement.repository.business;

import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("FROM ContactMessage cm WHERE cm.subject LIKE %:searchParam%")
    List<ContactMessage> findAllBySubjectLike(String searchParam);

    @Query("FROM ContactMessage WHERE creationDateTime BETWEEN :beginDate AND :finalDate")
    List<ContactMessage> findAllBetweenDates(LocalDateTime beginDate, LocalDateTime finalDate);
}
