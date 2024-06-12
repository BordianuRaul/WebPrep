import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user.model";
import {Router} from "@angular/router";
import {Project} from "../model/project";

@Injectable({
  providedIn: 'root'
})
export class Service {

  private baseURL = "http://localhost:8080/api/ITProjects";
  user: User;

  constructor(private http: HttpClient, private router: Router) {
    this.user = new User(0, "", "");
  }

  getUser(username: string, password: string): Observable<User> {
    let genericId = 0;
    return this.http.post<User>(this.baseURL + "/login", {id: genericId, name: username, password: password});
  }

  moveToHomePage(){
    this.router.navigate(['Home']);
  }

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.baseURL + "/projects");

  }
  setUser(user: User) {
    this.user = user;
  }

  getProjectsUserIsMember(): Observable<Project[]> {
    return this.http.get<Project[]>(this.baseURL + "/allProjectsAUserIsPartOf", {params:{userId: this.user.id}});
  }

  addDeveloperToProject(developerName: string, projectName: string): Observable<Project> {
    return this.http.post<any>(this.baseURL + "/addDeveloperToProject", {developerName: developerName, projectName: projectName});
  }

}
