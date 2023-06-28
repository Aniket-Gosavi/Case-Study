import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddNewProductComponent } from './add-new-product/add-new-product.component';
import { AddToCartComponent } from './add-to-cart/add-to-cart.component';
import { AddressDetailsComponent } from './address-details/address-details.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProductComponent } from './product/product.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';

const routes: Routes = [
  {path:"signup",component:RegisterComponent},
  {path:"login",component:LoginComponent},
  {path:"products",component:ProductComponent},
  {path:"add-product",component:AddNewProductComponent},
  {path:'update-product/:id',component:UpdateProductComponent},
  {path:"profile",component:ProfileComponent},
  {path:"cart",component:AddToCartComponent},
  {path:"add-address",component:AddressDetailsComponent},
  {path:"front-page",component:FrontPageComponent},
  {path:"update-profile",component:UpdateProfileComponent},
  {path:'',component:FrontPageComponent},
  {path:"home",component:HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
