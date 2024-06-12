import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {User} from "../model/user.model";
import {Service} from "../shared/service";

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  user: User;

  constructor(private service: Service)
  {
    this.user = new User(0, "", "");
  }

  submitForm() {
    this.checkLogin();

  }
  updateUser(newUser: User){
    this.user = newUser;
}

  getUserFromDB() {
    this.service.getUser(this.user.getName(), this.user.getPassword()).subscribe(response => {
      if (response.id && response.name && response.password) {

        if (response.name === this.user.name && response.password === this.user.password) {

          this.updateUser(response);
          this.setUserInService();
          this.service.moveToHomePage();
        }
      }
    }, error => {
      console.error("Error fetching user:", error);
      alert("Invalid username or password");
    });
  }
  checkLogin() {
    this.getUserFromDB();
  }

  setUserInService() {
    this.service.setUser(this.user);
  }
}
