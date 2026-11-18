
import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthService, RegisterRequest } from '../../../services/auth.service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class Register {

  private authService = inject(AuthService);
  private router = inject(Router);

  fullName = signal('');
  username = signal('');
  email = signal('');
  mobile = signal('');
  password = signal('');

  errorMessage = signal('');
  successMessage = signal('');
  isLoading = signal(false);

  onRegister(): void {

    if (
      !this.fullName().trim() ||
      !this.username().trim() ||
      !this.password()
    ) {
      this.errorMessage.set('Please fill all required fields.');
      return;
    }

    if (!this.email().trim() && !this.mobile().trim()) {
      this.errorMessage.set('Enter either an email address or mobile number.');
      return;
    }

    const request: RegisterRequest = {
      fullName: this.fullName().trim(),
      username: this.username().trim(),
      email: this.email().trim() || undefined,
      mobile: this.mobile().trim() || undefined,
      password: this.password()
    };

    this.isLoading.set(true);
    this.errorMessage.set('');
    this.successMessage.set('');

    this.authService.register(request).pipe(
      finalize(() => this.isLoading.set(false))
    ).subscribe({

      next: () => {
        this.successMessage.set(
          'Registration successful! Your account is pending verification.'
        );

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },

      error: (error) => {
        console.error('Registration failed:', error);

        this.errorMessage.set(
          error.error?.message || 'Registration failed. Please try again.'
        );
      }
    });
  }
}
