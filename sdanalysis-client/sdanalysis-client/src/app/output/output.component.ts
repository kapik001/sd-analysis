import {Component, OnInit} from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";
import {Message} from "./message";
import {SessionService} from "../session/session.service";
import {StrategyInputService} from "../strategy/strategy-input.service";

@Component({
  selector: 'app-output',
  templateUrl: './output.component.html',
  styleUrls: ['./output.component.css']
})
export class OutputComponent implements OnInit {

  messages: Message[];

  constructor(private codeRunner: CodeRunnerService, private sessionService: SessionService, private strategyInputService: StrategyInputService) {
    this.codeRunner.getEmitter().subscribe((event: InterpreterResult) =>
      this.addMessage(event)
    );
    this.strategyInputService.getEmitter().subscribe((event: InterpreterResult) =>
      this.addMessage(event)
    );
  }

  addMessage(result: InterpreterResult) {
    let m = new Message(result.result, result.operationResult);
    this.messages.push(m)
  }

  ngOnInit() {
    this.messages = [];
    this.sessionService.init().subscribe((response: InterpreterResult[]) => {
      response.forEach(r => {
        let m = new Message(r.result, r.operationResult);
        this.messages.push(m)
      })
    });
  }

  clear(){
    this.messages = [];
  }
}
