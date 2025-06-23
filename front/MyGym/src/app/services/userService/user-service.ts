import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface User {
  firstName: string;
  lastName: string;
  mail: string;
  adress: string;
  phoneNumber: string;
  statut: number;
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

   private apiUrl = 'http://localhost:8080/api/users'; // à adapter selon ton backend
  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }


  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }
}


