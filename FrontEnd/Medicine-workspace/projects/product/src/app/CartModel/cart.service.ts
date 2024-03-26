import { Injectable } from "@angular/core";
import { Product } from "../Model/product.model";
import { DataSource } from "./datasource";
import { Cart, User } from "./cart.model";

import { Router } from "@angular/router";
import { MainServiceService } from "../main-service.service ";
import { AuthService } from "../AuthService.service (1)";


@Injectable({
    providedIn: 'root'
  })
export class CartService{

    public productList:Product[]=[];
    public cart = new Cart(0,'',new User(),0,0);
    public userBean = new User();
    public dialogueBox: boolean = false;

    constructor(
      private dataSource: DataSource,
      private mainService:MainServiceService,
      private router:Router,
      private authService:AuthService,
      ) {
        this.userBean.userId=this.authService.getUser().userId;
       }

    addToCart(products: Product[]): void {
      this.cart.status="Active";
      this.cart.products=products;   
      this.mainService.getCartQuantity(this.cart.quantity);
      this.cart.user=this.userBean;        
      this.dataSource.saveCartDetails(this.cart).subscribe(
      (result) => {
        this.cart=result;      
        this.mainService.getCartQuantity(this.cart.quantity);
      }
        
    );
  }

    sendCartDetails(): Cart { 
      return this.cart;
    }

}