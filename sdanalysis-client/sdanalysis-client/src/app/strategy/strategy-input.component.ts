import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";
import {AceEditorComponent} from "ng2-ace-editor";
import * as ace from 'ace-builds'
import {InputRepository} from "../input-repository";
import {ActivatedRoute} from "@angular/router";
import {StrategyInputService} from "./strategy-input.service";

@Component({
  selector: 'strategy-input',
  templateUrl: './strategy-input.component.html',
  styleUrls: ['./strategy-input.component.css']
})
export class StrategyInputComponent implements OnInit {

  code: string;
  name: string;
  @ViewChild('editor', {read: AceEditorComponent, static: true}) editor: AceEditorComponent;

  constructor(private inputRepository: InputRepository, private activatedRoute: ActivatedRoute, private strategyInputService: StrategyInputService) {
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe((data) => {
      this.name = data.name;
      if (this.inputRepository.strategyInput != null && this.inputRepository.strategyInput[this.name] != null) {
        this.code = this.inputRepository.strategyInput[this.name];
      }
    });
  }

  uploadCode() {
    this.strategyInputService.uploadCode(this.code, this.name);
  }

  saveChanges() {
    if(this.inputRepository.strategyInput == null){
      this.inputRepository.strategyInput = {}
    }
    this.inputRepository.strategyInput[this.name] = this.code;
  }
}
