import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestComponent } from './test.component';

fdescribe('TestComponent', () => {
  let component: TestComponent;
  let fixture: ComponentFixture<TestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return sum of numbers',()=>{
    const result = component.sumOfNumber(3,9);
    expect(result).toEqual(12);
  })

});
