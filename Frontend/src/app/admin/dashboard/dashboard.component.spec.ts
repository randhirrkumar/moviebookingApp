import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardComponent } from './dashboard.component';
import { AdminService, IMovieResponse, IPayLoad } from 'src/app/services/admin.service';
import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

fdescribe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let adminservice: AdminService;
  let httpClient:HttpClient;
  let movieObj : IPayLoad[] = [
    {
      movieId: 1,
      movieName: "Race",
      theatreName: "Cinepolis",
      ticketsAvailable: 100,
      ticketsBooked: 0,
      ticket: []
    },
    {
      movieId: 77,
      movieName: "Kantara",
      theatreName: "PVR Sangam",
      ticketsAvailable: 100,
      ticketsBooked: 0,
      ticket: []
    }
  ];  

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardComponent ],
      imports:[HttpClientTestingModule]
    })
    .compileComponents();

    httpClient =  TestBed.inject(HttpClient);
    adminservice = TestBed.inject(AdminService);
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return http Post() call',()=>{
    adminservice.addMovie(movieObj).subscribe(response=>{
      expect(response).toBeTruthy();
    });

  const ctrl = TestBed.inject(HttpTestingController);
  const mockHttp= ctrl.expectOne('http://localhost:8082/api/v1.0/movie/addMovie');
  const httpReq=mockHttp.request;

  expect(httpReq.method).toEqual('POST');
});

  it('should not return http Post() call',()=>{
      adminservice.addMovie(movieObj).subscribe({
      error:(error)=>{
        expect(error).toBeTruthy();
        expect(error.status).withContext('status').toEqual(409);
      }
    });

    const ctrl = TestBed.inject(HttpTestingController);
    const mockHttp= ctrl.expectOne('http://localhost:8082/api/v1.0/movie/addMovie');
    const httpReq=mockHttp.request;

    expect(httpReq.method).toEqual('POST');
  });

  it('should return http Delete() call',()=>{
    const movieId = movieObj[1].movieId;
    adminservice.deleteMovie(movieId).subscribe(data=>{
    });

    const ctrl = TestBed.inject(HttpTestingController);
    const mockHttp= ctrl.expectOne('http://localhost:8082/api/v1.0/movie/delete/77');
    const httpReq=mockHttp.request;

    expect(httpReq.method).toEqual('DELETE');
  });
});
