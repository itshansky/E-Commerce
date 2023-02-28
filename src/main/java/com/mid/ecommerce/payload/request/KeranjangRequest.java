package com.mid.ecommerce.payload.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class KeranjangRequest implements Serializable {
    private String produkId;
    private Double kuantitas;
}
