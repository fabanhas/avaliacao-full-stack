export class Transaction {
    id?: number;
    fromAccount?: string;
    toAccount?: string;
    taxes?: number;
    value?: number;
    transferDate?: Date;
    scheduleDate?: Date;
    operation?: string;
  }
  