import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Register } from '../model/register'; 
import { RegisterComponent } from '../register/register.component';
import { RegisterService } from '../services/register.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-viewregister',
  templateUrl: './viewregister.component.html',
  styleUrls: ['./viewregister.component.css']
})
export class ViewregisterComponent implements OnInit {

  registerlist :Observable<Register[]>;
  registers:any;
  roles: any;
  
  constructor(private router:Router,private formBuilder:FormBuilder,private registerService:RegisterService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.registerService.getRegisterList().subscribe(data =>{
      this.registerlist=data;
      this.roles = this.tokenStorage.getUser().roles;
      
  })
} 
editRegistration(id:number){ 
    alert(id);
    this.router.navigate(['updateregister', id]);
  }

  deleteRegistration(id:number){ 
    this.registerService.deleteRegistration(id).subscribe(data =>{
      this.ngOnInit();
    })
  }

}
