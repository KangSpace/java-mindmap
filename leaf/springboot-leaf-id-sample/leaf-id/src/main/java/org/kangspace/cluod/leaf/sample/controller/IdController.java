package org.kangspace.cluod.leaf.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.kangspace.cluod.leaf.sample.entity.IdEntity;
import org.kangspace.cluod.leaf.sample.handler.LeafIdGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Id Controller
 * @author kango2gler@gmail.com
 */
@Slf4j
@RestController
public class IdController {

    @Resource
    private LeafIdGenerator leafIdGenerator;

    /**
     * ID 生成
     */
    @GetMapping("/id")
    @ResponseBody
    public IdEntity id() {
        long id = leafIdGenerator.getId();
        log.info("id:{}", id);
        return new IdEntity();
    }
}
