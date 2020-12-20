package com.valid.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.valid.backend.model.User;

@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends JpaRepository<User,Long>{

}
