import { Component, OnInit } from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  code: string;

  constructor(private codeRunner: CodeRunnerService) { }

  ngOnInit() {
    this.code = "";
  }

  runCode(){
    this.codeRunner.runCode(this.code);
  }
}
