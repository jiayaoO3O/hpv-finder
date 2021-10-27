/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.service;

import io.github.jiayaoO3O.response.*;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by jiayao on 2021/10/26.
 */
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@ClientHeaderParam(name = "Host", value = "${yuemiao.header.host}")
@ClientHeaderParam(name = "User-Agent", value = "${yuemiao.header.user-agent}")
@ClientHeaderParam(name = "Referer", value = "${yuemiao.header.referer}")
@ClientHeaderParam(name = "Origin", value = "${yuemiao.header.origin}")
public interface YuemiaoService {
    @GET
    @Path("/base/catalog/list.do")
    Uni<YuemiaoCatalogResponseEntity> getCatalog();

    @GET
    @Path("/base/catalog/catalogCustoms.do")
    Uni<YuemiaoCatalogDetailResponseEntity> getCatalogDetail(@QueryParam("catalogId") Integer catalogId);

    @GET
    @Path("/order/linkman/findByUserId.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<SeckillUserResponseEntity> getUser();

    @POST
    @Path("/base/department/getDepartments.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Uni<YuemiaoDepartmentResponseEntity> getDepartment(@FormParam("regionCode") String regionCode, @FormParam("customId") String catalogId);

    @GET
    @Path("/base/departmentVaccine/item.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoDepartmentVaccineResponseEntity> getVaccine(@QueryParam("id") Integer depaVaccId);

    @GET
    @Path("/subscribe/subscribe/isCanSubscribe.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoSubscribeInfoResponseEntity> getSubscribe(@QueryParam("id") Integer depaVaccId, @QueryParam("depaCode") String depaCode, @QueryParam("vaccineCode") Integer vaccineCode, @QueryParam("linkmanId") Integer linkmanId);

    @GET
    @Path("/passport/register/countSubscribe.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoSubscribeCountEntity> getSubscribeCount(@QueryParam("depaVaccId") Integer depaVaccId);

    @GET
    @Path("/order/subscribe/checkSubscribeLinkman.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoSubscribeStatusResponse> getSubscribeAllowed(@QueryParam("id") Integer depaVaccId, @QueryParam("linkmanId") Integer linkmanId);

    @GET
    @Path("/order/subscribe/workDaysByMonth.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoDepartmentWorkDayInfoResponseEntity> getWorkDay(@QueryParam("departmentVaccineId") Integer depaVaccId, @QueryParam("depaCode") String depaCode, @QueryParam("vaccCode") Integer vaccCode, @QueryParam("vaccIndex") Integer vaccIndex, @QueryParam("linkmanId") Integer linkmanId, @QueryParam("month") String month);

    @GET
    @Path("/subscribe/subscribe/findSubscribeAmountByDays.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoDepartmentVaccineAvailableAmountResponseEntity> getAvailableVaccineAmount(@QueryParam("departmentVaccineId") Integer depaVaccId, @QueryParam("depaCode") String depaCode, @QueryParam("vaccCode") Integer vaccCode, @QueryParam("vaccIndex") Integer vaccIndex, @QueryParam("days") String days);

    @GET
    @Path("/subscribe/subscribe/departmentWorkTimes2.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoDepartmentWorkTimeInfoResponseEntity> getWorkTime(@QueryParam("departmentVaccineId") Integer depaVaccId, @QueryParam("depaCode") String depaCode, @QueryParam("vaccCode") Integer vaccCode, @QueryParam("vaccIndex") Integer vaccIndex, @QueryParam("subsribeDate") String subsribeDate, @QueryParam("linkmanId") Integer linkmanId, @QueryParam("idCardNo") String idCardNo);

    @GET
    @Path("/subscribe/subscribe/add.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<YuemiaoOrderResponseEntity> order(@QueryParam("departmentVaccineId") Integer depaVaccId, @QueryParam("depaCode") String depaCode, @QueryParam("vaccineCode") Integer vaccCode, @QueryParam("vaccineIndex") Integer vaccIndex, @QueryParam("subscribeDate") String subscribeDate, @QueryParam("linkmanId") Integer linkmanId, @QueryParam("subscirbeTime") Integer subscirbeTime, @QueryParam("serviceFee") Integer serviceFee, @QueryParam("ticket") String ticket);

}
