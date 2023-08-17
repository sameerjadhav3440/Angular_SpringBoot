import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

id!: number;
employee : Employee = new Employee();
submitted = false;


  constructor(private employeeService : EmployeeService, private route : ActivatedRoute , private router : Router){ }

  ngOnInit(): void {
   
    this.id = this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe(data=>{
      this.employee = data;
      console.log(data);
    },
     error=>console.log(error));

}



onSubmit() {
  //this.submitted = true;
  console.log(this.employee);
  this.employeeService.updateEmployee(this.id , this.employee).subscribe(data =>{
    this.goToEmployeeList();
  }, error => console.log(error)
  );
}


goToEmployeeList(){
  this.router.navigate(['/employees']);
}


}
