import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { LoginService } from '../services/login.service';
import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let loginservice: LoginService;
  let httpClient:HttpClient;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports:[HttpClientTestingModule, MatSnackBarModule]
    })
    .compileComponents();

    httpClient =  TestBed.inject(HttpClient);
    loginservice = TestBed.inject(LoginService);
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get current user', () => {
    const mockUser = { id: 1, name: 'Priya Kumari' };
    loginservice.getCurrentUser().subscribe(user => {
      expect(user).toEqual(mockUser);
    });

    const ctrl = TestBed.inject(HttpTestingController);
    const req = ctrl.expectOne('http://localhost:8084/auth/v1.0/current-user');
    expect(req.request.method).toBe('GET');
    req.flush(mockUser);
  });

  it('should change password', () => {
    const mockUserData = { username: 'priyak', newPassword: 'newpassword' };
    loginservice.changePassword(mockUserData).subscribe(response => {
      expect(response).toBe('Success');
    });

    const ctrl = TestBed.inject(HttpTestingController);
    const req = ctrl.expectOne('http://localhost:8084/auth/v1.0/forgotPassword');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(mockUserData);
    req.flush('Success', { status: 200, statusText: 'OK' });
  });
});
