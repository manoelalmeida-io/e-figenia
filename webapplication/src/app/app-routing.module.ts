import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AboutComponent } from '../app/about/about.component';
import { HomeComponent } from '../app/home/home.component';
import { PaymentComponent } from '../app/payment/payment.component';
import { ProductsComponent } from '../app/products/products.component';
import { PurchaseComponent } from '../app/purchase/purchase.component';
import { ShopCartComponent } from '../app/shop-cart/shop-cart.component';
import { UserProfileComponent } from '../app/user-profile/user-profile.component';
import { LoginComponent } from '../app/login/login.component' ;
import { RegisterComponent } from '../app/register/register.component' ;
import { PostsComponent } from '../app/posts/posts.component';
import { LoginGuard } from './login.guard';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'products', component: ProductsComponent},
  {path: 'payment', canActivate:[LoginGuard], component: PaymentComponent},
  {path: 'purchase', canActivate:[LoginGuard], component: PurchaseComponent},
  {path: 'cart', component: ShopCartComponent},
  {path: 'profile', canActivate:[LoginGuard], component: UserProfileComponent},
  {path: 'about', component: AboutComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'posts/:id', component: PostsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
