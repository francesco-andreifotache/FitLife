import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { SharedModule } from '../../../shared/shared-module';
import { AuthService } from '../../../service/auth/auth.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  submitForm() {
    this.authService.login(this.loginForm.value).subscribe({
      next: (res) => {
        console.log("Date primite de la backend:", res);

        localStorage.setItem('userId', res.userId); 
        localStorage.setItem('userName', res.name);
        localStorage.setItem('token', res.jwt);

        this.message.success("Te-ai logat cu succes!", { nzDuration: 3000 });
        // Te teleportăm înapoi în aplicație
        this.router.navigateByUrl('/activity');
      },
      error: (err) => {
        this.message.error("Email sau parolă incorecte!", { nzDuration: 3000 });
      }
    });
  }
}