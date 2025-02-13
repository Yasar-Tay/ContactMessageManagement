package com.tay.ContactMessageManagement.repository.business;

import com.tay.ContactMessageManagement.dto.response.ContactMessageResponse;
import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByEmail(String email);

    boolean existsByEmail(String email);
}
