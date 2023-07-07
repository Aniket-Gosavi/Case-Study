import { Component, OnInit } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { TrainDetails } from '../model/trainDetails';
import { UserService } from '../user.service';

@Component({
  selector: 'app-searchtrain',
  templateUrl: './searchtrain.component.html',
  styleUrls: ['./searchtrain.component.css']
})
export class SearchtrainComponent {

  td: TrainDetails[];

  sourceName:any;
  destinationName:any;
  constructor(private user:UserService) { }

  ngOnInit() {
    // Initialization code here if needed
  }

  searchData() {
    this.user.searchTrain(this.sourceName,this.destinationName).subscribe(data => {
      this.td = data;
      console.log(data);
    });
  }
}
