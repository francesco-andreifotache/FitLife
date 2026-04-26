import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterModule } from '@angular/router';
import { SharedModule } from './shared/shared-module';
import { DemoNgZorroAntdModule } from './DemoNgZorroAntdModule';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule, SharedModule, DemoNgZorroAntdModule],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected readonly title = signal('fitnessWeb');
}
