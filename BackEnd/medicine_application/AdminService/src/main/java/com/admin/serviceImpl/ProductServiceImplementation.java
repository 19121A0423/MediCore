package com.admin.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.bean.CompositionBean;
import com.admin.bean.ProductBean;
import com.admin.entity.Category;
import com.admin.entity.Product;
import com.admin.exception.CategoryNotFoundException;
import com.admin.exception.ProductNotFoundException;
import com.admin.repository.ProductRepository;
import com.admin.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Inserts a new product into the database.
     * 
     * @param product The product to insert.
     * @return The inserted product.
     */
    @Override
    public ProductBean insertProduct(ProductBean product) {
        Product entity = new Product();
        entity.setProductId(product.getProductId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setQuantity(product.getQuantity());
        entity.setDescription(product.getDescription());
        entity.setQuantityProduct(product.getQuantityProduct());
        entity.setImage(product.getImage());
        entity.setStatus(product.getStatus());
        Category categoryEntity = new Category();
        categoryEntity.setCategoryId(product.getCategory().getCategoryId());
        entity.setCategory(categoryEntity);
        entity.setCompositions(product.getCompositions());
        productRepository.save(entity);
        return product;
    }

    /**
     * Retrieves a product by its ID.
     * 
     * @param productId The ID of the product to retrieve.
     * @return The product with the specified ID.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
    @Override
    public ProductBean getProductById(Integer productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product productEntity = productOptional.get();
            ProductBean product = new ProductBean();
            product.setProductId(productEntity.getProductId());
            product.setName(productEntity.getName());
            product.setPrice(productEntity.getPrice());
            product.setQuantity(productEntity.getQuantity());
            product.setDescription(productEntity.getDescription());
            product.setQuantityProduct(productEntity.getQuantityProduct());
            product.setImage(productEntity.getImage());
            product.setStatus(productEntity.getStatus());
           
            Category category = new Category();
            category.setCategoryId(productEntity.getCategory().getCategoryId());
            category.setCategoryName(productEntity.getCategory().getCategoryName());

            product.setCategory(category);
            product.setCompositions(productEntity.getCompositions());
            return product;
        } else {
            throw new ProductNotFoundException("Product not found with Id- " + productId);
        }
    }

    /**
     * Retrieves all products from the database.
     * 
     * @return A list of all products.
     */
    @Override
    public List<ProductBean> getAll() {
        List<Product> productEntities = productRepository.findAll();
        List<ProductBean> products = entityToBean(productEntities);
        return products;
    }

    /**
     * Converts a list of Product entities to ProductBean objects.
     * 
     * @param productEntities The list of Product entities to convert.
     * @return A list of ProductBean objects.
     */
    public List<ProductBean> entityToBean(List<Product> productEntities) {
        List<ProductBean> products = new ArrayList<>();
        for (Product entity : productEntities) {
            ProductBean product = new ProductBean();
            product.setProductId(entity.getProductId());
            product.setName(entity.getName());
            product.setPrice(entity.getPrice());
            product.setQuantity(entity.getQuantity());
            product.setDescription(entity.getDescription());
            product.setQuantityProduct(entity.getQuantityProduct());
            product.setImage(entity.getImage());
            product.setStatus(entity.getStatus());
            Category category = new Category();
            category.setCategoryId(entity.getCategory().getCategoryId());
            category.setCategoryName(entity.getCategory().getCategoryName());
            product.setCategory(category);
            product.setCompositions(entity.getCompositions());
            products.add(product);
        }
        return products;
    }

    /**
     * Converts a ProductBean object to a Product entity.
     * 
     * @param product The ProductBean object to convert.
     * @param entity  The Product entity to update.
     */
    public void beanToEntity(ProductBean product, Product entity) {
        entity.setProductId(product.getProductId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setQuantity(product.getQuantity());
        entity.setQuantityProduct(product.getQuantityProduct());
        entity.setDescription(product.getDescription());
        entity.setImage(product.getImage());
        entity.setStatus(product.getStatus());
        Category category = new Category();
        category.setCategoryId(product.getCategory().getCategoryId());
        category.setCategoryName(product.getCategory().getCategoryName());
        product.setCategory(category);
        entity.setCategory(category);
        entity.setCompositions(product.getCompositions());
    }

    /**
     * Updates a product in the database.
     * 
     * @param productId The ID of the product to update.
     * @param product   The updated product data.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
    @Override
    public void update(Integer productId, ProductBean product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product productEntity = productOptional.get();
            beanToEntity(product, productEntity);
            productRepository.save(productEntity);
        } else {
            throw new ProductNotFoundException("Product not found with Id- " + productId);
        }
    }

    /**
     * Deletes a product from the database.
     * 
     * @param productId The ID of the product to delete.
     * @throws ProductNotFoundException If the product with the specified ID is not found.
     */
    @Override
    public void delete(Integer productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product productEntity = productOptional.get();
            productRepository.delete(productEntity);
        } else {
            throw new ProductNotFoundException("Product not found with Id- " + productId);
        }
    }

    /**
     * Searches for products by category.
     * 
     * @param categoryId The ID of the category to search products for.
     * @return A list of products belonging to the specified category.
     */
    @Override
    public List<ProductBean> searchProductByCategory(Optional<Integer> categoryId) {
        List<Product> productEntities = productRepository.findByCategory(categoryId);
        List<ProductBean> products = entityToBean(productEntities);
        return products;
    }

    /**
     * Searches for products with names similar to the specified name.
     * 
     * @param productName The name to search for.
     * @return A list of products with names similar to the specified name.
     * @throws ProductNotFoundException If no products with names similar to the specified name are found.
     */
    @Override
    public List<ProductBean> searchSimilarProducts(String productName) throws ProductNotFoundException {
        List<Product> allProductEntities = productRepository.findAll();
        List<ProductBean> allProducts = entityToBean(allProductEntities);

        // List to store similar products
        List<ProductBean> similarProducts = new ArrayList<>();

        // Iterate through all products and check if the name contains the search query
        for (ProductBean product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                // Found a similar product
                similarProducts.add(product);
            }
        }
        if (!(similarProducts.isEmpty())) {
            return similarProducts;
        } else {
            throw new ProductNotFoundException("Product Not found with name: " + productName);
        }
    }

    /**
     * Retrieves products by category name.
     * 
     * @param categoryName The name of the category to retrieve products for.
     * @return A list of products belonging to the specified category.
     * @throws CategoryNotFoundException If no category with the specified name is found.
     */
    @Override
    public List<ProductBean> getProductsByCategoryName(String categoryName) throws CategoryNotFoundException {
        List<Product> productEntities = productRepository.findByCategoryName(categoryName);
        if (!productEntities.isEmpty()) {
            return entityToBean(productEntities);
        } else {
            throw new CategoryNotFoundException("Category Not found with name: " + categoryName);
        }
    }

    /**
     * Retrieves compositions associated with a product.
     * 
     * @param productId The ID of the product.
     * @return A list of compositions associated with the specified product.
     * @throws ProductNotFoundException If no product with the specified ID is found.
     */
    @Override
    public List<CompositionBean> getCompositionsByProductId(Integer productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product productEntity = productOptional.get();
            return productEntity.getCompositions().stream()
                    .map(compositionEntity -> new CompositionBean(compositionEntity.getCompositionId(),
                            compositionEntity.getCompositionName()))
                    .collect(Collectors.toList());
        } else {
            throw new ProductNotFoundException("Product not found with Id- " + productId);
        }
    }

}
