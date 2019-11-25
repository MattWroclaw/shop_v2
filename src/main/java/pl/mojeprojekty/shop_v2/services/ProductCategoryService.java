package pl.mojeprojekty.shop_v2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojeprojekty.shop_v2.dto.ProductCategoryDto;
import pl.mojeprojekty.shop_v2.entity.ProductCategory;
import pl.mojeprojekty.shop_v2.repositories.ProductCategoryRepsiotory;
import pl.mojeprojekty.shop_v2.utils.DtoToObjectConverters;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepsiotory productCategoryRepsiotory;
    private final DtoToObjectConverters dtoToObjectConverters;

    public List<ProductCategory> showAllProductCategories(){
        return productCategoryRepsiotory.findAll();
    }

    public ProductCategory findProductCategoryById(long id){
        ProductCategory category = productCategoryRepsiotory.findById(id)
                .orElseThrow(()-> new NoSuchElementException(
                        "No such product category with id =" +id
                ));
        return category;
    }

    public void createProductCategory(ProductCategoryDto dto){
        ProductCategory categoryEntity = dtoToObjectConverters
                .productCategoryDtoToEntity(dto);
        productCategoryRepsiotory.save(categoryEntity);
    }

    public void editProductCategory(long id, ProductCategoryDto dto){
        ProductCategory editedProductCategory = findProductCategoryById(id);
        editedProductCategory.setDescription(dto.getDescription());
        productCategoryRepsiotory.save(editedProductCategory);
    }

}
