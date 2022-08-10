import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Register } from '../model/register';
import { RegisterComponent } from '../register/register.component';
import { RegisterService } from '../services/register.service';

@Component({
  selector: 'app-updateregister',
  templateUrl: './updateregister.component.html',
  styleUrls: ['./updateregister.component.css']
})
export class UpdateregisterComponent implements OnInit {
  id:number;
  registers:any;
  //registers: Object;
  register:Register;

  constructor(private router:Router,private formBuilder:FormBuilder,
    private route: ActivatedRoute,private registerService:RegisterService) { }

  ngOnInit(): void {
   alert(JSON.stringify(this.route.snapshot.params));
    if( this.route.snapshot.params['id']){ 
      //alert("if");
      this.id = this.route.snapshot.params['id']; 
      //alert(this.id);
      this.registerService.editRegistration(this.id).subscribe(data =>{
        this.registers=data   })
    }
    
 
  }

  updateRegister(){
    this.registerService.updateRegistration(this.registers).subscribe(data =>{
      if(data){
        
        this.router.navigate(['viewregister']);
      }
       else{
        this.router.navigate(['viewregister'])
      }
    })
  }

}
