import { Routes } from '@angular/router';
import { Activity } from './components/activity/activity';
import { Workout } from './components/workout/workout';
import { Goal } from './components/goal/goal';
import { Register } from './components/auth/register/register';
import { Login } from './components/auth/login/login';

export const routes: Routes = [
  { path: 'activity', component: Activity },
  { path: '', redirectTo: '/activity', pathMatch: 'full' },
  { path: 'workout', component: Workout },
  { path: 'goal', component: Goal },
  { path: 'register', component: Register },
  { path: 'login', component: Login },
];
