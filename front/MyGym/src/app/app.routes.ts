import { UsersComponent } from './pages/users/get/users';
import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Salles } from './pages/salles/get/salles';
import { Equipments } from './pages/equipments/equipments';
import { AddUserComponent } from './pages/users/add/add-user';
import { AddSalle } from './pages/salles/add/add-salle';

export const routes: Routes = [
  {path: '', component: Home},
  { path: 'home', component: Home },
  {path: 'add-users', component: AddUserComponent},
  {path: 'users', component: UsersComponent},
  {path: 'salles', component: Salles},
  {path: 'add-salles', component: AddSalle},
  {path: 'equipments', component: Equipments},
  {path: '**',redirectTo: ''}
];
