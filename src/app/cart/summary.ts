import { Currency } from '../currency';
export class Summary {
    netTotal?: number;
    grossTotal?: number;
    shipping?: number;
    tax?: number;
    handlingCharges?: number;
    currency?:Currency;
}