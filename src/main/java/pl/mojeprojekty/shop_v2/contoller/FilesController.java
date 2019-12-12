package pl.mojeprojekty.shop_v2.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.mojeprojekty.shop_v2.services.FilesService;

import java.util.List;

//@Controller
@RequiredArgsConstructor
public class FilesController {

    private final FilesService filesService;

    @GetMapping
    public List<Resource> productFoto(){
       return filesService.findAllFotos();
    }

    @PostMapping("/foto")
    public String uploadProductFoto(@RequestParam("foto")MultipartFile file, String productName){
        filesService.save(productName, file);
        return "redirect:/products-settings";
    }
}
