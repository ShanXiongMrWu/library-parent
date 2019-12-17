package com.nf.library.entity;

import lombok.Data;

import java.util.List;

/**
 * 节点资源实体类
 * @author Sam
 */
@Data
public class NodeInfo {

    private Integer nodeId;
    private String nodeUrl;
    private String nodePath;
    private String nodeDescription;
    private Integer pid;

    private boolean parent;
    private String nodeTag;
    private List<NodeInfo> child;
}
