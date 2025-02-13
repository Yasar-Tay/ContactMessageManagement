package com.tay.ContactMessageManagement.repository.business;

import com.tay.ContactMessageManagement.entity.business.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
