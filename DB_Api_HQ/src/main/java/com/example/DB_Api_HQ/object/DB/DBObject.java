package com.example.DB_Api_HQ.object.DB;

import com.example.DB_Api_HQ.enemy.Enemy;
import com.example.DB_Api_HQ.object.Object;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBObject extends JpaRepository<Object, Integer> {
    Optional<Object> findByNameAndXAndYAndCoversVisibility(String name, int x, int y, boolean coversVisibility);
}
