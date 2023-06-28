import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { fromEvent, Subject, takeUntil } from 'rxjs';
import { HomeComponent } from '../home/home.component';
import { Product } from '../model/product';
import { AdminService } from '../services/admin.service';
import { CartService } from '../services/cart.service';
import { ProductService } from '../services/product.service';
import { StorageService } from '../services/storage.service';

@Component({
  selector: 'app-add-to-cart',
  templateUrl: './add-to-cart.component.html',
  styleUrls: ['./add-to-cart.component.css'],
})
export class AddToCartComponent implements OnInit {
  products: Product[] = [];
  id: any;
  currentUser: any;
  username: any;
  cartId: any;
  productId: any;
  cartData: any = [
    {
      cartId: '',
      items: [],
      totalPrice: '',
    },
  ];

  constructor(
    private cartService: CartService,
    private storageService: StorageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
    this.username = this.currentUser.username;
    this.cartId = this.username;
    console.log(this.cartId);
    this.cartService.getCartByCartId(this.cartId).subscribe((data) => {
      console.log(data);
      this.cartData = data;
      console.log(this.cartData);
    });
  }

  onClickDelete(productId: any) {
    this.cartService
      .deleteProductInCartById(productId, this.cartId)
      .subscribe((data) => {
        console.log(data);
        alert('product deleted successfully!');
        window.location.reload();
      });
  }

  onClickDecreaseButton(productId: any) {
    this.cartService
      .decreaseQuantityOfProduct(productId, this.cartId)
      .subscribe((data) => {
        console.log(data);
        window.location.reload();
      });
  }

  onClickIncreaseQuant(productId: any) {
    this.cartService
      .increaseQuantityOfProduct(productId, this.cartId)
      .subscribe((data) => {
        console.log(data);
        window.location.reload();
      });
  }

  onClickEmptyCart() {
    this.cartService.emptyCart(this.cartId).subscribe((data) => {
      console.log(data);
      alert('Cart deleted successfully');
      window.location.reload();
    });
  }

  goToHome() {
    this.router.navigate(['/home']).then(() => {
      window.location.reload();
    });
  }

  navigateToAddress() {
    this.router.navigate(['add-address']);
  }

  onclickShopNow() {
    this.router.navigate(['/home']).then(() => {
      window.location.reload();
    });
  }
}
