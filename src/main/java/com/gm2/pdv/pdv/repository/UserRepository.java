package com.gm2.pdv.pdv.repository;

import com.gm2.pdv.pdv.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
