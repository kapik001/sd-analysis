import {Component, OnInit, ViewChild} from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";
import {AceEditorComponent} from "ng2-ace-editor";
import * as ace from 'ace-builds'

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  code: string;
  waitingForCompletion: boolean;
  @ViewChild('editor', {read: AceEditorComponent, static: true}) editor: AceEditorComponent;

  constructor(private codeRunner: CodeRunnerService) {
    this.codeRunner.getEmitter().subscribe(() =>
      this.waitingForCompletion = false
    )
  }

  ngOnInit() {
    this.code = "";
    this.waitingForCompletion = false;
  }

  private runCode(code : string){
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

  cancelRequest(){
    this.codeRunner.cancelRequest();
    this.waitingForCompletion = false;
  }

  keyDownEvent($event) {
    if ($event.altKey && $event.key === 'Enter') {
      this.runSelectedCode();
    }
  }
}
