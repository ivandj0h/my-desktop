package ivandjoh.online.mydesktop.repository;

import ivandjoh.online.mydesktop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> { }
