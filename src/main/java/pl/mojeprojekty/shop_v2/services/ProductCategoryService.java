package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.repositories.ProductCategoryRepository;
import pl.mojeprojekty.shop_v2.utils.DtoToObjectConverters;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final DtoToObjectConverters dtoToObjectConverters;

    public List<ProductCategoryDto> showAllProductCategories(){
        List<ProductCategory> entities =  productCategoryRepository.findAll();
        List<ProductCategoryDto> dtos = new ArrayList<>();
        for (ProductCategory entity : entities) {
             ProductCategoryDto dto = new ProductCategoryDto();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            if(entity.getParent() != null){
                dto.setParentId(entity.getParent().getId());
            }else{
                dto.setParentId(1L);
            }
            dtos.add(dto);
        }
        return dtos;
    }

    public ProductCategoryDto findProductCategoryById(long id){
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException(
                        "No such product category with id =" +id
                ));
        ProductCategoryDto categoryDto = dtoToObjectConverters.categoryEntityToDto(category);
        return categoryDto;
    }

    public ProductCategoryDto createProductCategory(ProductCategoryDto dto){
        ProductCategory categoryEntity = dtoToObjectConverters
                .productCategoryDtoToEntity(dto);
        productCategoryRepository.save(categoryEntity);
        return dto;
    }

    public ProductCategoryDto editProductCategory(ProductCategoryDto dto){
        ProductCategory categoryEntity = productCategoryRepository.findById(dto.getId())
                .orElseThrow(()->new NoSuchElementException("There is no such category with id= "+dto.getId()));
        categoryEntity.setDescription(dto.getDescription());

        if(dto.getParentId() != null) {
            long idParentDto = dto.getParentId();

            ProductCategory paretnt = productCategoryRepository.findById(idParentDto)
                    .orElseThrow(() -> new NoSuchElementException("No parent category. This is root category"));
            categoryEntity.setParent(paretnt);
        }

        productCategoryRepository.save(categoryEntity);
        return dtoToObjectConverters.categoryEntityToDto(categoryEntity);
    }

    public void deleteProductCategory(long id){
        ProductCategory categoryToDelete = productCategoryRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("There is no such product.."));
        productCategoryRepository.delete(categoryToDelete);
    }

}
