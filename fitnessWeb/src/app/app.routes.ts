import { Routes } from '@angular/router';
import { Activity } from './components/activity/activity';
import { Workout } from './components/workout/workout';
import { Goal } from './components/goal/goal';
import { Register } from './components/auth/register/register';
import { Login } from './components/auth/login/login';
import { Dashboard } from './components/dashboard/dashboard';
import { Food } from './components/food/food';
import { authGuard } from './guards/auth.guard'; 

export const routes: Routes = [
  // Paginile Publice (oricine are acces)
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  // Paginile Private (accesibile DOAR daca esti logat)
  { path: 'dashboard', component: Dashboard, canActivate: [authGuard] },
  { path: 'activity', component: Activity, canActivate: [authGuard] },
  { path: 'workout', component: Workout, canActivate: [authGuard] },
  { path: 'goal', component: Goal, canActivate: [authGuard] },
  { path: 'food', component: Food, canActivate: [authGuard] }
];