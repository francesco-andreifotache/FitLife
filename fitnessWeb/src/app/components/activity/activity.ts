import { Component } from '@angular/core';
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

  constructor(private fb: FormBuilder, private message: NzMessageService,private userService:  User) {}

  ngOnInit(): void {
    this.activityForm = this.fb.group({
      caloriesBurned: [null, [Validators.required]],
      steps: [null, [Validators.required]],
      distance: [null, [Validators.required]],
      date: [null, [Validators.required]],
  })
}

 submitForm() {
  console.log('1. submitForm a fost apelat! Datele:', this.activityForm.value);

  this.userService.postActivity(this.activityForm.value).subscribe({
    next: (res) => {
      console.log('2. Răspuns primit de la server:', res);
      this.message.success('Activity submitted successfully', { nzDuration: 3000 });
      this.activityForm.reset();
    },
    error: (error) => {
      console.error('3. Eroare de la server:', error);
      this.message.error('Failed to submit activity', { nzDuration: 3000 });
    }
  });
}
}
