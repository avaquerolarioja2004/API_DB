package com.example.DB_Api_HQ.room.dto;

import com.example.DB_Api_HQ.room.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InPutRoomDTO {
    private int x,y;

    public Room inPutToRoom(){
        return new Room(this.getX(),this.getY());
    }

}
