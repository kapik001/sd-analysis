import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class InputRepository {
  freeInput: string = "";
  strategyInput: {};
  stockNames: string = "";
}
