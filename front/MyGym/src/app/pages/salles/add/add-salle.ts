import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Salle, SalleService } from '../../../services/salleService/salle-service';

@Component({
  selector: 'app-add-salle',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,],
  templateUrl: './add-salle.html',
  styleUrl: './add-salle.css'
})
export class AddSalle {

  salleForm;

  constructor(private fb: FormBuilder, private salleService: SalleService) {
    this.salleForm = this.fb.group({
      name: ['', Validators.required],
      adress: [''],
      statut: [1],
    });
  }

  onSubmit() {
    if (this.salleForm.valid) {
      const newSalle: Salle = this.formValueToSalle(this.salleForm.value);
      this.salleService.addSalle(newSalle).subscribe({
        next: () => {
          alert('Salle ajouté avec succès');
          this.salleForm.reset();
        },
        error: (err) => {
          console.error('Erreur lors de la création :', err);
          alert("Échec de l'ajout de la salle");
        },
      });
    }
  }

  private formValueToSalle(formValue: any): Salle {
    return {
      name: formValue.name ?? '',
      adress: formValue.adress ?? '',
      statut: formValue.statut ?? 1,
    };
  }
}
