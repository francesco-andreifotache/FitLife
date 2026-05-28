import { ChangeDetectorRef, Component } from '@angular/core';
import { SharedModule } from '../../shared/shared-module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../service/user';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-goal',
  imports: [SharedModule],
  templateUrl: './goal.html',
  styleUrl: './goal.scss',
})
export class Goal {
  gridStyle = {
    width: '100%',
    textAlign: 'center',
  };

  goalForm!: FormGroup;
  goals: any[] = [];

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private userService: User,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(){
    this.goalForm = this.fb.group({
      description: [null, [Validators.required]],
      startDate: [null, [Validators.required]],
      endDate: [null, [Validators.required]],
    });

    this.getAllGoals();
  }

  submitForm(): void {
    this.userService.postGoal(this.goalForm.value).subscribe(res => { 
      this.message.success('Goal created successfully', { nzDuration: 3000 });

      setTimeout(() => {
      this.goalForm.reset();
        this.getAllGoals();
      }, 0);
    }, error => {
      this.message.error('Failed to create goal', { nzDuration: 3000 });
    });
  }

  getAllGoals(){
    this.userService.getGoals().subscribe(res => {
      this.goals = res;
      this.cdr.markForCheck();
    });
  }

  updateStatus(id:number){
    this.userService.updateGoalStatus(id).subscribe(res =>{
      this.message.success('Goal status updated successfully', { nzDuration: 3000 });
      this.getAllGoals();
    }, error => {
      this.message.error('Failed to update goal status', { nzDuration: 3000 });
    })
  }

  deleteGoal(id: number) {
    this.userService.deleteGoal(id).subscribe({
      next: (res) => {
        this.message.success("Obiectivul a fost șters!", { nzDuration: 3000 });
        this.getAllGoals(); 
      },
      error: (err) => {
        this.message.error("Eroare la ștergerea obiectivului", { nzDuration: 3000 });
      }
    });
  }

}

