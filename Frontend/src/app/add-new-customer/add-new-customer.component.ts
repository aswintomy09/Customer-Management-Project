import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { OrderList } from '../orderList';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-add-new-customer',
  templateUrl: './add-new-customer.component.html',
  styleUrls: ['./add-new-customer.component.css']
})
export class AddNewCustomerComponent implements OnInit {

  customer: Customer=new Customer();
  orders!: OrderList[];
  constructor(private customerService: CustomerService,private orderService: OrderService,private router:Router) { }

  ngOnInit(): void {
      this.orderService.getOrderList().subscribe(data =>{
        this.orders=data;
      });
    
  }
  saveCustomer(){
    this.customerService.createCustomer(this.customer).subscribe(data =>{
      console.log(data);
      this.router.navigate(['/ListView']); 
    })
    //error =>console.log(error));
    
  }
  

  goToCustomerList(){
    this.router.navigate(['/customers']);
  }

onSubmit(){
  console.log(this.customer);
  this.saveCustomer();
  
}
}
