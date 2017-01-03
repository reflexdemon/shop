export class User {
    id: string;
    firstName: string;
    lastName: string;
    organization?: string;
    email?: string;
    phone?: string;
    mobile?: string;
    pricingTag?: string;
    username: string;
    password?: string;
    authorities?: Array<any>;
    enabled?: boolean;
    accountNonExpired?: boolean;
    accountNonLocked?: boolean;
    credentialsNonExpired?: boolean;
}
