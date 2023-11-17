package com.example.DB_Api_HQ.object.services;

import com.example.DB_Api_HQ.enemy.Enemy;
import com.example.DB_Api_HQ.object.DB.DBObject;
import com.example.DB_Api_HQ.object.Object;
import com.example.DB_Api_HQ.object.dto.InPutObjectDTO;
import com.example.DB_Api_HQ.object.dto.OutPutObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CRDObject implements ICRDObject {

    @Autowired
    private DBObject repository;


    @Override
    public OutPutObjectDTO createObject(InPutObjectDTO inObject) throws Exception {
        if (inObject.getName().isEmpty() || inObject.getX() < 1 || inObject.getY() < 1 || inObject.getImageInfo()==null) {
            System.err.println("Error: object's data is not correct");
            throw new IllegalArgumentException("Error: object's data is not correct");
        }
        Optional<Object> existingObjectOptional = repository.findByNameAndXAndYAndCoversVisibility(inObject.getName(),
                inObject.getX(), inObject.getY(), inObject.isCoversVisibility());

        if (existingObjectOptional.isPresent()) {
            System.err.println("Error: The object with name '" + inObject.getName() + "' and x '" + inObject.getX() +
                    "' and y '" + inObject.getY() +"' and visibility '"+ inObject.isCoversVisibility() +"' already exists");
            throw new IllegalStateException("Error: The object with name '" + inObject.getName() + "' and x '" + inObject.getX() +
                    "' and y '" + inObject.getY() +"' and visibility '"+ inObject.isCoversVisibility() +"' already exists");
        }
        Object o = new Object();
        o.setName(inObject.getName());
        o.setX(inObject.getX());
        o.setY(inObject.getY());
        o.setImageInfo(inObject.getImageInfo());
        o.setCoversVisibility(inObject.isCoversVisibility());
        try {
            repository.save(o);
            return o.objectToOutPut();
        } catch (Exception e) {
            System.err.println("Error: The object could not be saved");
            throw new Exception();
        }
    }

    @Override
    public OutPutObjectDTO findObject(int id) throws Exception {
        Optional<Object> object = repository.findById(id);
        if (object.isEmpty()) {
            System.err.println("Error: object not found");
            throw new NoSuchElementException("Error: object not found");
        }
        return object.get().objectToOutPut();
    }

    @Override
    public OutPutObjectDTO deleteObject(int id) throws Exception {
        Optional<Object> object = repository.findById(id);
        if (object.isEmpty()) {
            System.err.println("Error: object not found");
            throw new NoSuchElementException("Error: object not found");
        }
        try {
            repository.delete(object.get());
        } catch (Exception e) {
            System.err.println("Error: The object could not be deleted");
            throw new Exception();
        }
        return object.get().objectToOutPut();
    }

    @Override
    public List<OutPutObjectDTO> findAllObject() throws Exception {
        List<Object> objects = repository.findAll();
        List<OutPutObjectDTO> outPutObjectDTOS = new ArrayList<>();
        for (Object object : objects) {
            outPutObjectDTOS.add(object.objectToOutPut());
        }
        if (outPutObjectDTOS.isEmpty()) {
            System.err.println("Error: There are no objects in the database");
            throw new NoSuchElementException("Error: There are no objects in the database");
        }
        return outPutObjectDTOS;
    }
}
