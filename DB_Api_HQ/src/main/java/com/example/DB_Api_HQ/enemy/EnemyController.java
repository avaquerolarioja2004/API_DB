package com.example.DB_Api_HQ.enemy;


import com.example.DB_Api_HQ.enemy.dto.InPutEnemyDTO;
import com.example.DB_Api_HQ.enemy.dto.OutPutEnemyDTO;
import com.example.DB_Api_HQ.enemy.services.CRDEnemy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("enemy")
@RestController
public class EnemyController {

    private final CRDEnemy crdEnemy;

    public EnemyController(CRDEnemy crdEnemy) {
        this.crdEnemy = crdEnemy;
    }

    @GetMapping("/findEnemy:{id}")
    public ResponseEntity<OutPutEnemyDTO> findEnemyById(@PathVariable int id) throws Exception {
        try {
            return ResponseEntity.ok(crdEnemy.findEnemy(id));
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
    public ResponseEntity<List<OutPutEnemyDTO>> findAllEnemy(){
        try {
            return ResponseEntity.ok(crdEnemy.findAllEnemy());
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

    @PostMapping("/postEnemy")
    public ResponseEntity<OutPutEnemyDTO> createEnemy(@RequestBody InPutEnemyDTO in){
        try{
            return ResponseEntity.ok(crdEnemy.createEnemy(in));
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

    @DeleteMapping("/deleteEnemy:{id}")
    public ResponseEntity<OutPutEnemyDTO> deleteEnemy(@PathVariable int id){
        try{
            return ResponseEntity.ok(crdEnemy.deleteEnemy(id));
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
