import { TestBed } from '@angular/core/testing';

import { UserfuncService } from './userfunc.service';
import { UserDashboardComponent } from '../user/user-dashboard/user-dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

fdescribe('UserfuncService', () => {
  let service: UserfuncService;
  let usercomp:UserDashboardComponent;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations:[UserDashboardComponent],
      imports:[
      HttpClientModule,
      HttpClientTestingModule
    ],
    providers:[UserfuncService, UserDashboardComponent]
    });
    service = TestBed.inject(UserfuncService);
    usercomp = TestBed.inject(UserDashboardComponent);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
