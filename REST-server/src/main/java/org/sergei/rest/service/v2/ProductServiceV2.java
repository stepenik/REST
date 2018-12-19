package org.sergei.rest.service.v2;

import org.sergei.rest.dto.v2.ProductDTOV2;
import org.sergei.rest.exceptions.ResourceNotFoundException;
import org.sergei.rest.model.Product;
import org.sergei.rest.repository.ProductRepository;
import org.sergei.rest.service.ProductService;
import org.sergei.rest.util.ObjectMapperUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * V2 of product service
 *
 * @author Sergei Visotsky
 * Created on 12/9/2018
 */
@Service
public class ProductServiceV2 extends ProductService {

    public ProductServiceV2(ProductRepository productRepository) {
        super(productRepository);
    }

    /**
     * Find all products
     *
     * @return list of found product DTO
     */
    public List<ProductDTOV2> findAllV2() {
        List<Product> products = productRepository.findAll();
        return ObjectMapperUtil.mapAll(products, ProductDTOV2.class);
    }

    /**
     * Find all products paginated
     *
     * @return list of found product DTO
     */
    public Page<ProductDTOV2> findAllPaginatedV2(int page, int size) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
        return ObjectMapperUtil.mapAllPages(products, ProductDTOV2.class);
    }

    /**
     * Find product by product code
     *
     * @param productCode by which it should be found
     * @return product DTO
     */
    public ProductDTOV2 findByCodeV2(String productCode) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(PRODUCT_NOT_FOUND)
                );
        return ObjectMapperUtil.map(product, ProductDTOV2.class);
    }
}
