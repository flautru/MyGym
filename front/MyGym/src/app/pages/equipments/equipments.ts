import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Equipment, EquipmentServices } from '../../services/equipment-services/equipment-services';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { BookingDialog } from '../../component/booking-dialog/booking-dialog';

@Component({
  selector: 'app-equipments',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './equipments.html',
  styleUrls: ['./equipments.css']
})
export class Equipments implements OnInit {
  equipments: Equipment[] = [];

  constructor(private equipmentServices: EquipmentServices, private dialog: MatDialog) {}

  ngOnInit() {
    this.equipmentServices.getEquipments().subscribe({
      next: (equipments) => {
        this.equipments = equipments;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des équipements', err);
      }
    });
  }

  onReserve(equipment: Equipment) {
    alert(`Vous avez réservé : ${equipment.nom}`);
    // Ou appel API POST ici
  }
  openReservationDialog(equipment: any) {
  this.dialog.open(BookingDialog, {
    width: '1800px',
    data: { equipment },
  });
}
}
