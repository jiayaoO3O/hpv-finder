/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.service;

import io.github.jiayaoO3O.entity.miaomiao.SeckillEventEntity;
import io.github.jiayaoO3O.entity.miaomiao.SeckillUserEntity;
import io.github.jiayaoO3O.entity.yuemiao.*;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by jiayao on 2021/10/27.
 */
@ApplicationScoped
@Slf4j
public class HpvService {

    @Inject
    @RestClient
    MiaomiaoService miaomiaoService;

    @Inject
    @RestClient
    YuemiaoService yuemiaoService;

    public Uni<List<SeckillEventEntity>> querySeckillEventsByRegionCode(Integer regionCode) {
        return miaomiaoService.getSeckillEvent(regionCode)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取秒杀活动失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<Long> queryServerTime() {
        return miaomiaoService.getServerTime()
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取服务器时间失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<List<SeckillUserEntity>> queryUsers() {
        return miaomiaoService.getUser()
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取联系人失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<List<YuemiaoCatalogEntity>> queryCatalogs() {
        return yuemiaoService.getCatalog()
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取疫苗目录失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<List<YuemiaoCatalogCustomEntity>> queryCatalogCustomsByCatalogId(Integer catalogId) {
        return yuemiaoService.getCatalogDetail(catalogId)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取疫苗目录详情失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData()
                                    .getCatalogCustoms());
                });
    }

    public Uni<List<YuemiaoDepartmentDetailEntity>> queryDepartments(String regionCode, String customerId) {
        return yuemiaoService.getDepartment(regionCode, customerId, 1)//先查询一条
                .chain(totalResponseEntity -> {
                    if(totalResponseEntity.getNotOk() && !totalResponseEntity.getOk()) {
                        log.error("获取疫苗目录详情失败->[{}][{}]", totalResponseEntity.getCode(), totalResponseEntity.getMsg());
                    }
                    return yuemiaoService.getDepartment(regionCode, customerId, totalResponseEntity.getData()
                            .getTotal());
                })
                .chain(responseEntity -> Uni.createFrom()
                        .item(responseEntity.getData()
                                .getRows()));
    }

    public Uni<YuemiaoSubscribeInfoEntity> querySubscription(Integer depaVaccId, String departmentCode, Integer vaccineCode, Integer linkmanId) {
        return yuemiaoService.getSubscribe(depaVaccId, departmentCode, vaccineCode, linkmanId)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取该服务站能否预约失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<Integer> querySubscriptionCount(Integer depaVaccId) {
        return yuemiaoService.getSubscribeCount(depaVaccId)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取已预约人数失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<Integer> querySbuscriptioneAllowed(Integer depaVaccId, Integer linkmanId) {
        return yuemiaoService.getSubscribeAllowed(depaVaccId, linkmanId)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取预约权限失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<YuemiaoDepartmentWorkDayInfoEntity> queryWorkDay(Integer depaVaccId, String departmentCode, Integer vaccineCode, Integer vaccIndex, Integer linkmanId, String month) {
        return yuemiaoService.getWorkDay(depaVaccId, departmentCode, vaccineCode, vaccIndex, linkmanId, month)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取预约接种日期失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<List<YuemiaoDepartmentVaccineAvailableAmountEntity>> queryAvailableVaccineAmount(Integer depaVaccId, String departmentCode, Integer vaccineCode, Integer vaccIndex, String days) {
        return yuemiaoService.getAvailableVaccineAmount(depaVaccId, departmentCode, vaccineCode, vaccIndex, days)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取当天可接种数目失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

    public Uni<List<YuemiaoDepartmentWorkTimeDetailEntity>> queryWorkTime(Integer depaVaccId, String departmentCode, Integer vaccCode, Integer vaccIndex, String subscribeDate, Integer linkmanId, String idCardNo) {
        return yuemiaoService.getWorkTime(depaVaccId, departmentCode, vaccCode, vaccIndex, subscribeDate, linkmanId, idCardNo)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("获取预约接种时间失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData()
                                    .getData());
                });
    }

    public Uni<String> order(Integer depaVaccId, String depaCode, Integer vaccCode, Integer vaccIndex, String subscribeDate, Integer subscribeTime, Integer linkmanId, Integer serviceFee, String ticket) {
        return yuemiaoService.order(depaVaccId, depaCode, vaccCode, vaccIndex, subscribeDate, subscribeTime, linkmanId, serviceFee, ticket)
                .chain(responseEntity -> {
                    if(responseEntity.getNotOk() && !responseEntity.getOk()) {
                        log.error("预约失败->[{}][{}]", responseEntity.getCode(), responseEntity.getMsg());
                    }
                    return Uni.createFrom()
                            .item(responseEntity.getData());
                });
    }

}
