import {Component, OnInit, ViewChild} from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";
import {AceEditorComponent} from "ng2-ace-editor";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit {

  code: string;
  @ViewChild('editor', {read: AceEditorComponent, static: true}) editor: AceEditorComponent;

  constructor(private codeRunner: CodeRunnerService) {
  }

  ngOnInit() {
    this.code = "";
  }

  runFullCode() {
    this.codeRunner.runCode(this.code);
  }

  runSelectedCode() {
    let codeToRun = this.editor.getEditor().selection.doc;
    console.log(codeToRun)
    this.codeRunner.runCode(this.code);
  }

//   beautifyCode() {
//     console.log(this.editor.getEditor())
//     console.log(this.editorBeautify)
// }

  keyDownEvent($event) {
    if ($event.altKey && $event.key === 'Enter') {
      this.runSelectedCode();
    }
  }
}
