import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Customer } from '../customer';
import { Order } from '../order';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  customers!:Customer[];
  orders!:Order[];
  constructor(private customerService:CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }
  private getCustomers(){
    this.customerService.getCustomerList().subscribe(data =>{
      this.customers=data;
    });
  }
}
