import { Component, OnInit } from '@angular/core';
import { AdminserviceService } from '../adminservice.service';
import { TrainDetails } from '../model/trainDetails';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-searchtrain',
  templateUrl: './searchtrain.component.html',
  styleUrls: ['./searchtrain.component.css']
})
export class SearchtrainComponent {

  td: TrainDetails[];

  sourceName:any;
  destinationName:any;
  constructor(private user:UserService,
    private router:Router,
    private admin:AdminserviceService) { }

  ngOnInit() {
    // Initialization code here if needed
    this.showData();
  }

  showData(){
    this.admin.get().subscribe(data=>{
      this.td=data;
      console.log(data);
    });
  }

  bookTrain(trainno:any){
    this.user.saveNo(trainno);
    this.router.navigate(['book']);
  }

  searchData() {
    this.user.searchTrain(this.sourceName,this.destinationName).subscribe(data => {
      this.td = data;
      console.log(data);
    });
  }
}
