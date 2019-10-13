import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CodeRunnerService {

  @Output() emitter: EventEmitter<InterpreterResult> = new EventEmitter();

  constructor(private http: HttpClient) {
  }

  runCode(code: string) {
    this.http.post<InterpreterResult>("http://localhost:8080/interpreter-runner/run-code", {codeToRun: code},{withCredentials: true}).subscribe((res) =>
      this.emitter.emit(res)
    );
  }

  getEmitter(): EventEmitter<InterpreterResult> {
    return this.emitter;
  }
}
