package ro.ubb.donation.web.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OurStockResponse {
    private long zeroPositiveUsable;
    private long zeroPositiveExpired;
    private long zeroNegativeUsable;
    private long zeroNegativeExpired;

    private long APositiveUsable;
    private long APositiveExpired;
    private long ANegativeUsable;
    private long ANegativeExpired;

    private long BPositiveUsable;
    private long BPositiveExpired;
    private long BNegativeUsable;
    private long BNegativeExpired;

    private long ABPositiveUsable;
    private long ABPositiveExpired;
    private long ABNegativeUsable;
    private long ABNegativeExpired;

    private long thrombocyteUsable;
    private long thrombocyteExpired;

    private long plasmaUsable;
    private long plasmaExpired;
}
