package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 사용자 신고 API
     */
    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {

        //요청받은 유저 list 상태 업데이트
        int updateStatusUser = userRepository.updateStatusToBlocked(userIds);

        if (updateStatusUser == 0){
            throw new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다.");
        }

    }
}
