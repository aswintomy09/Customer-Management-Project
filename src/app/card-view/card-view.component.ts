import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-card-view',
  templateUrl: './card-view.component.html',
  styleUrls: ['./card-view.component.css']
})
export class CardViewComponent implements OnInit {

  customers!: Customer[];
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }
  private getCustomers(){
    this.customerService.getCustomerList().subscribe(data =>{
      this.customers=data;
    });
  }
}
