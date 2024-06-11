import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Channel} from "./channel/Model/channel";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl : string = "http://localhost/Subscribers/Backend/Controller/PersonController.php";
  constructor(private http: HttpClient) {

  }

  searchChannelByOwner(name: string): Observable<Channel[]>{
    let action = "channels";
    return this.http.get<Channel[]>(this.baseUrl, { params: {action, name}});
  }

  getSubscriptionByUsername(name: string): Observable<Channel[]>{
    let action = "subscriptions";
    return this.http.get<Channel[]>(this.baseUrl, {params: {action, name}});
  }

  subscribeUserToChannel(name: string, channelId: number):Observable<any>{

    return this.http.put<any>(this.baseUrl, {name, channelId});
  }
}
