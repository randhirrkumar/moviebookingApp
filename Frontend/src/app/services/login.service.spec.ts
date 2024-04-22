import { TestBed } from '@angular/core/testing';

import { LoginService } from './login.service';
import { LoginComponent } from '../login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { MatSnackBarModule } from '@angular/material/snack-bar';


fdescribe('LoginService', () => {
  let service: LoginService;
  let login: LoginComponent 

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations:[LoginComponent],
      imports:[
      MatSnackBarModule,
      HttpClientModule,
      HttpClientTestingModule
    ],
    providers:[LoginService, LoginComponent]
    });
    service = TestBed.inject(LoginService);
    login = TestBed.inject(LoginComponent);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call get() from service',()=>{
    const mockResponse = { id: 1, name: 'John Doe' };
    spyOn(service, 'getCurrentUser').and.returnValue(of(mockResponse));
    service.getCurrentUser().subscribe(response => {
    expect(response).toEqual(mockResponse);
  });
  expect(service.getCurrentUser).toHaveBeenCalled();
  });

  it('should call generateToken() from service',()=>{
    const mockResponse = {username: 'John Doe', password:'abcde' };
    spyOn(service, 'generateToken').and.returnValue(of(mockResponse));
    service.generateToken(mockResponse).subscribe(response => {
    expect(response).toEqual(mockResponse);
  });
  expect(service.generateToken).toHaveBeenCalled();
  });

  it('should call changePassword() from service',()=>{
    const mockResponse = 'Password changed successfully'
    spyOn(service, 'changePassword').and.returnValue(of(mockResponse));
    service.changePassword(mockResponse).subscribe(response => {
    expect(response).toEqual(mockResponse);
  });
  expect(service.changePassword).toHaveBeenCalled();
  });
  
});
