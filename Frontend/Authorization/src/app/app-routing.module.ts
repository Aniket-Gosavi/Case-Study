import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginComponent } from './login/login.component';

import { RegisterComponent } from './register/register.component';
import { AddtrainsComponent } from './addtrains/addtrains.component';
import { ShowTrainsComponent } from './show-trains/show-trains.component';
import { CancelticketComponent } from './cancelticket/cancelticket.component';
import { DeletetrainComponent } from './deletetrain/deletetrain.component';
import { SearchtrainComponent } from './searchtrain/searchtrain.component';
import { UpdatetrainComponent } from './updatetrain/updatetrain.component';
import { BookTrainComponent } from './book-train/book-train.component';


const routes: Routes = [
  {path:"signup",component:RegisterComponent},
  {path:"login",component:LoginComponent},
  {path:"addTrains",component:AddtrainsComponent},
  {path:"",component:FrontPageComponent},
  {path:"show",component:ShowTrainsComponent},
  {path:"cancel",component:CancelticketComponent},
  {path:"delete",component:DeletetrainComponent},
  {path:"search",component:SearchtrainComponent},
  {path:"update",component:UpdatetrainComponent},
  {path:"book",component:BookTrainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
