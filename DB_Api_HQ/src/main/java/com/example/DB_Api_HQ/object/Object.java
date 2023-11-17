package com.example.DB_Api_HQ.object;


import com.example.DB_Api_HQ.object.dto.OutPutObjectDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Object implements Serializable {

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

    public OutPutObjectDTO objectToOutPut() {
        return new OutPutObjectDTO(id, name, x, y, imageInfo, coversVisibility);
    }
}
