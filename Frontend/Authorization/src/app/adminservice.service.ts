import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TrainDetails } from './model/trainDetails';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AdminserviceService {

  constructor(private http: HttpClient) { }

  get(){
    return this.http.get<TrainDetails[]>("http://localhost:8001/show");
  }

  addtrain(td:TrainDetails){
    return this.http.post<TrainDetails>("http://localhost:8001/add",td);
  }

  delete(id:any){
    return this.http.delete("http://localhost:8001/delete/"+id);
  }
}
