package com.example.DB_Api_HQ.enemy.services;

import com.example.DB_Api_HQ.enemy.DB.DBEnemy;
import com.example.DB_Api_HQ.enemy.Enemy;
import com.example.DB_Api_HQ.enemy.dto.InPutEnemyDTO;
import com.example.DB_Api_HQ.enemy.dto.OutPutEnemyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CRDEnemy implements ICRDEnemy {

    @Autowired
    private DBEnemy repository;

    @Override
    public OutPutEnemyDTO createEnemy(InPutEnemyDTO inEnemy) throws Exception {
        byte[] image = null;
        if (inEnemy.getName().isEmpty() || inEnemy.getDificulty() < 1 || inEnemy.getInfoImage() == null) {
            System.err.println("Error: enemy's data is not correct");
            throw new IllegalArgumentException("Error: enemy's data is not correct");
        }
        Optional<Enemy> existingEnemyOptional = repository.findByNombreAndDificulty(inEnemy.getName(), inEnemy.getDificulty());

        if (existingEnemyOptional.isPresent()) {
            System.err.println("Error: The enemy with name '" + inEnemy.getName() + "' and difficulty '" +
                    inEnemy.getDificulty() + "' already exists");
            throw new IllegalStateException("Error: The enemy with name '" + inEnemy.getName() + "' and difficulty '" +
                    inEnemy.getDificulty() + "' already exists");
        }
        Enemy e = new Enemy();
        e.setNombre(inEnemy.getName());
        e.setDificulty(inEnemy.getDificulty());
        e.setImageData(inEnemy.getInfoImage());
        try {
            repository.save(e);
        } catch (Exception ex) {
            System.err.println("Error: The enemy could not be saved");
            throw new Exception();
        }


        return e.toOutPutEnemyDTO();
    }

    @Override
    public OutPutEnemyDTO findEnemy(int id) throws Exception {
        Optional<Enemy> enemy = repository.findById(id);
        if (!enemy.isPresent()) {
            System.err.println("Error: enemy not found");
            throw new NoSuchElementException("Error: enemy not found");
        }
        return enemy.get().toOutPutEnemyDTO();
    }

    @Override
    public OutPutEnemyDTO deleteEnemy(int id) throws Exception {
        Optional<Enemy> enemy = repository.findById(id);
        if (!enemy.isPresent()) {
            System.err.println("Error: enemy not found");
            throw new NoSuchElementException("Error: enemy not found");
        }
        Enemy enemyDelete = enemy.get();
        try {
            repository.delete(enemyDelete);
        } catch (Exception e) {
            System.err.println("Error: The enemy could not be deleted");
            throw new Exception();
        }
        return enemyDelete.toOutPutEnemyDTO();
    }

    @Override
    public List<OutPutEnemyDTO> findAllEnemy() throws Exception {
        List<Enemy> enemies = repository.findAll();
        List<OutPutEnemyDTO> outPutEnemyDTOS = new ArrayList<>();
        for (Enemy enemy : enemies) {
            outPutEnemyDTOS.add(enemy.toOutPutEnemyDTO());
        }
        if (outPutEnemyDTOS.isEmpty()) {
            System.err.println("Error: There are no enemies in the database");
            throw new NoSuchElementException("Error: There are no enemy in the database");
        }
        return outPutEnemyDTOS;
    }

}
