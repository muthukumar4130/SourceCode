 import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Register } from '../model/register';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {
    
  baseUrl:String="http://localhost:8080/"
  id: any;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  constructor(private http:HttpClient) { }

  getRegisterList(): Observable<any> {
   let url=this.baseUrl+"viewregister";  
   return this.http.get(url);
  }

  registerSaveDetails(register:Register){
  let url=this.baseUrl+"register";  
  return this.http.post<Register>(url,register,this.httpOptions);
  }

  editRegistration(id:number ) {
    let url=this.baseUrl+"editregister"+"/"+id;
   return this.http.get(url,this.httpOptions);
  }

   deleteRegistration(id: number) {
    alert("Click Ok to Delete This Data");
    let url=this.baseUrl+"deleteregister"+"/"+id;  
   return this.http.get(url,this.httpOptions); 
   }

   updateRegistration(register: Register) {
     let url=this.baseUrl+"updateregister";  
  return this.http.post<Register>(url,register,this.httpOptions);
  }
 
}
