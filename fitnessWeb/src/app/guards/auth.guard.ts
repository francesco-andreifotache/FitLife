import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

 
  if (isPlatformBrowser(platformId)) {
    const userId = localStorage.getItem('userId');
    
    if (userId && userId !== 'undefined' && userId !== '0') {
      return true;
    }

    router.navigateByUrl('/login');
    return false;
  }

  return true;
};