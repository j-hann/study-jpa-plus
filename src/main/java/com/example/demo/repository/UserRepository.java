package com.example.demo.repository;

import com.example.demo.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    //벌크 연산후 자동으로 영속성 컨텍스트 초기화
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.status = 'BLOCKED' WHERE u.id IN :userIds")
    int findByIdInAndsUpdateStatus(List<Long> userIds);
}
