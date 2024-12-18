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

    // TODO: 4. find or save 예제 개선
    //사용자 신고 기능
    @Transactional
    public void reportUsers(List<Long> userIds) {

//        for (Long userId : userIds) {
//            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다."));
//
//            user.updateStatusToBlocked();
//
//            userRepository.save(user);
//        }

        //요청받은 유저 list 상태 업데이트
        int updateStatusUser = userRepository.findByIdInAndsUpdateStatus(userIds);

        if (updateStatusUser == 0){
            throw new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다.");
        }

    }
}
