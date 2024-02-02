import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from '../_models/employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) {
  }

  private api: string = "http://localhost:8080/api/v1/employees";

  findAllEmployee(search_text: string): Observable<Employee[]> {
    let api = this.api + "?search_text="+search_text;
    return this.http.get<Employee[]>(api);
  }

  findByEmail(email: string): Observable<Employee> {
    let api = this.api + "/findByEmail/"+email;
    return this.http.get<Employee>(api);
  }

  saveEmployee(employee: Employee): Observable<any> {
    let api = this.api + "/save_employee";
    return this.http.post(api, employee);
  }

  updateEmployee(employee: Employee): Observable<any> {
    let api = this.api + "/update_employee";
    return this.http.put(api, employee);
  }

  deleteEmployee(email: string): Observable<Employee> {
    let api = this.api + "/delete_employee/" + email;
    return this.http.delete<Employee>(api);
  }
}
