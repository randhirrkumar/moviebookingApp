import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';
import { AdminGuard } from './services/admin.guard';
import { NormalGuard } from './services/normal.guard';
import { AddmovieComponent } from './admin/addmovie/addmovie.component';
import { UpdatemovieComponent } from './admin/updatemovie/updatemovie.component';
import { BookTicketsComponent } from './user/book-tickets/book-tickets.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path:'forgot-password',
    component:ForgotPasswordComponent,
    pathMatch:'full'
  },
  {
    path: 'admin',
    canActivate:[AdminGuard],
    children: [
      {
        path: "",
        component: DashboardComponent
      },
      {
        path: "add-movie",
        component: AddmovieComponent
      },
      {
        path: "update-movie/:id",
        component: UpdatemovieComponent
      }
    ]
  },
  {
    path: 'user-dashboard',
    canActivate: [NormalGuard],
    children: [
      {
        path: "",
        component: UserDashboardComponent
      },
      {
        path: "book-tickets/:id",
        component: BookTicketsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
