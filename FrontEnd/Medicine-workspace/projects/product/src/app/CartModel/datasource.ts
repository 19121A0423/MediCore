import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Cart } from "./cart.model";

@Injectable({providedIn: 'root'})
export class DataSource {

constructor(private http:HttpClient) { }

private baseUrlAdmin="http://13.48.82.196:8201/adminservice/cartcontroller/cart";
    
saveCartDetails(cart:Cart):Observable<Cart>{
   return this.http.post<Cart>(`${this.baseUrlAdmin}/save`,cart)
 }

 getCartDetailsBasedOnId(userId:number):Observable<Cart>{
  return this.http.get<Cart>(`${this.baseUrlAdmin}/${userId}`)
}

deleteBasedOnId(cartId:number, userId:number):Observable<Cart>{
  return this.http.delete<Cart>(`${this.baseUrlAdmin}/delete/${cartId}/${userId}`)
}

updateProductDetails(cart:Cart,productId:number):Observable<Cart>{
  return this.http.put<Cart>(`${this.baseUrlAdmin}/update/${productId}`,cart);
}

}