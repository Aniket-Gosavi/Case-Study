import { Component } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { TrainDetails } from '../model/trainDetails';

@Component({
  selector: 'app-searchtrain',
  templateUrl: './searchtrain.component.html',
  styleUrls: ['./searchtrain.component.css']
})
export class SearchtrainComponent {

  td:TrainDetails[];

  Source:any;
  Destination:any;

  constructor(private admin: AdminserviceService){}

  onInit(){
    
  }    

  searchData(){
    this.admin.get().subscribe(data=>{
      this.td=data;
      console.log(data);
    });
  }
}
