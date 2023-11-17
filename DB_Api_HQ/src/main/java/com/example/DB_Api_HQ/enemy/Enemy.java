package com.example.DB_Api_HQ.enemy;


import com.example.DB_Api_HQ.enemy.dto.InPutEnemyDTO;
import com.example.DB_Api_HQ.enemy.dto.OutPutEnemyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enemy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int dificulty;
    @Column(length = Integer.MAX_VALUE)
    private byte[] imageData;

    public OutPutEnemyDTO toOutPutEnemyDTO(){
        return new OutPutEnemyDTO(this.id,this.nombre,this.dificulty,this.imageData);
    }
}
