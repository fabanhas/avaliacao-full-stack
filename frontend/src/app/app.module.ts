import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TransactionComponent } from './transaction/transaction.component';
import { ScheduleTransactionComponent } from './schedule-transaction/schedule-transaction.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { ListComponent } from './list/list.component';

@NgModule({
  declarations: [
    AppComponent,
    TransactionComponent,
    ScheduleTransactionComponent,
    ScheduleComponent,
    ListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
