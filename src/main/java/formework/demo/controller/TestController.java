package formework.demo.controller;

import formework.annotation.MyAutowired;
import formework.annotation.MyController;
import formework.annotation.MyRequestMapping;
import formework.demo.service.ModifyService;
import formework.demo.service.QueryService;

/**
 * Name
 *
 * @author xuxb
 * Date 2019-12-21
 * VersionV1.0
 * @description
 */
@MyController
@MyRequestMapping("/web")
public class TestController {
    @MyAutowired
    QueryService queryService;

    @MyAutowired
    ModifyService modifyService;

}
