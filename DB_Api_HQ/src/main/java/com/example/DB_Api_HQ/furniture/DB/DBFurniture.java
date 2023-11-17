package com.example.DB_Api_HQ.furniture.DB;

import com.example.DB_Api_HQ.furniture.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBFurniture extends JpaRepository<Furniture, Integer> {
    Optional<Furniture> findByNameAndXAndYAndCoversVisibility(String name, int x, int y, boolean coversVisibility);
}
