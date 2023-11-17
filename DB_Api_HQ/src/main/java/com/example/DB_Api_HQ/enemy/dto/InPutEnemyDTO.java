package com.example.DB_Api_HQ.enemy.dto;

import com.example.DB_Api_HQ.enemy.Enemy;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InPutEnemyDTO {
    private String name;
    private int dificulty;
    private byte[] infoImage;

}
