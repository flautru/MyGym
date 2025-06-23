import { Users } from './pages/users/users';
import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Salles } from './pages/salles/salles';
import { Equipments } from './pages/equipments/equipments';

export const routes: Routes = [
  {path: '', component: Home},
  { path: 'home', component: Home },
  {path: 'users', component: Users},
  {path: 'salles', component: Salles},
  {path: 'equipments', component: Equipments},
  {path: '**',redirectTo: ''}
];
