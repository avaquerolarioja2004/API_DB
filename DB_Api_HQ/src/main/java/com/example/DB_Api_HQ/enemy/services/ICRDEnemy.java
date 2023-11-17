package com.example.DB_Api_HQ.enemy.services;



import com.example.DB_Api_HQ.enemy.Enemy;
import com.example.DB_Api_HQ.enemy.dto.InPutEnemyDTO;
import com.example.DB_Api_HQ.enemy.dto.OutPutEnemyDTO;

import java.util.List;

public interface ICRDEnemy {
    public OutPutEnemyDTO createEnemy(InPutEnemyDTO inEnemy) throws Exception;
    public OutPutEnemyDTO findEnemy(int id) throws Exception;
    public OutPutEnemyDTO deleteEnemy(int id) throws Exception;
    public List<OutPutEnemyDTO> findAllEnemy() throws Exception;
}
