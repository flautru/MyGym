import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { Component } from '@angular/core';
import { SalleService, Salle } from '../../../services/salleService/salle-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-salles',
  imports: [CommonModule, MatListModule, MatCardModule],
  templateUrl: './salles.html',
  styleUrl: './salles.css'
})
export class Salles {
  salles: Salle[] = [];

  constructor(private salleService: SalleService) {}

  ngOnInit() {
    this.salleService.getSalles().subscribe({
      next: (salles) => {
        this.salles = salles;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des salles', err);
      }
    });

  }
}
