/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.service;

import io.github.jiayaoO3O.response.*;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by jiayao on 2021/10/26.
 */
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@ClientHeaderParam(name = "Host", value = "${miaomiao.header.host}")
@ClientHeaderParam(name = "User-Agent", value = "${miaomiao.header.user-agent}")
@ClientHeaderParam(name = "Referer", value = "${miaomiao.header.referer}")
public interface MiaomiaoService {
    @GET
    @Path("/base/region/childRegions.do")
    Uni<RegionResponseEntity> getRegion(@QueryParam("parentCode") Integer regionCode);

    @GET
    @Path("/seckill/seckill/list.do")
    Uni<SeckillEventResponseEntity> getSeckillEvent(@QueryParam("regionCode") Integer regionCode);

    @GET
    @Path("/seckill/seckill/now2.do")
    Uni<SeckillServerTimeResponseEntity> getServerTime();

    @GET
    @Path("/seckill/seckill/checkstock2.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<SeckillEventStockResponseEntity> getVaccineStock(@QueryParam("id") Integer eventId);

    @GET
    @Path("seckill/linkman/findByUserId.do")
    @ClientHeaderParam(name = "Cookie", value = "${server.info.cookie}")
    @ClientHeaderParam(name = "tk", value = "${server.info.tk}")
    Uni<SeckillUserResponseEntity> getUser();
}
