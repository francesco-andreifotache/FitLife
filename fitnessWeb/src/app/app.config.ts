import { ApplicationConfig, importProvidersFrom, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withFetch } from '@angular/common/http';

// Import NzMessageModule instead of provideNzMessageService

import { routes } from './app.routes';

// --- Setările pentru Limba Engleză în NG-ZORRO ---
import { en_US, provideNzI18n } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
registerLocaleData(en);
// --------------------------------------------------

export const appConfig: ApplicationConfig = {
  providers: [
    // Folosim importProvidersFrom pentru modulele NG-ZORRO
    provideBrowserGlobalErrorListeners(), 
    provideRouter(routes),
    provideClientHydration(withEventReplay()),
    provideHttpClient(withFetch()), 
    provideAnimationsAsync(), 
    provideNzI18n(en_US)      
  ],
};