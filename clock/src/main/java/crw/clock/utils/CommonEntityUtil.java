package crw.clock.utils;

import crw.clock.entity.CommonEntityPO;

import java.util.Date;

public class CommonEntityUtil {

    public static CommonEntityPO createCommonEntity(String loginUserId){
        CommonEntityPO commonEntityPO = new CommonEntityPO();
        commonEntityPO.setRevision(0);
        commonEntityPO.setCreatedBy(loginUserId);
        commonEntityPO.setCreatedTime(new Date());
        return commonEntityPO;
    }

    public static CommonEntityPO updateCommonEntity(String loginUserId) {
        CommonEntityPO commonEntityPO = new CommonEntityPO();
        commonEntityPO.setRevision(0);
        commonEntityPO.setUpdatedBy(loginUserId);
        commonEntityPO.setUpdatedTime(new Date());
        return commonEntityPO;
    }

}
