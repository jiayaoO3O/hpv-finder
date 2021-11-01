/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.service;

import io.github.jiayaoO3O.entity.miaomiao.SeckillEventEntity;
import io.github.jiayaoO3O.entity.miaomiao.SeckillUserEntity;
import io.github.jiayaoO3O.entity.yuemiao.YuemiaoCatalogCustomEntity;
import io.github.jiayaoO3O.entity.yuemiao.YuemiaoCatalogEntity;
import io.github.jiayaoO3O.entity.yuemiao.YuemiaoDepartmentDetailEntity;
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
        return yuemiaoService.getDepartment(regionCode, customerId, 1)//先查询一i套
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

}
