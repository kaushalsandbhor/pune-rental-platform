import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { BuildingService } from '../../services/building';
import { Building } from '../../models/building';
import { Flat } from '../../models/flat';

@Component({
  selector: 'app-building-details',
  imports: [CommonModule],
  templateUrl: './building-details.html',
  styleUrl: './building-details.scss'
})
export class BuildingDetails implements OnInit {

  building = signal<Building | null>(null);
  flats = signal<Flat[]>([]);

  constructor(
    private route: ActivatedRoute,
    private buildingService: BuildingService
  ) {}

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.buildingService.getBuildingById(id).subscribe({
      next: (data) => {
        this.building.set(data);
      },
      error: (error) => {
        console.error(error);
      }
    });

    this.buildingService.getFlatsByBuilding(id).subscribe({
      next: (data) => {
        this.flats.set(data);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}