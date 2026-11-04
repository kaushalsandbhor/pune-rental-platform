import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Building } from '../models/building';

@Injectable({
  providedIn: 'root'
})
export class BuildingService {

  private apiUrl = 'http://localhost:8080/api/buildings';

  constructor(private http: HttpClient) {}

  getAllBuildings(): Observable<Building[]> {
    return this.http.get<Building[]>(this.apiUrl);
  }
}