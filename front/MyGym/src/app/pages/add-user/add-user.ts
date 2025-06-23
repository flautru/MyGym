import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { UserService, User } from '../../services/userService/user-service';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
  ],
  templateUrl: './add-user.html',
})
export class AddUserComponent {
  userForm;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      adress: [''],
      phoneNumber: [''],
      mail: ['', [Validators.required, Validators.email]],
      statut: [1],
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.userForm.valid) {
      const newUser: User = this.formValueToUser(this.userForm.value);
      this.userService.addUser(newUser).subscribe({
        next: () => {
          alert('Utilisateur ajouté avec succès');
          this.userForm.reset();
        },
        error: (err) => {
          console.error('Erreur lors de la création :', err);
          alert("Échec de l'ajout de l'utilisateur");
        },
      });
    }
  }

  private formValueToUser(formValue: any): User {
    return {
      firstName: formValue.firstName ?? '',
      lastName: formValue.lastName ?? '',
      adress: formValue.adress ?? '',
      phoneNumber: formValue.phoneNumber ?? '',
      mail: formValue.mail ?? '',
      statut: formValue.statut ?? 1,
      username: formValue.username ?? '',
      password: formValue.password ?? '',
    };
  }
}
