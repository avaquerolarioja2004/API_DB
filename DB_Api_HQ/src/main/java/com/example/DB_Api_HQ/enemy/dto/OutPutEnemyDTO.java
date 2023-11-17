package com.example.DB_Api_HQ.enemy.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutPutEnemyDTO {

    private int id;
    private String nombre;
    private int dificulty;
    private byte[] imageData;
}
