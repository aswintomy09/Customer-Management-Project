import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-map-view',
  templateUrl: './map-view.component.html',
  styleUrls: ['./map-view.component.css']
})
export class MapViewComponent implements OnInit {
  id!:number
  customers!:Customer[];
  
  constructor(private router:Router,private customerService: CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }
  private getCustomers(){
    this.customerService.getCustomerList().subscribe(data =>{
      this.customers=data.map(c => ({
      ...c,
      gender: localStorage.getItem(`gender_${c.firstname}_${c.lastname}`) || ''
    }));
    });
  }
  customerDetails(id:number){
    this.router.navigate(['CustomerDetails',id]);
  }
}
