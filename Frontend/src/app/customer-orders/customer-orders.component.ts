import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { Route } from '@angular/router';
import { Order } from '../order';

@Component({
  selector: 'app-customer-orders',
  templateUrl: './customer-orders.component.html',
  styleUrls: ['./customer-orders.component.css']
})
export class CustomerOrdersComponent implements OnInit {

  id!:number
  customer!: Customer
  orders!: Order

  constructor(private route: ActivatedRoute, private customerService: CustomerService) { }

  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];
    this.customer=new Customer();
    this.customerService.getCustomerById(this.id).subscribe(data =>{
      this.customer = data;
    });
  }

  getTotalSum(): number {
    return this.customer.orders.reduce((sum, order) => sum + order.orderTotal, 0);
  }

  downloadPdf(customerId: number): void {
    this.customerService.getDownloadBill(customerId).subscribe((data: Blob) => {
      var downloadURL = window.URL.createObjectURL(data);
      var link = document.createElement('a');
      link.href = downloadURL;
      link.download = "invoice.pdf";
      link.click();
    });
  }

}
