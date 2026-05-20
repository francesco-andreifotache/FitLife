import { ChangeDetectorRef, Component } from '@angular/core';
import { SharedModule } from '../../shared/shared-module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { User } from '../../service/user';


@Component({
  standalone: true,
  selector: 'app-activity',
  imports: [SharedModule],
  templateUrl: './activity.html',
  styleUrl: './activity.scss',
})
export class Activity {
  gridStyle = {
    width: '100%',
    textAlign: 'center',
  };

  activityForm!: FormGroup;
  activities: any[] = [];

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private userService: User,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.activityForm = this.fb.group({
      caloriesBurned: [null, [Validators.required]],
      steps: [null, [Validators.required]],
      distance: [null, [Validators.required]],
      date: [null, [Validators.required]],
    });
    this.getActivities();
  }

  submitForm() {
    console.log('1. submitForm a fost apelat! Datele:', this.activityForm.value);

    const currentUserId = localStorage.getItem('userId');

    const activityData = {
    ...this.activityForm.value,
    userId: currentUserId ? Number(currentUserId) : null
    };

    this.userService.postActivity(activityData).subscribe({
      next: (res) => {
        console.log('2. Răspuns primit de la server:', res);
        this.message.success('Activity submitted successfully', { nzDuration: 3000 });
        setTimeout(() => {
          this.activityForm.reset();
          this.getActivities();
        }, 0);
      },
      error: (error) => {
        console.error('3. Eroare de la server:', error);
        this.message.error('Failed to submit activity', { nzDuration: 3000 });
      },
    });
  }

  getActivities() {
    this.userService.getActivity().subscribe((res) => {
      this.activities = res;
      this.cdr.markForCheck();
      console.log(this.activities);
    });
  }

  deleteActivity(id: number) {
    this.userService.deleteActivity(id).subscribe({
      next: (res) => {
        this.message.success("Activitatea a fost ștearsă!", { nzDuration: 3000 });
        this.getActivities(); // Reîncărcăm lista ca să dispară de pe ecran
      },
      error: (err) => {
        this.message.error("Eroare la ștergere", { nzDuration: 3000 });
      }
    });
  }
}
