package com.example.DB_Api_HQ.furniture;


import com.example.DB_Api_HQ.furniture.dto.OutPutFurnitureDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Furniture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int x;
    @Column(nullable = false)
    private int y;
    private byte[] imageInfo;
    @Column(nullable = false)
    private boolean coversVisibility;

    public OutPutFurnitureDTO furnitureToOutPut(){
        return new OutPutFurnitureDTO(id,name,x,y,imageInfo,coversVisibility);
    }
}
