package com.example.DB_Api_HQ.enemy.DB;

import com.example.DB_Api_HQ.enemy.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBEnemy extends JpaRepository<Enemy,Integer> {
    Optional<Enemy> findByNombreAndDificulty(String nombre, int dificulty);
}
