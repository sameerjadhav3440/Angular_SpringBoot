import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Employee } from './employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8085/api/v1/employees";
  private addEmpURL = "http://localhost:8085/api/v1/saveemp";
  private getEmpById = "http://localhost:8085/api/v1/getEmpById";
  private updateemp = "http://localhost:8085/api/v1/UpdateEmpById";
  private deleteemp = "http://localhost:8085/api/v1/DeleteEmp";

  constructor(private HttpClient: HttpClient) { }

   getEmployeesList(): Observable<Employee[]>{
    return this.HttpClient.get<Employee[]>(`${this.baseURL}`);
   }

   createEmployee(employee : Employee):Observable<Object>{
    return this.HttpClient.post(`${this.addEmpURL}` , employee);
   }


   getEmployeeById(id: number):Observable<Employee> {
      return this.HttpClient.get<Employee>(`${this.getEmpById}/${id}`);
   }

   updateEmployee(id:number, employee : Employee):Observable<Object>{
    return this.HttpClient.put(`${this.updateemp}/${id}`,employee);
   }

   deleteEmployee(id:number):Observable<Object>{
    return this.HttpClient.delete(`${this.deleteemp}/${id}`);
   }

   
}
