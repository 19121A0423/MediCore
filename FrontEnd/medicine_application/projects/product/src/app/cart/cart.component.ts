import { Component, OnInit } from '@angular/core';
import { CartService } from '../CartModel/cart.service';

import { Router } from '@angular/router';
import { DataSource } from '../CartModel/datasource';
import { Cart, User } from '../CartModel/cart.model';
import { Product } from './../Model/product.model';
import { CookieService } from 'ngx-cookie-service';
import { MainServiceService } from '../main-service.service ';
import { AuthService } from '../AuthService.service (1)';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  public productsList: Product[] = [];
  public updateProductList: Product[] = [];
  public cart = new Cart(0,'',new User(),0,0);
  public dialogueBox:boolean=false;
  public cartId:number=0;
  public userDetails: any;
  public userId?: number;
  

  constructor(
    private router: Router,
    private cartService: CartService,
    private dataSource: DataSource,
    private mainService:MainServiceService,
    private cookieService: CookieService,
    private authService:AuthService,
    private snackBar: MatSnackBar
  ) {
    this.userId=this.authService.getUser().userId;
    this.getCartDetailsBasedOnId();
  }

  getCartDetails() {
    this.cart = this.cartService.sendCartDetails();
    return this.cart;
  }

  getCartDetailsBasedOnId() {
    if(this.userId!=undefined){
    this.dataSource.getCartDetailsBasedOnId(this.userId).subscribe(
      (data) => {
        console.log(data);
        this.cart=data;
        if(this.cart.status=="Active"){
          this.productsList=data.products;
          this.mainService.getCartQuantity(this.cart.quantity);
          this.dialogueBox = this.productsList.length > 0;
          console.log(this.productsList);
        }      
      },
      (error) => {
        this.dialogueBox=false;
      }
    );
    }
  }
  
  removeProduct(product:Product) { 
    this.dataSource.deleteBasedOnId(this.cart.cartId,product.productId).subscribe(
      (data)=>{
        this.getCartDetailsBasedOnId();    
      }
    )
  }

  private showSnackBar(message: string) {
    const config = new MatSnackBarConfig();
    config.panelClass = ['custom-snackbar'];
    config.duration = 2000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, 'Close', config);
  }

  onSelectChange(event: any, productUpdate: Product) {
    this.updateProductList.push(productUpdate);
    this.cart.products=this.updateProductList;
    if(productUpdate.quantity>=productUpdate.quantityProduct){
      this.dataSource.updateProductDetails(this.cart,productUpdate.productId).subscribe(
        (data)=>{
          this.cart.products=data.products;
          this.cart.amount=data.amount;
          this.mainService.getCartQuantity(this.cart.quantity);
          console.log(this.cart.products);
        },(error)=>{
        
        })
    }else{
      this.showSnackBar("Out Of Stock");
      this.getCartDetailsBasedOnId();
    }   
  }
}

