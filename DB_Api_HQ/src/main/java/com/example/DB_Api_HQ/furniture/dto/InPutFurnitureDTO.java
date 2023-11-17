package com.example.DB_Api_HQ.furniture.dto;

import com.example.DB_Api_HQ.furniture.Furniture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InPutFurnitureDTO {
    private String name;
    private int x,y;
    private byte[] imageInfo;
    private boolean coversVisibility;

}
