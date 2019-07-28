export class Message {
  message: string;
  kind: string;

  constructor(message: string, kind: string) {
    this.message = message;
    this.kind = kind;
  }
}
