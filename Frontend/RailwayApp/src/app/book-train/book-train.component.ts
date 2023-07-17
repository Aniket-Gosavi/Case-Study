import { Component } from '@angular/core';
import { Booking } from '../model/booking';
import { UserService } from '../user.service';
import { Route, Router } from '@angular/router';
import { TrainDetails } from '../model/trainDetails';
import { AdminserviceService } from '../adminservice.service';
import { LoginErrorDialogComponent } from '../login-error-dialog/login-error-dialog.component';
import { MatDialog } from '@angular/material/dialog';
//import * as Razorpay from 'razorpay';
declare var Razorpay:any;
@Component({
  selector: 'app-book-train',
  templateUrl: './book-train.component.html',
  styleUrls: ['./book-train.component.css']
})
export class BookTrainComponent {

  successmsg:any;
  num:any;
  bk: any = new Booking;
  boo:Booking;
  td:TrainDetails[];
  
  constructor(private user:UserService,private router:Router,private admin: AdminserviceService,private dialog: MatDialog){}

  book(){
    this.user.bookTrain(this.bk).subscribe(data=>{
      this.boo=this.user.currentBooking();
      console.log(this.boo);
      this.createTransaction();
  });
  }
  

  createTransaction(){
    if(this.bk.trainNo === 9713){
      this.num = 400 * this.bk.numberOfTravellers;
    }else if(this.bk.trainNo === 9710){
      this.num = 300 * this.bk.numberOfTravellers;
    }else if(this.bk.trainNo === 9012){
      this.num = 180 * this.bk.numberOfTravellers;
    }else if(this.bk.trainNo === 9172){
      this.num = 1200 * this.bk.numberOfTravellers;
    }else if(this.bk.trainNo === 7897){
      this.num = 1200 * this.bk.numberOfTravellers;
    }else{
      this.num = 350 * this.bk.numberOfTravellers;
    }
    var response = this.user.createTransaction(this.num).subscribe(
      (response) =>{
        console.log(response);
        this.openTransactionModel(response);
      },
      (error) =>{
        console.log(error);
      }
    )
  }

  openTransactionModel(response:any){
    var options = {
      order_id:response.orderId,
      key:response.key,
      amount:response.amount,
      currency:response.currency,
      name: 'Aniket',
      description: "Payment Train Booking",
      image: 'https://cdn.pixabay.com/photo/2023/06/18/04/57/crimson-collared-tanager-8071235_640.jpg',
      handler:(response :any)=>{
        this.processResponse(response);
      },
      prefill :{
        name:'Aniket',
        email:'aniketgosavi99@gmail.com',
        contact:'8380994363',
      },
      notes:{
        address:'Online train Booking'
      },
      theme:{
        color:"#F37254"
      }
    };

    var razorpay = new Razorpay(options);
    razorpay.open();
  }
  processResponse(resp:any){
    console.log(resp);
  }
  msg(){
    this.successmsg = 'Booking Done For '+this.bk.firstName+
    ' Please Procide to Pay the amount '+this.num;

    // Open the dialog with the error message
    this.dialog.open(LoginErrorDialogComponent, {
      data: this.successmsg,
    });
  }
}
