package com.ad.index.adunit;


import com.ad.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Mr.F on 2019/5/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject adUnitObject) {

        if (null != adUnitObject.getUnitId()) {
            this.unitId = adUnitObject.getUnitId();
        }
        if (null != adUnitObject.getUnitStatus()) {
            this.unitStatus = adUnitObject.getUnitStatus();
        }
        if (null != adUnitObject.getPositionType()) {
            this.positionType = adUnitObject.getPositionType();
        }
        if (null != planId) {
            this.planId = adUnitObject.getPlanId();
        }
        if (null != adUnitObject.getAdPlanObject()) {
            this.adPlanObject = adUnitObject.getAdPlanObject();
        }
    }

    private static boolean isKaiPing(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.KAIPING) > 0;
    }

    private static boolean isTiePian(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN) > 0;
    }

    private static boolean isTiePianMiddle(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE) > 0;
    }

    private static boolean isTiePianPause(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE) > 0;
    }

    private static boolean isTiePianPost(int positionType) {
        return (positionType & AdUnitConstants.POSITION_TYPE.TIEPIAN_POST) > 0;
    }

    public static boolean isAdSlotTypeOK(int adSlotType, int positionType) {

        switch (adSlotType) {
            case AdUnitConstants.POSITION_TYPE.KAIPING:
                return isKaiPing(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN:
                return isTiePian(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_MIDDLE:
                return isTiePianMiddle(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_PAUSE:
                return isTiePianPause(positionType);
            case AdUnitConstants.POSITION_TYPE.TIEPIAN_POST:
                return isTiePianPost(positionType);
            default:
                return false;
        }
    }
}
