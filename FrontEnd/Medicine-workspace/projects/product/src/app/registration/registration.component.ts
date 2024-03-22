import { Component } from '@angular/core';
import { User } from '../UserModel/user.model';
import { DataSource } from '../Service/datastore';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})

export class RegistrationComponent {
  user:User = new User(0,"",0,"","","","","");
  public errorType:String='';
  loginSuccessful: boolean = false;
  dialogue:boolean=false;
  isLoading:boolean=false;

  constructor(private dataSource:DataSource,
    private cookieService: CookieService, 
    private http: HttpClient,
    private router:Router,
    private location: Location) {
  }

  sendUserDetails(user:User){
    user.userRole="User";
    user.userStatus="Active";   
    this.isLoading=true;
    this.dataSource.saveUser(user).subscribe(
      (data)=>{
       this.isLoading=false;
       this.loginSuccessful=true;
       this.dialogue = true;
       setTimeout(()=>{
        this.router.navigate(['/login'])
       },2000);
       ;
     },
    (error)=>{
      this.isLoading=false;
      this.loginSuccessful=false;
      this.dialogue = true;
      console.log(error.error.message);
      
      this.errorType='User Registration Failed '+''+error.error.message;
      setTimeout(()=>{
        window.location.reload();
       },2000);
    }
   )
  }
}