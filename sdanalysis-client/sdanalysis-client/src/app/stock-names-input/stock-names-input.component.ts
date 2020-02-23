import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {CodeRunnerService} from "../code-runner/code-runner.service";
import {AceEditorComponent} from "ng2-ace-editor";
import * as ace from 'ace-builds'
import {InputRepository} from "../input-repository";
import {ActivatedRoute} from "@angular/router";
import {StrategyInputService} from "../strategy/strategy-input.service";

@Component({
  selector: 'stock-names-input',
  templateUrl: './stock-names-input.component.html',
  styleUrls: ['./stock-names-input.component.css']
})
export class StockNamesInputComponent implements OnInit {

  private stockNames: string;

  constructor(private inputRepository: InputRepository, private strategyInputService: StrategyInputService) {
  }

  ngOnInit() {
    if (this.inputRepository.stockNames != null) {
      this.stockNames = this.inputRepository.stockNames;
    }
  }

  uploadCode() {
    this.strategyInputService.uploadStockNames(this.stockNames);
  }

  saveChanges() {
    this.inputRepository.stockNames = this.stockNames;
  }
}
