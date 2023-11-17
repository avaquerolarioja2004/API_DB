package com.example.DB_Api_HQ.room;


import com.example.DB_Api_HQ.room.dto.OutPutRoomDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Room.class)
public class Room implements Serializable {
    @Id
    private int x;
    @Id
    private int y;

    public OutPutRoomDTO roomToOutPut(){
        return new OutPutRoomDTO(this.getX(),this.getY());
    }
}
