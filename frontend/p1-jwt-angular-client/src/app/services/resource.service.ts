import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resource } from '../interfaces/Resource';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  private apiUrl = 'http://localhost:8080/api';
  private apiAdminUrl = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) { }

  public getResource(): Observable<Resource> {
    return this.http.get<Resource>(`${this.apiUrl}/resource`);
  }

  public getAdminResource(): Observable<Resource> {
    return this.http.get<Resource>(`${this.apiAdminUrl}/resource`);
  }
}