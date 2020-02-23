import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";
import {AceEditorComponent} from "ng2-ace-editor";
import * as ace from 'ace-builds'
import {InputRepository} from "../input-repository";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'strategy-input',
  templateUrl: './strategy-input.component.html',
  styleUrls: ['./strategy-input.component.css']
})
export class StrategyInputComponent implements OnInit {

  code: string;
  waitingForCompletion: boolean;
  name: string;
  @ViewChild('editor', {read: AceEditorComponent, static: true}) editor: AceEditorComponent;

  constructor(private codeRunner: CodeRunnerService, private inputRepository: InputRepository, private activatedRoute: ActivatedRoute) {
    this.codeRunner.getEmitter().subscribe(() =>
      this.waitingForCompletion = false
    )
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe((data)=>{
      this.name = data.name;
      if (this.inputRepository.strategyInput != null && this.inputRepository.strategyInput[this.name] != null) {
        this.code = this.inputRepository.strategyInput[this.name];
      }
    });

    this.waitingForCompletion = false;
  }

  private runCode(code: string) {
    this.waitingForCompletion = true;
    this.codeRunner.runCode(code);
  }

  runFullCode() {
    this.runCode(this.code);
  }

  runSelectedCode() {
    let selectedText = this.editor.getEditor().getSelectedText();
    if (selectedText.length > 0) {
      this.runCode(selectedText);
    }
  }

  cancelRequest() {
    this.codeRunner.cancelRequest();
    this.waitingForCompletion = false;
  }

  keyDownEvent($event) {
    if ($event.altKey && $event.key === 'Enter') {
      this.runSelectedCode();
    }
  }

  saveChanges() {
    if(this.inputRepository.strategyInput == null){
      this.inputRepository.strategyInput = {}
    }
    this.inputRepository.strategyInput[this.name] = this.code;
  }
}
