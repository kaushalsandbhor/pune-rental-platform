
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { signal } from '@angular/core';
import { catchError, finalize, of, tap } from 'rxjs';

export interface RegisterRequest {
  fullName: string;
  username: string;
  email?: string;
  mobile?: string;
  password: string;
}

export interface LoginRequest {
  identifier: string;
  password: string;
}

export interface UserResponse {
  id: number;
  fullName: string;
  username: string;
  role: string;
}

export interface LoginResponse {
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly authUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  currentUser = signal<UserResponse | null>(null);

  isCheckingSession = signal(true);

  register(request: RegisterRequest): Observable<unknown> {
    return this.http.post(
      `${this.authUrl}/register`,
      request,
      { withCredentials: true }
    );
  }

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      `${this.authUrl}/login`,
      request,
      { withCredentials: true }
    );
  }

  getCurrentUser(): Observable<UserResponse> {
    return this.http.get<UserResponse>(
      `${this.authUrl}/me`,
      { withCredentials: true }
    );
  }

  
logout(): Observable<unknown> {
  return this.http.post(
    `${this.authUrl}/logout`,
    {},
    { withCredentials: true }
  ).pipe(
    tap(() => {
      this.currentUser.set(null);
    })
  );
}


  checkSession(): Observable<UserResponse | null> {

  this.isCheckingSession.set(true);

  return this.getCurrentUser().pipe(

    tap(user => {
      this.currentUser.set(user);
    }),

    catchError(() => {
      this.currentUser.set(null);
      return of(null);
    }),

    finalize(() => {
      this.isCheckingSession.set(false);
    })

  );
}
}
