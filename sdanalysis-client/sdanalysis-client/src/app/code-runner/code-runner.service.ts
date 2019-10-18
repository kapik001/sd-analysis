import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subscription} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CodeRunnerService {

  @Output() emitter: EventEmitter<InterpreterResult> = new EventEmitter();
  currentRequest: Subscription;

  constructor(private http: HttpClient) {
  }

  runCode(code: string) {
    this.currentRequest = this.http.post<InterpreterResult>("http://localhost:8080/interpreter-runner/run-code", {codeToRun: code}, {withCredentials: true})
      .subscribe((res) => {
          this.emitter.emit(res);
          this.currentRequest = null;
        }
      );
  }

  cancelRequest() {
    if (this.currentRequest) {
      this.currentRequest.unsubscribe();
    }
  }

  getEmitter(): EventEmitter<InterpreterResult> {
    return this.emitter;
  }
}
