package com.ford.respository;

import com.ford.entity.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttachmentRepository extends JpaRepository<Attachments, Long> {
    // Define query methods here if needed
}