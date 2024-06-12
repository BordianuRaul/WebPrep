import { Component } from '@angular/core';
import {Project} from "../model/project";
import {Service} from "../shared/service";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    FormsModule,
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

  projects: Project[];
  projectsUserIsMember: Project[];
  developerName: string;
  projectName: string;

  constructor(private service: Service) {
    this.projects = [];
    this.projectsUserIsMember = [];
    this.developerName = '';
    this.projectName = '';
    this.populateProjects();
    this.populateProjectsUserIsMember();
  }

  populateProjects() {
    this.service.getProjects().subscribe((data: Project[]) => {
      this.projects = data;
    });
  }

  populateProjectsUserIsMember() {
    this.service.getProjectsUserIsMember().subscribe((data: Project[]) => {
      this.projectsUserIsMember = data;
    });
  }

  addDeveloperToProject() {
    this.service.addDeveloperToProject(this.developerName, this.projectName).subscribe((data: Project) => {

    });
  }

}
