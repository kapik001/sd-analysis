import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StrategyInputService {

  @Output() emitter: EventEmitter<InterpreterResult> = new EventEmitter();

  constructor(private http: HttpClient) {
  }

  uploadCode(code: string, serviceName: string) {
    this.http.post("http://localhost:8080/strategy/" + serviceName, {codeToRun: code}, {withCredentials: true}).subscribe();
  }

  uploadStockNames(stockNames: string) {
    this.http.post("http://localhost:8080/strategy/stock-names", {codeToRun: stockNames}, {withCredentials: true}).subscribe();
  }

  execute() {
    this.http.get<InterpreterResult>("http://localhost:8080/strategy/execute", {withCredentials: true}).subscribe(res => {
      this.emitter.emit(res);
    });
  }

  getEmitter(): EventEmitter<InterpreterResult> {
    return this.emitter;
  }
}
