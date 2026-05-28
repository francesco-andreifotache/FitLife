import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExportService {
  private BASIC_URL = "http://localhost:8080"; 

  constructor(private http: HttpClient) { }

  exportExcel(userId: number): Observable<Blob> {
    
    const v = new Date().getTime().toString(); 
    
    const params = new HttpParams().set('v', v);

    return this.http.get(`${this.BASIC_URL}/api/export/excel/${userId}`, { 
      responseType: 'blob',
      params: params 
    });
  }
}