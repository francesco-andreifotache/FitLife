import { Component, ChangeDetectorRef, OnInit, ViewChild, ElementRef, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser, DatePipe } from '@angular/common'; 
import { User } from '../../service/user';
import { SharedModule } from '../../shared/shared-module';
import { CategoryScale, Chart } from 'chart.js/auto';

import { ExportService } from '../../service/export.service';

Chart.register(CategoryScale);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
  providers: [DatePipe]
})
export class Dashboard implements OnInit {
  statsData: any;
  workouts: any;
  activities: any;

  @ViewChild('workoutLineChart') private workoutLineChartRef!: ElementRef;
  @ViewChild('activityLineChart') private activityLineChartRef!: ElementRef;

  workoutChartInstance: any;
  activityChartInstance: any;

  constructor(
    private userService: User,
    private datePipe: DatePipe,
    private cdr: ChangeDetectorRef,
    @Inject(PLATFORM_ID) private platformId: Object,
    private exportService: ExportService   
  ) {}

  ngOnInit() {
    this.getStats();
    this.getGraphStats();
  }

  getGraphStats() {
    this.userService.getGraphs().subscribe((res) => {
      this.workouts = res.workouts;
      this.activities = res.activities;

      this.cdr.detectChanges(); 

      if (isPlatformBrowser(this.platformId)) {
        if (this.workoutLineChartRef && this.activityLineChartRef) { 
          this.createLineChart();
        }
      }
    });
  }

  
  downloadReport() {
    const userId = window.localStorage.getItem('userId'); 
    
    if (userId) {
      this.exportService.exportExcel(Number(userId)).subscribe({
        next: (blob: Blob) => {
          const a = document.createElement('a');
          const objectUrl = URL.createObjectURL(blob);
          a.href = objectUrl;
          
          
          const acum = new Date().toLocaleTimeString().replace(/:/g, '-');
          a.download = `FitLife_Raport_${acum}.xlsx`;
          
          
          document.body.appendChild(a);
          a.click();
          
          
          document.body.removeChild(a);
          URL.revokeObjectURL(objectUrl);
        },
        error: (err) => {
          console.error("Eroare la descărcarea fișierului Excel:", err);
        }
      });
    } else {
      alert("Eroare: Nu s-a găsit niciun utilizator logat în sistem!");
    }
  }

  createLineChart() {
    if (this.workoutChartInstance) {
      this.workoutChartInstance.destroy();
    }

    if (this.activityChartInstance) {
      this.activityChartInstance.destroy();
    }

    const workoutCtx = this.workoutLineChartRef.nativeElement.getContext('2d');
    const activityCtx = this.activityLineChartRef.nativeElement.getContext('2d');

    this.workoutChartInstance = new Chart(workoutCtx, {
      type: 'line',
      data: {
        labels: this.workouts.map((data : {date:any;}) => this.datePipe.transform(data.date, 'MM/dd')),
        datasets: [
          {
            label: 'Calories Burned',
            data: this.workouts.map((data : {caloriesBurned:any;}) => data.caloriesBurned), 
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(80, 200, 120, 0.6)',
            borderColor: 'rgba(0, 100, 0, 1)',
          },
          {
            label: 'Duration',
            data: this.workouts.map((data : {duration:any;}) => data.duration),
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(120, 120, 200, 0.6)',
            borderColor: 'rgba(0, 0, 100, 1)',
          }
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

    this.activityChartInstance = new Chart(activityCtx, {
      type: 'line',
      data: {
        labels: this.activities.map((data : {date:any;}) => this.datePipe.transform(data.date, 'MM/dd')),
        datasets: [
          {
            label: 'Calories Burned',
            data: this.activities.map((data : {caloriesBurned:any;}) => data.caloriesBurned), 
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(255, 100, 200, 0.6)',
            borderColor: 'rgba(255, 0, 100, 1)',
          },
          {
            label: 'Steps',
            data: this.activities.map((data : {steps:any;}) => data.steps),
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(255, 180, 120, 0.6)',
            borderColor: 'rgba(255, 100, 0, 1)',
          },
          {
            label: 'Distance',
            data: this.activities.map((data : {distance:any;}) => data.distance),
            fill: false,
            borderWidth: 2,
            backgroundColor: 'rgba(200, 200, 200, 0.6)',
            borderColor: 'rgba(255, 0, 100, 1)',
          }
        ]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  getStats() {
    this.userService.getStats().subscribe((res) => {
      this.statsData = res;
      this.cdr.detectChanges(); 
    });
  }
}