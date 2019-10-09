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

@NgModule({
  declarations: [
    AppComponent,
    InputComponent,
    OutputComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AceEditorModule,
  ],
  providers: [CodeRunnerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
