import { Routes } from '@angular/router';
import { Activity } from './components/activity/activity';

export const routes: Routes = [
    { path: "activity", component: Activity},
    { path: "", redirectTo: "/activity", pathMatch: "full" }
];
