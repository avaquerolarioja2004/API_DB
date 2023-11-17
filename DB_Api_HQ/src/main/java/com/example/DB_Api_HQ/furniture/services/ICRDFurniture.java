package com.example.DB_Api_HQ.furniture.services;



import com.example.DB_Api_HQ.furniture.dto.InPutFurnitureDTO;
import com.example.DB_Api_HQ.furniture.dto.OutPutFurnitureDTO;

import java.util.List;

public interface ICRDFurniture {
    public OutPutFurnitureDTO createFurniture(InPutFurnitureDTO inFurniture) throws Exception;
    public OutPutFurnitureDTO findFurniture(int id) throws Exception;
    public OutPutFurnitureDTO deleteFurniture(int id) throws Exception;
    public List<OutPutFurnitureDTO> findAllFurniture() throws Exception;
}
