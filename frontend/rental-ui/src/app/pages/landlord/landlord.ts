
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
  isLoading = signal(true);
  errorMessage = signal('');

  constructor(private buildingService: BuildingService) {}

  ngOnInit(): void {
    this.buildingService.getMyBuildings().subscribe({
  next: (data) => {
    this.buildings.set(data);
    this.isLoading.set(false);
  },
  error: (error) => {
    console.error('Error fetching buildings:', error);
    this.errorMessage.set('Unable to load your properties.');
    this.isLoading.set(false);
  }
});
  }
}
