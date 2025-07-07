import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer'
import { CustomerService } from '../customer.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list-view',
  templateUrl: './list-view.component.html',
  styleUrls: ['./list-view.component.css']
})
export class ListViewComponent implements OnInit {

  customers!: Customer[];

  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
    
    this.getCustomers();
 }
private getCustomers(){
  this.customerService.getCustomerList().subscribe(data =>{
    this.customers= data.map(c => ({
      ...c,
      gender: localStorage.getItem(`gender_${c.firstname}_${c.lastname}`) || ''
    }));
  });
}

customerOrders(id:number){
  this.router.navigate(['CustomerOrders',id]);
}

deleteCustomer(id:number){
  this.customerService.deleteCustomer(id).subscribe(data => {
    console.log(data);
    this.getCustomers();
  })
}

addOrder(id:number){
  this.router.navigate(['AddOrder',id]);
}


}