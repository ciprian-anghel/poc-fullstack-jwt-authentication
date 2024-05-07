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
  private httpOptions: any = {'Content-Type':'application/json'};

  constructor(private http: HttpClient) { }

  public userLogin(userLogin: Login): Observable<any> { 
    return this.http.post(`${this.apiUrl}/login`, 
      userLogin,                                  
      {'headers': this.httpOptions});
  }

  public registerUser(userRegister: Register): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, 
      userRegister,                                  
      {'headers': this.httpOptions});
  }

  //TODO: How can I access the token in the resource.service?
  // if (getAuthToken() !== null) {
  //   httpOptions['Authorization'] = "Bearer " + this.getAuthToken();
  // }

  //Maybe use an angular interceptor to intercept the requests and add the 'Authorization' httpOption there if exists.

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }
}
