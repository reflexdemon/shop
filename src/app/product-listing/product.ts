import {PricingInfo} from './pricing-info';

export class Product {
	id?:string;
	category?:string;
	name?:string;
	description?:string;
	imageURL:string;
	pricingInfo: PricingInfo;
}
