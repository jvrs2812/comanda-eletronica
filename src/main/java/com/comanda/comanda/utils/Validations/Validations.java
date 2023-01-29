package com.comanda.comanda.utils.Validations;

import java.util.UUID;

public class Validations {
    public static boolean isValidId(String id){
        try {
            UUID.fromString(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
