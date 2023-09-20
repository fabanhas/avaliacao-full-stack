import { Component } from '@angular/core';
import { TransactionService } from '../service/transaction.service';
import { Transaction } from '../model/Transaction';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html'
})
export class ScheduleComponent {
  transaction: Transaction = new Transaction();
  transactions: Transaction[] = [];

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.getAllTransactions();
  }

  formErrors = {
    fromAccount: '',
    toAccount: '',
    value: '',
    transferDate: '',
    operation: ''
  };

  formSubmitted = false;

  validateFormFields(): boolean {
    let isValid = true;

    if (!this.transaction.fromAccount) {
      this.formErrors.fromAccount = 'A conta de origem é obrigatória.';
      isValid = false;
    } else {
      this.formErrors.fromAccount = '';
    }

    if (!this.transaction.toAccount) {
      this.formErrors.toAccount = 'A conta de destino é obrigatória.';
      isValid = false;
    } else {
      this.formErrors.toAccount = '';
    }

    if (this.transaction.value === null || isNaN(Number(this.transaction.value))) {
      this.formErrors.value = 'O valor é obrigatório e deve ser um número.';
      isValid = false;
    } else {
      this.formErrors.value = '';
    }

    if (!this.transaction.transferDate) {
      this.formErrors.transferDate = 'A data de transferência é obrigatória.';
      isValid = false;
    } else {
      this.formErrors.transferDate = '';
    }

    if (!this.transaction.operation) {
      this.formErrors.operation = 'A operação é obrigatória.';
      isValid = false;
    } else {
      this.formErrors.operation = '';
    }

    return isValid;
  }

  getAllTransactions(): void{
    this.transactionService.getAllTransactions()
      .subscribe(data => {
        this.transactions = data;
      });
  }
  
  scheduleTransaction(): void {
    this.formSubmitted = true;

    if (this.validateFormFields()) {
      this.transactionService.scheduleTransaction(this.transaction).subscribe(
        (response) => {
          console.log('Transação agendada com sucesso:', response);
          this.getAllTransactions();
        },
        (error) => {
          console.error('Erro ao agendar transação:', error);
        }
      );
    }
  }


}
