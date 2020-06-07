/*
 * Copyright 2019 Pnoker. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dc3.api.center.manager.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dc3.api.center.manager.hystrix.DeviceClientHystrix;
import com.dc3.common.bean.R;
import com.dc3.common.constant.Common;
import com.dc3.common.dto.DeviceDto;
import com.dc3.common.model.Device;
import com.dc3.common.valid.Insert;
import com.dc3.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
 * 设备 FeignClient
 *
 * @author pnoker
 */
@FeignClient(path = Common.Service.DC3_MANAGER_DEVICE_URL_PREFIX, name = Common.Service.DC3_MANAGER_SERVICE_NAME, fallbackFactory = DeviceClientHystrix.class)
public interface DeviceClient {

    /**
     * 新增 Device
     *
     * @param device
     * @return Device
     */
    @PostMapping("/add")
    R<Device> add(@Validated(Insert.class) @RequestBody Device device);

    /**
     * 根据 ID 删除 Device
     *
     * @param id deviceId
     * @return Boolean
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") Long id);

    /**
     * 修改 Device
     *
     * @param device
     * @return Device
     */
    @PostMapping("/update")
    R<Device> update(@Validated(Update.class) @RequestBody Device device);

    /**
     * 根据 ID 查询 Device
     *
     * @param id
     * @return Device
     */
    @GetMapping("/id/{id}")
    R<Device> selectById(@NotNull @PathVariable(value = "id") Long id);

    /**
     * 根据 CODE 查询 Device
     *
     * @param code
     * @return Device
     */
    @GetMapping("/code/{code}")
    R<Device> selectByCode(@NotNull @PathVariable(value = "code") String code);

    /**
     * 分页查询 Device
     *
     * @param deviceDto
     * @return Page<Device>
     */
    @PostMapping("/list")
    R<Page<Device>> list(@RequestBody(required = false) DeviceDto deviceDto);

}
