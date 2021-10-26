/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.entity.yuemiao;

import lombok.Data;

import java.util.List;

/**
 * Created by jiayao on 2021/10/26.
 */
@Data
public class YuemiaoDepartmentVaccineEntity {
    private Integer id;
    private String departmentCode;
    private Integer productId;
    private String vaccineCode;
    private String departmentName;
    private String describtion;
    private List<String> instructionsUrls;
    private Integer isArriveVaccine;
    private String name;
    private String prompt;
    private Integer subscribed;
    private Integer total;
    private Integer stopSubscribe;
    private Integer sourceType;
    private List<String> urls;
    private List<YuemiaoVaccineEntity> items;
    private Integer departmentVaccineId;
    private Integer isNoticedUserAllowed;
    private Integer sexRequired;
    private String continuitySubscriptionDay;
    private Integer isNeedUserCard;
    private Integer status;
}
