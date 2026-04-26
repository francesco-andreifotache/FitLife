import { ApplicationConfig, provideBrowserGlobalErrorListeners, NgModule } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { en_US, provideNzI18n } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';

registerLocaleData(en);

import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

@NgModule({
  providers: [provideHttpClient(withInterceptorsFromDi())],
})
export class AppModule {}

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideClientHydration(withEventReplay()),
  ],
};
