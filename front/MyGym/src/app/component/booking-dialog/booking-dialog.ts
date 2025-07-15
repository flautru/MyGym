import { BookingService } from './../../services/bookingService/booking-service';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import {   CalendarModule,
  CalendarView,
  CalendarEvent,
  CalendarA11y ,
CalendarDateFormatter,
CalendarEventTitleFormatter } from 'angular-calendar';
import { CalendarUtils } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { CommonModule } from '@angular/common';
import { Booking } from '../../services/bookingService/booking-service';
import { Equipment } from '../../models/Equipment';


@Component({
  selector: 'app-booking-dialog',
  imports: [ MatDialogModule, MatButtonModule, CommonModule,],
  templateUrl: './booking-dialog.html',
  styleUrl: './booking-dialog.css',
  standalone: true
})
export class BookingDialog implements OnInit {
   weekDays: { name: string, date: Date }[] = [];
  hoursOfDay: string[] = ['08:00', '09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'];
  bookedSlots: { [key: string]: string[] } = {};
  equipment! : Equipment;

  constructor(
    public dialogRef: MatDialogRef<BookingDialog>,
    private bookingService: BookingService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      // Vous pouvez passer des données à la boîte de dialogue via le paramètre data
      if (data.equipment) {
        this.equipment = data.equipment;
        console.log('Equipment data:', this.equipment);
      }

    }
  ngOnInit(): void {
    // Initialize bookedSlots if not provided
    this.initWeekDays();
    this.bookedSlots = {
        'Lundi': [],
        'Mardi': [],
        'Mercredi': [],
        'Jeudi': [],
        'Vendredi': [],
        'Samedi': [],
        'Dimanche': []
      };

      this.bookingService.getBookingByEquipmentId(this.equipment.id).subscribe({
        next: (bookings) => {
          console.log('Bookings for equipment:', bookings);
          bookings.forEach((booking: Booking) => {
            const startDate = new Date(booking.startDateTime);
            const endDate = new Date(booking.endDateTime);
            const dayName = this.getDayName(startDate);
            const hour = startDate.getHours().toString().padStart(2, '0') + ':' + startDate.getMinutes().toString().padStart(2, '0');
            if (!this.bookedSlots[dayName]) {
              this.bookedSlots[dayName] = [];
            }
            this.bookedSlots[dayName].push(hour);
          });
          console.log('Booked slots:', this.bookedSlots);
        },
        error: (err) => {
          console.error('Erreur lors de la récupération des réservations :', err);
        }
      });
  }

  isSlotBooked(day: Date, hour: string): boolean {
    return this.bookedSlots[this.getDayName(day)] && this.bookedSlots[this.getDayName(day)].includes(hour);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  bookSlot(day: Date, hour: string): void {
    console.log(`Tentative de réservation pour ${this.equipment.nom} le ${day} à ${hour}`);
    if (!this.isSlotBooked(day, hour)) {
      // Ajoutez la logique pour réserver le créneau horaire ici
      // Par exemple, vous pouvez appeler une API pour enregistrer la réservation
      const [h, m] = hour.split(':');
      let booking: Booking = {
        userId: 1,
        equipmentId: this.equipment.id,
        startDateTime: `${this.getFormattedDate(day, hour)}`,
        endDateTime: `${this.getFormattedDate(day, h+":59")}`,
      };


      console.log('Booking data:', booking);
      this.bookingService.addBooking(booking).subscribe({
        next: () => {
          console.log(`Réservation réussie pour ${this.equipment.nom} le ${day} à ${hour}`);
          this.bookedSlots[this.getDayName(day)] = this.bookedSlots[this.getDayName(day)] || [];
          this.bookedSlots[this.getDayName(day)].push(hour);
          alert(`Réservation réussie pour ${this.equipment.nom} le ${day} à ${hour}`);
        },
        error: (err) => {
          console.error('Erreur lors de la réservation :', err);
          alert("Échec de la réservation. Veuillez réessayer.");
        }
      });
    } else {
      console.log(`Le créneau horaire ${hour} le ${day} est déjà réservé.`);
    }
  }

    initWeekDays() {
    const days = ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'];
    const today = new Date();
    // Trouve le lundi de la semaine courante
    const monday = new Date(today);
    const day = today.getDay();
    // getDay() : 0 = Dimanche, 1 = Lundi, ..., 6 = Samedi
    const diffToMonday = (day === 0 ? -6 : 1) - day;
    monday.setDate(today.getDate() + diffToMonday);

    this.weekDays = days.map((name, i) => {
      const date = new Date(monday);
      date.setDate(monday.getDate() + i);
      return { name, date };
    });
  }

  getDayName(date: Date): string {
  const days = ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'];
  return days[date.getDay()];
  }

  getFormattedDate(myDate: Date, hour: String): string {
    const [h, m] = hour.split(':');
    const year = myDate.getFullYear();
    const month = String(myDate.getMonth() + 1).padStart(2, '0');
    const date = String(myDate.getDate()).padStart(2, '0');

    return `${year}-${month}-${date}T${h}:${m}:00`;
  }

}
