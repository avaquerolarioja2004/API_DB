package com.example.DB_Api_HQ.room.services;



import com.example.DB_Api_HQ.room.dto.InPutRoomDTO;
import com.example.DB_Api_HQ.room.dto.OutPutRoomDTO;

import java.util.List;

public interface ICRDRoom {
    public OutPutRoomDTO createRoom(InPutRoomDTO inRoom) throws Exception;
    public OutPutRoomDTO findRoom(int x, int y) throws Exception;
    public OutPutRoomDTO deleteRoom(int x, int y) throws Exception;
    public List<OutPutRoomDTO> findAllRoom() throws Exception;
}
