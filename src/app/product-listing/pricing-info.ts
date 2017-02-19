import { Currency } from '../currency'
export class PricingInfo {
		id?:string;
		productId:string;
		basePrice?:number;
		taxPercentage?:number;
		currency:Currency;
		//BRONZE_TAG, SILVER_TAG, GOLD_TAG, PLATINUM_TAG
		tag:string;
}
