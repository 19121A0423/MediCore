import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Product } from "../Model/product.model";
import { Observable } from "rxjs";
import { CategoryEntity } from "../Model/category.model";
import { CompositionEntity } from "../Model/composition.model";

@Injectable({providedIn: 'root'})
export class DataSource{
    constructor(private http:HttpClient) { }

    private baseUrl="http://localhost:8081/adminservice/products";
    private categoryUrl="http://localhost:8081/adminservice/categories";
    private compositionUrl="http://localhost:8081/adminservice/compositions";

    insertProduct(Product:Product):Observable<Product>{
    return this.http.post<Product>(`${this.baseUrl}/insertproduct`,Product);
    }

    getProductDetails():Observable<Product[]>{
    return this.http.get<Product[]>(`${this.baseUrl}/getall`);
    }
    
    deleteProducts(productId:number){
        return this.http.delete(`${this.baseUrl}/delete/${productId}`);
        }
    
        getProductById(productId: number): Observable<Product> {
            return this.http.get<Product>(`${this.baseUrl}/getbyid/${productId}`);
          }

          getCategories(): Observable<CategoryEntity[]> {
            return this.http.get<CategoryEntity[]>(`${this.categoryUrl}/getallcategories`);
          }

          updateProduct(product: Product): Observable<any> {
            const url = `${this.baseUrl}/update/${product.productId}`;
            return this.http.put(url, product);
          }

          getComposition():Observable<CompositionEntity[]>{
            return this.http.get<CompositionEntity[]>(`${this.compositionUrl}/getallcategories`);
          }
}