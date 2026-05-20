import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { SharedModule } from './shared/shared-module';
import { DemoNgZorroAntdModule } from './DemoNgZorroAntdModule';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, SharedModule, DemoNgZorroAntdModule],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('fitnessWeb');
  constructor(private router: Router) { }
  isAuthPage(): boolean {
    return this.router.url === '/login' || this.router.url === '/register';
  }

  logout() {
    // 1. Curățăm memoria browser-ului (ștergem JWT-ul și datele userului)
    localStorage.clear(); 

    // 2. Îl trimitem înapoi pe pagina de Login
    this.router.navigateByUrl('/login');
  }

  getUserName(): string {
    // Verificăm dacă obiectul 'window' (browserul) există
    if (typeof window !== 'undefined' && window.localStorage) {
      const name = localStorage.getItem('userName');
      return name ? name : 'Utilizator';
    }
    // Dacă suntem pe server (SSR), returnăm un text standard
    return 'Utilizator'; 
  }
}
