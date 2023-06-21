import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {

  credentials={
    username:'',
    password:''
  }

constructor(private loginService:LoginService){}

onSubmit(): void {
  this.loginService.login(this.credentials).subscribe(
    response => {
      // Login successful, handle the response or redirect to a different page
      console.log('Login successful:', response);
      window.location.href="/dashboard"
      const token = this.loginService.getToken();
console.log('Token:', token);
      console.log(this.loginService.getToken())
      // Redirect the user to the desired page
    },
    error => {
      // Login failed, handle the error or display an error message
      console.error('Login failed:', error);
      window.location.href="/failed"
      // Display an error message to the user or perform any other error handling
    }
  );
}




//   onSubmit(){
//     console.log("form is submitted");
//    if((this.credentials.username!='' && this.credentials.password != '')&&
//    (this.credentials.username!= null && this.credentials.password != null))
//    {
//      console.log("we have to submit form to server");
//      this.loginService.generateToken(this.credentials).subscribe(
//       response =>{
//         //success
// console.log(response);
// const token = this.loginService.getToken();
// console.log('Token:', token);

// window.location.href="/dashboard"
//       },
//       error=>{
//         //error
// console.log(error);
// window.location.href="/failed"
//       }
//      )
//   }else{
//     console.log("Fields are empty");
//   }
// }




}
