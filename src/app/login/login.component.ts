import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from '../model/login';
import { LoginService } from '../services/login.service';
import { RegisterService } from '../services/register.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login:Login=new Login();

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  constructor(private router:Router,private formBuilder:FormBuilder,private loginservice:LoginService, 
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }
  loginDetails=new FormGroup({
    email:new FormControl(),
    password:new FormControl()
  })

  checklogin(loginDetails){
    this.login.email=this.Email.value;
    this.login.password=this.Password.value;
    this.check();
  }

check(){
  alert(this.login.email)
  this.loginservice.checkLogineDetails(this.login).subscribe(
    
    data => {
      this.tokenStorage.saveToken(data.accessToken);
      this.tokenStorage.saveUser(data);
 
      this.isLoginFailed = false;
      this.isLoggedIn = true; 
      localStorage.setItem('isLoggedIn',JSON.stringify(this.isLoggedIn));
      this.roles = this.tokenStorage.getUser().roles;
    
      // this.reloadPage();
      if(this.isLoggedIn){
        this.router.navigate(['home']);
      }
      else{
        this.router.navigate(['login'])
      }
    },
    err => {
      this.errorMessage = err.error.message;
      this.isLoginFailed = true;
    }
    
  );
}
get Email(){
  return this.loginDetails.get('email');
}
get Password(){
  return this.loginDetails.get('password');
}
}