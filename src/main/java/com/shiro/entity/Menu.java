package com.shiro.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private Integer id;
    private String name;
    private String  iconCls;
    private String path;
    private boolean leaf;
    private List<Menu> children;

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
