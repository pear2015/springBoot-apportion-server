package com.gs.crms.apportion.controller;

import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by zhengyali on 2017/12/25.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "分派服务 Api")
public class BaseController {
}
