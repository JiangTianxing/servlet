package org.redrock.template.data.entity;

import lombok.Getter;
import lombok.Setter;

public class Student {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private String stunum;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int gender;
    @Getter @Setter
    private String college;
    @Getter @Setter
    private String major;
    @Getter @Setter
    private String clazz;
}
