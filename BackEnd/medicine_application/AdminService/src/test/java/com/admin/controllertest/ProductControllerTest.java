package com.admin.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.admin.bean.CompositionBean;
import com.admin.bean.ProductBean;
import com.admin.controller.ProductController;
import com.admin.exception.ProductNotFoundException;
import com.admin.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductBean product;

    @BeforeEach
    public void setUp() {
        product = new ProductBean();
        product.setProductId(1);
        product.setName("Test Product");
    }

    @Test
    public void testSave() {
        when(productService.insertProduct(any(ProductBean.class))).thenReturn(product);

        ResponseEntity<ProductBean> response = productController.insertProduct(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetById() {
        when(productService.getProductById(1)).thenReturn(product);

        ResponseEntity<ProductBean> response = productController.getProductById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetAll() {
        List<ProductBean> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getAll()).thenReturn(productList);

        ResponseEntity<List<ProductBean>> response = productController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    public void testUpdate() {
        ResponseEntity<ProductBean> response = productController.update(product, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }


    @Test
    public void testDelete() {
        ResponseEntity<String> response = productController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Deleting the product with Id 1", response.getBody());
    }

  

    @Test
    public void testSearchByCategory() {
        List<ProductBean> productList = new ArrayList<>();
        productList.add(product);
        when(productService.searchProductByCategory(Optional.of(1))).thenReturn(productList);

        ResponseEntity<List<ProductBean>> response = productController.searchByCategory(Optional.of(1));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    public void testSearchSimilarProducts() {
        String productName = "Test Product";
        List<ProductBean> productList = new ArrayList<>();
        productList.add(product);

        when(productService.searchSimilarProducts(productName)).thenReturn(productList);

        ResponseEntity<List<ProductBean>> response = productController.searchSimilarProducts(productName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    public void testSearchProductByCategoryName() {
        String categoryName = "TestCategory";
        List<ProductBean> productList = new ArrayList<>();
        productList.add(product);

        when(productService.getProductsByCategoryName(categoryName)).thenReturn(productList);

        ResponseEntity<List<ProductBean>> response = productController.searchProductByCategoryName(categoryName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    public void testGetCompositionsByProductId() {
        Integer productId = 1;
        List<CompositionBean> compositions = new ArrayList<>();
        compositions.add(new CompositionBean(1, "Composition 1"));

        when(productService.getCompositionsByProductId(productId)).thenReturn(compositions);

        ResponseEntity<List<CompositionBean>> response = productController.getCompositionsByProductId(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(compositions, response.getBody());
    }
}
