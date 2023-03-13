import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees:Employee[];

  constructor(private employeeService : EmployeeService) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  //   this.employees=[{
  //     "id": 1,
  //     "firstName":"Balaji",
  //     "lastName":" K",
  //     "emailId":"bala@c.c"
  //   },
  // {
  //   "id": 2,
  //   "firstName":"Davy jones",
  //   "lastName":" H",
  //   "emailId":"davy@c.c"
  // },
  // {
  //   "id": 3,
  //   "firstName":"Kal El",
  //   "lastName":" Krypton",
  //   "emailId":"kidnapsing@c.c"
  // }];
private getEmployees(){
  this.employeeService.getEmployeesList().subscribe(data => {
this.employees = data;
  });
}

}


