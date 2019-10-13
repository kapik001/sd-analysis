import {EventEmitter, Injectable, Output} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SessionService {


  constructor(private http: HttpClient) {
  }

  init() {
    return this.http.get<InterpreterResult[]>("http://localhost:8080/session-repository/init",{withCredentials: true});
  }


}
