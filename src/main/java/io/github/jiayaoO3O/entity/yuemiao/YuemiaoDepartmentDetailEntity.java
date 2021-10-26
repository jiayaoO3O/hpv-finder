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
public class YuemiaoDepartmentDetailEntity {
    private String code;
    private String name;
    private String imgUrl;
    private String regionCode;
    private String address;
    private String tel;
    private Integer isOpen;
    private Double latitude;
    private Double longitude;
    private String workTimeDesc;
    private Integer isPay;
    private String vaccineCode;
    private String vaccineName;
    private Integer total;
    private Integer isSeckill;
    private Integer price;
    private Integer sourceType;
    private Integer isHiddenPrice;
    private List<Object> depaCodes;
    private List<Object> vaccines;
    private Integer depaVaccId;
    private Integer stopSubscribe;
    private Integer isNoticedUserAllowed;
    private Integer groupReservationChannelProductId;
}
