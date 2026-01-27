package com.example.jwt.repository;

import com.example.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    📌 JpaRepository 📌
    - JpaRepository는 Entity에 대한 기본적인 CRUD 메서드를 제공한다.
    - JpaRepository를 상속한 인터페이스는 Spring Data JPA에 의해 자동으로 구현체가 생성되고, Spring Bean으로 등록된다.
*/
public interface UserRepository extends JpaRepository<User, Long> {
    // 메서드명은 임의로 지은 것이 아니라, 규칙을 따른 것이다.  →  검색 키워드 : JPA 쿼리 메서드
    public User findByUsername(String username); // == SELECT * FROM user WHERE username = :username
}