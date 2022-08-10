import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Login } from '../model/login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
 
 

baseUrl:String="http://localhost:8080/"

 httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

constructor(private http:HttpClient) { }

checkLogineDetails(login: Login) {
let url=this.baseUrl+"login";
return this.http.post<Login>(url,login,this.httpOptions);
}

logout() {
  let url=this.baseUrl+"logout";
  return this.http.get(url);
}



}
