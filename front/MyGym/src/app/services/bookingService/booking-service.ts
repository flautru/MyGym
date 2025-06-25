import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Booking {
  id?: number;
  userId: number;
  equipmentId?: number;
  startDateTime: string;
  endDateTime: string;
  // Ajoute d'autres champs selon ton modèle backend
}

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private apiUrl = 'http://localhost:8080/api/bookings';

  constructor(private http: HttpClient) {}

  getBookings(): Observable<Booking[]> {
    return this.http.get<Booking[]>(this.apiUrl);
  }

  addBooking(booking: Booking): Observable<Booking> {
    return this.http.post<Booking>(this.apiUrl, booking);
  }

  getBookingByEquipmentId(equipmentId: number): Observable<Booking[]> {
    return this.http.get<Booking[]>(`${this.apiUrl}/equipment/${equipmentId}`);
  }
}
