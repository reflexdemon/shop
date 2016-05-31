package org.shop.model;

/**
 * Created by vprasanna on 5/22/2016.
 */
public class Summary {
    double netTotal;
    double grossTotal;
    double shipping;
    double tax;
    double handlingCharges;
    Currency currency;

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getHandlingCharges() {
        return handlingCharges;
    }

    public void setHandlingCharges(double handlingCharges) {
        this.handlingCharges = handlingCharges;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "netTotal=" + netTotal +
                ", grossTotal=" + grossTotal +
                ", shipping=" + shipping +
                ", tax=" + tax +
                ", handlingCharges=" + handlingCharges +
                ", currency=" + currency +
                '}';
    }
}
