import { TestBed } from '@angular/core/testing';

import { UserService } from './user.service';
import { SignupComponent } from '../signup/signup.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HttpClientModule } from '@angular/common/http';
import { of } from 'rxjs';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

fdescribe('UserService', () => {
  let service: UserService;
  let signupcomp : SignupComponent;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations:[SignupComponent],
      imports:[
        HttpClientModule,
        HttpClientTestingModule,
        MatSnackBarModule,
      ],
      providers:[UserService, SignupComponent]
    });
    service = TestBed.inject(UserService);
    signupcomp = TestBed.inject(SignupComponent);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call post() from service',()=>{
    let response: UserService|any;
    let spy = spyOn(service,'addUser').and.returnValue(of(response));
    expect(signupcomp.formSubmit()).toEqual(response);
  })
});
