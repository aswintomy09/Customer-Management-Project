import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderList } from './orderList';
import { Order } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseURL= "http://localhost:8080/api/v1/order";

  constructor(private httpClient: HttpClient) { }

  getOrderList(): Observable<OrderList[]>{
    return this.httpClient.get<OrderList[]>(`${this.baseURL}/${"dropdown"}`);
  }

  saveOrderForCustomer(orderList:Order[]): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, orderList);
  }
  }
