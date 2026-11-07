
import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { BuildingService } from '../../services/building';
import { Building } from '../../models/building';

@Component({
  selector: 'app-landlord',
  imports: [CommonModule, RouterLink],
  templateUrl: './landlord.html',
  styleUrl: './landlord.scss'
})
export class Landlord implements OnInit {

  buildings = signal<Building[]>([]);

  constructor(private buildingService: BuildingService) {}

  ngOnInit(): void {
    this.buildingService.getAllBuildings().subscribe({
      next: (data) => {
        this.buildings.set(data);
      },
      error: (error) => {
        console.error('Error fetching buildings:', error);
      }
    });
  }
}
