import { UsersComponent } from './pages/users/users';
import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Salles } from './pages/salles/salles';
import { Equipments } from './pages/equipments/equipments';
import { AddUserComponent } from './pages/add-user/add-user';

export const routes: Routes = [
  {path: '', component: Home},
  { path: 'home', component: Home },
  {path: 'add-users', component: AddUserComponent},
  {path: 'users', component: UsersComponent},
  {path: 'salles', component: Salles},
  {path: 'equipments', component: Equipments},
  {path: '**',redirectTo: ''}
];
