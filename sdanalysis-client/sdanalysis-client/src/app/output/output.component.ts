import {Component, OnInit} from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";
import {Message} from "./message";

@Component({
  selector: 'app-output',
  templateUrl: './output.component.html',
  styleUrls: ['./output.component.css']
})
export class OutputComponent implements OnInit {

  messages: Message[];

  constructor(private codeRunner: CodeRunnerService) {
    this.codeRunner.getEmitter().subscribe((event: InterpreterResult) =>
      this.addMessage(event)
    )
  }

  addMessage(result: InterpreterResult) {
    let m = new Message(result.result, result.operationResult);
    this.messages.push(m)
  }

  ngOnInit() {
    this.messages = [];
  }

}
