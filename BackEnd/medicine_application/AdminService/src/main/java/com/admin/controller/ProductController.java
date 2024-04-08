package com.admin.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.bean.CompositionBean;
import com.admin.bean.ProductBean;
import com.admin.exception.ProductNotFoundException;
import com.admin.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private static Logger log = LoggerFactory.getLogger(ProductController.class.getSimpleName());

    @Autowired
    private ProductService productService;

    /**
     * Inserts a new product.
     * 
     * @param product The product to be inserted.
     * @return The inserted product.
     */
    @PostMapping("/insertproduct")
    public ResponseEntity<ProductBean> insertProduct(@RequestBody ProductBean product) {

        log.info("Start Product Controller:insert()");
        log.info("Products{}", product);
        ProductBean products = productService.insertProduct(product);
        log.info("Products{}", products);
        log.info("End Product Controller:insert()");
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    /**
     * Retrieves a product by its ID.
     * 
     * @param productId The ID of the product to retrieve.
     * @return The product with the specified ID.
     */
    @GetMapping("/getbyid/{productId}")
    public ResponseEntity<ProductBean> getProductById(@PathVariable Integer productId) {
        log.info("Start::ProductController::getProductById");
        ProductBean product = productService.getProductById(productId);
        log.info("Product Details of id{}", product);
        log.info("End::ProductController::getProductById");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    /**
     * Retrieves all products.
     * 
     * @return A list of all products.
     */
    @GetMapping("/getall")
    public ResponseEntity<List<ProductBean>> getAll() {
        log.info("Start::ProductController::getAll");
        List<ProductBean> products = productService.getAll();
        log.info("Product Details {}", products);
        ResponseEntity<List<ProductBean>> entity = new ResponseEntity<List<ProductBean>>(products, HttpStatus.OK);
        log.info("End::ProductController::getAll");
        return entity;
    }

    /**
     * Updates a product.
     * 
     * @param product   The updated product information.
     * @param productId The ID of the product to be updated.
     * @return The updated product.
     */
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductBean> update(@RequestBody ProductBean product, @PathVariable Integer productId) {
        log.info("Start Product Controller:update()");
        productService.update(productId, product);
        log.info("End Product Controller:update()");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    /**
     * Deletes a product by its ID.
     * 
     * @param productId The ID of the product to delete.
     * @return A message indicating successful deletion.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> delete(@PathVariable Integer productId) {
        log.info("Start Product Controller:delete()");
        try {
            productService.delete(productId);
            return new ResponseEntity<String>("Deleting the product with Id " + productId, HttpStatus.NO_CONTENT);

        } catch (ProductNotFoundException e) {
            log.error("Handling Exception in ProductControl::delete() ");
            log.info("End Product Controller:delete()");
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Searches products by category.
     * 
     * @param categoryId The ID of the category to search for products.
     * @return A list of products belonging to the specified category.
     */
    @GetMapping("/searchbycategory/{categoryId}")
    public ResponseEntity<List<ProductBean>> searchByCategory(@PathVariable Optional<Integer> categoryId) {
        log.info(" Start::Product controller:searchByCategory::categoryId" + categoryId);
        List<ProductBean> products = productService.searchProductByCategory(categoryId);
        ResponseEntity<List<ProductBean>> entity = new ResponseEntity<List<ProductBean>>(products, HttpStatus.OK);
        log.info(" End::Product controller:searchByCategory::Product details" + products);

        return entity;

    }

    /**
     * Searches for products similar to a given product name.
     * 
     * @param productName The name of the product to search for.
     * @return A list of products similar to the given product name.
     */
    @GetMapping("/searchsimilarproducts/{productName}")
    public ResponseEntity<List<ProductBean>> searchSimilarProducts(
            @PathVariable(value = "productName") String productName) {
        log.info("Start::ProductController:searchSimilarProducts::productName" + productName);
        List<ProductBean> similarProducts = productService.searchSimilarProducts(productName);
        log.info("End::ProductController:searchSimilarProducts");
        return ResponseEntity.status(HttpStatus.OK).body(similarProducts);

    }

    /**
     * Searches products by category name.
     * 
     * @param categoryName The name of the category to search for products.
     * @return A list of products belonging to the specified category.
     */
    @GetMapping("/searchproductbycategoryname/{categoryName}")
    public ResponseEntity<List<ProductBean>> searchProductByCategoryName(
            @PathVariable(value = "categoryName") String categoryName) {
        log.info("Start::ProductController:searchProductByCategoryName::categoryName" + categoryName);
        List<ProductBean> list = null;
        list = productService.getProductsByCategoryName(categoryName);
        log.info("End::ProductController:searchProductByCategoryName");
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    /**
     * Retrieves compositions associated with a product.
     * 
     * @param productId The ID of the product to retrieve compositions for.
     * @return A list of compositions associated with the specified product.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
    @GetMapping("/getcompositionsbyproductid/{productId}")
    public ResponseEntity<List<CompositionBean>> getCompositionsByProductId(@PathVariable Integer productId) {
        log.info("Start::ProductController:searchProductByCategoryName::getCompositionsByProductId");
        try {
            List<CompositionBean> compositions = productService.getCompositionsByProductId(productId);
            return ResponseEntity.status(HttpStatus.OK).body(compositions);
        } catch (ProductNotFoundException e) {
            log.info("End::ProductController:searchProductByCategoryName::getCompositionsByProductId");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
