import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SharedModule } from '../../shared/shared-module';
import { NzMessageService } from 'ng-zorro-antd/message';
import { User } from '../../service/user';


@Component({
  selector: 'app-workout',
  imports: [SharedModule],
  templateUrl: './workout.html',
  styleUrl: './workout.scss',
})
export class Workout {
  gridStyle = {
    width: '100%',
    textAlign: 'center',
  };

  workoutForm!: FormGroup;

  listOfType: any[] = [
    'Cardio',
    'Strength Training',
    'Flexibility',
    'Balance',
    'High-Intensity Interval Training (HIIT)',
    'Circuit Training',
    'CrossFit',
    'Yoga',
    'Pilates',
    'Dance Fitness',
    'Martial Arts',
    'Outdoor Activities',
    'Swimming',
    'Cycling',
    'Running',
    'Bodyweight Exercises',
    'Weightlifting',
    'Functional Training',
    'Boxing',
    'Kickboxing',
    'Rowing',
    'Jump Rope',
  ];

  workouts: any;

  constructor(
    private fb: FormBuilder,
    private userService: User,
    private message: NzMessageService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.workoutForm = this.fb.group({
      type: [null, [Validators.required]],
      duration: [null, [Validators.required]],
      date: [null, [Validators.required]],
      caloriesBurned: [null, [Validators.required]],
    });
    this.getWorkouts();
  }

  getWorkouts() {
    this.userService.getWorkouts().subscribe((res) => {
      this.workouts = res;
      this.cdr.markForCheck();
    });
  }

  submitForm(): void {
    this.userService.postWorkout(this.workoutForm.value).subscribe(
      (res) => {
        this.message.success('Workout posted successfully', { nzDuration: 3000 });
        this.workoutForm.reset();
        this.getWorkouts();
      },
      (error) => {
        this.message.error('Failed to post workout', { nzDuration: 3000 });
      },
    );
  }

  deleteWorkout(id: number) {
    this.userService.deleteWorkout(id).subscribe({
      next: (res) => {
        this.message.success("Antrenamentul a fost șters!", { nzDuration: 3000 });
        this.getWorkouts(); 
      },
      error: (err) => {
        this.message.error("Eroare la ștergerea antrenamentului", { nzDuration: 3000 });
      }
    });
  }

}
