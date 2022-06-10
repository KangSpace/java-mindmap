package org.kangspace.cluod.leaf.sample.handler;

import com.sankuai.inf.leaf.service.SegmentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kango2gler@gmail.com
 * @version 1.0
 * @date 2021/06/16 11:07
 */
@Component
public class LeafIdGenerator {
    private final String DEFAULT_KEY = "leaf_id_generator";
    @Resource
    private SegmentService segmentService;

    public long getId(){
        return segmentService.getId(DEFAULT_KEY).getId();
    }
}
