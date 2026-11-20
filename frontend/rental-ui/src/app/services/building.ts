import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Building } from '../models/building';
import { Flat } from '../models/flat';

@Injectable({
  providedIn: 'root',
})
export class BuildingService {
  private apiUrl = 'http://localhost:8080/api/buildings';
    private apiUrl2 = 'http://localhost:8080/api';


  constructor(private http: HttpClient) {}

  getAllBuildings(): Observable<Building[]> {
    return this.http.get<Building[]>(this.apiUrl);
  }

  getBuildingById(id: number): Observable<Building> {
    return this.http.get<Building>(`${this.apiUrl}/${id}`);
  }

  getFlatsByBuilding(id: number): Observable<Flat[]> {
    return this.http.get<Flat[]>(`${this.apiUrl}/${id}/flats`);
  }

  getMyBuildings() {
  return this.http.get<Building[]>(
    `${this.apiUrl2}/landlord/buildings`,
    { withCredentials: true }
  );
}
}
