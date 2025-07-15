import {Component, OnInit} from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatChipsModule} from '@angular/material/chips';
import {MatBadgeModule} from '@angular/material/badge';
import { Equipment } from '../../models/Equipment';
import { EquipmentServices } from '../../services/equipment-services/equipment-services';
@Component({
  selector: 'app-equipment-tableau',
  imports: [MatTableModule, MatButtonModule, MatIconModule, MatChipsModule, MatBadgeModule],
  templateUrl: './equipment-tableau.html',
  styleUrl: './equipment-tableau.css'
})
export class EquipmentTableau implements OnInit{
  dataSource: Equipment[] = [];
  columnsToDisplay = ['nom', 'type', 'status'];
  columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
  expandedElement: Equipment | null = null;

  constructor(private equipmentService: EquipmentServices) {}
    ngOnInit() {
    // Ici vous pourrez appeler votre service pour récupérer les équipements
    this.loadEquipments();
  }

   loadEquipments() {
   this.equipmentService.getEquipments().subscribe({
      next: (equipments) => {
        this.dataSource = equipments;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des équipements', err);
      }
    });
  }
  /** Checks whether an element is expanded. */
  isExpanded(element: Equipment): boolean {
    return this.expandedElement === element;
  }

  /** Toggles the expanded state of an element. */
  toggle(element: Equipment) {
    this.expandedElement = this.isExpanded(element) ? null : element;
  }

  getStatusLabel(status: number): string {
    switch (status) {
      case 0: return 'Disponible';
      case 1: return 'En cours d\'utilisation';
      case 2: return 'Maintenance';
      case 3: return 'Hors service';
      default: return 'Inconnu';
    }
  }
   getStatusColor(status: number): string {
    switch (status) {
      case 0: return 'primary';
      case 1: return 'accent';
      case 2: return 'warn';
      case 3: return '';
      default: return '';
    }
  }
}
