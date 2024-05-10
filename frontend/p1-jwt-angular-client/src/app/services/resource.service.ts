import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resource } from '../interfaces/Resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private apiUrl = 'http://localhost:8080/api';
  private apiAdminUrl = 'http://localhost:8080/api/admin';

  private httpOptions = new HttpHeaders();

  constructor(private http: HttpClient) { }

  public getResource(): Observable<Resource> {
    // this.addAuthorizationHeader(this.httpOptions);
    this.httpOptions = this.httpOptions.set('Authorization', 'Bearer ' + window.localStorage.getItem("auth_token"));
    console.log(this.httpOptions.keys());
    return this.http.get<Resource>(`${this.apiUrl}/resource`,
      {'headers': this.httpOptions}
    );
  }

  public getAdminResource(): Observable<Resource> {
    this.setAuthorizationHeader(this.httpOptions);
    return this.http.get<Resource>(`${this.apiAdminUrl}/resource`,
      {'headers': this.httpOptions}
    );
  }

  //TODO: Extract the logic to add authorization header to a service or an interceptor or something else.
  private setAuthorizationHeader(headers: HttpHeaders): void {
    this.httpOptions = headers.set('Authorization', 'Bearer ' + window.localStorage.getItem("auth_token"));
  }

}