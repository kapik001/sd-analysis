import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InputComponent } from './input/input.component';
import { OutputComponent } from './output/output.component';
import {FormsModule} from "@angular/forms";
import {CodeRunnerService} from "./code-runner/code-runner.service";
import {HttpClientModule} from "@angular/common/http";
import {AceEditorModule} from "ng2-ace-editor";
import {SessionService} from "./session/session.service";
import {StrategyInputComponent} from "./on-start/strategy-input.component";
import {RouterModule, Routes} from "@angular/router";
import {AppHeaderComponent} from "./app-header/app-header.component";
import {InputRepository} from "./input-repository";

const appRoutes: Routes = [
  { path: 'input', component: InputComponent },
  { path: 'on-start', component: StrategyInputComponent, data: {name: 'on-start'}},
  { path: 'on-next', component: StrategyInputComponent, data: {name: 'on-next'}},
  { path: 'on-end', component: StrategyInputComponent, data: {name: 'on-end'}},
  { path: '', redirectTo: 'input', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    InputComponent,
    OutputComponent,
    StrategyInputComponent,
    AppHeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AceEditorModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [CodeRunnerService, SessionService, InputRepository],
  bootstrap: [AppComponent]
})
export class AppModule { }
