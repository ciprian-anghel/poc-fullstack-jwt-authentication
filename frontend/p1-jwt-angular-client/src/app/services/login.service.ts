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
  private httpOptions = new HttpHeaders();
  private isLoggedIn = false;

  constructor(private http: HttpClient) {
      this.httpOptions.set('Content-Type', 'application/json');
  }

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

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }
}
