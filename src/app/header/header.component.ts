import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { TokenStorageService } from "../services/token-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoggedIn: any;

  constructor(private router:Router,private tokenStorageService: TokenStorageService,
    private loginservice:LoginService,) { }

  ngOnInit(): void {
   
    if(localStorage.getItem("isLoggedIn")){
      this.isLoggedIn =localStorage.getItem("isLoggedIn");
      alert(this.isLoggedIn);
    }else{
      this.isLoggedIn=false; 
      this.isLoggedIn=false; 
    }
   
  }
  
  logout(): void {
    this.tokenStorageService.signOut();
    localStorage.clear();
    window.location.reload();
   
    
  }
}
