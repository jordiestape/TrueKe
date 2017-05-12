package com.trigues.entity;

import java.util.HashMap;

/**
 * Created by mbaque on 06/05/2017.
 */

public class ChatTrueke extends ChatMessage {

    String truekeId;

    // 0 -> A mà ; 1 -> Amb transport extern
    int shipmentType;

    // 0 - Pendent, 1 - Denegat, 2 - Acceptat, 3 - Esperar pagament, 4 - Transport 5 - Finalitzat
    int status;

    public ChatTrueke(int fromUserId, Long date, int shipmentType, int status) {
        super(fromUserId, date);
        this.shipmentType = shipmentType;
        this.status = status;
    }

    public ChatTrueke (HashMap<String, Object> params, String id){
        super(((Long) params.get("fromUserId")).intValue(), (Long) params.get("date"));

        this.truekeId = id;

        this.shipmentType = ((Long) params.get("shipmentType")).intValue();
        this.status = ((Long) params.get("status")).intValue();
    }

    public String getShipmentTypeString(){
        switch (shipmentType){
            case 0 :
                return "Entrega a mano";
            case 1 :
                return "Transporte externo";

        }
        return "";
    }

    public String getStatusString () {
        // 0 - Pendent, 1 - Denegat, 2 - Acceptat, 3 - Esperar pagament, 4 - Finalitzat
        switch (status){
            case 0 :
                return "Pendiente";
            case 1 :
                return "Rechazado";
            case 2 :
                return "Aceptado";
            case 3 :
                return "Esperando pago";
            case 4 :
                return "En transport";
            case 5 :
                return "Finalizado";
        }
        return "";
    }

    public String getTruekeId() {
        return truekeId;
    }

    public void setTruekeId(String truekeId) {
        this.truekeId = truekeId;
    }

    public int getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(int shipmentType) {
        this.shipmentType = shipmentType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
