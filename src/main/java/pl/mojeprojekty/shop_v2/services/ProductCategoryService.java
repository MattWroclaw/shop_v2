package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.repositories.ProductCategoryRepsiotory;
import pl.mojeprojekty.shop_v2.utils.DtoToObjectConverters;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepsiotory productCategoryRepsiotory;
    private final DtoToObjectConverters dtoToObjectConverters;

    public List<ProductCategoryDto> showAllProductCategories(){
        List<ProductCategory> entities =  productCategoryRepsiotory.findAll();
        List<ProductCategoryDto> dtos = new ArrayList<>();
        for (ProductCategory entity : entities) {
             ProductCategoryDto dto = new ProductCategoryDto();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            if(entity.getParent() != null){
                dto.setParentId(entity.getParent().getId());
            }else{
                dto.setParentId(1l);
            }
            dtos.add(dto);
        }
        return dtos;
    }

    public ProductCategoryDto findProductCategoryById(long id){
        ProductCategory category = productCategoryRepsiotory.findById(id)
                .orElseThrow(()-> new NoSuchElementException(
                        "No such product category with id =" +id
                ));
        ProductCategoryDto categoryDto = dtoToObjectConverters.categoryEntityToDto(category);
        return categoryDto;
    }

    public void createProductCategory(ProductCategoryDto dto){
        ProductCategory categoryEntity = dtoToObjectConverters
                .productCategoryDtoToEntity(dto);
        productCategoryRepsiotory.save(categoryEntity);
    }

    public void editProductCategory(ProductCategoryDto dto){
        ProductCategory categoryEntity = productCategoryRepsiotory.findById(dto.getId()).get();
        categoryEntity.setDescription(dto.getDescription());
        long idParentDto = dto.getParentId();
        ProductCategory paretnt = productCategoryRepsiotory.findById(idParentDto)
                .orElseThrow(()->new NoSuchElementException("No parent category. This is root category"));
        categoryEntity.setParent(paretnt);
        productCategoryRepsiotory.save(categoryEntity);
    }

    public void deleteProductCategory(long id){
        ProductCategory categoryToDelete = productCategoryRepsiotory.findById(id)
                .orElseThrow(()->new NoSuchElementException("There is no such product.."));
        productCategoryRepsiotory.delete(categoryToDelete);
    }

}