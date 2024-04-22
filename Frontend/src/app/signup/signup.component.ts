import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  constructor(private userService: UserService, private snack: MatSnackBar, private router: Router) { }

  public user = {
    fullname: '',
    username: '',
    password: '',
    question:'',
    email: '',
    phoneno: '',
  };

  ngOnInit(){}

  formSubmit() {
    this.userService.addUser(this.user).subscribe((data) => {
      console.log(data);
      Swal
        .fire('Success', 'User Registered Successfully !!', 'success')
        .then(() => {
          this.router.navigateByUrl("/login");
        })

    }, (error) => {
      console.log(error);
      Swal.fire('Username must be unique', 'User not registered', 'error');
    })

  }
}
