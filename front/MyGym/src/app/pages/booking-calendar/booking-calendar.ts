import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService, Booking } from '../../services/bookingService/booking-service';
import { EquipmentServices } from '../../services/equipment-services/equipment-services';
import { Equipment } from '../../models/Equipment';


interface TimeSlot {
  date: Date;
  time: string;
  hour: number;
  isBooked: boolean;
  booking?: Booking;
}

interface DaySchedule {
  date: Date;
  dayName: string;
  dayNumber: number;
  timeSlots: TimeSlot[];
}

@Component({
  selector: 'app-booking-calendar',
    imports: [
    CommonModule,
    MatCardModule,
    MatChipsModule,
    MatButtonModule,
    MatDialogModule,
    MatIconModule
  ],
  templateUrl: './booking-calendar.html',
  styleUrl: './booking-calendar.css',
})
export class BookingCalendar {
  weekSchedule: DaySchedule[] = [];
  hours: number[] = [];
  statusMessage: string = '';
  currentBookings: Booking[] = [];
  equipmentId: number = 0;
  equipment: Equipment | null = null;

  private readonly START_HOUR = 8;
  private readonly END_HOUR = 18;
  private readonly USER_ID = 1;

  constructor(
    private bookingService: BookingService,
    private equipmentService: EquipmentServices,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.equipmentId = +params['id'];
      this.loadEquipmentInfo();
    });

    this.generateWeekSchedule();
    this.generateHours();
  }

  private loadEquipmentInfo() {
    console.log(this.equipmentId);
    if (this.equipmentId) {

      this.equipmentService.getEquipmentById(this.equipmentId).subscribe({
        next: (equipment) => {
          this.equipment = equipment;
        },
        error: (error) => {
          console.error('Erreur lors du chargement de l\'équipement:', error);
          this.statusMessage = 'Erreur lors du chargement de l\'équipement';
        }
      });

      this.loadBookings();
    }
  }

  goBack(){
    this.router.navigate(['/equipments']);
  }

  private generateWeekSchedule() {
    const today = new Date();
    const dayNames = [
      'Dimanche',
      'Lundi',
      'Mardi',
      'Mercredi',
      'Jeudi',
      'Vendredi',
      'Samedi',
    ];

    this.weekSchedule = [];

    for (let i = 0; i < 7; i++) {
      const date = new Date(today);
      date.setDate(today.getDate() + i);

      const daySchedule: DaySchedule = {
        date: date,
        dayName: dayNames[date.getDay()],
        dayNumber: date.getDate(),
        timeSlots: [],
      };

      for (let hour = this.START_HOUR; hour < this.END_HOUR; hour++) {
        const timeSlot: TimeSlot = {
          date: date,
          time: `${hour.toString().padStart(2, '0')}:00`,
          hour: hour,
          isBooked: false,
        };
        daySchedule.timeSlots.push(timeSlot);
      }

      this.weekSchedule.push(daySchedule);
    }
  }

  private generateHours() {
    this.hours = [];
    for (let hour = this.START_HOUR; hour < this.END_HOUR; hour++) {
      this.hours.push(hour);
    }
  }

  private loadBookings() {
    if (!this.equipmentId) return;

    this.bookingService.getBookingByEquipmentId(this.equipmentId).subscribe({
      next: (bookings) => {
        this.currentBookings = bookings;
        this.updateTimeSlots();
      },
      error: (error) => {
        console.error('Erreur lors du chargement des réservations:', error);
        this.statusMessage = 'Erreur lors du chargement des réservations';
      },
    });
  }

  private updateTimeSlots() {
    this.weekSchedule.forEach((day) => {
      day.timeSlots.forEach((slot) => {
        const slotDateTime = new Date(slot.date);
        slotDateTime.setHours(slot.hour, 0, 0, 0);

        const booking = this.currentBookings.find((b) => {
          const startDate = new Date(b.startDateTime);
          const endDate = new Date(b.endDateTime);
          return slotDateTime >= startDate && slotDateTime < endDate;
        });

        if (booking) {
          slot.isBooked = true;
          slot.booking = booking;
        }
      });
    });
  }

  getTimeSlot(day: DaySchedule, hour: number): TimeSlot {
    return (
      day.timeSlots.find((slot) => slot.hour === hour) || {
        date: day.date,
        time: `${hour}:00`,
        hour: hour,
        isBooked: false,
      }
    );
  }

  getMonthNumber(date: Date): string {
    return (date.getMonth() + 1).toString().padStart(2, '0');
  }

  onSlotClick(day: DaySchedule, hour: number) {
    const timeSlot = this.getTimeSlot(day, hour);

    if (timeSlot.isBooked) {
      this.statusMessage = 'Ce créneau est déjà réservé';
      return;
    }

    // Vérifier si le créneau est dans le passé
    const slotDateTime = new Date(day.date);
    slotDateTime.setHours(hour, 0, 0, 0);

    if (slotDateTime < new Date()) {
      this.statusMessage = 'Impossible de réserver un créneau dans le passé';
      return;
    }
 this.openConfirmationDialog(day, hour);
  }

  private openConfirmationDialog(day: DaySchedule, hour: number) {
    const dialogRef = this.dialog.open(BookingConfirmationDialog, {
      width: '400px',
      data: {
        equipmentName: this.equipment?.nom || 'Équipement',
        dayName: day.dayName,
        dayNumber: day.dayNumber,
        month: this.getMonthNumber(day.date),
        hour: hour
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.confirmBooking(day, hour);
      }
    });
  }

  private confirmBooking(day: DaySchedule, hour: number) {
    const startDateTime = new Date(day.date);
    startDateTime.setUTCHours(hour, 0, 0, 0);

    const endDateTime = new Date(day.date);
    endDateTime.setUTCHours(hour + 1, 0, 0, 0);

    const newBooking: Booking = {
      userId: this.USER_ID,
      equipmentId: this.equipmentId,
      startDateTime: startDateTime.toISOString(),
      endDateTime: endDateTime.toISOString(),
    };

    this.statusMessage = 'Réservation en cours...';

    this.bookingService.addBooking(newBooking).subscribe({
      next: (booking) => {
        this.statusMessage = `Créneau réservé avec succès pour le ${day.dayName} ${day.dayNumber} à ${hour}h`;
        this.currentBookings.push(booking);
        this.updateTimeSlots();
      },
      error: (error) => {
        console.error('Erreur lors de la réservation:', error);
        this.statusMessage = 'Erreur lors de la réservation du créneau';
      },
    });
  }
}

@Component({
  selector: 'booking-confirmation-dialog',
  template: `
    <h2 mat-dialog-title>Confirmer la réservation</h2>
    <mat-dialog-content>
      <p>Voulez-vous réserver l'équipement <strong>{{ data.equipmentName }}</strong> ?</p>
      <p>Le <strong>{{ data.dayName }} {{ data.dayNumber }}/{{ data.month }}</strong> à <strong>{{ data.hour }}h00</strong></p>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button [mat-dialog-close]="false">Annuler</button>
      <button mat-raised-button color="primary" [mat-dialog-close]="true">Confirmer</button>
    </mat-dialog-actions>
  `,
  standalone: true,
  imports: [MatDialogModule, MatButtonModule]
})
export class BookingConfirmationDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}
}

