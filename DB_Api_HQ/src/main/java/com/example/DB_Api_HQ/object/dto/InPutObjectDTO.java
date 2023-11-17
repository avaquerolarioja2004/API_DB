package com.example.DB_Api_HQ.object.dto;

import com.example.DB_Api_HQ.object.Object;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InPutObjectDTO {
    private String name;
    private int x,y;
    private byte[] imageInfo;
    private boolean coversVisibility;

}
