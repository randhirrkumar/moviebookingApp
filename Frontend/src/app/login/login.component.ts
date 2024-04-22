import { Component, ViewChild } from '@angular/core';
import { LoginService } from '../services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public loginData = {
    username: '',
    password: '',
  }

  constructor(private login: LoginService, private snack: MatSnackBar, private router:Router) {}
  @ViewChild('loginform', { static: false })
  loginform!: NgForm;

  formSubmit() {
    if (this.loginData.username.trim() == '' || this.loginData.username == null) {
      this.snack.open('Username is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      })
      return;
    }
    if (this.loginData.password.trim() == '' || this.loginData.password == null) {
      this.snack.open('Password is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      })
      return;
    }

    // request to server to generate token
    this.login.generateToken(this.loginData).subscribe((data:any)=>{
      console.log(data);

      //login
      this.login.loginUser(data.jwtToken);
      this.login.getCurrentUser().subscribe((user:any)=>{
        this.login.setUser(user);
        console.log(user);

        //redirect
        if(this.login.getUserRole()=="ADMIN_USER"){
          this.router.navigate(['admin']);
          this.login.loginStatusSubject.next(true);
        } else if(this.login.getUserRole()=="NORMAL_USER"){
          this.router.navigate(['/user-dashboard']);
          this.login.loginStatusSubject.next(true);
        } else {
          this.login.logout();

        }
      })

    },(error)=>{
      console.log("Error");
      Swal.fire('Could not login', 'Invalid Credentials!!', 'error');
    })
  }
}
