package com.kiraw.LoginKiraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiraw.LoginKiraw.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

}
