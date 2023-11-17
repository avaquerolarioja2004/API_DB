package com.example.DB_Api_HQ.object.services;



import com.example.DB_Api_HQ.object.dto.InPutObjectDTO;
import com.example.DB_Api_HQ.object.dto.OutPutObjectDTO;

import java.util.List;

public interface ICRDObject {
    public OutPutObjectDTO createObject(InPutObjectDTO inObject) throws Exception;
    public OutPutObjectDTO findObject(int id) throws Exception;
    public OutPutObjectDTO deleteObject(int id) throws Exception;
    public List<OutPutObjectDTO> findAllObject() throws Exception;
}
