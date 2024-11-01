package com.ford.respository;

import com.ford.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact,Integer> {

}
