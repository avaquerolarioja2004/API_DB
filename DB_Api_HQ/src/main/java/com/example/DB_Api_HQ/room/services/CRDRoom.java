package com.example.DB_Api_HQ.room.services;

import com.example.DB_Api_HQ.room.DB.DBRoom;
import com.example.DB_Api_HQ.room.Room;
import com.example.DB_Api_HQ.room.dto.InPutRoomDTO;
import com.example.DB_Api_HQ.room.dto.OutPutRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CRDRoom implements ICRDRoom {

    @Autowired
    private DBRoom repository;


    @Override
    public OutPutRoomDTO createRoom(InPutRoomDTO inRoom) throws Exception {
        Optional<Room> op=repository.findById(new Room(inRoom.getX(),inRoom.getY()));
        Optional<Room> opReverse=repository.findById(new Room(inRoom.getY(),inRoom.getX()));
        if(inRoom.getX() <= 0 || inRoom.getY() <= 0) {
            System.err.println("Error: The room's data is not correct");
            throw  new IllegalArgumentException("Error: The room's data is not correct");
        }
        if(op.isPresent()||opReverse.isPresent()){
            System.err.println("Error: The room already exists");
            throw new IllegalStateException("Error: The room already exists");
        }
        try{
            repository.save(op.get());
            return op.get().roomToOutPut();
        }catch (Exception e){
            System.err.println("Error: The room could not be saved");
            throw new Exception();
        }
    }

    @Override
    public OutPutRoomDTO findRoom(int x, int y) throws Exception {
        Optional<Room> op=repository.findById(new Room(x,y));
        if(!op.isPresent()){
            System.err.println("Error: room not found");
            throw new NoSuchElementException("Error: room not found");
        }else{
            return op.get().roomToOutPut();
        }
    }

    @Override
    public OutPutRoomDTO deleteRoom(int x, int y) throws Exception {
        Optional<Room> op=repository.findById(new Room(x,y));
        if(!op.isPresent()){
            System.err.println("Error: room not found");
            throw new NoSuchElementException("Error: room not found");
        }
        try{
            repository.delete(op.get());
            return op.get().roomToOutPut();
        }catch (Exception e){
            System.err.println("Error: The room could not be deleted");
            throw new Exception();
        }
    }

    @Override
    public List<OutPutRoomDTO> findAllRoom() {
        ArrayList <OutPutRoomDTO> lista=new ArrayList<>();
        for (Room r:repository.findAll()) {
            lista.add(r.roomToOutPut());
        };
        if (lista.isEmpty()) {
            System.err.println("Error: There are no rooms in the database");
            throw new NoSuchElementException("Error: There are no rooms in the database");
        }
        return lista;
    }
}
