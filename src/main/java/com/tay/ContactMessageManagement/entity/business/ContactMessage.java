package com.tay.ContactMessageManagement.entity.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_contact_message")
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String subject;

    private String message;

    @Column(name = "creation_date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm", timezone = "US")
    private LocalDateTime creationDateTime;

    @PrePersist
    public void assignLocalDateTime(){
        creationDateTime = LocalDateTime.now();
    }
}
