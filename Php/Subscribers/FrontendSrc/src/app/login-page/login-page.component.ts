import {Component, HostListener} from '@angular/core';
import {Router, RouterOutlet} from "@angular/router";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    RouterOutlet,
    FormsModule
  ],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  username: string;

  constructor(private router: Router) {
    this.username = "";
  }

  submitForm() {
    localStorage.setItem('username', this.username);
    this.router.navigate(["MainPage"])
  }

  @HostListener('window:unload', ['$event'])
  onUnload(event: Event) {
    localStorage.removeItem('username');
  }
}
