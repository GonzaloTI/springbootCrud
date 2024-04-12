package com.gonzalo.apirestsoftwareApi.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class mensajeresponse {

    private String mensaje;
    private Object object;
}
