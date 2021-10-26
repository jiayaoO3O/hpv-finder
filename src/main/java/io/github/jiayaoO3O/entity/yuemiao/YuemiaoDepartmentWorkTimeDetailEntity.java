/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.entity.yuemiao;

import lombok.Data;

/**
 * Created by jiayao on 2021/10/26.
 */
@Data
public class YuemiaoDepartmentWorkTimeDetailEntity {
    private int id;
    private String depaCode;
    private String vaccCode;
    private String departmentVaccineId;
    private String vaccIndex;
    private int workdayId;
    private String startTime;
    private String endTime;
    private int maxSub;
    private int tIndex;
    private String createTime;
    private String modifyTime;
    private int yn;
    private String particularDate;
}
