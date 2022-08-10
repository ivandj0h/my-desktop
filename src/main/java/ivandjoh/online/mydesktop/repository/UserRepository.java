package ivandjoh.online.mydesktop.repository;

import ivandjoh.online.mydesktop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
