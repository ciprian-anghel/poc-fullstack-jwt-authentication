import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Login } from '../interfaces/Login';
import { Register } from '../interfaces/Register';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  public userLogin(userLogin: Login): Observable<any> {
    const httpOptions = {'Content-Type':'application/json'};
    return this.http.post(`${this.apiUrl}/login`, 
      userLogin,                                  
      {'headers': httpOptions});
  }

  public registerUser(userRegister: Register): Observable<any> {
    const httpOptions = {'Content-Type':'application/json'};
    return this.http.post(`${this.apiUrl}/register`, 
      userRegister,                                  
      {'headers': httpOptions});
  }
}
