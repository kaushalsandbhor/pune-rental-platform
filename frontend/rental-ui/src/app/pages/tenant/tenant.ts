import { Component, OnInit } from '@angular/core';
import { BuildingService } from '../../services/building';
import { Building } from '../../models/building';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-tenant',
  standalone: true,
  imports: [NgFor],
  templateUrl: './tenant.html',
  styleUrl: './tenant.scss'
})
export class Tenant implements OnInit {

  buildings: Building[] = [];

  constructor(private buildingService: BuildingService) {}

  ngOnInit(): void {
    this.buildingService.getAllBuildings().subscribe({
      next: (data) => {
  this.buildings = data;

  console.log('Count:', this.buildings.length);
  console.log('First building:', this.buildings[0].name);
},
      error: (error) => {
        console.error('Error fetching buildings:', error);
      }
    });
  }
}