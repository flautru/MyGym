import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Equipment {
  id: number;
  nom: string;
  imageUrl: string;
  statut: number;
}

@Injectable({
  providedIn: 'root'
})
export class EquipmentServices {

    private apiUrl = 'http://localhost:8080/api/equipments'; // à adapter selon ton backend
  constructor(private http: HttpClient) {}

  getEquipments(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(this.apiUrl);
  }


  addEquipments(user: Equipment): Observable<Equipment> {
    return this.http.post<Equipment>(this.apiUrl, user);
  }
}
