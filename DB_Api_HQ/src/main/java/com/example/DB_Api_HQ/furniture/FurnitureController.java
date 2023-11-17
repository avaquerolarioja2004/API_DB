package com.example.DB_Api_HQ.furniture;



import com.example.DB_Api_HQ.furniture.dto.InPutFurnitureDTO;
import com.example.DB_Api_HQ.furniture.dto.OutPutFurnitureDTO;
import com.example.DB_Api_HQ.furniture.services.CRDFurniture;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("furniture")
@RestController
public class FurnitureController {

    private final CRDFurniture crdFurniture;

    public FurnitureController(CRDFurniture crdFurniture) {
        this.crdFurniture = crdFurniture;
    }

    @GetMapping("/findFurniture:{id}")
    public ResponseEntity<OutPutFurnitureDTO> findFurnitureById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(crdFurniture.findFurniture(id));
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
    public ResponseEntity<List<OutPutFurnitureDTO>> findAllFurniture() {
        try{
            return ResponseEntity.ok(crdFurniture.findAllFurniture());
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

    @PostMapping("/postFurniture")
    public ResponseEntity<OutPutFurnitureDTO> createFurniture(@RequestBody InPutFurnitureDTO in) {
        try{
            return ResponseEntity.ok(crdFurniture.createFurniture(in));
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

    @DeleteMapping("/deleteFurniture:{id}")
    public ResponseEntity<OutPutFurnitureDTO> deleteFurniture(@PathVariable int id) {
        try{
            return ResponseEntity.ok(crdFurniture.deleteFurniture(id));
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
