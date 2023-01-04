package com.training.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
    componentModel = "spring")
public interface MapStructMapper {
	/* Product objects mapping
    @Mappings({
    	@Mapping(source = "product.category.name", target = "category"),
    })
    ProductDto productToProductDto(Product product);
    
    @Mappings({
        @Mapping(source = "productDto.category", target = "category.name"),
    })
    Product ruleDtoToRule(ProductDto productDto);
    */
}
