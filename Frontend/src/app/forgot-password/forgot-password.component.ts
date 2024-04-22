import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  constructor(private loginService: LoginService, private snack: MatSnackBar, private router: Router) {}
  public userData = {
    username: '',
    email: '',
    question:'',
    newPassword:'',
  };
  
  confirmPassword!: string;

  passwordsMatch() {
    this.confirmPassword = this.userData.newPassword;
  }

  ngOnInit(): void { }

  formSubmit(){
    if (this.userData.username.trim()== '' || this.userData.username == null) {
      this.snack.open('Username is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      })
      return;
    }

    if (this.userData.newPassword.trim()== '' || this.userData.newPassword == null) {
      this.snack.open('Password is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      })
      return;
    }

    if (this.confirmPassword.trim()== '' || this.confirmPassword == null) {
      this.snack.open('Confirm Password is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      })
      return;
    }
    if(this.userData.newPassword != this.confirmPassword){
      this.snack.open('Password and confirm Password should be same!!', '', {
        duration: 3000,
        verticalPosition: 'top'
    })
    return;
  }

    if (this.userData.email.trim()== '' || this.userData.email == null) {
      this.snack.open('Email is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      })
      return;
    }

    if (this.userData.question.trim()== '' || this.userData.question == null) {
      this.snack.open('Security Answer is required !!', '', {
        duration: 3000,
        verticalPosition: 'top'
      })
      return;
    }

    this.loginService.changePassword(this.userData).subscribe((data)=>{
      console.log(data);
      Swal
        .fire('Success', 'Password changed Successfully !!', 'success')
        .then(() => {
          this.router.navigateByUrl("/login");
        })

    }, (error) => {
      console.log(error);
      Swal.fire('Oops..!!', 'Password change Unsuccessfull !!', 'error');
    })

  }
}
