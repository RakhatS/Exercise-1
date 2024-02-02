import { Component, OnInit } from '@angular/core';
import { Employee } from './_models/employee';
import { EmployeeService } from './_services/employee.service';
import { HttpClient } from '@angular/common/http';
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit{

  loading: boolean = false;

  employees: Employee[] = [];
  countries: any[] = [];
  cities: string[] = [];
  newEmployee: Employee = new Employee();
  selectedEmployee: Employee | undefined;
  search_text: string =  "";
  isEdit: boolean = false;

  constructor(private employeeService: EmployeeService,
    private http: HttpClient,
    public translate: TranslateService) { 




    translate.addLangs(['kz', 'ru']);
    let languageFromLS = localStorage.getItem('language');
    if (languageFromLS === null || languageFromLS === undefined) {
      localStorage.setItem("language", 'ru');
      translate.setDefaultLang('ru');
    }
    else {
      translate.setDefaultLang(languageFromLS);
      localStorage.setItem("language", languageFromLS);
    }

    }


  ngOnInit(): void {
    this.getEmployees();
    this.fetchCountries()
  }

  getEmployees(){
   this.loading = true;
    this.employeeService.findAllEmployee(this.search_text!).subscribe(x => {
    this.employees = x;
    this.loading = false;
   })
  }

  saveEmployee(){
    this.loading = true;
    this.employeeService.saveEmployee(this.newEmployee).subscribe(x => {
      this.loading = false;
      this.search_text = "";
      this.getEmployees();
    })
  }

  updateEmployee() {
    this.loading = true;
    this.employeeService.updateEmployee(this.selectedEmployee!).subscribe(x => {
      this.loading = false;
      this.search_text = "";
      this.getEmployees();
    })
  }

  deleteEmployee(email: string) {
    this.loading = true;
    this.employeeService.deleteEmployee(email).subscribe(x => {
      this.loading = false;
      this.search_text = "";
      this.getEmployees();
    })
  }


  openEdit(employee: Employee){
    this.isEdit = true;
    this.selectedEmployee = employee;
    this.onCountryChange(employee)
  }
  closeEdit() {
    this.isEdit = false;
    this.selectedEmployee = undefined;;
  }


  fetchCountries() {
    this.http.get<any>('http://localhost:8080/api/v1/get_countries')
      .subscribe(data => {
        this.countries = data.countries;
        this.newEmployee.country = this.countries[0];
        this.onCountryChange(this.newEmployee); // Trigger city population for the default country
      },
        error => console.error('Error:', error));
  }

  onCountryChange(employee: Employee) {
    console.log(employee.country);
    
    const selectedCountryObj = this.countries.find(c => c.name === employee.country);
    this.cities = selectedCountryObj ? selectedCountryObj.cities : [];
  }


  switchLang(lang: string) {
    this.translate.use(lang);
    localStorage.setItem("language", lang);
  }


}
