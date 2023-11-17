package com.example.DB_Api_HQ.object;



import com.example.DB_Api_HQ.object.dto.InPutObjectDTO;
import com.example.DB_Api_HQ.object.dto.OutPutObjectDTO;
import com.example.DB_Api_HQ.object.services.CRDObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("object")
@RestController
public class ObjectController {

    private final CRDObject crdObject;

    public ObjectController(CRDObject crdObject) {
        this.crdObject = crdObject;
    }

    @GetMapping("/findObject:{id}")
    public ResponseEntity<OutPutObjectDTO> findObjectById(@PathVariable int id){
        try {
            return ResponseEntity.ok(crdObject.findObject(id));
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
    public ResponseEntity<List<OutPutObjectDTO>> findAllFurniture() {
        try {
            return ResponseEntity.ok(crdObject.findAllObject());
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

    @PostMapping("/postObject")
    public ResponseEntity<OutPutObjectDTO> createObject(@RequestBody InPutObjectDTO in){
        try{
            return ResponseEntity.ok(crdObject.createObject(in));
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

    @DeleteMapping("/deleteObject:{id}")
    public ResponseEntity<OutPutObjectDTO> deleteObject(@PathVariable int id){
        try{
            return ResponseEntity.ok(crdObject.deleteObject(id));
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
