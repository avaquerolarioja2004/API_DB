package com.example.DB_Api_HQ.furniture.services;

import com.example.DB_Api_HQ.furniture.DB.DBFurniture;
import com.example.DB_Api_HQ.furniture.Furniture;
import com.example.DB_Api_HQ.furniture.dto.InPutFurnitureDTO;
import com.example.DB_Api_HQ.furniture.dto.OutPutFurnitureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CRDFurniture implements ICRDFurniture {

    @Autowired
    private DBFurniture repository;

    @Override
    public OutPutFurnitureDTO createFurniture(InPutFurnitureDTO inFurniture) throws Exception {
        Optional<Furniture> furnitureOptional = repository.findByNameAndXAndYAndCoversVisibility(inFurniture.getName(), inFurniture.getX(), inFurniture.getY(), inFurniture.isCoversVisibility());
        if (inFurniture.getName().isEmpty() || (inFurniture.getImageInfo() == null) || (inFurniture.getX() < 0) || (inFurniture.getY() < 0)) {
            System.err.println("Error: furniture's data is not correct");
            throw new IllegalArgumentException("Error: furniture's data is not correct");
        }
        if (furnitureOptional.isPresent()) {
            System.out.println("Error: The object with name '" + inFurniture.getName() + "' and x '" +
                    inFurniture.getX() + "' and y '" + inFurniture.getY() +"' and visibility '"+ inFurniture.isCoversVisibility() +"' already exists");
            throw new IllegalStateException("Error: The object with name '" + inFurniture.getName() + "' and x '" +
                    inFurniture.getX() + "' and y '" + inFurniture.getY() +"' and visibility '"+ inFurniture.isCoversVisibility() +"' already exists");
        }
        try {
            repository.save(furnitureOptional.get());
            return furnitureOptional.get().furnitureToOutPut();
        } catch (Exception e) {
            System.err.println("Error: furniture not created");
            throw new Exception();
        }
    }

    @Override
    public OutPutFurnitureDTO findFurniture(int id) throws Exception {
        Optional<Furniture> furniture = repository.findById(id);
        if (furniture.isPresent()) {
            return furniture.get().furnitureToOutPut();
        }
        System.err.println("Error: furniture not found");
        throw new NoSuchElementException("Error: furniture not found");
    }

    @Override
    public OutPutFurnitureDTO deleteFurniture(int id) throws Exception {
        Optional<Furniture> furniture = repository.findById(id);
        if (furniture.isPresent()) {
            try{
                repository.delete(furniture.get());
                return furniture.get().furnitureToOutPut();
            }catch (Exception e){
                System.err.println("Error: furniture not deleted");
                throw new Exception("Error: furniture not deleted");
            }
        }
        System.err.println("Error: furniture not found");
        throw new NoSuchElementException();
    }

    @Override
    public List<OutPutFurnitureDTO> findAllFurniture() throws Exception {
        List<Furniture> furnitureList = repository.findAll();
        List<OutPutFurnitureDTO> outPutFurnitureDTOList = new ArrayList<>();
        for (Furniture furniture : furnitureList) {
            outPutFurnitureDTOList.add(furniture.furnitureToOutPut());
        }
        if (outPutFurnitureDTOList.isEmpty()) {
            System.err.println("Error: There are no furnitures in the database");
            throw new NoSuchElementException("Error: There are no furnitures in the database");
        }
        return outPutFurnitureDTOList;

    }
}
