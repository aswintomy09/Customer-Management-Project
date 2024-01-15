import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-add-new-customer',
  templateUrl: './add-new-customer.component.html',
  styleUrls: ['./add-new-customer.component.css']
})
export class AddNewCustomerComponent implements OnInit {

  customer: Customer=new Customer();
  constructor(private customerService: CustomerService,private router:Router) { }

  ngOnInit(): void {
  }
  saveCustomer(){
    this.customerService.createCustomer(this.customer).subscribe(data =>{
      console.log(data);
    })
    //error =>console.log(error));
    
  }

  goToCustomerList(){
    this.router.navigate(['/customers']);
  }

onSubmit(){
  console.log(this.customer);
  this.saveCustomer();
  this.router.navigate(['/ListView']); 
}
}
