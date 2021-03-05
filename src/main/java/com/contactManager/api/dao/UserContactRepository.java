package com.contactManager.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactManager.api.model.UserContact;

public interface UserContactRepository extends JpaRepository<UserContact,Integer>{

	UserContact findByEmail(String email);

	List<UserContact> findByStatus(String status);

}
