package pl.mojeprojekty.shop_v2.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FilesService {

    public Resource findProductFoto(String fotoName){
//        TODO
        return null;
    }

    public void save(String fotoName, MultipartFile file){
        //TODO
    }

    public List<Resource> findAllFotos(){

        return null;
    }
}
