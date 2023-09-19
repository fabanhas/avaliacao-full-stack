import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = 'http://localhost:8080/transaction';

  constructor(private http: HttpClient) { }

  getAllTransactions(): Observable<any> {
    return this.http.get(`${this.baseUrl}/list`);
  }

  scheduleTransaction(transactionData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/schedule`, transactionData);
  }
}
