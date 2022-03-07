package com.algaworks.deliveredapi.model.enums;

public enum DeliveryStatus {

    PENDING(0),
    FINISH(1),
    CANCEL(2);

    private Integer number;

    DeliveryStatus(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public static DeliveryStatus toEnum(Integer number){
        if (number == null){
            return null;
        }
        for (DeliveryStatus deliveryStatus : DeliveryStatus.values()){
            if (number.equals(deliveryStatus.getNumber())){
                return deliveryStatus;
            }
        }
        throw new IllegalArgumentException("Status Invalid");
    }
}
