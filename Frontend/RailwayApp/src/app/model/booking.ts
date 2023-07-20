import { Passenger } from "./Passenger";

export class Booking {
     id: number;
     firstName: string;
     lastName: string;
     email: string;
     trainNo: number;
     numberOfTravellers: number;
     passengers: Passenger[];
  }
  