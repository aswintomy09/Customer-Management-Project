import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CustomerOrdersComponent } from './customer-orders.component';
import { of } from 'rxjs';

describe('CustomerOrdersComponent', () => {
  let activatedRoute: ActivatedRoute;
  let component: CustomerOrdersComponent;
  let fixture: ComponentFixture<CustomerOrdersComponent>;

  beforeEach(async () => {
    activatedRoute= TestBed.inject(ActivatedRoute);

    await TestBed.configureTestingModule({
      declarations: [ CustomerOrdersComponent ],
      imports: [HttpClientModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
