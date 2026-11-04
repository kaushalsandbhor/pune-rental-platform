import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Tenant } from './pages/tenant/tenant';
import { Landlord } from './pages/landlord/landlord';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'tenant', component: Tenant },
  { path: 'landlord', component: Landlord }
];