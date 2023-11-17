package com.example.DB_Api_HQ.room;


import com.example.DB_Api_HQ.room.dto.InPutRoomDTO;
import com.example.DB_Api_HQ.room.dto.OutPutRoomDTO;
import com.example.DB_Api_HQ.room.services.CRDRoom;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("room")
@RestController
public class RoomController {

    private final CRDRoom crdRoom;

    public RoomController(CRDRoom crdRoom) {
        this.crdRoom = crdRoom;
    }

    @GetMapping("/findRoom:{x},{y}")
    public ResponseEntity<OutPutRoomDTO> findRoomById(@PathVariable int x, @PathVariable int y) {
        try {
            return ResponseEntity.ok(crdRoom.findRoom(x,y));
        } catch (NoSuchElementException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("NotFound",
                    e.getMessage());

            return ResponseEntity.notFound()
                    .headers(responseHeaders)
                    .build();
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ERROR",
                    e.getMessage());

            return ResponseEntity.status(500)
                    .headers(responseHeaders)
                    .build();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<OutPutRoomDTO>> findAllRoom(){
        try {
            return ResponseEntity.ok(crdRoom.findAllRoom());
        }catch(NoSuchElementException e){
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("NotFound",
                    e.getMessage());

            return ResponseEntity.notFound()
                    .headers(responseHeaders)
                    .build();
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ERROR",
                    e.getMessage());

            return ResponseEntity.status(500)
                    .headers(responseHeaders)
                    .build();
        }
    }

    @PostMapping("/postRoom")
    public ResponseEntity<OutPutRoomDTO> createRoom(@RequestBody InPutRoomDTO in){
        try{
            return ResponseEntity.ok(crdRoom.createRoom(in));
        } catch (IllegalArgumentException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("IllegalArguments",
                    e.getMessage());

            return ResponseEntity.status(400)
                    .headers(responseHeaders)
                    .build();
        } catch (IllegalStateException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("DuplicatedValue",
                    e.getMessage());

            return ResponseEntity.status(409)
                    .headers(responseHeaders)
                    .build();
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ERROR",
                    e.getMessage());

            return ResponseEntity.status(500)
                    .headers(responseHeaders)
                    .build();
        }
    }

    @DeleteMapping("/deleteRoom:{x},{y}")
    public ResponseEntity<OutPutRoomDTO> deleteRoom(@PathVariable int x, @PathVariable int y) {
        try{
            return ResponseEntity.ok(crdRoom.deleteRoom(x,y));
        }catch (NoSuchElementException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("NotFound",
                    e.getMessage());

            return ResponseEntity.notFound()
                    .headers(responseHeaders)
                    .build();
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("ERROR",
                    e.getMessage());

            return ResponseEntity.status(500)
                    .headers(responseHeaders)
                    .build();
        }
    }
}
