import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared-module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
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

  constructor(private fb: FormBuilder, private message: NzMessageService) {}

  ngOnInit(): void {
    this.activityForm = this.fb.group({
      caloriesBurned: [null, [Validators.required]],
      steps: [null, [Validators.required]],
      distance: [null, [Validators.required]],
      date: [null, [Validators.required]],
  })
}
}
