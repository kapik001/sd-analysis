import {Component, OnInit} from "@angular/core";
import {StrategyInputService} from "../strategy/strategy-input.service";

@Component({
  selector: 'app-header',
  templateUrl: './app-header.component.html',
  styleUrls: ['./app-header.component.css']
})
export class AppHeaderComponent {


  constructor(private strategyInputService: StrategyInputService) {
  }

  executeStrategy(){
    this.strategyInputService.execute();
  }
}
