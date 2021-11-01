/*
 * Copyright (c) 2021 jiayao hpv-finder is licensed under Mulan PSL v2. You can use this software according to the terms and conditions of the Mulan PSL v2. You may obtain a copy of Mulan PSL v2 at: http://license.coscl.org.cn/MulanPSL2 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE. See the Mulan PSL v2 for more details.
 */

package io.github.jiayaoO3O.task;

import io.github.jiayaoO3O.service.HpvService;
import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by jiayao on 2021/10/25.
 */
@ApplicationScoped
@Slf4j
public class HpvTask {
    @Inject
    HpvService hpvService;

    @Scheduled(every = "1m")
    public void check() {
        hpvService.querySeckillEventsByRegionCode(1201)
                .subscribe()
                .with(seckillEventEntities -> log.info(seckillEventEntities.toString()));
    }
}
