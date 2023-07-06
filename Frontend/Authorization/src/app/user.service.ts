import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  get(id:any){
    return this.http.delete("http://localhost:8002/cancelbooking/"+id);
  }
}
