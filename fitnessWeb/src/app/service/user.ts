import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root',
})
export class User {
  constructor(private http: HttpClient) {}

  // Funcție privată utilitară ca să nu scriem de 100 de ori aceeași linie
  private getUserId(): string {
    if (typeof window !== 'undefined' && window.localStorage) {
      const id = localStorage.getItem('userId');
      // Dacă există ID-ul îl dăm pe el, altfel dăm '0'
      return (id && id !== 'undefined') ? id : '0';
    }
    // Pe serverul Node.js returnăm '0' în loc de text gol ('')
    return '0'; 
  }

  // --- ACTIVITY ---
  postActivity(activityDto: any): Observable<any> {
    activityDto.userId = this.getUserId(); // Lipim ID-ul automat înainte de trimitere
    return this.http.post(BASIC_URL + 'api/activity/' + this.getUserId(), activityDto);
  }

  getActivity(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/activities/' + this.getUserId());
  }

  // --- WORKOUT ---
  postWorkout(workoutDto: any): Observable<any> {
    workoutDto.userId = this.getUserId();
    return this.http.post(BASIC_URL + 'api/workout/' + this.getUserId(), workoutDto);
  }

  getWorkouts(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/workouts/' + this.getUserId());
  }

  // --- GOAL ---
  postGoal(goalDto: any): Observable<any> {
    goalDto.userId = this.getUserId();
    return this.http.post(BASIC_URL + 'api/goal/' + this.getUserId(), goalDto);
  }

  getGoals(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/goals/' + this.getUserId());
  }

  updateGoalStatus(id: number): Observable<any> {
    // Aici e magia: lipim id-ul obiectivului + un slash + id-ul userului
    return this.http.get(BASIC_URL + "api/goal/status/" + id + "/" + this.getUserId());
  }

  // --- DASHBOARD (Statistici & Grafice) ---
  getStats(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/stats/' + this.getUserId());
  }

  getGraphs(): Observable<any> {
    return this.http.get(BASIC_URL + 'api/graphs/' + this.getUserId());
  }

  deleteActivity(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + 'api/activity/' + id);
  }

  deleteWorkout(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + 'api/workout/' + id);
  }

  deleteGoal(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + 'api/goal/' + id);
  }


  // --- Metode pentru secțiunea FOOD ---

  searchFood(query: string): Observable<any> {
    return this.http.get(BASIC_URL + `api/food/search/${query}`);
  }

  postFood(foodDto: any): Observable<any> {
    return this.http.post(BASIC_URL + `api/food/${this.getUserId()}`, foodDto);
  }

  getFoods(): Observable<any> {
    return this.http.get(BASIC_URL + `api/food/user/${this.getUserId()}`);
  }

  deleteFood(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + `api/food/${id}`);
  }

  
}