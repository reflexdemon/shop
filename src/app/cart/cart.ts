import { Summary } from './summary';
import { LineItem } from './line-item';

export class Cart {
    lineItems?:LineItem[];
    summary?:Summary;
    username?:string;
    id?:string;
}