import { Product } from './product';
export class SearchResponse {
	products:Product[];
	offSet:number;
	limit:number;
	maxCount:number;
	page:number;
}
