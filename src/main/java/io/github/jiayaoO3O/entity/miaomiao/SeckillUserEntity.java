/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.entity.miaomiao;

import lombok.Data;

/**
 * Created by jiayao on 2021/10/26.
 */
@Data
public class SeckillUserEntity {
    private Integer id;
    private Integer userId;
    private String name;
    private String idCardNo;
    private String birthday;
    private Integer sex;
    private String regionCode;
    private String address;
    private Integer isDefault;
    private Integer relationType;
    private String createTime;
    private String modifyTime;
    private Integer yn;
    private Integer idCardType;
}
