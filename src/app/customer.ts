import { Order } from "./order";

export class Customer {
    id!: number;
    firstname!: String;
    lastname!: String;
    gender!: String;
    address!: String;
    city!: String;
    state!: String;
    orders!: Order[];

}
