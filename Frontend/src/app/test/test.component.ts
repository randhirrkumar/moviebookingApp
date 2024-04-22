import { Component } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent {
  sumOfNumber(num1:number, num2:number):number{
    return num1+num2;
  }
}
