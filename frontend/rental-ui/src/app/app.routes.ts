import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Tenant } from './pages/tenant/tenant';
import { Landlord } from './pages/landlord/landlord';
import { BuildingDetails } from './pages/building-details/building-details';
import { authGuard } from './core/guards/auth-guard';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'tenant', component: Tenant },
  { path: 'landlord', component: Landlord },
  { path: 'building/:id', component: BuildingDetails },
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login').then((m) => m.Login),
  },

  {
  path: 'register',
  loadComponent: () =>
    import('./features/auth/register/register')
      .then(m => m.Register)
}
];