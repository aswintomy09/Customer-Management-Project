import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../customer.service';
import { OrderService } from '../order.service';
import { Customer } from '../customer';
import { Order } from '../order';
import { OrderList } from '../orderList';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {

orders!: OrderList[];
orderList!:Order[];
id!:number;
customer!: Customer;

  constructor(private customerService: CustomerService,private orderService: OrderService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id= this.route.snapshot.params['id'];
    this.customer=new Customer();
    this.customerService.getCustomerById(this.id).subscribe(data =>{
    this.customer = data;
    });
    this.orderService.getOrderList().subscribe(data =>{
      this.orders=data;
  });
}

  set selectedOrders(val: Order[]) {
    // initialize quantity to 1 for every selected order
    val.forEach(order => {
      if (!order.quantity) {
        order.quantity = 1;
      }
      order.customerId = this.customer.id;
    });
    this.customer.orders = val;
  }
  
  get selectedOrders() {
    return this.customer.orders;
  }

  saveOrder(){
    this.orderService.saveOrderForCustomer(this.selectedOrders).subscribe(data =>{
      console.log(data);
      this.router.navigate(['/ListView']); 
    })
  }

onSubmitOrder(){
  console.log(this.customer);
  this.saveOrder();
  
}

}
