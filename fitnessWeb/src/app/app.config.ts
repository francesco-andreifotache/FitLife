import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { provideBrowserGlobalErrorListeners } from '@angular/core';

import { routes } from './app.routes';

// --- Setările pentru Limba Engleză în NG-ZORRO ---
import { en_US, provideNzI18n } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
registerLocaleData(en);
// --------------------------------------------------

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(), // L-am pus la loc pe cel din codul tău original
    provideRouter(routes),
    provideClientHydration(withEventReplay()),
    provideHttpClient(withInterceptorsFromDi()), // Mutat aici din vechiul modul
    provideAnimationsAsync(), // Pentru calendar
    provideNzI18n(en_US)      // Pentru limba engleză
  ],
};