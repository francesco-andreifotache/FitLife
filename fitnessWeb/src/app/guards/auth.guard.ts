import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  // 1. Verificăm dacă suntem fizic în browser (unde există localStorage)
  if (isPlatformBrowser(platformId)) {
    const userId = localStorage.getItem('userId');
    
    // Dacă utilizatorul are un ID valid salvat, îl lăsăm să treacă
    if (userId && userId !== 'undefined' && userId !== '0') {
      return true;
    }

    // Dacă e în browser, dar NU are ID, îl dăm afară la login
    router.navigateByUrl('/login');
    return false;
  }

  // 2. Dacă suntem pe server (la momentul refresh-ului), permitem temporar
  // trecerea, pentru a lăsa browser-ul să preia comanda în secunda următoare.
  return true;
};