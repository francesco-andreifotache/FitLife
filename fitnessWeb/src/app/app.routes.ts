import { Routes } from '@angular/router';
import { Activity } from './components/activity/activity';
import { Workout } from './components/workout/workout';

export const routes: Routes = [
  { path: 'activity', component: Activity },
  { path: '', redirectTo: '/activity', pathMatch: 'full' },
  { path: 'workout', component: Workout },
];
