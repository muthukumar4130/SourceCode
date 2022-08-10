import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TokenStorageService } from "../services/token-storage.service";

const TOKEN_HEADER_KEY = 'Authorization';       // for Spring Boot back-end

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService) { }
  
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authenticationRequest = request;
    const token = this.token.getToken();
      if (token != null) {
        // for Spring Boot back-end
        authenticationRequest = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
       }
    return next.handle(authenticationRequest);
       // throw new Error("Method not implemented.");
    }
}
    export const authInterceptorProviders = [                                   //(2)
        { provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true }
      ];

