import { Component } from '@angular/core';
import { TransactionService } from '../service/transaction.service';
import { Transaction } from '../model/Transaction';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html'
})
export class ScheduleComponent {
  transaction: Transaction = new Transaction(); // Instância vazia para o formulário
  transactions: Transaction[] = [];

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.getAllTransactions();
  }

  getAllTransactions(): void{
    this.transactionService.getAllTransactions()
      .subscribe(data => {
        this.transactions = data; // Armazena os dados das transações no array transactions
      });
  }
  
  scheduleTransaction(): void {
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
