
import { Component, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { finalize } from 'rxjs';

import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {

  private authService = inject(AuthService);
  private router = inject(Router);

  identifier = signal('');
  password = signal('');

  errorMessage = signal('');
  isLoading = signal(false);

  onLogin(): void {

    if (!this.identifier().trim() || !this.password()) {
      this.errorMessage.set('Enter your username/email/mobile and password.');
      return;
    }

    this.isLoading.set(true);
    this.errorMessage.set('');

    this.authService.login({
      identifier: this.identifier().trim(),
      password: this.password()
    })
    .pipe(
      finalize(() => this.isLoading.set(false))
    )
    .subscribe({
      next: () => {
        this.router.navigate(['/landlord/dashboard']);
      },

      error: (error) => {
        console.error('Login failed:', error);

        if (error.status === 401) {
          this.errorMessage.set('Invalid username or password.');
        } else if (error.status === 0) {
          this.errorMessage.set('Unable to connect to the server.');
        } else {
          this.errorMessage.set('Something went wrong. Please try again.');
        }
      }
    });
  }


onLogout(): void {
  this.authService.logout().subscribe({
    next: () => {
      this.router.navigate(['/login']);
    },
    error: (error) => {
      console.error('Logout failed:', error);
    }
  });
}

}
