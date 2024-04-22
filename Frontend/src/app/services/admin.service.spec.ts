import { TestBed } from '@angular/core/testing';

import { AdminService } from './admin.service';
import { DashboardComponent } from '../admin/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

fdescribe('AdminService', () => {
  let adminservice : AdminService;
  let moviecomp:DashboardComponent;

  beforeEach(() => {
    TestBed.configureTestingModule({ 
      declarations:[DashboardComponent],
      imports:[
      HttpClientModule,
      HttpClientTestingModule
    ],
    providers:[AdminService, DashboardComponent]
  });
    adminservice = TestBed.inject(AdminService);
    moviecomp = TestBed.inject(DashboardComponent);
  });

  it('should be created', () => {
    expect(adminservice).toBeTruthy();
  });

  it('should call get() from service',()=>{
    let response: AdminService|any;
    let spy = spyOn(adminservice,'fetchMovies').and.returnValue(of(response));
    expect(moviecomp.fetchAllMovies()).toEqual(response);
  })

  it('should call delete() from service',()=>{
    const movieId=67;
    let spy = spyOn(adminservice,'deleteMovie').and.returnValue(of());
    moviecomp.delete(movieId);
    expect(spy).toHaveBeenCalled();
  })
});
