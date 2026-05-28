import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { SharedModule } from '../../../shared/shared-module';

import { AuthService } from '../../../service/auth/auth.service';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class Register {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService, 
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.registerForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      name: [null, [Validators.required]],
      password: [null, [Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
    });
  }

  
  confirmationValidator = (control: any): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.registerForm.controls['password'].value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  submitForm() {
    
    this.authService.register(this.registerForm.value).subscribe({
      next: (res) => {
        localStorage.setItem('userId', res.userId);
        localStorage.setItem('userName', res.name);
        localStorage.setItem('token', res.jwt);

        this.message.success("Cont creat cu succes! Te poți loga.", { nzDuration: 3000 });
        this.router.navigateByUrl('/login');
      },
      error: (err) => {
        this.message.error("Eroare la înregistrare: " + err.error, { nzDuration: 3000 });
      }
    });
  }
}