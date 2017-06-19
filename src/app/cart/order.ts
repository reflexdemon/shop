import { Summary } from './summary';
import { LineItem } from './line-item';

export class Order {
    lineItems?:LineItem[];
    summary?:Summary;
    username?:string;
    id?:string;
    status?:string;
    created?:Date;
    updated:Date;
    notes?:string;
}