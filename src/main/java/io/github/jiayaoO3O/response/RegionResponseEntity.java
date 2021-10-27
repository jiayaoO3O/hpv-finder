/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.response;

import io.github.jiayaoO3O.entity.miaomiao.RegionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by jiayao on 2021/10/26.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class RegionResponseEntity extends BaseResponseEntity {
    private List<RegionEntity> data;
}
