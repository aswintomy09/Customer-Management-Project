import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
import { OrderList } from '../orderList';
import { OrderService } from '../order.service';
import { Order } from '../order';

@Component({
  selector: 'app-add-new-customer',
  templateUrl: './add-new-customer.component.html',
  styleUrls: ['./add-new-customer.component.css']
})
export class AddNewCustomerComponent implements OnInit {

  customer: Customer=new Customer();
  orders!: OrderList[];
  selectedGender: string = '';
  constructor(private customerService: CustomerService,private orderService: OrderService,private router:Router) { }

  ngOnInit(): void {
      this.orderService.getOrderList().subscribe(data =>{
        this.orders=data;
      });
    
  }
  saveCustomer(){
    this.customer.gender = this.selectedGender;
  // Save gender in localStorage using a unique key (e.g., firstname+lastname)
  localStorage.setItem(
    `gender_${this.customer.firstname}_${this.customer.lastname}`,
    this.selectedGender
  );
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
