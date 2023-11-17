package com.example.DB_Api_HQ.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutPutFurnitureDTO {
    private int id;
    private String name;
    private int x,y;
    private byte[] image;
    private boolean coversVisibility;
}
