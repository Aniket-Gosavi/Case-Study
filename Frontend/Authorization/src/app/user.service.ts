import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TrainDetails } from './model/trainDetails';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  get(id:any){
    return this.http.delete("http://localhost:8002/cancelbooking/"+id);
  }

  searchTrain(source:any,destination:any){
    return this.http.get<TrainDetails[]>("http://localhost:8002/findbysourceanddestination/"+source+"/"+destination);
  }
}
