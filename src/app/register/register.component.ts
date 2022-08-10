import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Register } from '../model/register';
import { RegisterService } from '../services/register.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  register:Register=new Register();
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  constructor(private router:Router,private formBuilder:FormBuilder,private registerService:RegisterService) { }

  ngOnInit(): void {
 
    
  }
  
   registerDetails=new FormGroup({
    name:new FormControl('',Validators.required),
    email:new FormControl('',Validators.required),
    password:new FormControl('',Validators.required),
    mobile:new FormControl('',Validators.required)
  })
  

  registerSave(registerDetails){  
    alert(this.register.name);

    

    this.register.name=this.Name.value;
    this.register.email=this.Email.value;
    this.register.password=this.Password.value;
    this.register.mobile=this.Mobile.value;
    this.save();
  }
  save(){
    this.registerService.registerSaveDetails(this.register).subscribe(
      
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        if(this.isSuccessful){
          this.router.navigate(['login']);
        }
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
        if(this.isSignUpFailed){
          this.router.navigate(['register'])
        }
      },
      
      
    );
  }
  get Name(){
    return this.registerDetails.get('name');
  }
  get Email(){
    return this.registerDetails.get('email');
  }
  get Password(){
    return this.registerDetails.get('password');
  }
  get Mobile(){
    return this.registerDetails.get('mobile');
  }

}
