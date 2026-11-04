
import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { BuildingService } from '../../services/building';
import { Building } from '../../models/building';

@Component({
  selector: 'app-tenant',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './tenant.html',
  styleUrl: './tenant.scss',
})
export class Tenant implements OnInit {

  // Signal containing an array of buildings
  buildings = signal<Building[]>([]);

  // Signal for loading state
  isLoading = signal<boolean>(true);

  constructor(
    private buildingService: BuildingService
  ) {}

  ngOnInit(): void {

    this.buildingService.getAllBuildings().subscribe({

      next: (data) => {
        this.buildings.set(data);
        this.isLoading.set(false);
      },

      error: (error) => {
        console.error('Error fetching buildings:', error);
        this.isLoading.set(false);
      }

    });

  }
}
