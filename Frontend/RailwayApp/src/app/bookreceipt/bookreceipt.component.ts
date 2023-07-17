import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AdminserviceService } from '../adminservice.service';
import { Booking } from '../model/booking';

@Component({
  selector: 'app-bookreceipt',
  templateUrl: './bookreceipt.component.html',
  styleUrls: ['./bookreceipt.component.css']
})
export class BookreceiptComponent {

  bookingDetails:any; 
  id:any;

  constructor(private user:UserService,private router:Router,private admin: AdminserviceService){}

  ngOnInit(): void {
    this.bookingDetails = new Booking;
    this.id = this.admin.returnID();
    console.log(this.id);
    this.user.findBooking(this.id).subscribe(data=>{
      this.bookingDetails=data;
      console.log(data);
    })
  }

  printReceipt() {
    window.print();
  }

}
