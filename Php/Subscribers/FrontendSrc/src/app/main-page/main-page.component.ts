import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {ApiService} from "../api.service";
import {Channel} from "../channel/Model/channel";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    NgForOf
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent {

  channels: Channel[];
  searchInput: string;
  subscriptions: Channel[];
  username: string;
  constructor(private service: ApiService) {
    this.channels = [];
    this.searchInput = "";
    this.username = this.getUsername();
    this.subscriptions = [];
    this.getSubscriptions();
  }

  searchChannel() {
    this.channels = [];
    this.service.searchChannelByOwner(this.searchInput).subscribe((channels: Channel[]) =>{
      this.channels = channels;
    })
  }

  getSubscriptions(){
    return this.service.getSubscriptionByUsername(this.username).subscribe((channels: Channel[]) => {
      this.subscriptions = channels;
    })
  }

  getUsername(): string{
    return localStorage.getItem("username") || '';
  }

  subscribeUserToChannel(channel: Channel) {
    this.service.subscribeUserToChannel(this.username, channel.id).subscribe(
      response => {
        this.refreshSubscriptions();
      },
      error => {
        console.error('An error occurred', error);
      }
    );
  }

  private refreshSubscriptions(){
    this.getSubscriptions();
  }
}
