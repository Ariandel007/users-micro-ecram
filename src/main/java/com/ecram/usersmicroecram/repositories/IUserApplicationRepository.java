package com.ecram.usersmicroecram.repositories;

import com.ecram.usersmicroecram.models.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserApplicationRepository extends JpaRepository<UserApplication, Long> {
}
