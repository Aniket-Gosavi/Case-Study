import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

private url = "http://localhost:9999"


  private token!: string;


  constructor(private http:HttpClient) { }

  //calling the server to generate token

generateToken(credentials:any){
  //token generate
  return this.http.post(`${this.url}/api/auth/signin`,credentials);
}



login(credentials: any): Observable<any> {
  return this.http.post<any>(`${this.url}/api/auth/signin`, credentials).pipe(
    tap((response: any) => {
      this.token = response.token;
      localStorage.setItem('token', this.token);
    })
  );
}



//to check user is logged in
isLoggedIn(){

  let token= localStorage.getItem("token");
  if(token==undefined || token==='' || token==null){
    return false;
  }else{
    return true;
  }
}


//for logout
logout(){
  localStorage.removeItem("token");
  return true;
}

//To get Token
getToken()
{
  return localStorage.getItem("token");
}


}



// import { HttpEvent,HttpHandler,HttpInterceptor,HttpRequest } from "@angular/common/http";
// import { Injectable } from "@angular/core";
// import { Observable } from "rxjs";
// import { LoginComponent } from "../components/login/login.component";
// import { LoginService } from "./login.service";


// @Injectable()
// export class AuthInterceptor implements HttpInterceptor{

// constructor(private loginService:LoginService)
// {}


//     intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//         throw new Error("Method not implemented.");

// let newReq = req
// let token = this.loginService.getToken()

// console.log("INTERCEPTOR",token);

// if(token!= null){
//     newReq = newReq.clone({setHeaders:{Authorization: `Bearer ${token}`}})
// }

// return next.handle(newReq)


//     }

// }