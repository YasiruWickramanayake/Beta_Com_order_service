package com.betacom.common.utils.requestAndResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Integer responseCode;
    private String responseMessage;
    private String orderTrackingId;
}
